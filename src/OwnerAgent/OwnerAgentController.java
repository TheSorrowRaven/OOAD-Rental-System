package src.OwnerAgent;

import src.Users.*;
import java.util.*;

import src.*;
import src.Property.*;

import javax.swing.event.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * CG
 * 
 * This class is the controller for Owner/Agent
 * This handles the events and execution of owner/agent interface
 */
public class OwnerAgentController extends PropertyController {

    public OwnerAgent ownerAgent;
    public OwnerAgentUser loggedInOwnerAgent;
    public OwnerAgentCreateEditGUIPanel createEditPanel;
    
    /**
     * Constructor receiving a logged in owner agent user
     * @param loggedInOwnerAgent
     */
    public OwnerAgentController(OwnerAgentUser loggedInOwnerAgent){
        this.loggedInOwnerAgent = loggedInOwnerAgent;
        ownerAgent = new OwnerAgent();
    }

    /**
     * Setups the panels required to reference to
     * @param createEditPanel
     * @param tablePanel
     */
    public void setPanels(OwnerAgentCreateEditGUIPanel createEditPanel, PropertyTableGUIPanel tablePanel){
        this.createEditPanel = createEditPanel;
        setTablePanel(tablePanel);
    }

    /**
     * Gets the action when a table row is selected
     */
    @Override
    public <T extends GUIPanel<?>> ListSelectionListener getTableSelected(T panel, final PropertyTableGUIPanel propertyPanel, final Table table){
        return new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = table.getSelectedRow();

                //Not the change capture needed
                if (row == -1){
                    return;
                }
                int column = table.getSelectedColumn();
                //Select to delete
                if (column == table.getColumnCount() - 1){
                    //Don't edit
                    propertyPanel.clearSelection();
                    return;
                }

                int c = 0;
                boolean isActive = (boolean)table.getValueAt(row, c++);
                String name = (String)table.getValueAt(row, c++);
                String address = (String)table.getValueAt(row, c++);
                String type = (String)table.getValueAt(row, c++);
                String size = (String)table.getValueAt(row, c++);
                int rooms = (int)table.getValueAt(row, c++);
                int bathrooms = (int)table.getValueAt(row, c++);
                int rent = (int)table.getValueAt(row, c++);
                String facilitiesString = (String)table.getValueAt(row, c++);
                createEditPanel.setupEditMode(isActive, name, address, type, size, rooms, bathrooms, rent, facilitiesString);

                propertyPanel.setEditingProperty(propertyPanel.properties.get(row));
                
                if (panel instanceof OwnerAgentGUIPanel ownerAgentPanel){
                    ownerAgentPanel.snapScrollToTop();
                }
                
            }
            
        };
    }

    /**
     * Event for creating or editing the property
     * @param propertyPanel
     * @return
     */
    public ActionListener getCreateEditListener(final PropertyTableGUIPanel propertyPanel){
        return e -> {
            boolean isEditing = createEditPanel.getIsEditing();

            PropertyListing editingProperty = propertyPanel.getEditingProperty(); 

            boolean isActive = createEditPanel.activeCheckBox.isSelected();
            String name = createEditPanel.nameField.getText();
            String address = createEditPanel.addressField.getText();
            PropertyListing.Type type = PropertyListing.Type.getFromString((String)createEditPanel.typeCombo.getSelectedItem());
            String size = createEditPanel.sizeField.getText();
            int rooms = (Integer)createEditPanel.roomsSpinner.getValue();
            int bathrooms = (Integer)createEditPanel.bathroomsSpinner.getValue();
            int rent = 0;
            try {
                rent = NumberFormat.getIntegerInstance().parse(createEditPanel.rentField.getText()).intValue();
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            PropertyListing.Facilities facilities = createEditPanel.facilitiesPanel.getFacilities();

            if (isEditing){
                ownerAgent.editProperty(editingProperty, isActive, name, address, type, size, rooms, bathrooms, rent, facilities, loggedInOwnerAgent);
            }
            else{
                ownerAgent.createProperty(isActive, name, address, type, size, rooms, bathrooms, rent, facilities, loggedInOwnerAgent);
            }

            propertyPanel.refreshTable();
            createEditPanel.setNotEditing();
            
        };
    }

    /**
     * Event for deleting properties
     * @return
     */
    public ActionListener getDeleteActionListener(){
        return e -> {
            Table table = tablePanel.table;
            ArrayList<PropertyListing> properties = tablePanel.properties;
            ArrayList<PropertyListing> deletingProps = new ArrayList<PropertyListing>();
            for (int i = 0; i < table.getRowCount(); i++){
                boolean isTicked = (Boolean)table.getValueAt(i, tablePanel.deletionTickboxColumn);
                if (isTicked){
                    deletingProps.add(properties.get(i));
                }
            }
            ownerAgent.deleteProperties(deletingProps);
            tablePanel.refreshTable();
        };
    }


    /**
     * Event for stop editing a property when it is in edit mode
     * @return
     */
    public ActionListener getNotEditingListener(){
        return e -> {
            createEditPanel.setNotEditing();
        };
    }

}
