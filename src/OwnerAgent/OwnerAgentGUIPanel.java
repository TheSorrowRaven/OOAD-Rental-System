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
    private GridBagConstraints gbc;

    private PropertyFacilityFilterGUIPanel propertyFacilityFilter;

    public OwnerAgentGUIPanel(OwnerAgentGUIWindow parent) {
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
        setLayout(new GridBagLayout());
        panel = new JPanel();
        panel.setBackground(Theme().panel_background_color);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        scrollPane = JScrollPane(panel);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(Resource().scroll_speed);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(Resource().scroll_speed);
        add(scrollPane, gbc);
        gbc.gridy++;

    }

    @Override
    public void onCreatePanel() {
        PropertyTableGUIPanel propertyTableGUIPanel = new PropertyTableGUIPanel(parent, this, parent.ownerAgentController.loggedInOwnerAgent, true);
        OwnerAgentCreateEditGUIPanel createEditPanel = new OwnerAgentCreateEditGUIPanel(parent, this, propertyTableGUIPanel);
        panel.add(createEditPanel, gbc);
        gbc.gridy++;

        propertyFacilityFilter = new PropertyFacilityFilterGUIPanel(parent, this);
        panel.add(propertyFacilityFilter, gbc);
        executePanelLifecycle(propertyFacilityFilter);
        gbc.gridy++;

        
        JLabel sortInstructions = JLabel(Resource().property_str_sort_instructions);
        sortInstructions.setFont(Resource().general_font_minor);
        panel.add(sortInstructions, gbc);
        gbc.gridy++;

        panel.add(propertyTableGUIPanel, gbc);
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
        return parent.ownerAgentController.getOnFacilityChanged(facility, propertyFacilityFilter);
    }

    @Override
    public PropertyFacilityFilterGUIPanel getFacilityFilterPanel() {
        return propertyFacilityFilter;
    }

}
