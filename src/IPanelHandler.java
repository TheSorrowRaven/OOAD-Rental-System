package src;

/**
 * Any class that can attach a panel
 */
public interface IPanelHandler {
    
    void attachPanel(GUIPanel<?> panel);
    void detachPanel(GUIPanel<?> panel);

    void onViewAttachPanels();

}
