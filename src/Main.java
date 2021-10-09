package src;

/**
 * 
 * This Main class serves as an entry point and thread handling
 * 
 */
public class Main {

    public static Main _instance;
    public static Main instance(){
        if (_instance != null){
            return _instance;
        }
        _instance = new Main();
        return _instance;
    }

    private Main(){
        nav = Navigation.instance();
    }
    private void start(){
        nav.startProgram();
    }

    public Navigation nav;

    //Program Entry
    public static void main(String[] args){
        instance().start();
    }

}
