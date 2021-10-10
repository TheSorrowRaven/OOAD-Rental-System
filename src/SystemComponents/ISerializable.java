package src.SystemComponents;

/**
 * Interface to serialize the class itself
 */
public interface ISerializable<T extends ISerializable<T>> {
    
    String getFilePath();

    String getSaveableText();
    T loadSaveableText(String text);

    /**
     * This will be called if all objects are successfully loaded for 2nd pass loading 
     * NOTE: Will be executed on the dummy object
     */
    void onFinishLoading();





}
