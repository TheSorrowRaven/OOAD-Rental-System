package src;

import javax.swing.*;
import java.awt.*;
import src.Users.*;

public abstract class MenuGUIWindow extends GUIWindow {

    private MenuGUIPanel menuPanel;

    public MenuGUIWindow(src.Resource.Theme theme) {
        super(theme);
        
    }

    @Override
    public void onCreatePanel() {
        menuPanel = new MenuGUIPanel(this, getMenuBarContentPanel(), getContentGUIPanel());
        attachPanel(menuPanel);
    }

    @Override
    public void onFrozen(){
        setVisible(false);
    }

    @Override
    public void onThawed(){
        setVisible(true);
        menuPanel.onThawed();
    }

    protected abstract MenuBarGUIPanel getMenuBarContentPanel();
    protected abstract MenuContentGUIPanel<?> getContentGUIPanel();

}
