package src;

/**
 * This defines the menu window
 */
public abstract class MenuGUIWindow extends GUIWindow {

    private MenuGUIPanel menuPanel;
    public MenuController menuController;

    /**
     * Constructor accepting a theme
     * @param theme
     */
    public MenuGUIWindow(src.Resource.Theme theme) {
        super(theme);
        menuController = new MenuController();
    }

    /**
     * Creates the menu panel from menu bar and content
     */
    @Override
    public void onCreatePanel() {
        menuPanel = new MenuGUIPanel(this, getMenuBarPanel(), getContentGUIPanel());
        attachPanel(menuPanel);
    }

    /**
     * Sets itself to be invisible when frozen (hidden/inactive)
     */
    @Override
    public void onFrozen(){
        setVisible(false);
    }

    /**
     * Sets itself to be visible when unfrozen/thawed and calls the thawed function on the menu panel
     */
    @Override
    public void onThawed(){
        setVisible(true);
        menuPanel.onThawed();
    }

    /**
     * Gets the menu bar panel
     * @return
     */
    protected abstract MenuBarGUIPanel getMenuBarPanel();
    /**
     * Gets the menu content panel
     */
    protected abstract MenuContentGUIPanel<?> getContentGUIPanel();

}
