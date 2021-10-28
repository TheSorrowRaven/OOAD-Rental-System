package src.Profile;

import src.*;
import src.Users.*;
import javax.swing.*;
import java.awt.*;

/**
 * CG
 * 
 * This is the View of Profile
 */
public class ProfileGUIPanel extends GUIPanel<ProfileGUIWindow> {


    private JTextField changeNameField;
    private JTextField changePasswordField;

    /**
     * Returns the change name field for text retrieval
     */
    public JTextField getChangeNameField(){
        return changeNameField;
    }
    /**
     * Returns the change password field for text retrieval
     */
    public JTextField getChangePasswordField(){
        return changePasswordField;
    }

    /**
     * Constructor accepting a logged in user
     * @param parent
     * @param loggedInUser
     */
    public ProfileGUIPanel(ProfileGUIWindow parent, User<?> loggedInUser) {
        super(parent);
        parent.profileController.setProfilePanel(this);
    }

    /**
     * Creates the components for display
     */
    @Override
    public void onCreate() {
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = Resource().general_inset_all;

        gbc.anchor = GridBagConstraints.EAST;

        JLabel changeNameLabel = JLabel(Resource().profile_str_change_name);
        add(changeNameLabel, gbc);

        gbc.gridy++;
        JLabel changePasswordLabel = JLabel(Resource().profile_str_change_password);
        add(changePasswordLabel, gbc);
        gbc.gridy++;

        gbc.gridx++;
        gbc.gridy = 0;

        gbc.anchor = GridBagConstraints.CENTER;
        changeNameField = JTextField();
        add(changeNameField, gbc);
        gbc.gridy++;
        changePasswordField = JPasswordField();
        add(changePasswordField, gbc);
        gbc.gridy++;

        gbc.gridx = 0;
        
        Image logoutImage = Resource().getLogoutIcon();
        src.Button backButton = Button();
        backButton.addActionListener(parent.profileController.getGoBackAction());
        backButton.setIcon(new ImageIcon(logoutImage));
        add(backButton, gbc);

        gbc.gridx++;

        src.Button saveButton = Button(Resource().profile_str_button_save);
        saveButton.addActionListener(parent.profileController.getSaveActionListener());
        add(saveButton, gbc);

        parent.profileController.setCurrentNamePassword();

    }

    @Override
    public void onCreatePanel() {
        
    }

    @Override
    public void onView() {
        
    }

    @Override
    public void onPreparingToFreeze() {
        
    }

    @Override
    public void onFrozen() {
        
    }

    @Override
    public void onThawed() {
        
    }

    @Override
    public void onDestroy() {
        
    }
    
}
