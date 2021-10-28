package src.OwnerAgent;

import src.MenuBarGUIPanel;
import src.MenuGUIWindow;
import src.Users.*;

/**
 * CG
 * 
 * This is the menu bar for the owner/agent panel
 */
public class OwnerAgentBarGUIPanel extends MenuBarGUIPanel {

    /**
     * Constructor receiving a logged in Owner agent user
     * @param parent
     * @param loggedInUser
     */
    public OwnerAgentBarGUIPanel(MenuGUIWindow parent, User<?> loggedInUser) {
        super(parent, loggedInUser);
    }

    /**
     * Returns the logged in user's name as title
     */
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
