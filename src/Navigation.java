package src;

import src.Login.*;
import java.util.Stack;

/**
 * 
 * This Navigation class handles windows and panel flow
 * 
 */
public class Navigation {

    public static Navigation _instance;
    public static Navigation instance(){
        if (_instance != null){
            return _instance;
        }
        _instance = new Navigation();
        return _instance;
    }

    /**
     * Prevent creation of additional Navigation objects besides the singleton itself
     */
    private Navigation(){}



    private Stack<GUIWindow> windowsStack = new Stack<GUIWindow>();
    
    //Starts login page (by default)
    public void startProgram(){
        LoginGUIWindow loginWindow = new LoginGUIWindow();
        windowsStack.add(loginWindow);

        startLifeCycleOfWindow(loginWindow);
    }

    /**
     * Starts the creation life cycle of a window
     */
    private void startLifeCycleOfWindow(GUIWindow window){
        window.onCreate();
        window.onCreatePanel();
        window.onView();
    }

    /**
     * Flushes the stack, removing it from memory (sent to garbage collector)
     * This will reset the back button
     * NOTE: When transitioning to a new stack, flush before navigating
     */
    public void flushWindowStack(){
        while (!windowsStack.isEmpty()){
            GUIWindow window = windowsStack.pop();
            destroyWindow(window);
        }
    }

    /**
     * Ends the lifecycle of a window
     */
    private void destroyWindow(GUIWindow window){
        window.onDestroy();
    }

}
