package src.SystemComponents;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import src.*;

import java.io.BufferedReader;

public class Serializer {

    private String savePath;

    public Serializer(String savePath){
        this.savePath = savePath;
    }

    private void checkCreateDirectory(File directory){
        if (!directory.exists()){
            directory.mkdirs();
        }
    }
    private void checkCreateFile(String filePath){
        File file = new File(filePath);
        checkCreateDirectory(file);
        if (!file.exists()){
            try{
                file.createNewFile();
            }
            catch (IOException ex){
                CLI.log("Error creating file");
                ex.printStackTrace();
            }
        }
    }

    /**
     * 
     * Loads all objects present in the file
     * 
     * @param <T> Implemented ISerializable class used to save and load that contains the data
     * @param serializable The object for saving and loading
     * @return A list of all objects. Null if file does not exist
     */
    public <T extends ISerializable<T>> ArrayList<T> readAll(ISerializable<T> serializable){
        File file = new File(serializable.getFilePath());
        if (!file.exists()){
            return null;
        }
        ArrayList<T> objects = new ArrayList<T>();
        ICommand<T> command = new Command<T>(){
            @Override
            public boolean execute(T data){
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
     * @param serializable The object for saving and loading
     * @param execution Action for each loaded <T> object
     */
    public <T extends ISerializable<T>> void readForEach(ISerializable<T> serializable, ICommand<T> execution){
        File file = new File(serializable.getFilePath());
        if (!file.exists()){
            return;
        }
        boolean isFullLoad = true;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                T value = serializable.loadSaveableText(line);
                boolean stop = execution.execute(value);
                if (stop){
                    isFullLoad = false;
                    break;
                }
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


