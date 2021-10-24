package src;

/**
 * Any class that can attach a panel
 */
public interface IPanelHandler {
    
    /**
     * Attachs a panel to this
     * @param panel
     */
    void attachPanel(GUIPanel<?> panel);
    /**
     * Dettaches a panel from this
     * @param panel
     */
    void detachPanel(GUIPanel<?> panel);

    /**
     * Calls to attach the panel on viewing
     */
    void onViewAttachPanels();

}
