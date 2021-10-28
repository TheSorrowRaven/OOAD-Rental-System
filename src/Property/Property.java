package src.Property;

import java.util.*;
import src.*;
import src.SystemComponents.*;

/**
 * Raven
 * 
 * Model for Property
 */
public class Property {
    
    /**
     * Returns all properties in an array list
     * @return
     */
    public ArrayList<PropertyListing> fetchAllProperties(){
        
        Serializer serializer = Main.instance().serializer;
        ArrayList<PropertyListing> properties = serializer.readAll(PropertyListing.createProperty());
        if (properties == null){
            properties = new ArrayList<PropertyListing>(0);
        }
        return properties;

    }
    
    /**
     * Deletes all properties selected in an arrayList
     */
    public void deleteProperties(ArrayList<PropertyListing> delete){
        
        if (delete.size() == 0){
            return;
        }
        var executionCommand = new Command<PropertyListing>(){
            @Override
            public boolean execute(PropertyListing prop, Object lineNumber){
                for (PropertyListing p : delete){
                    if (p.getID().equals(prop.getID())){
                        return true;
                    }
                }
                return false;
            }
        };
        PropertyListing dummy = PropertyListing.createProperty();
        Main.instance().serializer.removeForEach(dummy, executionCommand);
    }

}
