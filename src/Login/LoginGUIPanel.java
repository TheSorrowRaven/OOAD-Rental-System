package src.Login;

import src.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginGUIPanel extends GUIPanel {
    
    public LoginGUIPanel(GUIWindow parentWindow) {
        super(parentWindow);
    }

    @Override
    public void onCreate() {
        
        JButton loginWithTenantButton = new JButton("Login Tenant");
        loginWithTenantButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                parentWindow.onSwitchedOff();
            }
        });
        add(loginWithTenantButton);

    }
    
    @Override
    public void onCreatePanel() {
    }

    @Override
    public void onView() {
        setVisible(true);
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
