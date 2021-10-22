package src.OwnerAgent;

import src.MenuBarGUIPanel;
import src.MenuGUIWindow;
import src.Users.*;

public class OwnerAgentBarGUIPanel extends MenuBarGUIPanel {

    public OwnerAgentBarGUIPanel(MenuGUIWindow parent, User<?> loggedInUser) {
        super(parent, loggedInUser);
    }

    @Override
    public String getMenuBarTitle() {
        OwnerAgentController ownerAgentController = ((OwnerAgentGUIWindow)parent).ownerAgentController;
        return ownerAgentController.loggedInOwnerAgent.name;
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
