package src;


public interface ICommandReturnnable<T, E>{

    E execute(T data);
    
}
