package src;

import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import src.SystemComponents.CLI;

import java.awt.*;

public class Table extends JTable {
    
    public boolean editable = true;

    public Table(DefaultTableModel model){
        super(model);
    }

    private HashMap<Integer, Class<?>> editableColumnToClass = new HashMap<Integer, Class<?>>();
    private HashSet<Integer> editableColumn = new HashSet<Integer>();

    public void setEditableColumnClass(int index, Class<?> c){
        editableColumnToClass.put(index, c);
    }

    public void addEditableColumn(int c){
        editableColumn.add(c);
    }

    @Override
    public Class<?> getColumnClass(int column){
        if (editableColumnToClass.containsKey(column)){
            return editableColumnToClass.get(column);
        }
        return super.getColumnClass(column);
    }

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
