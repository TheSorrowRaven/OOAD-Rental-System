package src;

/**
 * Raven
 * 
 * This menu content panel defines the content below the menu bar
 */
public abstract class MenuContentGUIPanel<T extends GUIWindow> extends GUIPanel<T> {

    /**
     * Constructor
     * @param parent
     */
    public MenuContentGUIPanel(T parent) {
        super(parent);
    }

    /**
     * Set itself to be visible on view
     */
    @Override
    public void onView() {
        setVisible(true);
    }
    
}
