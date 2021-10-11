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

public class Serializer {

    public static Resource Resource(){
        return Resource.instance();
    }

    private String savePath;

    public Serializer(String savePath){
        this.savePath = savePath;
    }

    private void checkCreateDirectory(File directory){
        if (!directory.exists()){
            directory.mkdirs();
        }
    }
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
        else{
            CLI.log("File Exists - " + file.getPath());
        }
    }
    private void checkCreateFile(String filePath){
        File file = new File(filePath);
        checkCreateFile(file);
    }

    //Appends the savePath into the file path from serializable
    private String getFilePathFromSerializable(ISerializable<?> serializable){
        return savePath + serializable.getFilePath();
    }


    private String newLineSerialize(String text){
        String t = text.replace('\n', Resource().newLineReplacementCharacter);
        return t;
    }
    private String newLineDeserialize(String text){
        String t = text.replace(Resource().newLineReplacementCharacter, '\n');
        return t;
    }


    public <T extends ISerializable<T>> void write(ISerializable<T> serializable){
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

    public <T extends ISerializable<T>> void writeAll(ArrayList<ISerializable<T>> serializables){
        for (ISerializable<T> serializable : serializables){
            write(serializable);
        }
    }
    




    public <T extends ISerializable<T>> void remove(ISerializable<T> serializable, int lineNumber){
        String filePath = getFilePathFromSerializable(serializable);
        File file = new File(filePath);
        if (!file.exists()){
            return;
        }
        ArrayList<String> fileLines = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineCount = 1;
            while ((line = br.readLine()) != null) {
                if (lineCount == lineNumber){   //Skip the line to delete
                    lineCount++;
                    continue;
                }
                fileLines.add(line);
                lineCount++;
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
    public <T extends ISerializable<T>> ArrayList<T> readAll(ISerializable<T> serializable){
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
    public <T extends ISerializable<T>> void readForEach(ISerializable<T> serializable, ICommand<T> execution){
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


