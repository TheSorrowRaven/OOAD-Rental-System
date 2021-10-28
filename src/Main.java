package src;

import src.Login.*;
import src.SystemComponents.*;

/**
 * Raven
 * 
 * This Main class serves as an entry point and thread handling
 * 
 */
public class Main {

    private static Main _instance;
    /**
     * Returns the instance singleton
     * @return
     */
    public static Main instance(){
        if (_instance != null){
            return _instance;
        }
        _instance = new Main();
        return _instance;
    }

    /**
     * Constructor to initialize Serializer and Navigation
     */
    private Main(){
        serializer = new Serializer(Resource.instance().savePath);
        nav = new Navigation();
    }
    /**
     * Startups the program - Boots the login window
     */
    private void start(){
        LoginGUIWindow loginWindow = new LoginGUIWindow();
        nav.startNavigation(loginWindow);
    }

    public Serializer serializer;
    public Navigation nav;

    /**
     * Program entry to create an instance of itself
     */
    public static void main(String[] args){
        instance().start();
    }

}
