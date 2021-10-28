package src.Admin;

import java.awt.event.*;
import javax.swing.*;

import src.Property.IProperty;
import src.Property.PropertyController;
import src.Property.PropertyFacilityFilterGUIPanel;
import src.Property.*;
import src.Property.PropertyListing.Facility;

/**
 * Tasha
 * 
 * Sub menu content panel for the properties
 */
public class AdminPropertyViewGUIPanel extends AdminViewGUIPanel implements IProperty {

    private PropertyFacilityFilterGUIPanel propertyFacilityFilter;

    /**
     * Constructor
     * @param parent
     */
    public AdminPropertyViewGUIPanel(AdminGUIWindow parent) {
        super(parent);
    }
    
    /**
     * Setups the components for properties
     */
    @Override
    public void onCreate(){
        super.onCreate();

        PropertyTableGUIPanel propertyTableGUIPanel = new PropertyTableGUIPanel(parent, this, null, true);
        

        JLabel deleteLabel = JLabel(Resource().property_str_delete_instructions);
        deleteLabel.setFont(Resource().general_font_minor);
        add(deleteLabel, gbc);
        gbc.gridy++;

        src.Button deleteButton = Button(Resource().property_str_button_delete);
        deleteButton.addActionListener(parent.adminController.getDeletePropertiesActionListener());
        add(deleteButton, gbc);
        gbc.gridy++;

        propertyFacilityFilter = new PropertyFacilityFilterGUIPanel(parent, this);
        add(propertyFacilityFilter, gbc);
        executePanelLifecycle(propertyFacilityFilter);
        gbc.gridy++;

        JLabel sortInstructions = JLabel(Resource().property_str_sort_instructions);
        sortInstructions.setFont(Resource().general_font_minor);
        add(sortInstructions, gbc);
        gbc.gridy++;

        gbc.fill = 1;
        add(propertyTableGUIPanel, gbc);
        executePanelLifecycle(propertyTableGUIPanel);

        parent.adminController.setTablePanel(propertyTableGUIPanel);

    }

    /**
     * Returns the admin controller down casted as property controller
     */
    @Override
    public PropertyController getPropertyController() {
        return parent.adminController;
    }

    /**
     * Returns the number of columns for the facilities
     */
    @Override
    public int getFacilityIntendedColumns() {
        return 3;
    }

    /**
     * Returns the event for facilities that changed
     */
    @Override
    public ItemListener getOnFacilityChangedFor(Facility facility) {
        return parent.adminController.getOnFacilityChanged(facility, propertyFacilityFilter);
    }

    @Override
    public void snapScrollToTop() {
        
    }

    /**
     * Returns the filter panel
     */
    @Override
    public PropertyFacilityFilterGUIPanel getFacilityFilterPanel() {
        return propertyFacilityFilter;
    }

}
