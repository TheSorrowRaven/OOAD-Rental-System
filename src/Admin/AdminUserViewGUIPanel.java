package src.Admin;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;

import src.*;
import src.Users.*;
import java.util.*;

/**
 * Tasha
 * 
 * This panel shows the user list depending on the type (T)
 */
public class AdminUserViewGUIPanel<T extends User<T>> extends AdminViewGUIPanel implements IOnUsersChangedObserver {

    public boolean isAllUsers;
    public Class<T> handlingUserClass;
    public boolean ownerAgent;

    public int deletionTickboxColumn;

    public Table table;
    public ArrayList<User<?>> users = new ArrayList<User<?>>();

    /**
     * Constructor
     */
    public AdminUserViewGUIPanel(AdminGUIWindow parent, Class<T> userClass) {
        super(parent);
        handlingUserClass = userClass;
    }
    /**
     * Constructor specific for owner agents
     */
    public AdminUserViewGUIPanel(AdminGUIWindow parent, Class<T> userClass, boolean ownerAgent) {
        super(parent);
        handlingUserClass = userClass;
        if (handlingUserClass == OwnerAgentUser.class){
            this.ownerAgent = ownerAgent;
        }
    }
    /**
     * Constructor for all users
     */
    public AdminUserViewGUIPanel(AdminGUIWindow parent){
        super(parent);
        isAllUsers = true;
    }

    /**
     * Returns the handling table
     * @return
     */
    public Table getTable(){
        return table;
    }
    /**
     * Returns the handling users
     */
    public ArrayList<User<?>> getUsers(){
        return users;
    }

    /**
     * Creates the interface, and mainly the table
     */
    @Override
    public void onCreate() {

        super.onCreate();

        parent.adminController.observe(this);

        if (!isAllUsers){

            AdminCreateAccountGUIPanel<T> createAccountPanel = new AdminCreateAccountGUIPanel<T>(parent, handlingUserClass, ownerAgent);
            add(createAccountPanel, gbc);
            executePanelLifecycle(createAccountPanel);
            gbc.gridy++;
        }

        JLabel deleteTitleLabel = JLabel(Resource().admin_str_content_deletion_title);
        add(deleteTitleLabel, gbc);
        gbc.gridy++;
        JLabel deleteInstructionsLabel = JLabel(Resource().admin_str_content_deletion_instructions);
        deleteInstructionsLabel.setFont(Resource().general_font_minor);
        add(deleteInstructionsLabel, gbc);
        gbc.gridy++;

        JButton deleteButton = Button(Resource().admin_str_content_deletion_button);
        deleteButton.addActionListener(parent.adminController.getDeleteSelectedActionListener(this));
        add(deleteButton, gbc);
        gbc.gridy++;

        gbc.fill = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        table = JTable();
        table.setRowHeight(Resource().table_cell_height);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFillsViewportHeight(true);
        refreshTable();

        JScrollPane scrollPane = JScrollPane(table);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, gbc);
    }

    /**
     * Old
     * When re-viewed, refresh the table
     */
    @Override
    public void onThawed() {
        super.onThawed();
        refreshTable();
    }

    /**
     * Releases the cache for this as the observer
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        parent.adminController.stopObserving(this);
    }

    /**
     * Refreshes the table in case of any updates
     */
    public void refreshTable(){
        if (isAllUsers){
            Admin.AllUsers allUsers = parent.adminController.fetchAllUsers();
            refreshTableAll(allUsers.allUsers);
        }
        else{
            ArrayList<T> array = parent.adminController.fetchAllUsersOfType(handlingUserClass);
            refreshTable(array);
        }
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
    }
    /**
     * Refreshes the table based on the specific user types
     * @param array
     */
    private void refreshTable(ArrayList<T> array){
        users.clear();
        for (T user : array){
            users.add(user);
        }
        DefaultTableModel model = (DefaultTableModel)(table.getModel());
        model.setRowCount(0);

        boolean isOwnerAgent = handlingUserClass == OwnerAgentUser.class;
        T sample = User.getNewUserFromClass(handlingUserClass);
        int columns = sample.getTableDisplayColumns();
        model.setColumnCount(columns + 1);
        var columnModel = table.getColumnModel();
        ArrayList<String> columnHeaders = sample.getColumnHeaders(ownerAgent);
        int c = 0;
        for (; c < columns; c++){
            table.getColumnModel().getColumn(c).setHeaderValue(columnHeaders.get(c));
        }
        columnModel.getColumn(c).setHeaderValue(Resource().admin_str_content_deletion_table_header);
        table.setEditableColumnClass(c, Boolean.class);
        deletionTickboxColumn = c;

        if (array.size() != 0){
            for (int i = 0; i < array.size(); i++){
                T user = array.get(i);
                if (isOwnerAgent){
                    OwnerAgentUser ownerAgentUser = (OwnerAgentUser)user;
                    if (ownerAgentUser.isOwner != ownerAgent){
                        continue;
                    }
                }
                ArrayList<Object> userDisplayColumns = user.getTableDisplayColumnsData();
                addDeletionToArrayList(userDisplayColumns);
                model.addRow(userDisplayColumns.toArray());
            }
        }
    }
    /**
     * Same as refreshTable, with many slight changes
     * Because displaying all users as a general abstract class
     * This function has to be separated due to its difference especially in overrides
     * @param array
     */
    private void refreshTableAll(ArrayList<User<?>> array){
        users = array;
        DefaultTableModel model = (DefaultTableModel)(table.getModel());
        model.setRowCount(0);

        int columns = User.getBaseTableDisplayColumns();
        model.setColumnCount(columns + 1);
        var columnModel = table.getColumnModel();
        ArrayList<String> columnHeaders = User.getBaseColumnHeaders();
        int c = 0;
        for (; c < columns; c++){
            table.getColumnModel().getColumn(c).setHeaderValue(columnHeaders.get(c));
        }
        columnModel.getColumn(c).setHeaderValue(Resource().admin_str_content_deletion_table_header);
        table.setEditableColumnClass(c, Boolean.class);
        deletionTickboxColumn = c;

        if (array.size() != 0){
            for (User<?> user : array){
                ArrayList<Object> userDisplayColumns = user.getBaseTableDisplayColumnsData();
                addDeletionToArrayList(userDisplayColumns);
                model.addRow(userDisplayColumns.toArray());
            }
        }
    }
    /**
     * Adds the checkbox to the table for deleting
     */
    private void addDeletionToArrayList(ArrayList<Object> array){
        array.add(false);
    }

    /**
     * Receiver for observing admin (observable), and refresh if has any changes
     */
    @Override
    public <E extends User<E>> void usersChanged() {
        refreshTable();
    }
    
}
