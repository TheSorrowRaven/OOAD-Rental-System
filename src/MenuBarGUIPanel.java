package src;

import javax.swing.*;
import java.awt.*;
import src.Users.*;

/**
 * Raven
 * 
 * This is the menu bar for all menus
 */
public abstract class MenuBarGUIPanel extends GUIPanel<MenuGUIWindow> {

    protected JPanel gridBagPanel;
    protected GridBagConstraints gbc;

    private JLabel titleLabel;

    private User<?> loggedInUser;

    /**
     * Constructor accepting a logged in user
     * @param parent
     * @param loggedInUser
     */
    public MenuBarGUIPanel(MenuGUIWindow parent, User<?> loggedInUser) {
        super(parent);
        this.loggedInUser = loggedInUser;
    }

    /**
     * Set to content title, like property selection?, since window already has main title
     * @return
     */
    public abstract String getMenuBarTitle();

    /**
     * Creates the menu bar settings and buttons
     */
    @Override
    public void onCreate() {
        setBackground(Theme().bar_color);

        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        setLayout(flowLayout);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        gbc.fill = 0;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = Resource().general_inset_all;

        gridBagPanel = new JPanel();
        gridBagPanel.setBackground(Resource().color_invisible);
        gridBagPanel.setLayout(gridBagLayout);

        add(gridBagPanel);
        setTargetPanel(gridBagPanel);

        Image logoutImage = Resource().getLogoutIcon();
        Button backButton = Button();
        backButton.addActionListener(parent.menuController.getLogoutAction());
        backButton.setIcon(new ImageIcon(logoutImage));
        add(backButton, gbc);

        gbc.gridx++;
        titleLabel = JLabel(getMenuBarTitle());
        add(titleLabel, gbc);

        gbc.gridx++;

        Button editProfileButton = Button(Resource().profile_str_button_edit);
        editProfileButton.addActionListener(parent.menuController.getProfileAction(loggedInUser));

        
        add(editProfileButton, gbc);
    }

    /**
     * Resets the menu bar title by requesting get title again
     */
    public void resetMenuBarTitle(){
        titleLabel.setText(getMenuBarTitle());
    }

    @Override
    public void onCreatePanel() {
        
    }

    /**
     * Resets the menu bar title on thawed (re-viewed)
     */
    @Override
    public void onThawed(){
        resetMenuBarTitle();
    }

    /**
     * Sets itself to be visible on view
     */
    @Override
    public void onView() {
        setVisible(true);
    }
    
}
