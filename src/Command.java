package src;

/**
 * 
 * Command DP
 * Acts as a passable function as parameter
 * 
 */
public class Command<T> implements ICommand<T>{

    /**
     * Executes whatever is overriden with a generic parameter and returns a boolean
     */
    @Override
    public boolean execute(T data){
        return false;
    }
    /**
     * Executes with an extra Object parameter
     */
    @Override
    public boolean execute(T data, Object add){
        return false;
    }

}

