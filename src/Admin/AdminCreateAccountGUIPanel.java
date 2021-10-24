package src.Admin;

import src.*;
import src.Users.*;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that creates a user account (internally attached)
 */
public class AdminCreateAccountGUIPanel<T extends User<T>> extends GUIPanel<AdminGUIWindow> {

    private Class<T> userClass;
    private boolean ownerAgent;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField nameField;
    private JLabel infoLabel;

    /**
     * Constructor accepting a logged in user and if needs an owner or agent
     * @param parent
     * @param userClass
     * @param isOwnerAgent
     */
    public AdminCreateAccountGUIPanel(AdminGUIWindow parent, Class<T> userClass, boolean isOwnerAgent) {
        super(parent);
        this.userClass = userClass;
        ownerAgent = isOwnerAgent;
    }

    /**
     * Creates all components to form the final view
     */
    @Override
    public void onCreate() {

        setLayout(new GridBagLayout());
        setBackground(Theme().panel_background_color);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = Resource().general_inset_all;

        gbc.gridwidth = 2;
        JLabel createTitleLabel = JLabel(Resource().admin_str_content_create_title);
        add(createTitleLabel, gbc);
        gbc.gridy++;
        JLabel createInstructionsLabel = JLabel(Resource().admin_str_content_create_instructions);
        createInstructionsLabel.setFont(Resource().general_font_minor);
        add(createInstructionsLabel, gbc);
        gbc.gridy++;

        gbc.insets = Resource().general_inset_medium;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 1;
        JLabel usernameLabel = JLabel(Resource().admin_str_content_create_username);
        JLabel nameLabel = JLabel(Resource().admin_str_content_create_name);
        JLabel passwordLabel = JLabel(Resource().admin_str_content_create_password);
        add(usernameLabel, gbc);
        gbc.gridy++;
        add(passwordLabel, gbc);
        gbc.gridy++;
        add(nameLabel, gbc);
        gbc.gridy++;

        gbc.fill = 1;
        usernameField = JTextField();
        passwordField = JTextField();
        nameField = JTextField();
        gbc.gridy = 2;
        gbc.gridx = 1;
        add(usernameField, gbc);
        gbc.gridy++;
        add(passwordField, gbc);
        gbc.gridy++;
        add(nameField, gbc);
        gbc.gridy++;

        gbc.gridx = 0;
        infoLabel = JLabel("");
        infoLabel.setOpaque(true);
        infoLabel.setVisible(false);
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(infoLabel, gbc);

        gbc.gridx++;
        JButton createAccountButton = Button(Resource().admin_str_content_create_acc_button);
        createAccountButton.addActionListener(parent.adminController.getCreateAccountActionListener(this, userClass, ownerAgent));
        add(createAccountButton, gbc);
        
        parent.adminController.setCreateAccountTextFields(usernameField, passwordField, nameField);

    }

    /**
     * Clears the text fields and information label when successfully created a user
     */
    public void finishCreateClear(){
        usernameField.setText("");
        passwordField.setText("");
        nameField.setText("");
        infoLabel.setText(Resource().admin_str_content_create_success);
        infoLabel.setForeground(Theme().sub_text_color);
        infoLabel.setBackground(Theme().sub_background_color);
        infoLabel.setVisible(true);
    }

    /**
     * Shows an error via the information label when failed to create a user (username already exist)
     */
    public void failedCreate(){
        infoLabel.setText(Resource().admin_str_content_create_fail);
        infoLabel.setForeground(Resource().general_error_text_color);
        infoLabel.setBackground(Resource().general_error_background_color);
        infoLabel.setVisible(true);
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
