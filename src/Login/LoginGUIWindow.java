package src.Login;
import src.CLI;
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
    protected void onCreatePanel() {
        
    }

    @Override
    protected void onView() {
        setVisible(true);
    }

    @Override
    protected void onSwitched() {
        
    }

    @Override
    protected void onDestroy() {
        
    }

}
