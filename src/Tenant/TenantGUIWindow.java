package src.Tenant;

import src.*;
import src.Users.*;

/**
 * Raihan
 * 
 * Window View for Tenant
 */
public class TenantGUIWindow extends MenuGUIWindow {

    public TenantController tenantController;

    /**
     * Constructor requiring a logged in tenant
     * @param loggedInTenant
     */
    public TenantGUIWindow(TenantUser loggedInTenant) {
        super(Resource().tenant_theme);
        tenantController = new TenantController(loggedInTenant);
    }

    /**
     * Returns the tenant window title from resources
     */
    @Override
    public String getWindowTitle() {
        return Resource().tenant_window_title;
    }

    /**
     * Sets the size of the window
     */
    @Override
    public void onCreate() {
        setSize(Resource().menu_window_width, Resource().menu_window_height);
    }

    /**
     * Sets itself to be visible
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

    /**
     * Returns a new tenant menu bar
     */
    @Override
    protected MenuBarGUIPanel getMenuBarPanel() {
        return new TenantBarGUIPanel(this, tenantController.loggedInTenant);
    }

    /**
     * Returns a new tenant content panel
     */
    @Override
    protected MenuContentGUIPanel<?> getContentGUIPanel() {
        return new TenantGUIPanel(this);
    }
    
}
