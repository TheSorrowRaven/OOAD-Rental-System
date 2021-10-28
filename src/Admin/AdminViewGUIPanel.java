package src.Admin;


import src.*;

import javax.swing.*;
import java.awt.*;

/**
 * Tasha
 * 
 * This is a base class for any sub menu view for the admin
 */
public class AdminViewGUIPanel extends GUIPanel<AdminGUIWindow>{

    public String title;

    /**
     * Constructor
     * @param parent
     */
    public AdminViewGUIPanel(AdminGUIWindow parent) {
        super(parent);
    }

    protected GridBagConstraints gbc;


    /**
     * Sets the title of this Sub menu
     * @param title
     */
    public void initializeTitle(String title){
        this.title = title;
    }

    /**
     * Creates the necessary components to view
     */
    @Override
    public void onCreate() {

        gbc = new GridBagConstraints();
        gbc.fill = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = Resource().general_inset_all;
        gbc.anchor = GridBagConstraints.CENTER;

        setLayout(new GridBagLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Theme().background_color);
        JScrollPane panelScroll = JScrollPane(panel);
        panelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelScroll.getVerticalScrollBar().setUnitIncrement(Resource().scroll_speed);
        panelScroll.getHorizontalScrollBar().setUnitIncrement(Resource().scroll_speed);
        add(panelScroll, gbc);

        setTargetPanel(panel);

        

        gbc.fill = 0;
        JLabel titleLabel = JLabel(this.title);
        titleLabel.setFont(Resource().general_font_title);
        add(titleLabel, gbc);
        gbc.gridy++;
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
