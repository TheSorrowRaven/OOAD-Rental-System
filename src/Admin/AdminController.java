package src.Admin;

import src.*;
import src.Admin.Admin.UserCreationResult;
import src.Login.ILoginnable;
import src.Users.*;
import src.SystemComponents.*;
import java.awt.event.ActionListener;
import java.util.*;
import src.Property.*;

import javax.swing.JTextField;

public class AdminController extends PropertyController implements IOnUsersChangedObservable {
    
    public Resource Resource(){
        return Resource.instance();
    }


    public AdminUser adminUser;
    public Admin admin;

    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField nameField;

    public AdminController(AdminUser loggedInAdmin){
        adminUser = loggedInAdmin;
        admin = new Admin(adminUser);
    }

    public ActionListener getViewAllUsersAction(final AdminSubMenuGUIPanel adminSubMenuGUIPanel){
        return e -> {
            AdminUserViewGUIPanel<?> switchingPanel = adminSubMenuGUIPanel.getAllUsersTablePanel();
            switchPanels(adminSubMenuGUIPanel, switchingPanel);
        };
    }
    public ActionListener getViewTenantsAction(final AdminSubMenuGUIPanel adminSubMenuGUIPanel){
        return e -> {
            AdminUserViewGUIPanel<?> switchingPanel = adminSubMenuGUIPanel.getTenantsTablePanel();
            switchPanels(adminSubMenuGUIPanel, switchingPanel);
        };
    }
    public ActionListener getViewOwnersAction(final AdminSubMenuGUIPanel adminSubMenuGUIPanel){
        return e -> {
            AdminUserViewGUIPanel<?> switchingPanel = adminSubMenuGUIPanel.getOwnersTablePanel();
            switchPanels(adminSubMenuGUIPanel, switchingPanel);
        };
    }
    public ActionListener getViewAgentsActionListener(final AdminSubMenuGUIPanel adminSubMenuGUIPanel){
        return e -> {
            AdminUserViewGUIPanel<?> switchingPanel = adminSubMenuGUIPanel.getAgentsTablePanel();
            switchPanels(adminSubMenuGUIPanel, switchingPanel);
        };
    }
    public ActionListener getViewAdminsActionListener(final AdminSubMenuGUIPanel adminSubMenuGUIPanel){
        return e -> {
            AdminUserViewGUIPanel<?> switchingPanel = adminSubMenuGUIPanel.getAdminsTablePanel();
            switchPanels(adminSubMenuGUIPanel, switchingPanel);
        };
    }
    public ActionListener getViewPropertiesActionListener(final AdminSubMenuGUIPanel adminSubMenuGUIPanel){
        return e -> {
            AdminPropertyViewGUIPanel switchingPanel = adminSubMenuGUIPanel.getPropertiesTablePanel();
            switchPanels(adminSubMenuGUIPanel, switchingPanel);
        };
    }

    private void switchPanels(final AdminSubMenuGUIPanel adminSubMenuGUIPanel, AdminViewGUIPanel switchingPanel){
        GUIPanel<?> currentPanel = adminSubMenuGUIPanel.getCurrentTablePanel();
        if (!currentPanel.equals(switchingPanel)){
            adminSubMenuGUIPanel.switchTableView(switchingPanel);
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


    public <T extends User<T>> ActionListener getDeleteSelectedActionListener(final AdminUserViewGUIPanel<T> adminTable){
        return e -> {
            Table table = adminTable.getTable();
            ArrayList<User<?>> users = adminTable.getUsers();
            ArrayList<User<?>> deletingUsers = new ArrayList<User<?>>();
            for (int i = 0; i < table.getRowCount(); i++){
                boolean isTicked = (Boolean)table.getValueAt(i, adminTable.deletionTickboxColumn);
                if (isTicked){
                    deletingUsers.add(users.get(i));
                }
            }
            admin.deleteAllAnyUsers(deletingUsers);
            createDeleteRefreshCallback();
        };
    }



    public void setCreateAccountTextFields(JTextField usernameField, JTextField passwordField, JTextField nameField){
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.nameField = nameField;
    }

    public <T extends User<T>> ActionListener getCreateAccountActionListener(final AdminCreateAccountGUIPanel<T> adminCreateAccPanel, final Class<T> userClass, final boolean isOwnerAgent){
        return e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String name = nameField.getText();
            UserCreationResult result = admin.createAccount(userClass, username, password, name, isOwnerAgent);
            if (result.isSuccessful()){
                adminCreateAccPanel.finishCreateClear();
            }
            else{
                adminCreateAccPanel.failedCreate();
            }
            createDeleteRefreshCallback();
        };
    }



    public ActionListener getDeletePropertiesActionListener(){
        return e -> {
            Table table = tablePanel.table;
            ArrayList<PropertyListing> properties = tablePanel.properties;
            ArrayList<PropertyListing> deletingProps = new ArrayList<PropertyListing>();
            for (int i = 0; i < table.getRowCount(); i++){
                boolean isTicked = (Boolean)table.getValueAt(i, tablePanel.deletionTickboxColumn);
                if (isTicked){
                    deletingProps.add(properties.get(i));
                }
            }
            admin.deleteProperties(deletingProps);
            tablePanel.refreshTable();
        };
    }





    /**
     * Whenever a user is created or deleted, call this function to refresh the tables
     * @param <T>
     * @param userClass
     */
    public <T extends User<T>> void createDeleteRefreshCallback(){
        usersChangedNotify();
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
    public <T extends User<T>> void usersChangedNotify() {
        for (IOnUsersChangedObserver observer : observers){
            observer.usersChanged();
        }
    }


    

}

interface IOnUsersChangedObserver{
    <T extends User<T>> void usersChanged();
}
interface IOnUsersChangedObservable{
    
    void observe(IOnUsersChangedObserver observer);
    void stopObserving(IOnUsersChangedObserver observer);
    <T extends User<T>> void usersChangedNotify();

}