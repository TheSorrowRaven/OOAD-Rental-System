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

    //Initialize on create
    public LoginGUIWindow(){
        super();
        loginController = new LoginController(this);
    }

    @Override
    public String getWindowTitle(){
        return Resource().login_window_title;
    }

    @Override
    public void onCreate(){
        setSize(Resource().login_window_Width, Resource().login_window_Height);
    }

    @Override
    public void onCreatePanel() {
        LoginGUIPanel loginPanel = new LoginGUIPanel(this);
        attachPanel(loginPanel);
    }

    @Override
    public void onView() {
        setVisible(true);
    }

    @Override
    public void onPreparingToSwitch() {
        
    }

    @Override
    public void onSwitchedOff() {
        setVisible(false);
    }

    @Override
    public void onSwitchedIn() {
        setVisible(true);
    }

    @Override
    public void onDestroy() {
        
    }



    public ArrayList<ILoginnable> loginnables = new ArrayList<ILoginnable>();
    @Override
    public ArrayList<ILoginnable> getLoginnables() {
        return loginnables;
    }

    @Override
    public void attachPanel(GUIPanel<?> panel){
        super.attachPanel(panel);
        if (panel instanceof ILoginnable loginnable){
            loginnables.add(loginnable);
        }
    }




}
