package src.OwnerAgent;

import java.util.ArrayList;

import src.*;
import src.Property.*;
import src.Users.OwnerAgentUser;

public class OwnerAgent {
    
    public void createProperty(PropertyListing prop){
        Main.instance().serializer.write(prop);
    }

    public void createProperty(boolean active, String name, String address, PropertyListing.Type type, String size, int rooms, int bathrooms, int rent, PropertyListing.Facilities facilities, OwnerAgentUser ownerAgent){
        PropertyListing prop = PropertyListing.createProperty();
        prop.newPropertyGenerateUUID();
        prop.setAll(active, name, address, type, size, rooms, bathrooms, rent, facilities, ownerAgent);

        createProperty(prop);
    }

    public void editProperty(PropertyListing prop, boolean active, String name, String address, PropertyListing.Type type, String size, int rooms, int bathrooms, int rent, PropertyListing.Facilities facilities, OwnerAgentUser ownerAgent){
        prop.setAll(active, name, address, type, size, rooms, bathrooms, rent, facilities, ownerAgent);

        var command = new Command<PropertyListing>(){
            
            public int lineNumber;

            @Override
            public boolean execute(PropertyListing data, Object add) {
                if (data.getID().equals(prop.getID())){
                    lineNumber = (int)add;
                    return true;
                }
                return false;
            }
        };

        Main.instance().serializer.readForEach(prop, command);
        int lineNumber = command.lineNumber;

        Main.instance().serializer.remove(prop, lineNumber);
        Main.instance().serializer.write(prop);

    }

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