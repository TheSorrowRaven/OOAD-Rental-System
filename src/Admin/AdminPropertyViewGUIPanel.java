package src.Admin;

import java.awt.event.*;
import javax.swing.*;

import src.Property.IProperty;
import src.Property.PropertyController;
import src.Property.PropertyFacilityFilterGUIPanel;
import src.Property.*;
import src.Property.PropertyListing.Facility;

public class AdminPropertyViewGUIPanel extends AdminViewGUIPanel implements IProperty {

    private PropertyFacilityFilterGUIPanel propertyFacilityFilter;

    public AdminPropertyViewGUIPanel(AdminGUIWindow parent) {
        super(parent);
    }
    
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

    @Override
    public PropertyController getPropertyController() {
        return parent.adminController;
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
        return parent.adminController.getOnFacilityChanged(facility, propertyFacilityFilter);
    }

    @Override
    public void snapScrollToTop() {
        
    }

    @Override
    public PropertyFacilityFilterGUIPanel getFacilityFilterPanel() {
        return null;
    }

}
