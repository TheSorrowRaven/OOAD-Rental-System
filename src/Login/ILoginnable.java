package src.Login;

import java.util.ArrayList;

import src.Users.User;

public interface ILoginnable {
    
    ArrayList<ILoginnable> getLoginnables();

    default void notifyLoginError(){
        notifyLoginErrorChildren();
    }
    default void notifyLoginErrorChildren(){
        for (ILoginnable loginnable : getLoginnables()){
            loginnable.notifyLoginError();
        }
    }

    default void notifyLoginSuccessful(User<?> user){
        notifyLoginSuccessfulChildren(user);
        
    }
    default void notifyLoginSuccessfulChildren(User<?> user){
        for (ILoginnable loginnable : getLoginnables()){
            loginnable.notifyLoginSuccessful(user);
        }
    }

}
