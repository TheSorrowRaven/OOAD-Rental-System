package src.SystemComponents;

/**
 * Interface to serialize the class itself
 */
public interface ISerializable<T extends ISerializable<T>> {
    
    /**
     * Gets the file path of the serializing object
     * @return
     */
    String getFilePath();

    /**
     * Gets the saveable text of the object (serialize)
     * @return
     */
    String getSaveableText();
    /**
     * Loads the saveable text of the object (deserialize)
     */
    T loadSaveableText(String text);

    /**
     * This will be called if all objects are successfully loaded for 2nd pass loading 
     * NOTE: Will be executed on the dummy object
     */
    void onFinishLoading();





}
