package src.Admin;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;

import src.*;
import src.SystemComponents.CLI;
import src.Users.*;
import java.util.*;

public class AdminTableViewGUIPanel<T extends User<T>> extends GUIPanel<AdminGUIWindow> implements IOnUsersChangedObserver {

    public boolean isAllUsers;
    public Class<T> handlingUserClass;
    public boolean ownerAgent;

    public String title;
    public ArrayList<T> users;
    public ArrayList<User<?>> allUsers;

    public JTable table;

    public AdminTableViewGUIPanel(AdminGUIWindow parent, Class<T> userClass) {
        super(parent);
        handlingUserClass = userClass;
    }
    public AdminTableViewGUIPanel(AdminGUIWindow parent, Class<T> userClass, boolean ownerAgent) {
        super(parent);
        handlingUserClass = userClass;
        if (handlingUserClass == OwnerAgentUser.class){
            this.ownerAgent = ownerAgent;
        }
    }
    public AdminTableViewGUIPanel(AdminGUIWindow parent){
        super(parent);
        isAllUsers = true;
    }

    public void initializeTitle(String title){
        this.title = title;
    }
    
    public void setTable(ArrayList<T> users){
        this.users = users;
    }
    public void setTableAll(ArrayList<User<?>> allUsers){
        this.allUsers = allUsers;
    }

    @Override
    public void onCreate() {

        parent.admin.observe(this);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = Resource().general_inset_all;

        JLabel titleLabel = JLabel(this.title);
        titleLabel.setFont(Resource().general_font_title);
        add(titleLabel, gbc);
        gbc.gridy++;

        table = JTable();
        table.setRowHeight(Resource().table_cell_height);
        refreshTable();

        JScrollPane scrollPanel = new JScrollPane(table);
        add(scrollPanel, gbc);

        CLI.log("CREATE: " + title);
        
    }

    @Override
    public void onCreatePanel() {
        
    }

    @Override
    public void onView() {
        
    }

    @Override
    public void onPreparingToFreeze() {
        
    }

    @Override
    public void onFrozen() {
    }

    @Override
    public void onThawed() {
        refreshTable();
    }

    @Override
    public void onDestroy() {
        
    }

    public void refreshTable(){
        if (isAllUsers){
            Admin.AllUsers allUsers = parent.admin.fetchAllUsers();
            refreshTableAll(allUsers.allUsers);
            return;
        }
        ArrayList<T> array = parent.admin.fetchAllUsersOfType(handlingUserClass);
        refreshTable(array);
        return;
    }
    private void refreshTable(ArrayList<T> array){
        DefaultTableModel model = (DefaultTableModel)(table.getModel());
        model.setRowCount(0);

        T sample = User.getNewUserFromClass(handlingUserClass);
        int columns = sample.getTableDisplayColumns();
        model.setColumnCount(columns);
        for (int i = 0; i < columns; i++){
            table.getColumnModel().getColumn(i).setHeaderValue("headerrrrrrrrr");
        }
        CLI.log("Header set");


        if (array.size() == 0){
            return;
        }
        for (int i = 0; i < array.size(); i++){
            T user = array.get(i);
            ArrayList<Object> userDisplayColumns = user.getTableDisplayColumnsData();
            model.addRow(userDisplayColumns.toArray());
            CLI.log("Row added");
        }
    }
    private void refreshTableAll(ArrayList<User<?>> array){
        DefaultTableModel model = (DefaultTableModel)(table.getModel());
        model.setRowCount(0);

        if (array.size() == 0){
            return;
        }
        User<?> sampleUser = array.get(0);
        model.setColumnCount(sampleUser.getBaseTableDisplayColumns());
        for (User<?> user : array){
            ArrayList<Object> userDisplayColumns = user.getBaseTableDisplayColumnsData();
            model.addRow(userDisplayColumns.toArray());
        }
    }

    @Override
    public <E extends User<E>> void usersChanged(Class<E> userClass) {
        if (userClass == handlingUserClass){
            //TODO
        }
    }
    
}
