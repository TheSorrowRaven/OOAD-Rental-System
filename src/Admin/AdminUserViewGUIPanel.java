package src.Admin;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;

import src.*;
import src.SystemComponents.CLI;
import src.Users.*;
import java.util.*;

public class AdminUserViewGUIPanel<T extends User<T>> extends GUIPanel<AdminGUIWindow> implements IOnUsersChangedObserver {

    public boolean isAllUsers;
    public Class<T> handlingUserClass;
    public boolean ownerAgent;

    public String title;

    public int deletionTickboxColumn;

    public Table table;
    public ArrayList<User<?>> users = new ArrayList<User<?>>();

    public AdminUserViewGUIPanel(AdminGUIWindow parent, Class<T> userClass) {
        super(parent);
        handlingUserClass = userClass;
    }
    public AdminUserViewGUIPanel(AdminGUIWindow parent, Class<T> userClass, boolean ownerAgent) {
        super(parent);
        handlingUserClass = userClass;
        if (handlingUserClass == OwnerAgentUser.class){
            this.ownerAgent = ownerAgent;
        }
    }
    public AdminUserViewGUIPanel(AdminGUIWindow parent){
        super(parent);
        isAllUsers = true;
    }

    public void initializeTitle(String title){
        this.title = title;
    }

    public Table getTable(){
        return table;
    }
    public ArrayList<User<?>> getUsers(){
        return users;
    }

    @Override
    public void onCreate() {

        parent.adminController.observe(this);

        setBackground(Theme().panel_background_color);
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Theme().background_color);
        JScrollPane panelScroll = JScrollPane(panel);
        panelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelScroll.getVerticalScrollBar().setUnitIncrement(Resource().scroll_speed);
        add(panelScroll);

        setTargetPanel(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = Resource().general_inset_all;
        gbc.anchor = GridBagConstraints.CENTER;


        JLabel titleLabel = JLabel(this.title);
        titleLabel.setFont(Resource().general_font_title);
        add(titleLabel, gbc);
        gbc.gridy++;


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

        //gbc.anchor = GridBagConstraints.EAST;
        JButton deleteButton = Button(Resource().admin_str_content_deletion_button);
        deleteButton.addActionListener(parent.adminController.getDeleteSelectedActionListener(this));
        add(deleteButton, gbc);
        gbc.gridy++;

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
        parent.adminController.stopObserving(this);
    }

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
        ArrayList<String> columnHeaders = sample.getColumnHeaders();
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
    private void addDeletionToArrayList(ArrayList<Object> array){
        array.add(false);
    }

    @Override
    public <E extends User<E>> void usersChanged() {
        refreshTable();
    }
    
}
