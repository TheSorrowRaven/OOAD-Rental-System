package src;

import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import src.SystemComponents.CLI;

import java.awt.*;

public class Table extends JTable {
    
    public Table(DefaultTableModel model){
        super(model);
    }

    private HashMap<Integer, Class<?>> editableColumnToClass = new HashMap<Integer, Class<?>>();

    public void setEditableColumnClass(int index, Class<?> c){
        editableColumnToClass.put(index, c);
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
        if (editableColumnToClass.containsKey(column)){
            return true;
        }
        return false;
    }

}
