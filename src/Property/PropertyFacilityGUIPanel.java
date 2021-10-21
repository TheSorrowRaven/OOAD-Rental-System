package src.Property;

import src.*;
import javax.swing.*;
import java.awt.*;

public class PropertyFacilityGUIPanel extends GUIPanel<GUIWindow> {

    public JCheckBox[] checkBoxes;
    public int columns;
    private IProperty property;

    public PropertyFacilityGUIPanel(GUIWindow parent, IProperty property) {
        super(parent);
        this.property = property;
        checkBoxes = new JCheckBox[PropertyListing.Facility.values().length];
        columns = property.getFacilityIntendedColumns();
    }

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

    public PropertyListing.Facilities getFacilities(){
        PropertyListing.Facilities fs = new PropertyListing.Facilities();
        for (JCheckBox box : checkBoxes){
            if (box.isSelected()){
                fs.add(PropertyListing.Facility.getFromString(box.getText()));
            }
        }
        return fs;
    }

    @Override
    public void onCreate() {
        setBackground(Theme().background_color);
        setLayout(new GridBagLayout());

        GridBagConstraints ctr = new GridBagConstraints();
        ctr.fill = GridBagConstraints.HORIZONTAL;
        ctr.gridx = 0;
        ctr.gridy = 0;
        ctr.insets = Resource().general_inset_tiny;

        var facilities = PropertyListing.Facility.values();
        for (int i = 0; i < facilities.length; i++){

            PropertyListing.Facility f = facilities[i];
            checkBoxes[i] = JCheckBox(f.getName());
            checkBoxes[i].addItemListener(property.getOnFacilityChangedFor(f));

            add(checkBoxes[i], ctr);

            ctr.gridx++;
            if (ctr.gridx == columns){
                ctr.gridx = 0;
                ctr.gridy++;
            }
        }

    }

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
