package src.SystemComponents;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import src.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 * Serializer for serializing objects into raw string and vice versa for deserializing
 */
public class Serializer {

    /**
     * Shorthand for resource singleton
     * @return
     */
    public static Resource Resource(){
        return Resource.instance();
    }

    private String savePath;

    /**
     * Constructor accepting the save directory
     */
    public Serializer(String savePath){
        this.savePath = savePath;
    }

    /**
     * Checks and creates the specified directory
     * @param directory
     */
    private void checkCreateDirectory(File directory){
        if (!directory.exists()){
            directory.mkdirs();
        }
    }
    /**
     * Checks and creates the specified file
     * @param file
     */
    private void checkCreateFile(File file){
        checkCreateDirectory(file.getParentFile());
        if (!file.exists()){
            try{
                file.createNewFile();
                CLI.log("Created new file");
            }
            catch (IOException ex){
                CLI.log("Error creating file");
                ex.printStackTrace();
            }
        }
        // else{
        //     CLI.log("File Exists - " + file.getPath());
        // }
    }
    private void checkCreateFile(String filePath){
        File file = new File(filePath);
        checkCreateFile(file);
    }

    /**
     * Appends the savePath into the file path from serializable
     */
    private String getFilePathFromSerializable(ISerializable<?> serializable){
        return savePath + serializable.getFilePath();
    }


    /**
     * Last Layer serializing to prevent new lines from corrupting the file
     * @param text
     * @return
     */
    private String newLineSerialize(String text){
        String t = text.replace('\n', Resource().newLineReplacementCharacter);
        return t;
    }
    /**
     * First Layer deserializing to add back the new line of the file
     * @param text
     * @return
     */
    private String newLineDeserialize(String text){
        String t = text.replace(Resource().newLineReplacementCharacter, '\n');
        return t;
    }


