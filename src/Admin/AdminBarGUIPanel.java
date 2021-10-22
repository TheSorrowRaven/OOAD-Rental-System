package src.Admin;

import javax.swing.JButton;
import javax.swing.JLabel;

import src.*;
import src.SystemComponents.CLI;
import src.Users.*;

public class AdminBarGUIPanel extends MenuBarGUIPanel {

    public AdminBarGUIPanel(MenuGUIWindow parent, User<?> loggedInUser) {
        super(parent, loggedInUser);
    }

    @Override
    public String getMenuBarTitle(){
        AdminController adminController = ((AdminGUIWindow)parent).adminController;
        return adminController.adminUser.name;
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
    public void onDestroy() {
        
    }
    
}
