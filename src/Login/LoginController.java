package src.Login;

import src.*;
import src.Admin.AdminGUIWindow;
import src.SystemComponents.CLI;
import src.Users.*;

import java.awt.event.ActionListener;

import javax.swing.JTextField;
public class LoginController {

    public Resource Resource(){
        return Resource.instance();
    }

    public Login login;
    public ILoginnable view;

    private JTextField usernameField;
    private JTextField passwordField;

    private String getUsername(){
        return usernameField.getText();
    }
    private String getPassword(){
        return passwordField.getText();
    }

    public LoginController(ILoginnable view){
        this.view = view;
        login = new Login();
        login.ensureDefaultAdminExists(Resource().defaultAdminUsername, Resource().defaultAdminPassword, Resource().defaultAdminEntry);
    }


    public void attachUsernameTextField(JTextField usernameField){
        this.usernameField = usernameField;
    }
    public void attachPasswordTextField(JTextField passwordField){
        this.passwordField = passwordField;
    }

    private void loginCheckNotify(User<?> user){
        if (user == null){
            view.notifyLoginError();
            return;
        }
        view.notifyLoginSuccessful(user);
        loginWindow(user);
    }

    private void loginWindow(User<?> user){
        if (user instanceof TenantUser tenant){

        }
        else if (user instanceof OwnerAgentUser ownerAgent){

        }
        else if (user instanceof AdminUser admin){
            Main.instance().nav.newWindow(new AdminGUIWindow(admin));
        }
    }

    public ActionListener getLoginTenantAction(){
        return e -> {
            login(TenantUser.class);
        };
    }

    public ActionListener getLoginOwnerAgentAction(){
        return e->{
            login(OwnerAgentUser.class);
        };
    }

    public ActionListener getLoginAdminAction(){
        return e->{
            login(AdminUser.class);
        };
    }
    private <T extends User<T>> void login(Class<T> userClass){
        T user = login.tryLogin(userClass, getUsername(), getPassword());
        loginCheckNotify(user);

    }
    
}
