package src.Property;

import src.*;
import src.Property.PropertyListing.Facilities;
import src.Users.*;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.util.*;
import javax.swing.table.*;

/**
 * Table of the properties panel
 */
public class PropertyTableGUIPanel extends GUIPanel<GUIWindow> {

    public PropertyController propertyController;
    public boolean canDelete;
    public Table table;

    private PropertyListing editingProperty;
    public ArrayList<PropertyListing> properties;

    public int deletionTickboxColumn;
    private OwnerAgentUser limitingOwnerAgent;

    /**
     * Constructor
     * @param parent
     * @param property
     * @param limitingOwnerAgent only show properties for a certain owner/agent
     * @param canDelete if delete checkbox is available
     */
    public PropertyTableGUIPanel(GUIWindow parent, IProperty property, OwnerAgentUser limitingOwnerAgent, boolean canDelete) {
        super(parent);
        propertyController = property.getPropertyController();
        this.limitingOwnerAgent = limitingOwnerAgent;
        this.canDelete = canDelete;
    }

    /**
     * Clears the selection from the table
     */
    public void clearSelection(){
        table.clearSelection();
    }

    /**
     * Creates the components for the table and scroll pane
     */
    @Override
    public void onCreate() {

        setLayout(new GridLayout(0, 1));


        table = JTable();
        table.setRowHeight(Resource().table_cell_height);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().addMouseListener(propertyController.getHeaderOnClick(this));
        refreshTable();

        table.editable = canDelete; //Enable clicking of checkbox

        JScrollPane scrollPane = JScrollPane(table);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        add(scrollPane);

    }

    /**
     * Returns the editing property
     * @return
     */
    public PropertyListing getEditingProperty(){
        return editingProperty;
    }
    /**
     * Sets the editing property
     */
    public void setEditingProperty(PropertyListing editingProp){
        editingProperty = editingProp;
    }

    /**
     * Sets the table listener when a row is clicked
     * @param listener
     */
    public void setTableListener(ListSelectionListener listener){
        table.getSelectionModel().addListSelectionListener(listener);
    }

    /**
     * Returns the table
     */
    public Table getTable(){
        return table;
    }

    private boolean isReverse;
    private Comparator<PropertyListing> lastComparator;
    private Facilities lastFilter;

    /**
     * Refreshes the table based on the sorter and filter facilities
     */
    public void refreshTable(Comparator<PropertyListing> comparator, Facilities facilities){
        var unsortedProperties = propertyController.getAllProperties();

        var filteredProperties = unsortedProperties;
        if (facilities == null){
            facilities = lastFilter;
        }
        if (facilities != null){
            var fs = facilities.facilities;
            lastFilter = facilities;
            if (fs.size() != 0){
                filteredProperties = new ArrayList<PropertyListing>();
                for (PropertyListing prop : unsortedProperties){
                    var propFacilities = prop.getFacilities().facilities;
                    boolean contains = propFacilities.containsAll(fs);
                    if (contains){
                        filteredProperties.add(prop);
                    }
                }
            }
        }
        

        boolean reverseSort = false;
        if (comparator != null && lastComparator != null){
            if (comparator.getClass().equals(lastComparator.getClass())){
                reverseSort = true;
                isReverse = !isReverse;
            }
            else{
                isReverse = false;
            }
        }
        ArrayList<PropertyListing> properties = null;
        Comparator<PropertyListing> usingComparator = comparator == null ? lastComparator : comparator;
        lastComparator = usingComparator;
        if (usingComparator == null){
            properties = filteredProperties;
        }
        else{
            properties = new ArrayList<PropertyListing>(filteredProperties);
            properties.sort(usingComparator);
            if (reverseSort && isReverse){
                Collections.reverse(properties);
            }
        }

        this.properties = properties;


        DefaultTableModel model = (DefaultTableModel)(table.getModel());
        model.setRowCount(0);
        refreshHeader(model);

        if (properties.size() != 0){
            for (PropertyListing prop : properties){
                if (limitingOwnerAgent != null){
                    if (!limitingOwnerAgent.getID().equals(prop.getOwnerAgentID())){
                        continue;
                    }
                }
                ArrayList<Object> propDisplayColumns = prop.getTableData();
                if (canDelete){
                    propDisplayColumns.add(false);
                }
                model.addRow(propDisplayColumns.toArray());
            }
        }

        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        revalidate();

    }

    /**
     * Refreshes the table only based on the sorters
     * @param comparator
     */
    public void refreshTable(Comparator<PropertyListing> comparator){
        refreshTable(comparator, null);
    }

    /**
     * Refreshes the table
     */
    public void refreshTable(){
        refreshTable(null);
    }

    /**
     * Refreshes the headers from the given model
     */
    private void refreshHeader(DefaultTableModel model){

        PropertyListing.PropertyTableMetaData propertyTableMetaData = new PropertyListing.PropertyTableMetaData();

        int columns = propertyTableMetaData.columnCount;
        model.setColumnCount(columns + (canDelete ? 1 : 0));
        TableColumnModel columnModel = table.getColumnModel();
        String[] columnHeaders = propertyTableMetaData.headers;
        int c = 0;
        for (; c < columns; c++){
            columnModel.getColumn(c).setHeaderValue(columnHeaders[c]);
            table.setEditableColumnClass(c, propertyTableMetaData.classes[c]);
        }
        if (canDelete){
            columnModel.getColumn(c).setHeaderValue(Resource().ownerAgent_str_table_header_delete);
            table.setEditableColumnClass(c, Boolean.class);
            table.addEditableColumn(c);
        }
        deletionTickboxColumn = c;

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
