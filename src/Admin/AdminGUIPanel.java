package src.Admin;

import src.*;
import src.SystemComponents.CLI;

import java.awt.*;

public class AdminGUIPanel extends MenuContentGUIPanel<AdminGUIWindow> {

    public AdminGUIPanel(AdminGUIWindow parent) {
        super(parent);
    }

    @Override
    public void onCreate() {
        GridLayout grid = new GridLayout(0, 1);
        setLayout(grid);
        //BorderLayout borderLayout = new BorderLayout();
        //setLayout(borderLayout);//Fill Parent
    }

    @Override
    public void onCreatePanel() {
        AdminSubMenuGUIPanel subMenuPanel = new AdminSubMenuGUIPanel(parent);
        attachPanel(subMenuPanel);
    }

    @Override
    public void onPreparingToFreeze() {
        
    }

    @Override
    public void onView(){
        
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
