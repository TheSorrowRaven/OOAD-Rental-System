package src.Property;

import java.util.*;
import javax.swing.table.*;

import src.*;
import src.Property.PropertyListing.Facilities;
import src.SystemComponents.CLI;

import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PropertyController {

    public Property property;
    public PropertyTableGUIPanel tablePanel;
    
    
    public PropertyController(){
        property = new Property();
    }

    public ArrayList<PropertyListing> getAllProperties(){
        return property.fetchAllProperties();
    }

    public void setTablePanel(PropertyTableGUIPanel tablePanel){
        this.tablePanel = tablePanel;
    }

    public <T extends GUIPanel<?>> ListSelectionListener getTableSelected(T panel, final PropertyTableGUIPanel propertyPanel, final Table table){
        return null;
    }

    public MouseAdapter getHeaderOnClick(PropertyTableGUIPanel tablePanel){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PropertyListing.PropertyTableMetaData data = new PropertyListing.PropertyTableMetaData();
                int column = tablePanel.table.columnAtPoint(e.getPoint());
                if (column == data.columnCount){
                    return;
                    //Delete option
                }
                Comparator<PropertyListing> comparator = data.getComparatorFromColumn(column);
                tablePanel.refreshTable(comparator);
            }
        };
    }

    public void filterBy(Facilities fs){
        tablePanel.refreshTable(null, fs);
    }

}
