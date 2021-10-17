package src.OwnerAgent;

import src.MenuBarGUIPanel;
import src.MenuGUIWindow;

public class OwnerAgentBarGUIPanel extends MenuBarGUIPanel {

    public OwnerAgentBarGUIPanel(MenuGUIWindow parent) {
        super(parent);
    }

    @Override
    public String getMenuBarTitle() {
        return Resource().bar_back_logout;
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
