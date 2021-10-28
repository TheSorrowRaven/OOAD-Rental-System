package src.OwnerAgent;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import src.MenuContentGUIPanel;

import src.Property.*;
import src.Property.PropertyListing.Facility;

/**
 * CG
 * 
 * This is the view of the Owner/Agent
 * This is the content of the menu of owner/agent
 */
public class OwnerAgentGUIPanel extends MenuContentGUIPanel<OwnerAgentGUIWindow> implements IProperty{

    //public PropertyController propertyController;
    public JPanel panel;

    private JScrollPane scrollPane;
    private GridBagConstraints gbc;

    private PropertyFacilityFilterGUIPanel propertyFacilityFilter;

    /**
     * Constructor
     * @param parent
     */
    public OwnerAgentGUIPanel(OwnerAgentGUIWindow parent) {
        super(parent);
    }

    /**
     * This creates the scroll panel for the components
     */
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

    /**
     * This creates the panels for the create/edit of property, the table for display and the filter to filter the table
     */
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

    /**
     * When a table row is selected, edit is assumed thus it will automatically scroll to the top for editing
     */
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

    /**
     * Returns the owner agent controller downcasted as a property controller
     */
    @Override
    public PropertyController getPropertyController() {
        return parent.ownerAgentController;
    }

    /**
     * Returns the columns of the facility for display
     */
    @Override
    public int getFacilityIntendedColumns() {
        return 3;
    }

    /**
     * Gets the listener when a facility filter is changed so it can refresh the table
     */
    @Override
    public ItemListener getOnFacilityChangedFor(Facility facility) {
        return parent.ownerAgentController.getOnFacilityChanged(facility, propertyFacilityFilter);
    }

    /**
     * Returns the faciltiy filter panel
     */
    @Override
    public PropertyFacilityFilterGUIPanel getFacilityFilterPanel() {
        return propertyFacilityFilter;
    }

}
