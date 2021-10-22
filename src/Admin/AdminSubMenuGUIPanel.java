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
        JButton viewPropertiesButton = Button(Resource().admin_str_submenu_ViewProperties);

        viewAllButton.addActionListener(parent.adminController.getViewAllUsersAction(this));
        viewTenantsButton.addActionListener(parent.adminController.getViewTenantsAction(this));
        viewOwnersButton.addActionListener(parent.adminController.getViewOwnersAction(this));
        viewAgentsButton.addActionListener(parent.adminController.getViewAgentsActionListener(this));
        viewAdminsButton.addActionListener(parent.adminController.getViewAdminsActionListener(this));
        viewPropertiesButton.addActionListener(parent.adminController.getViewPropertiesActionListener(this));

        viewAllButton.setFont(Resource().general_font_highlight);
        viewTenantsButton.setFont(Resource().general_font_highlight);
        viewOwnersButton.setFont(Resource().general_font_highlight);
        viewAgentsButton.setFont(Resource().general_font_highlight);
        viewAdminsButton.setFont(Resource().general_font_highlight);
        viewPropertiesButton.setFont(Resource().general_font_highlight);

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
        add(viewPropertiesButton, gbc);
        gbc.gridx++;

    }

    private GUIPanel<?> currentTablePanel;

    public void setCurrentTablePanel(GUIPanel<?> newPanel){
        currentTablePanel = newPanel;
    }
    public GUIPanel<?> getCurrentTablePanel(){
        return currentTablePanel;
    }
    public AdminUserViewGUIPanel<?> getAllUsersTablePanel(){
        AdminUserViewGUIPanel<?> allUsersTablePanel = new AdminUserViewGUIPanel<>(parent);
        allUsersTablePanel.initializeTitle(Resource().admin_str_content_title_AllUsers);
        return allUsersTablePanel;
    }
    public AdminUserViewGUIPanel<TenantUser> getTenantsTablePanel(){
        AdminUserViewGUIPanel<TenantUser> tenantsTablePanel = new AdminUserViewGUIPanel<TenantUser>(parent, TenantUser.class);
        tenantsTablePanel.initializeTitle(Resource().admin_str_content_title_Tenants);
        return tenantsTablePanel;
    }
    public AdminUserViewGUIPanel<OwnerAgentUser> getOwnersTablePanel(){
        AdminUserViewGUIPanel<OwnerAgentUser> ownersTablePanel = new AdminUserViewGUIPanel<OwnerAgentUser>(parent, OwnerAgentUser.class, true);
        ownersTablePanel.initializeTitle(Resource().admin_str_content_title_Owners);
        return ownersTablePanel;
    }
    public AdminUserViewGUIPanel<OwnerAgentUser> getAgentsTablePanel(){
        AdminUserViewGUIPanel<OwnerAgentUser> agentsTablePanel = new AdminUserViewGUIPanel<OwnerAgentUser>(parent, OwnerAgentUser.class, false);
        agentsTablePanel.initializeTitle(Resource().admin_str_content_title_Agents);
        return agentsTablePanel;
    }
    public AdminUserViewGUIPanel<AdminUser> getAdminsTablePanel(){
        AdminUserViewGUIPanel<AdminUser> adminsTablePanel = new AdminUserViewGUIPanel<AdminUser>(parent, AdminUser.class);
        adminsTablePanel.initializeTitle(Resource().admin_str_content_title_Admins);
        return adminsTablePanel;
    }
    public AdminPropertyViewGUIPanel getPropertiesTablePanel(){
        AdminPropertyViewGUIPanel propertiesPanel = new AdminPropertyViewGUIPanel(parent);
        propertiesPanel.initializeTitle(Resource().admin_str_content_title_Properties);
        return propertiesPanel;
    }

    @Override
    public void onCreatePanel() {

        setTargetPanel(this);
        switchTableView(getAllUsersTablePanel());
        
    }

    public void switchTableView(GUIPanel<?> newPanel){
        setTargetPanel(this);
        GUIPanel<?> previous = getCurrentTablePanel();
        if (previous != null){
            previous.onDestroyInternal();
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
    
}
 