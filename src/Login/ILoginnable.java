package src.Login;

import java.util.ArrayList;

import src.Users.User;

/**
 * Raven
 * 
 * This class is to create default behaviours to handle same functions across different inherited classes
 */
public interface ILoginnable {
    
    ArrayList<ILoginnable> getLoginnables();

    /**
     * Notify the managing children when there's an error
     */
    default void notifyLoginError(){
        notifyLoginErrorChildren();
    }
    /**
     * Notify all of the children of the error
     */
    default void notifyLoginErrorChildren(){
        for (ILoginnable loginnable : getLoginnables()){
            loginnable.notifyLoginError();
        }
    }

    /**
     * Notify the managing children when the login is successful
     */
    default void notifyLoginSuccessful(User<?> user){
        notifyLoginSuccessfulChildren(user);
        
    }
    /**
     * Notify all of the children of the successful login
     */
    default void notifyLoginSuccessfulChildren(User<?> user){
        for (ILoginnable loginnable : getLoginnables()){
            loginnable.notifyLoginSuccessful(user);
        }
    }

}
