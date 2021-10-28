package src.Property;

import java.util.*;
import java.util.UUID;

import src.*;
import src.Property.PropertyListing.Facilities;
import src.Property.PropertyListing.Facility;
import src.Users.*;
import src.SystemComponents.*;

/**
 * Raven
 * 
 * This is the object (saveable) of the property
 */
public class PropertyListing implements ISerializable<PropertyListing> {
    
    /**
     * Shorthand for the Resource singleton
     */
    public static Resource Resource(){
        return Resource.instance();
    }

    /**
     * Type of property
     */
    public enum Type{

        Mansion("Mansion"),
        Bungalow("Bungalow"),
        SemiD("Semi-D"),
        Terrace("Terrace"),
        Townhouse("Townhouse"),
        Apartment("Apartment"),
        DoubleStorey("Double Storey"),
        TripleStorey("Triple Storey"),
        Condominium("Condominium"),
        ;

        private final String name;

        /**
         * Enum Constructor to create the display text
         * @param name
         */
        Type(String name){
            this.name = name;
        }
        /**
         * Returns the name of the enum defined
         */
        public String getName(){
            return name;
        }

        /**
         * Returns all of the enums in string format (their names)
         * @return
         */
        public static String[] stringNameValues(){
            var types = Type.values();
            String[] strings = new String[types.length];
            for (int i = 0; i < strings.length; i++){
                strings[i] = types[i].getName();
            }
            return strings;
        }

        /**
         * Returns the converted type from string (name)
         * @param text
         * @return
         */
        public static Type getFromString(String text){
            var types = Type.values();
            for (Type type : types){
                if (text.equals(type.getName())){
                    return type;
                }
            }
            return null;
        }

    }
    /**
     * The Facilitiy provided of the property
     */
    public enum Facility{

        Wifi("WiFi"),
        Aircon("Air Conditioner"),
        WaterHeater("Water Heater"),
        SwimmingPool("Swimming Pool"),
        Gym("Gym"),
        Security("Security"),
        Kitchen("Kitchen"),
        ;

        private final String name;
        /**
         * Constructor to define the display name
         * @param name
         */
        Facility(String name){
            this.name = name;
        }
        /**
         * Returns the display name
         * @return
         */
        public String getName(){
            return name;
        }

        /**
         * Returns the facility from the display name
         */
        public static Facility getFromString(String text){
            var facilities = Facility.values();
            for (Facility f : facilities){
                if (text.equals(f.getName())){
                    return f;
                }
            }
            return null;
        }
    }
    /**
     * Facilities container
     */
    public static class Facilities{
        public ArrayList<Facility> facilities = new ArrayList<Facility>();

        /**
         * Converts the facilities into a display string by adding commas
         * @return
         */
        public String displayString(){
            String text = "";
            for (int i = 0; i < facilities.size(); i++){
                Facility f = facilities.get(i);
                text += f.getName();
                if ((i + 1) != facilities.size()){
                    text += ", ";
                }
            }
            return text;
        }
        /**
         * Creates a new object of this class from the display string above
         * @param text
         * @return
         */
        public static Facilities createFromDisplayString(String text){
            Facilities f = new Facilities();
            String[] split = text.split(", ");
            var allF = Facility.values();
            for (int i = 0; i < split.length; i++){
                for (int j = 0; j < allF.length; j++){
                    if (allF[j].getName().equals(split[i])){
                        f.facilities.add(Facility.valueOf(split[i]));
                        break;
                    }
                }
            }
            return f;
        }

        /**
         * Combines and returns the string for saving
         */
        public String toString(){
            return Input.combineArray(facilities);
        }
        /**
         * Uncombine and returns the facilities from the string above
         * @param text
         * @return
         */
        public static Facilities fromString(String text){
            Facilities facilities = new Facilities();
            ArrayList<String> strings = Input.separateArray(text);
            for (String string : strings){
                facilities.facilities.add(Facility.valueOf(string));
            }
            return facilities;
        }

        /**
         * Adds a facility
         * @param facility
         */
        public void add(Facility facility){
            facilities.add(facility);
        }
        /**
         * Removes a facility
         * @param facility
         */
        public void remove(Facility facility){
            facilities.remove(facility);
        }

    }

    private UUID id;
    private String name;
    private String address;
    private boolean isActive;
    private Type type;
    private String size;
    private int rooms;
    private int bathrooms;
    private Facilities facilities = new Facilities();
    private int rent;

    private UUID ownerAgentID;

    /**
     * Returns the ID
     * @return
     */
    public UUID getID(){
        return id;
    }
    /**
     * Generates an new ID for this
     */
    public void newPropertyGenerateUUID(){
        id = Resource().getUUID();
    }

    /**
     * Sets the name of the property
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * Returns the name of the property
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the address of the property
     */
    public void setAddress(String address){
        this.address = address;
    }
    /**
     * Returns the address of the property
     * @return
     */
    public String getAddress(){
        return address;
    }

