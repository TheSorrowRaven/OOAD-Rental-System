package src;

import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Raven
 * 
 * Creates a custom table to set the column class types and editable cells
 */
public class Table extends JTable {
    
    public boolean editable = true;

    /**
     * Constructor accepting a table model
     */
    public Table(DefaultTableModel model){
        super(model);
    }

    private HashMap<Integer, Class<?>> editableColumnToClass = new HashMap<Integer, Class<?>>();
    private HashSet<Integer> editableColumn = new HashSet<Integer>();

    /**
     * Sets a column class to be editable (like checkboxes)
     * @param index
     * @param c
     */
    public void setEditableColumnClass(int index, Class<?> c){
        editableColumnToClass.put(index, c);
    }

    /**
     * Adds ta column as an editable
     * @param c
     */
    public void addEditableColumn(int c){
        editableColumn.add(c);
    }

    /**
     * Gets the class of a column
     */
    @Override
    public Class<?> getColumnClass(int column){
        if (editableColumnToClass.containsKey(column)){
            return editableColumnToClass.get(column);
        }
        return super.getColumnClass(column);
    }

    /**
     * Returns the editable cells based on the settings
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        if (!editable){
            return false;
        }
        if (editableColumn.size() != 0){
            if (editableColumn.contains(column)){
                return true;
            }
            return false;
        }
        if (editableColumnToClass.containsKey(column)){
            return true;
        }
        return false;
    }

}
