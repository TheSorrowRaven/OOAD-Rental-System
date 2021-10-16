package src;

public abstract class MenuContentGUIPanel<T extends GUIWindow> extends GUIPanel<T> {

    public MenuContentGUIPanel(T parent) {
        super(parent);
    }

    @Override
    public void onView() {
        setVisible(true);
    }
    
}
