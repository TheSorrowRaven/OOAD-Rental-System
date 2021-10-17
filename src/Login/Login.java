package src.Login;

import src.*;
import src.SystemComponents.CLI;
import src.Users.*;

/**
 * 
 * This Login class is the internal handling of the Login system
 * 
 */
public class Login {

    //Line numbers are for deletion
    private int lastReadUserLineNumber = 0;
    public int getLastReadUserLineNumber(){
        return lastReadUserLineNumber;
    }

    /**
     * Try to login with username and password. Uses User class as generic function
     * @param <T>
     * @param userClass
     * @param username
     * @param password
     * @return Null if the credentials do not match
     */
    public <T extends User<T>> T tryLogin(Class<T> userClass, String username, String password){
        T user = getUserFromCredentials(userClass, username, password);
        return user;
    }


    public void ensureDefaultAdminExists(String defaultUserame, String defaultPassword, String defaultSerializedAdmin){
        AdminUser admin = tryLogin(AdminUser.class, defaultUserame, defaultPassword);
        if (admin == null){
            Main.instance().serializer.write(AdminUser.getDefaultAdminUser(defaultSerializedAdmin));
        }
    }
    

    /**
     * Find user based on the credentials
     * @param <T> User subtype - Tenant, Owner/Agent, Admin 
     * @param userClass T's class
     * @param username
     * @param password
     * @return User if credentials are valid, else null
     */
    private <T extends User<T>> T getUserFromCredentials(Class<T> userClass, final String username, final String password){
        T dummy = User.getNewUserFromClass(userClass);

        T foundUser = null;
        //var is used here to accomodate the new lambda type extended from Command
        var checkCommand = new Command<User<T>>(){
            private T user = null;
            private int lineNumber = 0;
            public T getUser(){
                return user;
            }
            public int getLineNumber(){
                return lineNumber;
            }
            @Override
            public boolean execute(User<T> user){
                if (user.username.equals(username) && user.password.equals(password)){
                    this.user = userClass.cast(user);
                    return true;
                }
                return false;
            }
            @Override
            public boolean execute(User<T> user, Object lineNumber){
                if (user.username.equals(username) && user.password.equals(password)){
                    this.user = userClass.cast(user);
                    this.lineNumber = (Integer)lineNumber;
                    return true;
                }
                return false;
            }
        };
        Main.instance().serializer.readForEach(dummy, checkCommand);
        //tenant would be inaccessible if the checkCommand was "ICommand" or "Command<User>"
        foundUser = checkCommand.getUser();
        lastReadUserLineNumber = checkCommand.getLineNumber();
        
        return foundUser;
    }
    
}
