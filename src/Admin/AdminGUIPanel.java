package src.Admin;

import javax.swing.*;

import src.*;
import src.SystemComponents.CLI;

public class AdminGUIPanel extends MenuContentGUIPanel {

    public AdminGUIPanel(AdminGUIWindow parent) {
        super(parent);
    }

    @Override
    public void onCreate() {
        JLabel text = JLabel("CONTENT");
        add(text);
    }

    @Override
    public void onCreatePanel() {
        
    }

    @Override
    public void onPreparingToSwitch() {
        
    }

    @Override
    public void onSwitchedOff() {
        
    }

    @Override
    public void onSwitchedIn() {
        
    }

    @Override
    public void onDestroy() {
        
    }
    
}
