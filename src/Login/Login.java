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


    /**
     * Try to login with tenant with credentials and callbacks
     */
    public void tryLoginWithTenant(String username, String password, Command<Tenant> successLoginAction, Command<Object> failedLoginAction){
        Tenant tenant = getUserFromCredentials(Tenant.class, username, password);

        if (tenant == null){
            //Credentials invalid
            failedLoginAction.execute(null);
            return;
        }
        successLoginAction.execute(tenant);

    }

    /**
     * Try to login with owner/agent with credentials and callbacks
     */
    public void tryLoginWithOwnerAgent(String username, String password, Command<OwnerAgent> successLoginAction, Command<Object> failedLoginAction){
        OwnerAgent ownerAgent = getUserFromCredentials(OwnerAgent.class, username, password);

        if (ownerAgent == null){
            failedLoginAction.execute(null);
            return;
        }
        successLoginAction.execute(ownerAgent);
    }

    /**
     * Try to login with admin with credentials and callbacks
     */
    public void tryLoginWithAdmin(String username, String password, Command<Admin> successLoginAction, Command<Object> failedLoginAction){
        Admin admin = getUserFromCredentials(Admin.class, username, password);

        if (admin == null){
            failedLoginAction.execute(null);
            return;
        }
        successLoginAction.execute(admin);
    }
    

    /**
     * Find user based on the credentials
     * @param <T> User subtype - Tenant, Owner/Agent, Admin 
     * @param userClass T's class
     * @param username
     * @param password
     * @return User if credentials are valid, else null
     */
    private <T extends User<T>> T getUserFromCredentials(Class<T> userClass, String username, String password){
        final String username_ = Input.standardize(username);
        final String password_ = Input.standardize(password);

        T dummy;
        try {
            dummy = userClass.getDeclaredConstructor().newInstance();
        } catch (Exception e){
            CLI.log("Class type is not the same as the requested type");
            e.printStackTrace();
            return null;
        }

        T foundUser = null;
        //var is used here to accomodate the new lambda type extended from Command
        var checkCommand = new Command<User<T>>(){
            private T user = null;
            public T getUser(){
                return user;
            }
            @Override
            public boolean execute(User<T> user){
                if (user.username.equals(username_) && user.password.equals(password_)){
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