    /**
     * Sets the active status of the property
     */
    public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }
    /**
     * Returns the active status of the property
     * @return
     */
    public boolean getIsActive(){
        return isActive;
    }

    /**
     * Sets the type of the property
     * @param type
     */
    public void setType(Type type){
        this.type = type;
    }
    /**
     * Returns the type of the property
     */
    public Type getType(){
        return type;
    }

    /**
     * Sets the size of the property
     */
    public void setSize(String size){
        this.size = size;
    }
    /**
     * Returns the size of the property
     */
    public String getSize(){
        return size;
    }

    /**
     * Sets the number of rooms of the property
     * @param rooms
     */
    public void setRooms(int rooms){
        this.rooms = rooms;
    }
    /**
     * Returns the number of rooms of the property
     * @return
     */
    public int getRooms(){
        return rooms;
    }

    /**
     * Sets the number of bathrooms of the property
     * @param bathrooms
     */
    public void setBathrooms(int bathrooms){
        this.bathrooms = bathrooms;
    }
    /**
     * Returns the number of bathrooms of the property
     * @return
     */
    public int getBathrooms(){
        return bathrooms;
    }

    /**
     * Sets the rent of the property
     */
    public void setRent(int rent){
        this.rent = rent;
    }
    /**
     * Returns the rent of the property
     */
    public int getRent(){
        return rent;
    }

    /**
     * Sets the owner or agent of the property
     */
    public void setOwnerAgent(OwnerAgentUser ownerAgent){
        ownerAgentID = ownerAgent.getID();
    }
    /**Returns the owner or agent's ID */
    public UUID getOwnerAgentID(){
        return ownerAgentID;
    }
    
    /**
     * Remove the facility from the property
     */
    public void removeFacility(Facility facility){
        facilities.remove(facility);
    }
    /**
     * Adds the facility from the property
     */
    public void addFacility(Facility facility){
        facilities.add(facility);
    }

    /**
     * Returns the faciltiies of the property
     * @return
     */
    public Facilities getFacilities(){
        return facilities;
    }

    /**
     * Sets all of the values of the property
     * @param active
     * @param name
     * @param address
     * @param type
     * @param size
     * @param rooms
     * @param bathrooms
     * @param rent
     * @param facilities
     * @param ownerAgent
     */
    public void setAll(boolean active, String name, String address, Type type, String size, int rooms, int bathrooms, int rent, Facilities facilities, OwnerAgentUser ownerAgent){
        this.isActive = active;
        this.name = name;
        this.address = address;
        this.type = type;
        this.size = size;
        this.rooms = rooms;
        this.bathrooms = bathrooms;
        this.rent = rent;
        this.facilities = facilities;
        setOwnerAgent(ownerAgent);
    }

    /**
     * Creates a new property and generate a new id
     */
    public PropertyListing newPropertyCreate(){
        PropertyListing prop = createProperty();
        newPropertyGenerateUUID();
        return prop;
    }



    /**
     * Object to define the display data in table
     */
    public static class PropertyTableMetaData{

        public final int columnCount = 9;
        public final int facilitiesIndex = 8;

        public final String[] headers = new String[]{
            Resource().str_active,
            Resource().str_name,
            Resource().str_address,
            Resource().str_type,
            Resource().str_size,
            Resource().str_rooms,
            Resource().str_bathrooms,
            Resource().str_rent,
            Resource().str_facilities
        };

        public final Class<?>[] classes = new Class<?>[]{
            Boolean.class,
            String.class,
            String.class,
            String.class,
            String.class,
            Integer.class,
            Integer.class,
            Integer.class,
            String.class,
        };

        /**
         * Returns the sorter when each header is clicked
         * @param column
         * @return
         */
        public final Comparator<PropertyListing> getComparatorFromColumn(int column){
            switch (column){
                case 0: return new PLActive();
                case 1: return new PLName();
                case 2: return new PLAddress();
                case 3: return new PLType();
                case 4: return new PLSize();
                case 5: return new PLRooms();
                case 6: return new PLBathrooms();
                case 7: return new PLRent();
                case 8: return new PLFacilities();
            }
            return null;
        }

    }

    /**
     * Returns the table data in a list format
     * @return
     */
    public ArrayList<Object> getTableData(){
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(isActive);
        data.add(name);
        data.add(address);
        data.add(type.getName());
        data.add(size);
        data.add(rooms);
        data.add(bathrooms);
        data.add(rent);
        data.add(facilities.displayString());
        return data;
    }

    /**
     * Returns the file path of the property
     */
    @Override
    public String getFilePath() {
        return Resource().propertyFile;
    }
    /**
     * Returns the saveable text of the property by combining all the elements
     */
    @Override
    public String getSaveableText() {
        return Input.combineData(id.toString(), name, address, Boolean.toString(isActive), type.toString(), size, Integer.toString(rooms), Integer.toString(bathrooms), facilities.toString(), Integer.toString(rent), ownerAgentID.toString());
    }
    /**
     * Decompiles the text data from the save into a property
     */
    @Override
    public PropertyListing loadSaveableText(String text) {
        String[] split = Input.separateIntoData(text);
        PropertyListing property = new PropertyListing();
        loadSaveableSplitTextIntoProperty(split, property);
        return property;
    }
    @Override
    public void onFinishLoading() {
        
    }


    /**
     * Loads the saveable split text into the property
     * @param split
     * @param property
     * @return
     */
    protected int loadSaveableSplitTextIntoProperty(String[] split, PropertyListing property){
        int c = 0;
        property.id = Resource().getUUIDFrom(split[c++]);
        property.name = split[c++];
        property.address = split[c++];
        property.isActive = Boolean.parseBoolean(split[c++]);
        property.type = Type.valueOf(split[c++]);
        property.size = split[c++];
        property.rooms = Integer.parseInt(split[c++]);
        property.bathrooms = Integer.parseInt(split[c++]);
        property.facilities = Facilities.fromString(split[c++]);
        property.rent = Integer.parseInt(split[c++]);
        property.ownerAgentID = Resource().getUUIDFrom(split[c++]);
        return c;
    }

    /**
     * Creates a new property
     */
    public static PropertyListing createProperty(){
        return new PropertyListing();
    }

}

