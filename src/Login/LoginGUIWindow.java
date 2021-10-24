package src.Login;
import java.util.ArrayList;

import src.GUIPanel;
import src.GUIWindow;

/**
 * 
 * This LoginGUIWindow is the main greeting page when the app is opened
 * 
 */
public class LoginGUIWindow extends GUIWindow implements ILoginnable {

    public LoginController loginController;
    private LoginGUIPanel loginPanel;

    /**
     * Constructor, creates a controller
     */
    public LoginGUIWindow(){
        super(Resource().login_theme);
        loginController = new LoginController(this);
    }

    /**
     * Returns the login window title from Resource
     */
    @Override
    public String getWindowTitle(){
        return Resource().login_window_title;
    }

    /**
     * Sets the size of the login window
     */
    @Override
    public void onCreate(){
        setSize(Resource().login_window_width, Resource().login_window_height);
    }

    /**
     * Creates the login panel and attach to itself
     */
    @Override
    public void onCreatePanel() {
        loginPanel = new LoginGUIPanel(this);
        attachPanel(loginPanel);
    }

    /**
     * Sets itself to be visible
     */
    @Override
    public void onView() {
        setVisible(true);
    }

    @Override
    public void onPreparingToFreeze() {
        
    }

    /**
     * When frozen (logged in), it hides itself
     */
    @Override
    public void onFrozen() {
        setVisible(false);
    }

    /**
     * When thawed (logged out), it shows itself back, and notifies the panel about the logout
     */
    @Override
    public void onThawed() {
        setVisible(true);
        loginPanel.onThawed();
    }

    @Override
    public void onDestroy() {
        
    }



    public ArrayList<ILoginnable> loginnables = new ArrayList<ILoginnable>();
    /**
     * Keeps track of child loginnables for notification
     */
    @Override
    public ArrayList<ILoginnable> getLoginnables() {
        return loginnables;
    }

    /**
     * Adds to keep track of the loginnables when the panel is attached
     */
    @Override
    public void attachPanel(GUIPanel<?> panel){
        super.attachPanel(panel);
        if (panel instanceof ILoginnable loginnable){
            loginnables.add(loginnable);
        }
    }




}
