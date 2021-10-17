package src.Users;

import src.*;
import src.SystemComponents.*;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This User class represents a loginnable user
 */
public abstract class User<T extends User<T>> implements ISerializable<User<T>> {

    public static Resource Resource(){
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
    public String name;

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
    private void newUserSetName(String name){
        this.name = name;
    }

    public void newUserSet(String username, String password, String name){
        newUserGenerateUUID();
        newUserSetUsername(username);
        newUserSetPassword(password);
        newUserSetName(name);
    }

    @Override
    public String getSaveableText() {
        return Input.combineData(id.toString(), username, password, name);
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
        user.name = split[c++];
        return c;
    }

    public abstract T createUser();

    public static final int getBaseTableDisplayColumns(){
        return 3;
    }
    public final ArrayList<Object> getBaseTableDisplayColumnsData(){
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(username);
        data.add(password);
        data.add(name);
        return data;
    }
    public static final ArrayList<String> getBaseColumnHeaders(){
        ArrayList<String> array = new ArrayList<String>();
        array.add(Resource().str_username);
        array.add(Resource().str_password);
        array.add(Resource().str_name);
        return array;
    }
    /**
     * For when displaying tables of this data, total columns required, override if require more
     * @return
     */
    public int getTableDisplayColumns(){
        return 3;   //Username & password
    }
    /**
     * Creates the object data required to display on a table, override if need more
     * @return
     */
    public ArrayList<Object> getTableDisplayColumnsData(){
        return getBaseTableDisplayColumnsData();
    }
    public ArrayList<String> getColumnHeaders(){
        return getBaseColumnHeaders();
    }
    
}
