package src.Property;

import java.util.*;
import java.util.UUID;

import src.*;
import src.SystemComponents.*;

public class Property implements ISerializable<Property> {
    
    public static Resource Resource(){
        return Resource.instance();
    }

    public enum Type{

    }
    public enum Facility{

    }
    public static class Facilities{
        public ArrayList<Facility> facilities = new ArrayList<Facility>();

        public String toString(){
            return Input.combineArray(facilities);
        }
        public static Facilities fromString(String text){
            Facilities facilities = new Facilities();
            ArrayList<String> strings = Input.separateArray(text);
            for (String string : strings){
                facilities.facilities.add(Facility.valueOf(string));
            }
            return facilities;
        }

        public void add(Facility facility){
            facilities.add(facility);
        }
        public void remove(Facility facility){
            facilities.remove(facility);
        }

    }

    private UUID id;
    private String name;
    private String address;
    private boolean isActive;
    private Type type;
    private Facilities facilities;
    private int rent;

    public UUID getID(){
        return id;
    }
    private void newUserGenerateUUID(){
        id = Resource().getUUID();
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }

    public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }
    public boolean getIsActive(){
        return isActive;
    }

    public void setType(Type type){
        this.type = type;
    }
    public Type getType(){
        return type;
    }

    public void setRent(int rent){
        this.rent = rent;
    }
    public int getRent(){
        return rent;
    }
    
    public void removeFacility(Facility facility){
        facilities.remove(facility);
    }
    public void addFacility(Facility facility){
        facilities.add(facility);
    }






    //TODO
    @Override
    public String getFilePath() {
        return Resource().propertyFile;
    }
    @Override
    public String getSaveableText() {
        return Input.combineData(id.toString(), name, address, Boolean.toString(isActive), type.toString(), facilities.toString(), Integer.toString(rent));
    }
    @Override
    public Property loadSaveableText(String text) {
        String[] split = Input.separateIntoData(text);
        Property property = new Property();
        loadSaveableSplitTextIntoProperty(split, property);
        return property;
    }
    @Override
    public void onFinishLoading() {
        
    }


    protected int loadSaveableSplitTextIntoProperty(String[] split, Property property){
        int c = 0;
        property.id = Resource().getUUIDFrom(split[c++]);
        property.name = split[c++];
        property.address = split[c++];
        property.isActive = Boolean.parseBoolean(split[c++]);
        property.type = Type.valueOf(split[c++]);
        property.facilities = Facilities.fromString(split[c++]);
        property.rent = Integer.parseInt(split[c++]);
        return c;
    }

    public Property createProperty(){
        return new Property();
    }

}
