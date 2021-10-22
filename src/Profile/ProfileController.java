package src.Profile;

import src.Users.*;
import src.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ProfileController {

    public Profile profile;
    public User<?> loggedInUser;
    public ProfileGUIWindow profileWindow;
    public ProfileGUIPanel profilePanel;

    public ProfileController(ProfileGUIWindow profileWindow, User<?> loggedInUser){
        this.loggedInUser = loggedInUser;
        this.profileWindow = profileWindow;
        profile = new Profile();
    }

    public void setProfilePanel(ProfileGUIPanel profilePanel){
        this.profilePanel = profilePanel;
    }

    public void setCurrentNamePassword(){
        profilePanel.getChangeNameField().setText(loggedInUser.name);
        profilePanel.getChangePasswordField().setText(loggedInUser.password);
    }

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

    public ActionListener getGoBackAction(){
        return e ->{
            back();
        };
    }

    public void back(){
        Main.instance().nav.backToLastWindow();
    }

    public void successNotify(){

    }
    public void failureNotify(){

    }

}
