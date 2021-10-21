package src.Tenant;

import src.MenuBarGUIPanel;
import src.MenuGUIWindow;
import javax.swing.*;

public class TenantBarGUIPanel extends MenuBarGUIPanel {

    public TenantBarGUIPanel(MenuGUIWindow parent) {
        super(parent);
    }

    @Override
    public void onCreate(){
        super.onCreate();
        ctr.gridx++;
        
    }

    //TODO LEFT? Edit profile, change name?
    //Admin properties
    //Admin set owner/agent
    //owner/agent instructions

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
    public void onThawed() {
        
    }

    @Override
    public void onDestroy() {
        
    }
    
}
