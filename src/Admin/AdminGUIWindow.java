package src.Admin;

import src.*;
import src.Users.*;

/**
 * This class is the main View for Admin
 * This is the window defintion for the admin window
 */
public class AdminGUIWindow extends MenuGUIWindow {


    public AdminController adminController;

    /**
     * Constructor accepting a logged in admin user
     * @param adminUser
     */
    public AdminGUIWindow(AdminUser adminUser) {
        super(Resource().admin_theme);
        adminController = new AdminController(adminUser);
    }

    /**
     * Poitns to the Resource string that has the admin window title
     */
    @Override
    public String getWindowTitle() {
        return Resource().admin_window_title;
    }

    /**
     * Gives the specific admin bar panel
     */
    @Override
    protected MenuBarGUIPanel getMenuBarPanel() {
        return new AdminBarGUIPanel(this, adminController.adminUser);
    }

    /**
     * Gives the specified main Admin Paned
     */
    @Override
    protected MenuContentGUIPanel<AdminGUIWindow> getContentGUIPanel() {
        return new AdminGUIPanel(this);
    }

    /**
     * Sets the size of the window
     */
    @Override
    public void onCreate() {
        setSize(Resource().menu_window_width, Resource().menu_window_height);
    }

    /**
     * Set this window to be visible
     */
    @Override
    public void onView() {
        setVisible(true);
    }

    @Override
    public void onPreparingToFreeze() {
        
    }

    @Override
    public void onDestroy() {
        
    }
    
}
