package src.Login;

import src.*;
import src.SystemComponents.CLI;
import src.Users.*;

import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;

public class LoginGUIPanel extends GUIPanel<LoginGUIWindow> implements ILoginnable {
    
    private JLabel errorLabel;

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

        errorLabel = JLabel(Resource().login_str_error_text);
        errorLabel.setForeground(Resource().general_error_text_color);
        errorLabel.setBackground(Resource().general_error_background_color);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setOpaque(true);
        errorLabel.setVisible(false);
        add(errorLabel, ctr);
        ctr.gridy++;

        JButton loginWithTenantButton = Button(Resource().login_str_button_LoginAsTenant);
        JButton loginWithOwnerAgentButton = Button(Resource().login_str_button_LoginAsOwnerAgent);
        JButton loginWithAdminButton = Button(Resource().login_str_button_LoginAsAdmin);
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

        errorLabel.setVisible(true);

        notifyLoginErrorChildren();
    }

    @Override
    public void notifyLoginSuccessful(User<?> user){
        errorLabel.setVisible(false);

    }



    

    



}
