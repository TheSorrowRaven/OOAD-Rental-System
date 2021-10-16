package src;

import java.awt.*;

import src.SystemComponents.CLI;

public final class MenuGUIPanel extends GUIPanel<MenuGUIWindow> {

    public MenuBarGUIPanel menuBar;
    public MenuContentGUIPanel menuContent;

    public MenuGUIPanel(MenuGUIWindow parent, MenuBarGUIPanel menuBar, MenuContentGUIPanel menuContent) {
        super(parent);
        this.menuBar = menuBar;
        this.menuContent = menuContent;
    }

    @Override
    public void onCreate() {
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
    }

    @Override
    public void onCreatePanel() {
        attachPanel(menuBar, BorderLayout.PAGE_START);
        attachPanel(menuContent, BorderLayout.CENTER);
    }

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

    @Override
    public void onThawed() {
        
    }

    @Override
    public void onDestroy() {
        
    }
    



}
