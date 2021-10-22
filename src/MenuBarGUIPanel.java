package src;

import javax.swing.*;
import java.awt.*;
import src.SystemComponents.*;
import src.*;
import src.Profile.*;
import src.Users.*;

public abstract class MenuBarGUIPanel extends GUIPanel<MenuGUIWindow> {

    protected JPanel gridBagPanel;
    protected GridBagConstraints gbc;

    private JLabel titleLabel;

    private User<?> loggedInUser;

    public MenuBarGUIPanel(MenuGUIWindow parent, User<?> loggedInUser) {
        super(parent);
        this.loggedInUser = loggedInUser;
    }

    /**
     * Set to content title, like property selection?, since window already has main title
     * @return
     */
    public abstract String getMenuBarTitle();

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
        backButton.addActionListener(e ->{
            Main.instance().nav.backToLastWindow();
        });
        backButton.setIcon(new ImageIcon(logoutImage));
        add(backButton, gbc);

        gbc.gridx++;
        titleLabel = JLabel(getMenuBarTitle());
        add(titleLabel, gbc);

        gbc.gridx++;

        Button editProfileButton = Button(Resource().profile_str_button_edit);
        editProfileButton.addActionListener(
            e -> {
                Navigation nav = Main.instance().nav;
                nav.newWindow(new ProfileGUIWindow(loggedInUser));
            }
        );

        
        add(editProfileButton, gbc);
    }

    public void resetMenuBarTitle(){
        titleLabel.setText(getMenuBarTitle());
    }

    @Override
    public void onCreatePanel() {
        
    }

    @Override
    public void onThawed(){
        resetMenuBarTitle();
    }

    @Override
    public void onView() {
        setVisible(true);
    }
    
}
