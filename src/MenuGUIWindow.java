package src;

import javax.swing.*;
import java.awt.*;

public abstract class MenuGUIWindow extends GUIWindow {

    public MenuGUIWindow(src.Resource.Theme theme) {
        super(theme);
        
    }

    @Override
    public void onCreatePanel() {
        MenuGUIPanel menuPanel = new MenuGUIPanel(this, getMenuBarContentPanel(), getContentGUIPanel());
        attachPanel(menuPanel);
    }

    protected abstract MenuBarGUIPanel getMenuBarContentPanel();
    protected abstract MenuContentGUIPanel getContentGUIPanel();

}
