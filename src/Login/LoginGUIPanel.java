package src.Login;

import src.*;
import src.SystemComponents.CLI;
import src.Users.*;

import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;

public class LoginGUIPanel extends GUIPanel<LoginGUIWindow> implements ILoginnable {
    

    private JTextField usernameField;
    private JTextField passwordField;

    private JLabel errorLabel;

    public LoginGUIPanel(LoginGUIWindow parent) {
        super(parent);
    }

    @Override
    public void onCreate() {
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = Resource().general_inset_bottom;
        gbc.fill = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        GridBagLayout gridLayout = new GridBagLayout();
        setLayout(gridLayout);



        JLabel usernameLabel = JLabel(Resource().login_str_prompt_Username);
        usernameField = JTextField();
        parent.loginController.attachUsernameTextField(usernameField);
        add(usernameLabel, gbc);
        gbc.gridy++;
        add(usernameField, gbc);
        gbc.gridy++;

        JLabel passwordLabel = JLabel(Resource().login_str_prompt_Password);
        passwordField = JPasswordField();
        parent.loginController.attachPasswordTextField(passwordField);
        add(passwordLabel, gbc);
        gbc.gridy++;

        gbc.insets = Resource().general_inset_bottom_major_spacing;
        add(passwordField, gbc);
        gbc.gridy++;

        gbc.insets = Resource().general_inset_bottom;

        errorLabel = JLabel(Resource().login_str_error_text);
        errorLabel.setForeground(Resource().general_error_text_color);
        errorLabel.setBackground(Resource().general_error_background_color);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setOpaque(true);
        errorLabel.setVisible(false);
        add(errorLabel, gbc);
        gbc.gridy++;

        JButton loginWithTenantButton = Button(Resource().login_str_button_LoginAsTenant);
        JButton loginWithOwnerAgentButton = Button(Resource().login_str_button_LoginAsOwnerAgent);
        JButton loginWithAdminButton = Button(Resource().login_str_button_LoginAsAdmin);
        loginWithTenantButton.addActionListener(parent.loginController.getLoginTenantAction());
        loginWithOwnerAgentButton.addActionListener(parent.loginController.getLoginOwnerAgentAction());
        loginWithAdminButton.addActionListener(parent.loginController.getLoginAdminAction());
        add(loginWithTenantButton, gbc);
        gbc.gridy++;
        add(loginWithOwnerAgentButton, gbc);
        gbc.gridy++;
        add(loginWithAdminButton, gbc);
        gbc.gridy++;


    }
    
    @Override
    public void onCreatePanel() {
    }

    @Override
    public void onView() {
        setVisible(true);
    }

    @Override
    public void onPreparingToFreeze() {
        
    }

    @Override
    public void onFrozen() {
        
    }

    @Override
    public void onThawed() {
        clearTextFields();
    }

    @Override
    public void onDestroy() {
        
    }

    private void clearTextFields(){
        usernameField.setText("");
        passwordField.setText("");
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
