package src.Property;

import java.util.*;
import src.*;
import src.SystemComponents.*;

public class Property {
    
    
    public ArrayList<PropertyListing> fetchAllProperties(){
        
        Serializer serializer = Main.instance().serializer;
        ArrayList<PropertyListing> properties = serializer.readAll(PropertyListing.createProperty());
        if (properties == null){
            properties = new ArrayList<PropertyListing>(0);
        }
        return properties;

    }

}
