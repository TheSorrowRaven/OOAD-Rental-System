package src;

import javax.swing.*;
import java.awt.*;
import src.SystemComponents.CLI;

public abstract class MenuBarGUIPanel extends GUIPanel<MenuGUIWindow> {

    protected JPanel gridBagPanel;
    protected GridBagConstraints gridBagConstraints;

    public MenuBarGUIPanel(MenuGUIWindow parent) {
        super(parent);
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
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = Resource().general_inset_all;

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
        add(backButton, gridBagConstraints);

        gridBagConstraints.gridx ++;
        JLabel label = JLabel(getMenuBarTitle());
        add(label, gridBagConstraints);
    }

    @Override
    public void onCreatePanel() {
        
    }

    @Override
    public void onView() {
        setVisible(true);
    }
    
}
