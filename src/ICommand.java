package src;

/**
 * 
 * Command DP
 * 
 */
public interface ICommand<T> {
    
    boolean execute(T data);
    boolean execute(T data, Object add);

}
