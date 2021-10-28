package src.Users;

import src.*;
import src.SystemComponents.*;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Raven
 * 
 * Base Serializable Object - User (base)
 * This User class represents a loginnable user
 */
public abstract class User<T extends User<T>> implements ISerializable<T> {

    /**
     * Shorthand for Resource singleton
     * @return
     */
    public static Resource Resource(){
        return Resource.instance();
    }

    /**
     * Creates a user object (inherited) from its generic class
     * @param <T>
     * @param userClass
     * @return
     */
    public static <T> T getNewUserFromClass(Class<T> userClass){
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

    /**
     * Returns the user id
     * @return
     */
    public UUID getID(){
        return id;
    }

    /**
     * Creates and assigns itself a new ID
     */
    private void newUserGenerateUUID(){
        id = Resource().getUUID();
    }
    /**
     * Sets the username as a new user
     * @param username
     */
    private void newUserSetUsername(String username){
        this.username = username;
    }
    /**
     * Sets the password as a new user
     * @param password
     */
    private void newUserSetPassword(String password){
        this.password = password;
    }
    /**
     * Sets the name as a new user
     * @param name
     */
    private void newUserSetName(String name){
        this.name = name;
    }

    /**
     * Sets the values as a new user
     * @param username
     * @param password
     * @param name
     */
    public void newUserSet(String username, String password, String name){
        newUserGenerateUUID();
        newUserSetUsername(username);
        newUserSetPassword(password);
        newUserSetName(name);
    }

    /**
     * Returns the saveable text after combining its id, username, password and name
     */
    @Override
    public String getSaveableText() {
        return Input.combineData(id.toString(), username, password, name);
    }

    /**
     * Loads the saveable text into a split string
     */
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

    /**
     * Loads the split string into a user
     * @param split
     * @param user
     * @return
     */
    protected int loadSaveableSplitTextIntoUser(String[] split, T user){
        int c = 0;
        user.id = Resource().getUUIDFrom(split[c++]);
        user.username = split[c++];
        user.password = split[c++];
        user.name = split[c++];
        return c;
    }

    public abstract T createUser();

    /**
     * Returns the number of columns required to display the table for user
     * @return
     */
    public static final int getBaseTableDisplayColumns(){
        return 3;
    }
    /**
     * Returns the row data representing itself for each column
     * @return
     */
    public final ArrayList<Object> getBaseTableDisplayColumnsData(){
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(username);
        data.add(password);
        data.add(name);
        return data;
    }
    /**
     * Returns the column headers string name to display
     * @return
     */
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
    /**
     * Returns the column headers
     */
    public ArrayList<String> getColumnHeaders(boolean isOwnerAgent){
        return getBaseColumnHeaders();
    }
    /**
     * Returns the column headers
     */
    public final ArrayList<String> getColumnHeaders(){
        return getColumnHeaders(false);
    }
    
}
