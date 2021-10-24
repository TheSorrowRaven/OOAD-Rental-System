package src.Profile;

import src.*;
import src.Users.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * The window view for Profile
 */
public class ProfileGUIWindow extends GUIWindow {

    public ProfileController profileController;
    public User<?> loggedInUser;

    /**
     * Constructor accepting a logged in user
     * @param loggedInUser
     */
    public ProfileGUIWindow(User<?> loggedInUser) {
        super(Resource().profile_theme);
        this.loggedInUser = loggedInUser;
        profileController = new ProfileController(this, loggedInUser);
    }

    /**
     * Returns the profile window title retrieved from resource
     */
    @Override
    public String getWindowTitle() {
        return Resource().profile_window_title;
    }

    /**
     * Creates the components to display
     */
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

    /**
     * Use close window to navigate back instead of shutting down the program
     */
    private void closeWindow(){
        profileController.back();
    }

    @Override
    public void onCreatePanel() {
        
    }

    /**
     * Sets the window visible
     */
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
