package src.Admin;

import src.*;
import src.Login.ILoginnable;
import src.Users.*;
import src.SystemComponents.*;
import java.awt.event.ActionListener;
import java.util.*;

public class AdminController implements IOnUsersChangedObservable {
    
    public Resource Resource(){
        return Resource.instance();
    }


    public AdminUser adminUser;
    public Admin admin;

    public AdminController(AdminUser loggedInAdmin){
        adminUser = loggedInAdmin;
        admin = new Admin(adminUser);
    }

    public ActionListener getViewAllUsersAction(final AdminSubMenuGUIPanel adminSubMenuGUIPanel){
        return e -> {
            AdminTableViewGUIPanel<?> switchingPanel = adminSubMenuGUIPanel.getAllUsersTablePanel();
            switchPanels(adminSubMenuGUIPanel, switchingPanel);
        };
    }
    public ActionListener getViewTenantsAction(final AdminSubMenuGUIPanel adminSubMenuGUIPanel){
        return e -> {
            AdminTableViewGUIPanel<?> switchingPanel = adminSubMenuGUIPanel.getTenantsTablePanel();
            switchPanels(adminSubMenuGUIPanel, switchingPanel);
        };
    }
    public ActionListener getViewOwnersAction(final AdminSubMenuGUIPanel adminSubMenuGUIPanel){
        return e -> {
            AdminTableViewGUIPanel<?> switchingPanel = adminSubMenuGUIPanel.getOwnersTablePanel();
            switchPanels(adminSubMenuGUIPanel, switchingPanel);
        };
    }
    public ActionListener getViewAgentsActionListener(final AdminSubMenuGUIPanel adminSubMenuGUIPanel){
        return e -> {
            AdminTableViewGUIPanel<?> switchingPanel = adminSubMenuGUIPanel.getAgentsTablePanel();
            switchPanels(adminSubMenuGUIPanel, switchingPanel);
        };
    }
    public ActionListener getViewAdminsActionListener(final AdminSubMenuGUIPanel adminSubMenuGUIPanel){
        return e -> {
            AdminTableViewGUIPanel<?> switchingPanel = adminSubMenuGUIPanel.getAdminsTablePanel();
            switchPanels(adminSubMenuGUIPanel, switchingPanel);
        };
    }

    private void switchPanels(final AdminSubMenuGUIPanel adminSubMenuGUIPanel, AdminTableViewGUIPanel<?> switchingPanel){
        AdminTableViewGUIPanel<?> currentPanel = adminSubMenuGUIPanel.getCurrentTablePanel();
        if (!currentPanel.equals(switchingPanel)){
            adminSubMenuGUIPanel.switchTableView(switchingPanel);
            //adminSubMenuGUIPanel.switchExistingPanels(currentPanel, switchingPanel);
            //adminSubMenuGUIPanel.setCurrentTablePanel(switchingPanel);
            return;
        }
    }

    public <T extends User<T>> ArrayList<T> fetchAllUsersOfType(Class<T> userClass){
        return admin.fetchAllUsersOfType(userClass);
    }
    public Admin.AllUsers fetchAllUsers(){
        Admin.AllUsers allUsers = admin.fetchAllUsers();
        return allUsers;
    }

    /**
     * Whenever a user is created or deleted, call this function to refresh the tables
     * @param <T>
     * @param userClass
     */
    public <T extends User<T>> void createDeleteRefreshCallback(Class<T> userClass){
        usersChangedNotify(userClass);
    }

    private HashSet<IOnUsersChangedObserver> observers = new HashSet<IOnUsersChangedObserver>();

    @Override
    public void observe(IOnUsersChangedObserver observer) {
        observers.add(observer);
    }

    @Override
    public void stopObserving(IOnUsersChangedObserver observer) {
        observers.remove(observer);
    }

    @Override
    public <T extends User<T>> void usersChangedNotify(Class<T> userClass) {
        for (IOnUsersChangedObserver observer : observers){
            observer.usersChanged(userClass);
        }
    }
    

}

interface IOnUsersChangedObserver{
    <T extends User<T>> void usersChanged(Class<T> userClass);
}
interface IOnUsersChangedObservable{
    
    void observe(IOnUsersChangedObserver observer);
    void stopObserving(IOnUsersChangedObserver observer);
    <T extends User<T>> void usersChangedNotify(Class<T> userClass);

}