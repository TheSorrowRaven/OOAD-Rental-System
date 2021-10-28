package src.Login;

import src.*;
import src.Users.*;

/**
 * Raven
 * 
 * 
 * This class is the model
 * This Login class is the internal handling of the Login system
 * 
 */
public class Login {

    //Line numbers are for deletion

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


    /**
     * Makes sure defult admin exists
     * @param defaultUserame
     * @param defaultPassword
     * @param defaultSerializedAdmin
     */
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
        var checkCommand = new Command<T>(){
            private T user = null;
            public T getUser(){
                return user;
            }
            @Override
            public boolean execute(T user){
                if (user.username.equals(username) && user.password.equals(password)){
                    this.user = userClass.cast(user);
                    return true;
                }
                return false;
            }
            @Override
            public boolean execute(T user, Object lineNumber){
                if (user.username.equals(username) && user.password.equals(password)){
                    this.user = userClass.cast(user);
                    return true;
                }
                return false;
            }
        };
        Main.instance().serializer.readForEach(dummy, checkCommand);
        //tenant would be inaccessible if the checkCommand was "ICommand" or "Command<User>"
        foundUser = checkCommand.getUser();
        
        return foundUser;
    }
    
}
