package src.Tenant;

import src.*;
import src.Users.*;

public class TenantGUIWindow extends MenuGUIWindow {

    public TenantController tenantController;

    public TenantGUIWindow(TenantUser loggedInTenant) {
        super(Resource().tenant_theme);
        tenantController = new TenantController(loggedInTenant);
    }

    @Override
    public String getWindowTitle() {
        return Resource().tenant_window_title;
    }

    @Override
    public void onCreate() {
        setSize(Resource().menu_window_width, Resource().menu_window_height);
    }

    @Override
    public void onCreatePanel() {
        super.onCreatePanel();
    }

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

    @Override
    protected MenuBarGUIPanel getMenuBarContentPanel() {
        return new TenantBarGUIPanel(this, tenantController.loggedInTenant);
    }

    @Override
    protected MenuContentGUIPanel<?> getContentGUIPanel() {
        return new TenantGUIPanel(this);
    }
    
}
