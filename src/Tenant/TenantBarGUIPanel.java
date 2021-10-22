package src.Tenant;

import src.MenuBarGUIPanel;
import src.MenuGUIWindow;
import src.SystemComponents.CLI;

import javax.swing.*;
import src.Users.*;

public class TenantBarGUIPanel extends MenuBarGUIPanel {

    public TenantBarGUIPanel(MenuGUIWindow parent, User<?> loggedInUser) {
        super(parent, loggedInUser);
    }

    @Override
    public void onCreate(){
        super.onCreate();
        gbc.gridx++;
        
    }

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
