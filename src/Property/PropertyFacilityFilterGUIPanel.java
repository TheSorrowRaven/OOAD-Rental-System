package src.Property;

import javax.swing.*;
import java.awt.*;

import src.*;

/**
 * Raven
 * 
 * The Property Facility Filter panel
 */
public class PropertyFacilityFilterGUIPanel extends GUIPanel<GUIWindow> {

    private IProperty property;

    private PropertyFacilityGUIPanel facilityPanel;

    /**
     * Constructor accepting the property interface
     * @param parent
     * @param property
     */
    public PropertyFacilityFilterGUIPanel(GUIWindow parent, IProperty property) {
        super(parent);
        this.property = property;
    }

    /**
     * Returns the facility panel
     * @return
     */
    public PropertyFacilityGUIPanel getPropertyFacilityGUIPanel(){
        return facilityPanel;
    }

    /**
     * Creates the components to show the filters
     */
    @Override
    public void onCreate() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        facilityPanel = new PropertyFacilityGUIPanel(parent, property);
        JLabel filterLabel = JLabel(Resource().str_filter);
        filterLabel.setBackground(Theme().sub_background_color);
        filterLabel.setForeground(Theme().sub_text_color);
        add(filterLabel, gbc);
        gbc.gridy++;

        JLabel filterInstructionsLabel = JLabel(Resource().property_str_filter_instructions);
        filterInstructionsLabel.setFont(Resource().general_font_minor);
        add(filterInstructionsLabel, gbc);
        gbc.gridy++;

        attachPanel(facilityPanel, gbc);

    }

    /**
     * Calls to filter the facilities when changes occured
     * @param facilityCheckBox
     * @param f
     */
    public void facilityFilterChanged(JCheckBox facilityCheckBox, PropertyListing.Facility f){
        PropertyListing.Facilities fs = facilityPanel.getFacilities();
        var controller = property.getPropertyController();
        controller.filterBy(fs);
    }

    @Override
    public void onCreatePanel() {
        
    }

    /**
     * Explicitly sets the background color for the facility to override it
     */
    @Override
    public void onView() {
        facilityPanel.setBackground(Theme().sub_background_color);
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
