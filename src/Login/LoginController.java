package src.Login;

import src.*;
import src.Admin.AdminGUIWindow;
import src.OwnerAgent.*;
import src.Tenant.*;
import src.Users.*;

import java.awt.event.ActionListener;

import javax.swing.JTextField;

/**
 * Raven
 * 
 * This class is the Controller for Login
 * This class handles events and inputs
 */
public class LoginController {

    /**
     * Shorthand for the resource singleton
     * @return
     */
    public static Resource Resource(){
        return Resource.instance();
    }

    public Login login;
    public ILoginnable view;

    private JTextField usernameField;
    private JTextField passwordField;

    /**
     * Gets the text from the username field
     * @return
     */
    private String getUsername(){
        return usernameField.getText();
    }
    /**
     * Gets the text from the password field
     * @return
     */
    private String getPassword(){
        return passwordField.getText();
    }

    /**
     * Constructor accepting a loginnable view
     * @param view
     */
    public LoginController(ILoginnable view){
        this.view = view;
        login = new Login();
        login.ensureDefaultAdminExists(Resource().defaultAdminUsername, Resource().defaultAdminPassword, Resource().defaultAdminEntry);
    }


    /**
     * Assign and setup the usename field
     * @param usernameField
     */
    public void attachUsernameTextField(JTextField usernameField){
        this.usernameField = usernameField;
    }
    /**
     * Assign and setup the password field
     * @param usernameField
     */
    public void attachPasswordTextField(JTextField passwordField){
        this.passwordField = passwordField;
    }

    /**
     * Checks if user is null or not, then notify error or success
     * @param user
     */
    private void loginCheckNotify(User<?> user){
        if (user == null){
            view.notifyLoginError();
            return;
        }
        view.notifyLoginSuccessful(user);
        loginWindow(user);
    }

    /**
     * Logs into the other windows to navigate to them once logged in
     * @param user
     */
    private void loginWindow(User<?> user){
        Navigation nav = Main.instance().nav;
        if (user instanceof TenantUser tenant){
            nav.newWindow(new TenantGUIWindow(tenant));
        }
        else if (user instanceof OwnerAgentUser ownerAgent){
            nav.newWindow(new OwnerAgentGUIWindow(ownerAgent));
        }
        else if (user instanceof AdminUser admin){
            nav.newWindow(new AdminGUIWindow(admin));
        }
    }

    /**
     * Logs in as Tenant
     * @return
     */
    public ActionListener getLoginTenantAction(){
        return e -> {
            login(TenantUser.class);
        };
    }

    /**
     * Logs in as Owner/agent
     * @return
     */
    public ActionListener getLoginOwnerAgentAction(){
        return e->{
            login(OwnerAgentUser.class);
        };
    }

    /**
     * Logs in as Admin
     * @return
     */
    public ActionListener getLoginAdminAction(){
        return e->{
            login(AdminUser.class);
        };
    }
    /**
     * Logs in based on a specific class type (tenant, owner/agent, admin)
     */
    private <T extends User<T>> void login(Class<T> userClass){
        T user = login.tryLogin(userClass, getUsername(), getPassword());
        loginCheckNotify(user);

    }
    
}
