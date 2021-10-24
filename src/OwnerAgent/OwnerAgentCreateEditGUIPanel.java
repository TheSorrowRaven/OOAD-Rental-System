package src.OwnerAgent;

import src.*;
import src.Property.*;

import javax.swing.*;
import java.awt.*;
import java.text.*;

/**
 * This panel contains the creation and editing of properties
 */
public class OwnerAgentCreateEditGUIPanel extends GUIPanel<OwnerAgentGUIWindow> {

    public src.Button createEditButton;
    public src.Button restoreEditButton;
    public src.Button deleteButton;

    public PropertyTableGUIPanel tablePanel;
    public IProperty property;

    public JCheckBox activeCheckBox;
    public JTextField nameField;
    public JTextField addressField;
    public JComboBox<String> typeCombo;
    public JTextField sizeField;
    public JSpinner roomsSpinner;
    public JSpinner bathroomsSpinner;
    public JFormattedTextField rentField;
    public PropertyFacilityGUIPanel facilitiesPanel;

    private boolean isEditing = false;
    

    /**
     * Constructor receiving the window, property and the table panel
     */
    public OwnerAgentCreateEditGUIPanel(OwnerAgentGUIWindow parent, IProperty property, PropertyTableGUIPanel tablePanel) {
        super(parent);
        this.property = property;
        this.tablePanel = tablePanel;
        parent.ownerAgentController.setPanels(this, tablePanel);
    }

    /**
     * Starts edit mode of a property by receiving all data about the editing property and setting to all its field
     * @param isActive
     * @param name
     * @param address
     * @param type
     * @param size
     * @param rooms
     * @param bathrooms
     * @param rent
     * @param facilitiesString
     */
    public void setupEditMode(boolean isActive, String name, String address, String type, String size, int rooms, int bathrooms, int rent, String facilitiesString){
        activeCheckBox.setSelected(isActive);
        nameField.setText(name);
        addressField.setText(address);
        typeCombo.setSelectedItem(type);
        sizeField.setText(size);
        roomsSpinner.setValue(rooms);
        bathroomsSpinner.setValue(bathrooms);
        rentField.setText(Integer.toString(rent));
        facilitiesPanel.tickOnlyIn(facilitiesString.split(", "));
        isEditing = true;
        restoreEditButton.setVisible(true);
        updateCreateEditButton();
    }
    
    /**
     * Sets the property table panel of the property display
     * @param tablePanel
     */
    public void setPropertyTablePanel(PropertyTableGUIPanel tablePanel){
        this.tablePanel = tablePanel;
    }

    /**
     * Returns if this is currently editing a property
     * @return
     */
    public boolean getIsEditing(){
        return isEditing;
    }

    /**
     * Set to stop editing a property and hide the stop editing button
     */
    public void setNotEditing(){
        isEditing = false;
        restoreEditButton.setVisible(false);
        updateCreateEditButton();
        tablePanel.clearSelection();
    }

    /**
     * Updates the create or edit button basede on if its editing or not
     */
    public void updateCreateEditButton(){
        createEditButton.setText(isEditing ? Resource().ownerAgent_str_button_createEdit_edit : Resource().ownerAgent_str_button_createEdit_create);
    }

    /**
     * Creates the components necessary to display the create edit panel
     */
    @Override
    public void onCreate() {
        
        setBackground(Theme().panel_background_color);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = 0;
        //gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = Resource().general_inset_all;

        gbc.anchor = GridBagConstraints.EAST;
        JLabel activeLabel = JLabel(Resource().ownerAgent_str_edit_active);
        JLabel nameLabel = JLabel(Resource().ownerAgent_str_edit_name);
        JLabel addressLabel = JLabel(Resource().ownerAgent_str_edit_address);
        JLabel typeLabel = JLabel(Resource().ownerAgent_str_edit_type);
        JLabel sizeLabel = JLabel(Resource().ownerAgent_str_edit_size);
        JLabel roomsLabel = JLabel(Resource().ownerAgent_str_edit_rooms);
        JLabel bathroomsLabel = JLabel(Resource().ownerAgent_str_edit_bathrooms);
        JLabel rentLabel = JLabel(Resource().ownerAgent_str_edit_rent);
        JLabel facilitiesLabel = JLabel(Resource().ownerAgent_str_edit_facilities);

        add(activeLabel, gbc);
        gbc.gridy++;
        add(nameLabel, gbc);
        gbc.gridy++;
        add(addressLabel, gbc);
        gbc.gridy++;
        add(typeLabel, gbc);
        gbc.gridy++;
        add(sizeLabel, gbc);
        gbc.gridy++;
        add(roomsLabel, gbc);
        gbc.gridy++;
        add(bathroomsLabel, gbc);
        gbc.gridy++;
        add(rentLabel, gbc);
        gbc.gridy++;
        add(facilitiesLabel, gbc);
        gbc.gridy++;

        gbc.fill = 1;
        gbc.gridx++;
        gbc.gridy = 0;

        SpinnerModel roomsModel = new SpinnerNumberModel(0, 0, 999, 1);
        SpinnerModel bathroomsModel = new SpinnerNumberModel(0, 0, 999, 1);
        activeCheckBox = JCheckBox("");
        activeCheckBox.setBackground(Theme().panel_background_color);
        nameField = JTextField();
        addressField = JTextField();
        typeCombo = JComboBox(PropertyListing.Type.stringNameValues());
        sizeField = JTextField();
        roomsSpinner = JSpinner(roomsModel);
        bathroomsSpinner = JSpinner(bathroomsModel);
        rentField = JFormattedTextField(NumberFormat.getIntegerInstance());
        facilitiesPanel = new PropertyFacilityGUIPanel(parent, property);

        add(activeCheckBox, gbc);
        gbc.gridy++;
        add(nameField, gbc);
        gbc.gridy++;
        add(addressField, gbc);
        gbc.gridy++;
        add(typeCombo, gbc);
        gbc.gridy++;
        add(sizeField, gbc);
        gbc.gridy++;
        add(roomsSpinner, gbc);
        gbc.gridy++;
        add(bathroomsSpinner, gbc);
        gbc.gridy++;
        add(rentField, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        add(facilitiesPanel, gbc);
        executePanelLifecycle(facilitiesPanel);

        gbc.gridx = 0;
        gbc.gridy++;
        restoreEditButton = Button(Resource().ownerAgent_str_button_restoreEdit);
        restoreEditButton.addActionListener(parent.ownerAgentController.getNotEditingListener());
        add(restoreEditButton, gbc);
        restoreEditButton.setVisible(false);

        gbc.gridx++;
        createEditButton = Button();
        updateCreateEditButton();
        createEditButton.addActionListener(parent.ownerAgentController.getCreateEditListener(tablePanel));
        add(createEditButton, gbc);
        gbc.gridy++;

        JLabel deleteLabel = JLabel(Resource().property_str_delete_instructions);
        deleteLabel.setFont(Resource().general_font_minor);
        add(deleteLabel, gbc);
        gbc.gridy++;

        deleteButton = Button(Resource().property_str_button_delete);
        deleteButton.addActionListener(parent.ownerAgentController.getDeleteActionListener());
        add(deleteButton, gbc);

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
