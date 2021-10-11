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

    public UUID id;
    public String username;
    public String password;

    public UUID getID(){
        return id;
    }

    public void newUserGenerateUUID(){
        id = Resource().getUUID();
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

    protected abstract T createUser();
    
}
