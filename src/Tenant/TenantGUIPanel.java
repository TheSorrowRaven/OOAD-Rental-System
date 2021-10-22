package src.Tenant;

import java.awt.event.*;

import javax.swing.*;
import java.awt.*;

import src.MenuContentGUIPanel;
import src.Property.*;
import src.Property.PropertyListing.Facility;

public class TenantGUIPanel extends MenuContentGUIPanel<TenantGUIWindow> implements IProperty {


    public JPanel panel;
    
    private JScrollPane scrollPane;
    private GridBagConstraints gbc;
    
    private PropertyFacilityFilterGUIPanel propertyFacilityFilter;

    public TenantGUIPanel(TenantGUIWindow parent) {
        super(parent);
    }

    @Override
    public void onCreate() {
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = Resource().general_inset_all;
        gbc.anchor = GridBagConstraints.CENTER;
        setLayout(new GridBagLayout());
        panel = new JPanel();
        panel.setBackground(Theme().panel_background_color);
        panel.setLayout(new GridBagLayout());

        setBackground(Color.cyan);

        scrollPane = JScrollPane(panel);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(Resource().scroll_speed);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(Resource().scroll_speed);
        add(scrollPane, gbc);
        gbc.gridy++;

        PropertyTableGUIPanel propertyTableGUIPanel = new PropertyTableGUIPanel(parent, this, null, false);
        propertyFacilityFilter = new PropertyFacilityFilterGUIPanel(parent, this);
        parent.tenantController.setTablePanel(propertyTableGUIPanel);

        panel.add(propertyFacilityFilter, gbc);
        executePanelLifecycle(propertyFacilityFilter);
        gbc.gridy++;

        gbc.fill = 0;
        JLabel sortInstructions = JLabel(Resource().property_str_sort_instructions);
        panel.add(sortInstructions, gbc);
        gbc.gridy++;

        gbc.fill = 1;
        panel.add(propertyTableGUIPanel, gbc);
        executePanelLifecycle(propertyTableGUIPanel);
    }

    @Override
    public void onCreatePanel() {
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

    @Override
    public PropertyController getPropertyController() {
        return parent.tenantController;
    }

    @Override
    public boolean isEditable() {
        return false;
    }

    @Override
    public int getFacilityIntendedColumns() {
        return 3;
    }

    @Override
    public ItemListener getOnFacilityChangedFor(Facility facility) {
        return parent.tenantController.getOnFacilityChanged(facility, propertyFacilityFilter);
    }

    @Override
    public void snapScrollToTop() {
        
    }

    @Override
    public PropertyFacilityFilterGUIPanel getFacilityFilterPanel() {
        return propertyFacilityFilter;
    }
    
}
