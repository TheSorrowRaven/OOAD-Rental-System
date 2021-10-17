package src.OwnerAgent;

import src.*;
import src.Users.*;

public class OwnerAgentGUIWindow extends MenuGUIWindow {

    public OwnerAgentController ownerAgentController;

    public OwnerAgentGUIWindow(OwnerAgentUser loggedInOwnerAgent) {
        super(Resource().ownerAgent_theme);
        ownerAgentController = new OwnerAgentController(loggedInOwnerAgent);
    }

    @Override
    public String getWindowTitle() {
        return Resource().ownerAgent_window_title;
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
    public void onFrozen() {
        
    }

    @Override
    public void onThawed() {
        
    }

    @Override
    public void onDestroy() {
        
    }

    @Override
    protected MenuBarGUIPanel getMenuBarContentPanel() {
        return new OwnerAgentBarGUIPanel(this);
    }

    @Override
    protected MenuContentGUIPanel<?> getContentGUIPanel() {
        return new OwnerAgentGUIPanel(this);
    }
    
}
