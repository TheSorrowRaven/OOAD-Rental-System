package src.Login;

import src.*;
import src.SystemComponents.CLI;
import src.Users.*;

import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;

public class LoginGUIPanel extends GUIPanel<LoginGUIWindow> implements ILoginnable {
    

    public LoginGUIPanel(LoginGUIWindow parent) {
        super(parent);
    }

    @Override
    public void onCreate() {
        
        GridBagConstraints ctr = new GridBagConstraints();
        ctr.insets = Resource().general_inset_bottom;
        ctr.fill = 1;
        ctr.gridheight = 1;
        ctr.gridwidth = 1;
        ctr.gridx = 0;
        ctr.gridy = 0;
        GridBagLayout gridLayout = new GridBagLayout();
        setLayout(gridLayout);



        JLabel usernameLabel = JLabel(Resource().login_str_prompt_Username);
        JTextField usernameField = JTextField();
        parent.loginController.attachUsernameTextField(usernameField);
        add(usernameLabel, ctr);
        ctr.gridy++;
        add(usernameField, ctr);
        ctr.gridy++;

        JLabel passwordLabel = JLabel(Resource().login_str_prompt_Password);
        JPasswordField passwordField = JPasswordField();
        parent.loginController.attachPasswordTextField(passwordField);
        add(passwordLabel, ctr);
        ctr.gridy++;

        ctr.insets = Resource().general_inset_bottom_major_spacing;
        add(passwordField, ctr);
        ctr.gridy++;

        ctr.insets = Resource().general_inset_bottom;

        JButton loginWithTenantButton = JButton(Resource().login_str_button_LoginAsTenant);
        JButton loginWithOwnerAgentButton = JButton(Resource().login_str_button_LoginAsOwnerAgent);
        JButton loginWithAdminButton = JButton(Resource().login_str_button_LoginAsAdmin);
        loginWithTenantButton.addActionListener(parent.loginController.getLoginTenantAction());
        loginWithOwnerAgentButton.addActionListener(parent.loginController.getLoginOwnerAgentAction());
        loginWithAdminButton.addActionListener(parent.loginController.getLoginAdminAction());
        add(loginWithTenantButton, ctr);
        ctr.gridy++;
        add(loginWithOwnerAgentButton, ctr);
        ctr.gridy++;
        add(loginWithAdminButton, ctr);
        ctr.gridy++;


    }
    
    @Override
    public void onCreatePanel() {
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
        
    }

    @Override
    public void onSwitchedIn() {
        
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

    @Override
    public void notifyLoginError() {

        CLI.log("ERROR");

        notifyLoginErrorChildren();
    }

    @Override
    public void notifyLoginSuccessful(User<?> user){
        CLI.log("SUCCESS?");
        if (user instanceof Tenant tenant){
            CLI.log("is tenant");
        }
        else if (user instanceof OwnerAgent ownerAgent){
            CLI.log("is ownerAgent");
        }
        else if (user instanceof Admin admin){
            CLI.log("is admin");
        }
    }


    

    



}
