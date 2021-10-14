package src;

public abstract class MenuContentGUIPanel extends GUIPanel<MenuGUIWindow> {

    public MenuContentGUIPanel(MenuGUIWindow parent) {
        super(parent);
    }

    @Override
    public void onView() {
        setVisible(true);
    }
    
}
