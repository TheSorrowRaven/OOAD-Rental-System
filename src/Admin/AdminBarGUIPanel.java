package src.Admin;

import src.*;
import src.Users.*;

/**
 * This class is the menu bar (containing logout and edit profile options)
 */
public class AdminBarGUIPanel extends MenuBarGUIPanel {

    /**
     * Constructor
     * @param parent
     * @param loggedInUser
     */
    public AdminBarGUIPanel(MenuGUIWindow parent, User<?> loggedInUser) {
        super(parent, loggedInUser);
    }

    /**
     * Gives the logged in admin's name
     */
    @Override
    public String getMenuBarTitle(){
        AdminController adminController = ((AdminGUIWindow)parent).adminController;
        return adminController.adminUser.name;
    }

    @Override
    public void onPreparingToFreeze() {
        
    }

    @Override
    public void onFrozen() {
        
    }

    @Override
    public void onDestroy() {
        
    }
    
}
