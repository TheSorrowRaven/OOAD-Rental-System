package src;

import java.awt.*;

/**
 * Creates the bar and content panel
 */
public final class MenuGUIPanel extends GUIPanel<MenuGUIWindow> {

    public MenuBarGUIPanel menuBar;
    public MenuContentGUIPanel<?> menuContent;

    /**
     * Constructor accepting the menu Window, Menu Bar and Menu Content
     * @param parent
     * @param menuBar
     * @param menuContent
     */
    public MenuGUIPanel(MenuGUIWindow parent, MenuBarGUIPanel menuBar, MenuContentGUIPanel<?> menuContent) {
        super(parent);
        this.menuBar = menuBar;
        this.menuContent = menuContent;
    }

    /**
     * Creates and sets the border layout
     */
    @Override
    public void onCreate() {
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
    }

    /**
     * Attches the menu bar and content to this panel
     */
    @Override
    public void onCreatePanel() {
        attachPanel(menuBar, BorderLayout.PAGE_START);
        attachPanel(menuContent, BorderLayout.CENTER);
    }

    /**
     * Sets itself to be visible on view
     */
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

    /**
     * Calls the on thawed functions on the menu bar and content
     */
    @Override
    public void onThawed() {
        menuBar.onThawed();
        menuContent.onThawed();
    }

    @Override
    public void onDestroy() {
        
    }
    



}
