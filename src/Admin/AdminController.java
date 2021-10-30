package src.Admin;

import src.*;
import src.Admin.Admin.UserCreationResult;
import src.Users.*;
import java.awt.event.ActionListener;
import java.util.*;
import src.Property.*;

import javax.swing.JTextField;

/**
 * Tasha
 * 
 * This class is a Controller for Admin
 * Handles events and fetch component data from the view
 */
public class AdminController extends PropertyController implements IOnUsersChangedObservable {
    
    /**
     * Shorthand for Resource singleton
     */
    public static Resource Resource(){
        return Resource.instance();
    }


    public AdminUser adminUser;
    public Admin admin;

    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField nameField;

    /**
     * Constructor receiving a logged in admin user
     * @param loggedInAdmin
     */
    public AdminController(AdminUser loggedInAdmin){
        adminUser = loggedInAdmin;
        admin = new Admin(adminUser);
    }

    /**
     * View all users (including tenants, owner/agent, admin) event
     * @param adminSubMenuGUIPanel
     * @return
     */
    public ActionListener getViewAllUsersAction(final AdminSubMenuGUIPanel adminSubMenuGUIPanel){
        return e -> {
            AdminUserViewGUIPanel<?> switchingPanel = adminSubMenuGUIPanel.getAllUsersTablePanel();
            switchPanels(adminSubMenuGUIPanel, switchingPanel);
        };
    }
    /**
     * View all tenants event
     * @param adminSubMenuGUIPanel
     * @return
     */
    public ActionListener getViewTenantsAction(final AdminSubMenuGUIPanel adminSubMenuGUIPanel){
        return e -> {
            AdminUserViewGUIPanel<?> switchingPanel = adminSubMenuGUIPanel.getTenantsTablePanel();
            switchPanels(adminSubMenuGUIPanel, switchingPanel);
        };
    }
    /**
     * View all owners event
     * @param adminSubMenuGUIPanel
     * @return
     */
    public ActionListener getViewOwnersAction(final AdminSubMenuGUIPanel adminSubMenuGUIPanel){
        return e -> {
            AdminUserViewGUIPanel<?> switchingPanel = adminSubMenuGUIPanel.getOwnersTablePanel();
            switchPanels(adminSubMenuGUIPanel, switchingPanel);
        };
    }
    /**
     * View all agents event
     * @param adminSubMenuGUIPanel
     * @return
     */
    public ActionListener getViewAgentsActionListener(final AdminSubMenuGUIPanel adminSubMenuGUIPanel){
        return e -> {
            AdminUserViewGUIPanel<?> switchingPanel = adminSubMenuGUIPanel.getAgentsTablePanel();
            switchPanels(adminSubMenuGUIPanel, switchingPanel);
        };
    }
    /**
     * View all admins event
     * @param adminSubMenuGUIPanel
     * @return
     */
    public ActionListener getViewAdminsActionListener(final AdminSubMenuGUIPanel adminSubMenuGUIPanel){
        return e -> {
            AdminUserViewGUIPanel<?> switchingPanel = adminSubMenuGUIPanel.getAdminsTablePanel();
            switchPanels(adminSubMenuGUIPanel, switchingPanel);
        };
    }
    /**
     * View all properties event
     * @param adminSubMenuGUIPanel
     * @return
     */
    public ActionListener getViewPropertiesActionListener(final AdminSubMenuGUIPanel adminSubMenuGUIPanel){
        return e -> {
            AdminPropertyViewGUIPanel switchingPanel = adminSubMenuGUIPanel.getPropertiesTablePanel();
            switchPanels(adminSubMenuGUIPanel, switchingPanel);
        };
    }

    /**
     * Switches the content panel from one to another
     */
    private void switchPanels(final AdminSubMenuGUIPanel adminSubMenuGUIPanel, AdminViewGUIPanel switchingPanel){
        GUIPanel<?> currentPanel = adminSubMenuGUIPanel.getCurrentTablePanel();
        if (!currentPanel.equals(switchingPanel)){
            adminSubMenuGUIPanel.switchTableView(switchingPanel);
            return;
        }
    }

    /**
     * Gets all users of a specific type (call to model)
     */
    public <T extends User<T>> ArrayList<T> fetchAllUsersOfType(Class<T> userClass){
        return admin.fetchAllUsersOfType(userClass);
    }
    /**
     * Gets all users (call to model)
     */
    public Admin.AllUsers fetchAllUsers(){
        Admin.AllUsers allUsers = admin.fetchAllUsers();
        return allUsers;
    }

    /**
     * Event for delete selected users (checkmarked)
     * @param <T> User
     * @param adminTable
     * @return
     */
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
            src.SystemComponents.CLI.log("Deleting " + deletingUsers.size());
            admin.deleteAllAnyUsers(deletingUsers);
            createDeleteRefreshCallback();
        };
    }


    /**
     * Prepares the fields to get from in creation
     * @param usernameField
     * @param passwordField
     * @param nameField
     */
    public void setCreateAccountTextFields(JTextField usernameField, JTextField passwordField, JTextField nameField){
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.nameField = nameField;
    }

    /**
     * Event for create account is clicked to create the account (calls to model)
     * @param <T>
     * @param adminCreateAccPanel
     * @param userClass
     * @param isOwnerAgent
     * @return
     */
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


    /**
     * Event for deleting properties (calls to model)
     * @return
     */
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
            src.SystemComponents.CLI.log("Deleting " + deletingProps.size());
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