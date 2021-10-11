package src.Login;

import src.*;
import src.SystemComponents.CLI;
import src.Users.*;

import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

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
    }

    public ActionListener getLoginTenantAction(){
        return e -> {
            login(Tenant.class);
            // Tenant tenant = new Tenant();
            // tenant.username = getUsername();
            // tenant.password = getPassword();
            // tenant.newUserGenerateUUID();
            // Main.instance().serializer.write(tenant);
        };
    }

    public ActionListener getLoginOwnerAgentAction(){
        return e->{
            login(OwnerAgent.class);
        };
    }

    public ActionListener getLoginAdminAction(){
        return e->{
            login(Admin.class);
        };
    }
    private <T extends User<T>> void login(Class<T> userClass){
        T user = login.tryLogin(userClass, getUsername(), getPassword());
        loginCheckNotify(user);

    }
    
}
