package src.Property;

import src.*;
import javax.swing.*;
import java.awt.*;

/**
 * Raven
 * 
 * Property Facility panel
 */
public class PropertyFacilityGUIPanel extends GUIPanel<GUIWindow> {

    public JCheckBox[] checkBoxes;
    public int columns;
    private IProperty property;

    /**
     * Constructor accepting the property interface
     * @param parent
     * @param property
     */
    public PropertyFacilityGUIPanel(GUIWindow parent, IProperty property) {
        super(parent);
        this.property = property;
        checkBoxes = new JCheckBox[PropertyListing.Facility.values().length];
        columns = property.getFacilityIntendedColumns();
    }

    /**
     * Sets the ticks only in the facilties in an array
     * @param facilities
     */
    public void tickOnlyIn(String[] facilities){
        for (JCheckBox box : checkBoxes){
            boolean found = false;
            for (int i = 0; i < facilities.length; i++){
                if (box.getText().equals(facilities[i])){
                    box.setSelected(true);
                    found = true;
                }
            }
            if (!found){
                box.setSelected(false);
            }
        }
    }

    /**
     * Returns all of the checked the facilities
     */
    public PropertyListing.Facilities getFacilities(){
        PropertyListing.Facilities fs = new PropertyListing.Facilities();
        for (JCheckBox box : checkBoxes){
            if (box.isSelected()){
                fs.add(PropertyListing.Facility.getFromString(box.getText()));
            }
        }
        return fs;
    }

    /**
     * Creates the components to display the facilities
     */
    @Override
    public void onCreate() {
        setBackground(Theme().background_color);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = Resource().general_inset_tiny;

        var facilities = PropertyListing.Facility.values();
        for (int i = 0; i < facilities.length; i++){

            PropertyListing.Facility f = facilities[i];
            checkBoxes[i] = JCheckBox(f.getName());
            checkBoxes[i].addItemListener(property.getOnFacilityChangedFor(f));

            add(checkBoxes[i], gbc);

            gbc.gridx++;
            if (gbc.gridx == columns){
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }

    }

    /**
     * Reset all the check boxes of the facilties
     */
    public void reset(){
        for (JCheckBox box : checkBoxes){
            box.setSelected(false);
        }
    }

    @Override
    public void onCreatePanel() {
        
    }

    @Override
    public void onView() {
        
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
