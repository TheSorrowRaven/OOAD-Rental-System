package src;

import javax.swing.*;
import java.awt.*;
import src.SystemComponents.CLI;

public abstract class MenuBarGUIPanel extends GUIPanel<MenuGUIWindow> {

    protected JPanel gridBagPanel;
    protected GridBagConstraints ctr;

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
        ctr = new GridBagConstraints();
        ctr.fill = 0;
        ctr.gridy = 0;
        ctr.gridx = 0;
        ctr.insets = Resource().general_inset_all;

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
        add(backButton, ctr);

        ctr.gridx ++;
        JLabel label = JLabel(getMenuBarTitle());
        add(label, ctr);
    }

    @Override
    public void onCreatePanel() {
        
    }

    @Override
    public void onView() {
        setVisible(true);
    }
    
}
