package src.OwnerAgent;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import src.MenuContentGUIPanel;

import src.*;
import src.Property.*;
import src.Property.PropertyListing.Facility;
import src.SystemComponents.CLI;

public class OwnerAgentGUIPanel extends MenuContentGUIPanel<OwnerAgentGUIWindow> implements IProperty{

    //public PropertyController propertyController;
    public JPanel panel;

    private JScrollPane scrollPane;
    private GridBagConstraints ctr;

    private PropertyFacilityFilterGUIPanel propertyFacilityFilter;

    public OwnerAgentGUIPanel(OwnerAgentGUIWindow parent) {
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
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        scrollPane = JScrollPane(panel);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(Resource().scroll_speed);
        add(scrollPane, ctr);
        ctr.gridy++;

    }

    @Override
    public void onCreatePanel() {
        PropertyTableGUIPanel propertyTableGUIPanel = new PropertyTableGUIPanel(parent, this, parent.ownerAgentController.loggedInOwnerAgent);
        OwnerAgentCreateEditGUIPanel createEditPanel = new OwnerAgentCreateEditGUIPanel(parent, this, propertyTableGUIPanel);
        panel.add(createEditPanel, ctr);
        ctr.gridy++;

        propertyFacilityFilter = new PropertyFacilityFilterGUIPanel(parent, this);
        panel.add(propertyFacilityFilter, ctr);
        executePanelLifecycle(propertyFacilityFilter);
        ctr.gridy++;

        panel.add(propertyTableGUIPanel, ctr);
        createEditPanel.setPropertyTablePanel(propertyTableGUIPanel);
        executePanelLifecycle(createEditPanel);
        executePanelLifecycle(propertyTableGUIPanel);
        propertyTableGUIPanel.setTableListener(parent.ownerAgentController.getTableSelected(this, propertyTableGUIPanel, propertyTableGUIPanel.getTable()));


    }

    @Override
    public void snapScrollToTop(){
        scrollPane.getVerticalScrollBar().setValue(0);
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
        return parent.ownerAgentController;
    }

    @Override
    public boolean isEditable() {
        return true;
    }

    @Override
    public int getFacilityIntendedColumns() {
        return 3;
    }

    @Override
    public ItemListener getOnFacilityChangedFor(Facility facility) {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JCheckBox facilityCheckBox = (JCheckBox)e.getItem();
                if (facilityCheckBox.getParent() == propertyFacilityFilter.getPropertyFacilityGUIPanel()){
                    propertyFacilityFilter.facilityFilterChanged(facilityCheckBox, facility);
                }
            }
        };
    }

    @Override
    public PropertyFacilityFilterGUIPanel getFacilityFilterPanel() {
        return propertyFacilityFilter;
    }

}