    /**
     * Writes the object into the file
     * @param <T>
     * @param serializable
     */
    public <T extends ISerializable<T>> void write(T serializable){
        String filePath = getFilePathFromSerializable(serializable);
        checkCreateFile(filePath);
        File file = new File(filePath);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
            String line = serializable.getSaveableText();
            line = newLineSerialize(line) + "\n";
            bw.write(line);
        }
        catch (FileNotFoundException notFoundEx){
            CLI.log("File Not Found while write");
            notFoundEx.printStackTrace();
        }
        catch (IOException ioEx) {
            CLI.log("IO Exception occured while write");
            ioEx.printStackTrace();
        }

    }

    /**
     * Writes all data in an array list to the file
     * @param <T>
     * @param serializables
     */
    public <T extends ISerializable<T>> void writeAll(ArrayList<T> serializables){
        for (T serializable : serializables){
            write(serializable);
        }
    }
    



    /**
     * Removes for each casted entry row processed in execution
     * @param <T>
     * @param serializable
     * @param execution Override the one with the line number
     */
    public <T extends ISerializable<T>> void removeForEach(T serializable, ICommand<T> execution){
        final ArrayList<Integer> linesToDelete = new ArrayList<Integer>();
        var readForEachCommand = new Command<T>(){
            @Override
            public boolean execute(T data){
                return false;
            }
            @Override
            public boolean execute(T data, Object lineNumber){
                boolean deleteThis = execution.execute(data, lineNumber);
                if (deleteThis){
                    linesToDelete.add((Integer)lineNumber);
                }
                return false;   //Always return false to assert dominance
            }
        };
        readForEach(serializable, readForEachCommand);
        remove(serializable, linesToDelete);
    }

    /**
     * Removes multiple line numbers from the dummy serializable
     * @param <T>
     * @param serializable
     * @param lineNumbers
     */
    public <T extends ISerializable<T>> void remove(T serializable, ArrayList<Integer> lineNumbers){
        String filePath = getFilePathFromSerializable(serializable);
        File file = new File(filePath);
        if (!file.exists()){
            return;
        }
        ArrayList<String> fileLines = new ArrayList<String>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            removeProcessLine(br, fileLines, lineNumbers);            
        }
        catch (FileNotFoundException notFoundEx){
            CLI.log("File Not Found while remove");
            notFoundEx.printStackTrace();
        }
        catch (IOException ioEx) {
            CLI.log("IO Exception occured while remove");
            ioEx.printStackTrace();
        }
        
        removeWrite(file, fileLines);
    }
    /**
     * Removes a line number from the dummy serializable
     * @param <T>
     * @param serializable
     * @param lineNumber
     */
    public <T extends ISerializable<T>> void remove(T serializable, int lineNumber){
        ArrayList<Integer> lineNumbers = new ArrayList<>(1);
        lineNumbers.add(lineNumber);
        remove(serializable, lineNumbers);
    }
    /**
     * Process and remove a line
     * @param br
     * @param fileLines
     * @param lineNumbers
     */
    private void removeProcessLine(BufferedReader br, ArrayList<String> fileLines, ArrayList<Integer> lineNumbers){
        String line;
        int lineCount = 1;
        try {
            while ((line = br.readLine()) != null) {
                if (lineNumbers.contains(lineCount)){   //Skip the line to delete
                    lineCount++;
                    continue;
                }
                fileLines.add(line);
                lineCount++;
            }
        } catch (IOException e) {
            CLI.log("IO Exception occured while remove");
            e.printStackTrace();
        }
    }
    /**
     * Process and write back the entire file
     * @param file
     * @param fileLines
     */
    private void removeWrite(File file, ArrayList<String> fileLines){
        //No append mode, rewrite the entire file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            for (String str : fileLines){
                bw.write(str + "\n");
            }
        }
        catch (FileNotFoundException notFoundEx){
            CLI.log("File Not Found while remove");
            notFoundEx.printStackTrace();
        }
        catch (IOException ioEx) {
            CLI.log("IO Exception occured while remove");
            ioEx.printStackTrace();
        }
    }








    /**
     * 
     * Loads all objects present in the file
     * 
     * @param <T> Implemented ISerializable class used to save and load that contains the data
     * @param serializable The object for saving and loading (dummy)
     * @return A list of all objects. Null if file does not exist
     */
    public <T extends ISerializable<T>> ArrayList<T> readAll(T serializable){
        String filePath = getFilePathFromSerializable(serializable);
        File file = new File(filePath);
        if (!file.exists()){
            return null;
        }
        ArrayList<T> objects = new ArrayList<T>();
        ICommand<T> command = new Command<T>(){
            @Override
            public boolean execute(T data, Object lineNumber){
                objects.add(data);
                return false;
            }
        };
        readForEach(serializable, command);
        return objects;
    }

    /**
     * 
     * Process serializable line by line, and execute each one by one without creating garbage
     * 
     * @param <T> Implemented ISerializable class used to save and load that contains the data
     * @param serializable The object for saving and loading (dummy)
     * @param execution Action for each loaded <T> object. Additional parameter in execution is line number to refer to when deleting
     */
    // public <T extends ISerializable<T>> void readForEach(ISerializable<T> serializable, ICommand<T> execution){
    //     readForEachT(serializable, execution);
    // }

    public <T extends ISerializable<T>> void readForEach(T serializable, ICommand<T> execution) {
        String filePath = getFilePathFromSerializable(serializable);
        File file = new File(filePath);
        if (!file.exists()){
            return;
        }
        boolean isFullLoad = true;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineCount = 1;
            while ((line = br.readLine()) != null) {
                //Skip empty line
                if (line == ""){
                    lineCount++;
                    continue;
                }
                line = newLineDeserialize(line);
                T value = serializable.loadSaveableText(line);
                boolean stop = execution.execute(value, lineCount);
                if (stop){
                    isFullLoad = false;
                    break;
                }
                lineCount++;
            }
        }
        catch (FileNotFoundException notFoundEx){
            CLI.log("File Not Found while readAll");
            notFoundEx.printStackTrace();
        }
        catch (IOException ioEx) {
            CLI.log("IO Exception occured while readAll");
            ioEx.printStackTrace();
        }
        if (isFullLoad){
            serializable.onFinishLoading();
        }
    }
    
}


