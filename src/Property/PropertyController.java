package src.Property;

import java.util.*;

import src.*;
import src.Property.PropertyListing.Facilities;
import src.Property.PropertyListing.Facility;

import javax.swing.event.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Raven
 * 
 * Controller for Property
 */
public class PropertyController {

    public Property property;
    public PropertyTableGUIPanel tablePanel;
    
    /**
     * Constructor
     */
    public PropertyController(){
        property = new Property();
    }

    /**
     * Gets all the properties from model
     */
    public ArrayList<PropertyListing> getAllProperties(){
        return property.fetchAllProperties();
    }

    /**
     * Sets the handling table panel
     */
    public void setTablePanel(PropertyTableGUIPanel tablePanel){
        this.tablePanel = tablePanel;
    }

    /**
     * Event for when a row is selected in the table
     * @param <T>
     * @param panel
     * @param propertyPanel
     * @param table
     * @return
     */
    public <T extends GUIPanel<?>> ListSelectionListener getTableSelected(T panel, final PropertyTableGUIPanel propertyPanel, final Table table){
        return null;
    }

    /**
     * Event for when the header is clicked. Sorting is done to refresh table
     * @param tablePanel
     * @return
     */
    public MouseAdapter getHeaderOnClick(PropertyTableGUIPanel tablePanel){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PropertyListing.PropertyTableMetaData data = new PropertyListing.PropertyTableMetaData();
                int column = tablePanel.table.columnAtPoint(e.getPoint());
                if (column == data.columnCount){
                    return;
                    //Delete option, no need to sort
                }
                Comparator<PropertyListing> comparator = data.getComparatorFromColumn(column);
                tablePanel.refreshTable(comparator);
            }
        };
    }

    /**
     * Filter the table by the facilities
     * @param fs
     */
    public void filterBy(Facilities fs){
        tablePanel.refreshTable(null, fs);
    }

    /**
     * Event for when the facility is changed (ticked or unticked)
     * @param facility
     * @param propertyFacilityFilter
     * @return
     */
    public ItemListener getOnFacilityChanged(Facility facility, PropertyFacilityFilterGUIPanel propertyFacilityFilter){
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

}
