package src.Admin;

import javax.swing.JButton;
import javax.swing.JLabel;

import src.*;
import src.SystemComponents.CLI;

public class AdminBarGUIPanel extends MenuBarGUIPanel {

    public AdminBarGUIPanel(MenuGUIWindow parent) {
        super(parent);
    }

    @Override
    public String getMenuBarTitle(){
        return Resource().bar_back_logout;
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public void onCreatePanel(){
        super.onCreatePanel();
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
