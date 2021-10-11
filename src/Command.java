package src;

/**
 * 
 * Command DP
 * 
 */
public class Command<T> implements ICommand<T>{

    @Override
    public boolean execute(T data){
        return false;
    }
    @Override
    public boolean execute(T data, Object add){
        return false;
    }

}

