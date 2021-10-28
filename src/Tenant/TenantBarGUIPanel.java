package src.Tenant;

import src.MenuBarGUIPanel;
import src.MenuGUIWindow;

import src.Users.*;

/**
 * Raihan
 * 
 * Tenant's menu bar
 */
public class TenantBarGUIPanel extends MenuBarGUIPanel {

    /**
     * Constructor accepting a logged in tenant user
     */
    public TenantBarGUIPanel(MenuGUIWindow parent, User<?> loggedInUser) {
        super(parent, loggedInUser);
    }

    /**
     * Returns the logged in tenant name as the menu bar title
     */
    @Override
    public String getMenuBarTitle() {
        TenantController tenantController = ((TenantGUIWindow)parent).tenantController;
        return tenantController.loggedInTenant.name;
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
