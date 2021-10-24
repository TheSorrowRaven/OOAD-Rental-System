package src.Admin;

import src.*;

import java.awt.*;

/**
 * This is the view content extending the menu content panel (for menu bar)
 */
public class AdminGUIPanel extends MenuContentGUIPanel<AdminGUIWindow> {

    /**
     * Constructor
     */
    public AdminGUIPanel(AdminGUIWindow parent) {
        super(parent);
    }

    /**
     * Setups the layout
     */
    @Override
    public void onCreate() {
        GridLayout grid = new GridLayout(0, 1); //Sets to 0,1 to ensure the content fills this panel
        setLayout(grid);
    }

    /**
     * Creates the sub menu panel and attach it to this panel
     */
    @Override
    public void onCreatePanel() {
        AdminSubMenuGUIPanel subMenuPanel = new AdminSubMenuGUIPanel(parent);
        attachPanel(subMenuPanel);
    }

    @Override
    public void onPreparingToFreeze() {
        
    }

    @Override
    public void onView(){
        
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