/**
 * Propert Listing Active Sorter
 */
class PLActive implements Comparator<PropertyListing>{

    @Override
    public int compare(PropertyListing o1, PropertyListing o2) {
        boolean a = o1.getIsActive();
        boolean b = o2.getIsActive();
        if (a && b){
            return 0;
        }
        if (a){
            return -1;
        }
        if (b){
            return 1;
        }
        return 0;
    }

}
/**
 * Property Listing Name sorter
 */
class PLName implements Comparator<PropertyListing>{
    @Override
    public int compare(PropertyListing o1, PropertyListing o2) {
        String a = o1.getName();
        String b = o2.getName();

        return a.compareTo(b);
        //return PropertyListing.compareStrings(a, b);
    }
}
/**
 * Property Listing Address sorter
 */
class PLAddress implements Comparator<PropertyListing>{
    @Override
    public int compare(PropertyListing o1, PropertyListing o2) {
        String a = o1.getAddress();
        String b = o2.getAddress();

        return a.compareTo(b);
        //return PropertyListing.compareStrings(a, b);
    }
}
/**
 * Property Listing Type sorter
 */
class PLType implements Comparator<PropertyListing>{
    @Override
    public int compare(PropertyListing o1, PropertyListing o2) {
        PropertyListing.Type a = o1.getType();
        PropertyListing.Type b = o2.getType();
        return a.compareTo(b);
    }
}
/**
 * Property Listing Size sorter
 */
class PLSize implements Comparator<PropertyListing>{
    @Override
    public int compare(PropertyListing o1, PropertyListing o2) {
        String a = o1.getSize();
        String b = o2.getSize();

        return a.compareTo(b);
        //return PropertyListing.compareStrings(a, b);
    }
}
/**
 * Property Listing rooms sorter
 */
class PLRooms implements Comparator<PropertyListing>{
    @Override
    public int compare(PropertyListing o1, PropertyListing o2) {
        int a = o1.getRooms();
        int b = o2.getRooms();

        return a - b;
    }
}
/**
 * Property Listing bathrooms sorter
 */
class PLBathrooms implements Comparator<PropertyListing>{
    @Override
    public int compare(PropertyListing o1, PropertyListing o2) {
        int a = o1.getBathrooms();
        int b = o2.getBathrooms();

        return a - b;
    }
}
/**
 * Property Listing rent sorter
 */
class PLRent implements Comparator<PropertyListing>{
    @Override
    public int compare(PropertyListing o1, PropertyListing o2) {
        int a = o1.getRent();
        int b = o2.getRent();

        return a - b;
    }
}
/**
 * Property Listing Facilities sorter
 */
class PLFacilities implements Comparator<PropertyListing>{
    @Override
    public int compare(PropertyListing o1, PropertyListing o2) {
        Facilities aFFs = o1.getFacilities();
        Facilities bFFs = o2.getFacilities();
        if (aFFs == null && bFFs == null){
            return 0;
        }
        if (aFFs == null){
            return -1;
        }
        if (bFFs == null){
            return 1;
        }

        var aFs = aFFs.facilities;
        var bFs = bFFs.facilities;
        int min = Math.min(aFs.size(), bFs.size());
        for (int i = 0; i < min; i++){
            Facility aF = aFs.get(i);
            Facility bF = bFs.get(i);
            if (aF.equals(bF)){
                continue;
            }
            return aF.getName().compareTo(bF.getName());
        }

        if (aFs.size() == bFs.size()){
            return 0;
        }
        if (aFs.size() > bFs.size()){
            return 1;
        }
        return -1;
    }
}

