package src.Users;

import src.*;
import src.SystemComponents.*;

import java.util.UUID;

/**
 * This User class represents a loginnable user
 */
public abstract class User<T extends User<T>> implements ISerializable<User<T>> {

    public Resource Resource(){
        return Resource.instance();
    }

    public static <T extends User<T>> T getNewUserFromClass(Class<T> userClass){
        try {
            return userClass.getDeclaredConstructor().newInstance();
        } catch (Exception e){
            CLI.log("Class type is not the same as the requested type");
            e.printStackTrace();
            return null;
        }
    }

    public UUID id;
    public String username;
    public String password;

    public UUID getID(){
        return id;
    }

    private void newUserGenerateUUID(){
        id = Resource().getUUID();
    }
    private void newUserSetUsername(String username){
        this.username = username;
    }
    private void newUserSetPassword(String password){
        this.password = password;
    }
    public void newUserSet(String username, String password){
        newUserGenerateUUID();
        newUserSetUsername(username);
        newUserSetPassword(password);
    }

    @Override
    public String getSaveableText() {
        return Input.combineData(id.toString(), username, password);
    }

    @Override
    public T loadSaveableText(String text) {
        String[] split = Input.separateIntoData(text);
        T user = createUser();
        loadSaveableSplitTextIntoUser(split, user);
        return user;
    }

    @Override
    public void onFinishLoading(){

    }

    protected int loadSaveableSplitTextIntoUser(String[] split, T user){
        int c = 0;
        user.id = Resource().getUUIDFrom(split[c++]);
        user.username = split[c++];
        user.password = split[c++];
        return c;
    }

    public abstract T createUser();
    
}
