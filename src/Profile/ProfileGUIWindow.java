package src.Profile;

import src.*;
import src.Users.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class ProfileGUIWindow extends GUIWindow {

    public ProfileController profileController;
    public User<?> loggedInUser;

    public ProfileGUIWindow(User<?> loggedInUser) {
        super(Resource().profile_theme);
        this.loggedInUser = loggedInUser;
        profileController = new ProfileController(this, loggedInUser);
    }

    @Override
    public String getWindowTitle() {
        return Resource().profile_window_title;
    }

    @Override
    public void onCreate() {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowListener(){


            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
                e.getWindow().dispose();
            }

            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {}
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });


        setSize(Resource().profile_window_width, Resource().profile_window_height);
        ProfileGUIPanel profilePanel = new ProfileGUIPanel(this, loggedInUser);
        attachPanel(profilePanel);
    }

    private void closeWindow(){
        profileController.back();
    }

    @Override
    public void onCreatePanel() {
        
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
