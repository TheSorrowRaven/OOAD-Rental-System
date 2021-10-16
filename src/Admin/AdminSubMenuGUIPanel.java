package src.Admin;

import src.*;
import src.Users.*;
import src.SystemComponents.CLI;

import javax.swing.*;
import java.awt.*;

public class AdminSubMenuGUIPanel extends GUIPanel<AdminGUIWindow> {

    protected GridBagConstraints gbc;

    public AdminSubMenuGUIPanel(AdminGUIWindow parent) {
        super(parent);
    }

    @Override
    public void onCreate() {
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        setBackground(Theme().panel_background_color);

        JPanel gridBagPanel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagPanel.setLayout(gridBagLayout);
        gridBagPanel.setBackground(Resource().color_invisible);

        add(gridBagPanel, BorderLayout.PAGE_START);
        setTargetPanel(gridBagPanel);

        gbc = new GridBagConstraints();
        gbc.fill = 0;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = Resource().general_inset_all;

        JButton viewAllButton = Button(Resource().admin_str_submenu_ViewAllUsers);
        JButton viewTenantsButton = Button(Resource().admin_str_submenu_ViewTenants);
        JButton viewOwnersButton = Button(Resource().admin_str_submenu_ViewOwners);
        JButton viewAgentsButton = Button(Resource().admin_str_submenu_ViewAgents);
        JButton viewAdminsButton = Button(Resource().admin_str_submenu_ViewAdmins);
        JButton createAccButton = Button(Resource().admin_str_submenu_CreateAcc);

        viewAllButton.addActionListener(parent.admin.getViewAllUsersAction(this));
        viewTenantsButton.addActionListener(parent.admin.getViewTenantsAction(this));
        viewOwnersButton.addActionListener(parent.admin.getViewOwnersAction(this));
        viewAgentsButton.addActionListener(parent.admin.getViewAgentsActionListener(this));
        viewAdminsButton.addActionListener(parent.admin.getViewAdminsActionListener(this));

        viewAllButton.setFont(Resource().general_font_highlight);
        viewTenantsButton.setFont(Resource().general_font_highlight);
        viewOwnersButton.setFont(Resource().general_font_highlight);
        viewAgentsButton.setFont(Resource().general_font_highlight);
        viewAdminsButton.setFont(Resource().general_font_highlight);
        createAccButton.setFont(Resource().general_font_highlight);

        add(viewAllButton, gbc);
        gbc.gridx++;
        add(viewTenantsButton, gbc);
        gbc.gridx++;
        add(viewOwnersButton, gbc);
        gbc.gridx++;
        add(viewAgentsButton, gbc);
        gbc.gridx++;
        add(viewAdminsButton, gbc);
        gbc.gridx++;
        add(createAccButton, gbc);
        gbc.gridx++;

    }

    private AdminTableViewGUIPanel<?> currentTablePanel;

    public void setCurrentTablePanel(AdminTableViewGUIPanel<?> newPanel){
        currentTablePanel = newPanel;
    }
    public AdminTableViewGUIPanel<?> getCurrentTablePanel(){
        return currentTablePanel;
    }
    public AdminTableViewGUIPanel<?> getAllUsersTablePanel(){
        AdminTableViewGUIPanel<?> allUsersTablePanel = new AdminTableViewGUIPanel<>(parent);
        allUsersTablePanel.initializeTitle(Resource().admin_str_content_title_AllUsers);
        return allUsersTablePanel;
    }
    public AdminTableViewGUIPanel<TenantUser> getTenantsTablePanel(){
        AdminTableViewGUIPanel<TenantUser> tenantsTablePanel = new AdminTableViewGUIPanel<TenantUser>(parent, TenantUser.class);
        tenantsTablePanel.initializeTitle(Resource().admin_str_content_title_Tenants);
        return tenantsTablePanel;
    }
    public AdminTableViewGUIPanel<OwnerAgentUser> getOwnersTablePanel(){
        AdminTableViewGUIPanel<OwnerAgentUser> ownersTablePanel = new AdminTableViewGUIPanel<OwnerAgentUser>(parent, OwnerAgentUser.class, true);
        ownersTablePanel.initializeTitle(Resource().admin_str_content_title_Owners);
        return ownersTablePanel;
    }
    public AdminTableViewGUIPanel<OwnerAgentUser> getAgentsTablePanel(){
        AdminTableViewGUIPanel<OwnerAgentUser> agentsTablePanel = new AdminTableViewGUIPanel<OwnerAgentUser>(parent, OwnerAgentUser.class, false);
        agentsTablePanel.initializeTitle(Resource().admin_str_content_title_Agents);
        return agentsTablePanel;
    }
    public AdminTableViewGUIPanel<AdminUser> getAdminsTablePanel(){
        AdminTableViewGUIPanel<AdminUser> adminsTablePanel = new AdminTableViewGUIPanel<AdminUser>(parent, AdminUser.class);
        adminsTablePanel.initializeTitle(Resource().admin_str_content_title_Admins);
        return adminsTablePanel;
    }

    @Override
    public void onCreatePanel() {

        setTargetPanel(this);

        switchTableView(getAllUsersTablePanel());
        
    }

    public void switchTableView(AdminTableViewGUIPanel<?> newPanel){
        setTargetPanel(this);
        AdminTableViewGUIPanel<?> previous = getCurrentTablePanel();
        if (previous != null){
            remove(previous);
        }
        attachPanel(newPanel, BorderLayout.CENTER);
        viewAttachedPanel(newPanel);
        setCurrentTablePanel(newPanel);
        revalidate();
    }

    @Override
    public void onView() {
        //setVisible(true);
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
    
    public void swapContentPanel(){

    }
    
}
