package src;


/**
 * 
 * This Resource class contains string data of file paths, colors etc
 * 
 */
public class Resource {
    
    //Singleton DP - object
    private static Resource _instance;

    //Singleton - Singleton request
    public static Resource instance(){
        if (_instance != null){
            return _instance;
        }
        _instance = new Resource();
        return _instance;
    }

    /**
     * Prevent creation of additional Resource objects besides the singleton itself
     */
    private Resource(){}




    public int mainMenu_WindowWidth = 1440;
    public int mainMenu_WindowHeight = 900;

    public int login_WindowWidth = 900;
    public int login_WidowHeight = 600;



}
