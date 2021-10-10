package src;

public class CommandReturnnable<T, E> implements ICommandReturnnable<T, E> {

    @Override
    public E execute(T data){
        return null;
    }
    
}
