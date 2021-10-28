package src.OwnerAgent;

import src.*;
import src.Users.*;

/**
 * CG
 * 
 * This is the window of the owner agent
 */
public class OwnerAgentGUIWindow extends MenuGUIWindow {

    public OwnerAgentController ownerAgentController;

    /**
     * Constructor receiving a logged in owner/agent user
     * @param loggedInOwnerAgent
     */
    public OwnerAgentGUIWindow(OwnerAgentUser loggedInOwnerAgent) {
        super(Resource().ownerAgent_theme);
        ownerAgentController = new OwnerAgentController(loggedInOwnerAgent);
    }

    /**
     * Returns the title of the owner agent window
     */
    @Override
    public String getWindowTitle() {
        return Resource().ownerAgent_window_title;
    }

    /**
     * Sets the window size
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
     * Returns a new menu bar specific to the owner/agent
     */
    @Override
    protected MenuBarGUIPanel getMenuBarPanel() {
        return new OwnerAgentBarGUIPanel(this, ownerAgentController.loggedInOwnerAgent);
    }

    /**
     * Returns a new content panel for owner/agent
     */
    @Override
    protected MenuContentGUIPanel<?> getContentGUIPanel() {
        return new OwnerAgentGUIPanel(this);
    }
    
}
