package src.Property;

import javax.swing.*;
import java.awt.*;

import src.*;
import src.SystemComponents.CLI;

public class PropertyFacilityFilterGUIPanel extends GUIPanel<GUIWindow> {

    private IProperty property;

    private PropertyFacilityGUIPanel facilityPanel;

    public PropertyFacilityFilterGUIPanel(GUIWindow parent, IProperty property) {
        super(parent);
        this.property = property;
    }

    public PropertyFacilityGUIPanel getPropertyFacilityGUIPanel(){
        return facilityPanel;
    }

    @Override
    public void onCreate() {
        setLayout(new GridBagLayout());
        GridBagConstraints ctr = new GridBagConstraints();
        ctr.fill = 0;
        ctr.gridx = 0;
        ctr.gridy = 0;
        facilityPanel = new PropertyFacilityGUIPanel(parent, property);
        JLabel filterLabel = JLabel(Resource().str_filter);
        filterLabel.setBackground(Theme().sub_background_color);
        filterLabel.setForeground(Theme().sub_text_color);
        add(filterLabel, ctr);
        ctr.gridy++;

        JLabel filterInstructionsLabel = JLabel(Resource().property_str_filter_instructions);
        filterInstructionsLabel.setFont(Resource().general_font_minor);
        add(filterInstructionsLabel, ctr);
        ctr.gridy++;

        attachPanel(facilityPanel, ctr);

    }

    public void facilityFilterChanged(JCheckBox facilityCheckBox, PropertyListing.Facility f){
        PropertyListing.Facilities fs = facilityPanel.getFacilities();
        var controller = property.getPropertyController();
        controller.filterBy(fs);
    }

    @Override
    public void onCreatePanel() {
        
    }

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
