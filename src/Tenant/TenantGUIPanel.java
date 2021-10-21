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
    private GridBagConstraints ctr;
    
    private PropertyFacilityFilterGUIPanel propertyFacilityFilter;

    public TenantGUIPanel(TenantGUIWindow parent) {
        super(parent);
    }

    @Override
    public void onCreate() {
        ctr = new GridBagConstraints();
        ctr.fill = GridBagConstraints.BOTH;
        ctr.gridx = 0;
        ctr.gridy = 0;
        ctr.weightx = 1;
        ctr.weighty = 1;
        setLayout(new GridBagLayout());
        panel = new JPanel();
        panel.setBackground(Theme().panel_background_color);
        panel.setLayout(new GridBagLayout());

        scrollPane = JScrollPane(panel);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(Resource().scroll_speed);
        add(scrollPane, ctr);
        ctr.gridy++;


        PropertyTableGUIPanel propertyTableGUIPanel = new PropertyTableGUIPanel(parent, this, null);
        propertyFacilityFilter = new PropertyFacilityFilterGUIPanel(parent, this);
        parent.tenantController.setTablePanel(propertyTableGUIPanel);

        panel.add(propertyFacilityFilter, ctr);
        executePanelLifecycle(propertyFacilityFilter);
        ctr.gridy++;

        panel.add(propertyTableGUIPanel, ctr);
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
        return null;    //Not used
    }

    @Override
    public void snapScrollToTop() {
        
    }

    @Override
    public PropertyFacilityFilterGUIPanel getFacilityFilterPanel() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
