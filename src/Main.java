package src;

import src.Login.*;
import src.SystemComponents.*;

/**
 * 
 * This Main class serves as an entry point and thread handling
 * 
 */
public class Main {

    private static Main _instance;
    public static Main instance(){
        if (_instance != null){
            return _instance;
        }
        _instance = new Main();
        return _instance;
    }

    public Resource Resource(){
        return Resource.instance();
    }

    private Main(){
        serializer = new Serializer(Resource().savePath);
        nav = new Navigation();
    }
    private void start(){
        LoginGUIWindow loginWindow = new LoginGUIWindow();
        nav.startNavigation(loginWindow);
    }

    public Serializer serializer;
    public Navigation nav;

    //Program Entry
    public static void main(String[] args){
        instance().start();
    }

}
