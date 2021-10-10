package src.Login;
import src.GUIWindow;

/**
 * 
 * This LoginGUIWindow is the main greeting page when the app is opened
 * 
 */
public class LoginGUIWindow extends GUIWindow {
    
    public Login Login;

    //Initialize on create
    public LoginGUIWindow(){
        super();
        Login = new Login();
    }

    @Override
    public void onCreate(){
        setSize(Resource().login_WindowWidth, Resource().login_WidowHeight);
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
        
    }

    @Override
    public void onDestroy() {
        
    }


}
