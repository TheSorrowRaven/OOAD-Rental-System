package src;

import src.Profile.*;
import src.Users.User;
import java.awt.event.*;

/**
 * Raven
 * 
 * Controller for menu
 */
public class MenuController {

    /**
     * Event for when logout is clicked to go back to login screen
     * @return
     */
    public ActionListener getLogoutAction(){
        return e ->{
            Main.instance().nav.backToLastWindow();
        };
    }

    /**
     * Event for when profile is clicked to go to profile window
     * @param loggedInUser
     * @return
     */
    public ActionListener getProfileAction(User<?> loggedInUser){
        return e -> {
            Navigation nav = Main.instance().nav;
            nav.newWindow(new ProfileGUIWindow(loggedInUser));
        };
    }
    
}
