package src;

/**
 * 
 * Command DP
 * 
 */
public interface ICommand<T> {
    
    /**
     * Executes with a generic parameter and returns a boolean
     * @param data
     * @return
     */
    boolean execute(T data);
    /**
     * Executes with a generic parameter and an extra parameter and returns a boolean
     * @param data
     * @param add
     * @return
     */
    boolean execute(T data, Object add);

}
