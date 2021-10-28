package src.Profile;

import src.Users.*;
import src.*;

import java.awt.event.*;

/**
 * CG
 * 
 * This the Controller for Profile
 * This handles the save button event and returning to the menu
 */
public class ProfileController {

    public Profile profile;
    public User<?> loggedInUser;
    public ProfileGUIWindow profileWindow;
    public ProfileGUIPanel profilePanel;

    /**
     * Constructor accpeting the profile window and a logged in user
     */
    public ProfileController(ProfileGUIWindow profileWindow, User<?> loggedInUser){
        this.loggedInUser = loggedInUser;
        this.profileWindow = profileWindow;
        profile = new Profile();
    }

    /**
     * Assigns the profile panel to retrieve its fields later
     */
    public void setProfilePanel(ProfileGUIPanel profilePanel){
        this.profilePanel = profilePanel;
    }

    /**
     * Sets the current name and password to show on the text fields
     */
    public void setCurrentNamePassword(){
        profilePanel.getChangeNameField().setText(loggedInUser.name);
        profilePanel.getChangePasswordField().setText(loggedInUser.password);
    }

    /**
     * Event for saving the changes, and goes back to the menu
     * @return
     */
    public ActionListener getSaveActionListener(){
        return e -> {
            String name = profilePanel.getChangeNameField().getText();
            String password = profilePanel.getChangePasswordField().getText();

            boolean success = profile.changeNamePasswordOf(loggedInUser, name, password);
            if (success){
                successNotify();
            }
            else{
                failureNotify();
            }
            back();
        };
    }

    /**
     * Event for going back to the menu without changes
     * @return
     */
    public ActionListener getGoBackAction(){
        return e ->{
            back();
        };
    }

    /**
     * Navigate back to the menu
     */
    public void back(){
        Main.instance().nav.backToLastWindow();
    }

    /**
     * When successfully changed, notify (not used)
     */
    public void successNotify(){

    }
    /**
     * When failed to change (IO error), notify (not used)
     */
    public void failureNotify(){

    }

}
