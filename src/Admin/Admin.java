package src.Admin;

import java.util.*;

import src.*;
import src.Property.PropertyListing;
import src.Users.*;
import src.SystemComponents.*;

/**
 * This Admin class is capable of creating all 3 types of accounts, and delete them too
 */
public class Admin {
    

    public AdminUser loggedInAdmin;

    public static class UserCreationResult{
        private boolean successful;
        private User<?> user;

        public void setSuccessful(boolean successful){
            this.successful = successful;
        }
        public boolean isSuccessful(){
            return successful;
        }
        public void setUser(User<?> user){
            this.user = user;
        }

        public User<?> createdUser(){
            if (successful){
                return user;
            }
            return null;
        }
        public User<?> existingUser(){
            if (successful){
                return null;
            }
            return user;
        }
    }

    /**
     * Only function to create an account. Will check if username already used
     * @param <T>
     * @param userClass
     * @param username
     * @param password
     * @return
     */
    public <T extends User<T>> UserCreationResult createAccount(Class<T> userClass, String username, String password, String name, boolean isOwnerAgent){

        UserCreationResult result = new UserCreationResult();
        User<?> existingUser = userExists(username);
        if (existingUser != null){
            result.setUser(existingUser);
            result.setSuccessful(false);
            return result;
        }

        User<T> user = checkedCreateAccount(userClass, username, password, name, isOwnerAgent);
        result.setUser(user);
        result.setSuccessful(true);
        return result;

    }
    private <T extends User<T>> User<T> checkedCreateAccount(Class<T> userClass, String username, String password, String name, boolean isOwnerAgent){
        T user = User.getNewUserFromClass(userClass);
        user.newUserSet(username, password, name);

        if (user instanceof OwnerAgentUser ownerAgent){
            ownerAgent.isOwner = isOwnerAgent;
        }

        Main.instance().serializer.write(user);

        return user;

    }

    public ArrayList<TenantUser> fetchAllTenants(){
        return fetchAllUsersOfType(TenantUser.class);
    }
    public ArrayList<OwnerAgentUser> fetchAllOwnerAgent(){
        return fetchAllUsersOfType(OwnerAgentUser.class);
    }
    public ArrayList<AdminUser> fetchAllAdmins(){
        return fetchAllUsersOfType(AdminUser.class);
    }
    public <T extends User<T>> ArrayList<T> fetchAllUsersOfType(Class<T> userClass){
        Serializer serializer = Main.instance().serializer;
        ArrayList<T> uncastedArray = serializer.readAll(User.getNewUserFromClass(userClass));
        if (uncastedArray == null){
            uncastedArray = new ArrayList<T>(0);
        }
        ArrayList<T> finalArray = new ArrayList<T>(uncastedArray.size());
        //Checked Cast (Only single class of - TenantUser / User<TenantUser>)
        for (User<T> user : uncastedArray){
            finalArray.add(userClass.cast(user));
        }
        return finalArray;
    }
    public static class AllUsers{
        public ArrayList<User<?>> allUsers;
        public ArrayList<TenantUser> tenants;
        public ArrayList<OwnerAgentUser> ownerAgents;
        public ArrayList<AdminUser> admins;

        public void generate(Admin admin){
            tenants = admin.fetchAllTenants();
            ownerAgents = admin.fetchAllOwnerAgent();
            admins = admin.fetchAllAdmins();

            allUsers = new ArrayList<User<?>>();
            allUsers.addAll(tenants);
            allUsers.addAll(ownerAgents);
            allUsers.addAll(admins);
        }
    }
    public AllUsers fetchAllUsers(){
        AllUsers allUsers = new AllUsers();
        allUsers.generate(this);
        return allUsers;
    }

    private User<?> userExists(String username){
        User<TenantUser> tenantUser = userExistsForType(TenantUser.class, username);
        if (tenantUser != null){
            return tenantUser;
        }
        User<OwnerAgentUser> ownerAgentUser = userExistsForType(OwnerAgentUser.class, username);
        if (ownerAgentUser != null){
            return ownerAgentUser;
        }
        User<AdminUser> adminUser = userExistsForType(AdminUser.class, username);
        if (adminUser != null){
            return adminUser;
        }
        return null;
    }
    /**
     * If a user exists from given username, it will return the existing user if it's already taken, or null
     * @param <T>
     * @param userClass
     * @param username
     * @return
     */
    private <T extends User<T>> User<T> userExistsForType(Class<T> userClass, final String username){
        Serializer serializer = Main.instance().serializer;
        T dummy = User.getNewUserFromClass(userClass);
        var command = new Command<T>(){
            private T existingUser;
            public T getExistingUser(){
                return existingUser;
            }
            @Override
            public boolean execute(T user){
                if (user.username.equals(username)){
                    existingUser = user;
                    return true;
                }
                return false;
            }
            @Override
            public boolean execute(T user, Object discard){
                return execute(user);
            }
        };
        serializer.readForEach(dummy, command);
        User<T> user = command.getExistingUser();
        return user;
    }

    public Admin(AdminUser loggedInAdmin){
        this.loggedInAdmin = loggedInAdmin;
    }

    public void deleteAllAnyUsers(ArrayList<User<?>> users){
        ArrayList<TenantUser> tenants = new ArrayList<TenantUser>();;
        ArrayList<OwnerAgentUser> ownerAgents = new ArrayList<OwnerAgentUser>();
        ArrayList<AdminUser> admins = new ArrayList<AdminUser>();
        for (User<?> user : users){
            if (user instanceof TenantUser tenant){
                tenants.add(tenant);
            }
            else if (user instanceof OwnerAgentUser ownerAgent){
                ownerAgents.add(ownerAgent);
            }
            else if (user instanceof AdminUser admin){
                admins.add(admin);
            }
        }
        deleteTenants(tenants);
        deleteOwnerAgent(ownerAgents);
        deleteAdmins(admins);
    }
    public void deleteTenants(ArrayList<TenantUser> tenants){
        deleteUser(TenantUser.class, tenants);
    }
    public void deleteOwnerAgent(ArrayList<OwnerAgentUser> ownerAgents){
        deleteUser(OwnerAgentUser.class, ownerAgents);
    }
    public void deleteAdmins(ArrayList<AdminUser> admins){
        deleteUser(AdminUser.class, admins);
    }

    public <T extends User<T>> void deleteUser(Class<T> userClass, ArrayList<T> users){
        if (users.size() == 0){
            return;
        }
        var executionCommand = new Command<T>(){
            @Override
            public boolean execute(T user, Object lineNumber){
                for (T t : users){
                    if (t.username.equals(user.username)){
                        return true;
                    }
                }
                return false;
            }
        };
        T dummy = User.getNewUserFromClass(userClass);
        Main.instance().serializer.removeForEach(dummy, executionCommand);
    }
    

    public void deleteProperties(ArrayList<PropertyListing> delete){
        
        if (delete.size() == 0){
            return;
        }
        var executionCommand = new Command<PropertyListing>(){
            @Override
            public boolean execute(PropertyListing prop, Object lineNumber){
                for (PropertyListing p : delete){
                    if (p.getID().equals(prop.getID())){
                        return true;
                    }
                }
                return false;
            }
        };
        PropertyListing dummy = PropertyListing.createProperty();
        Main.instance().serializer.removeForEach(dummy, executionCommand);
    }

}
