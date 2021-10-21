package src.Property;

import java.util.*;
import java.util.UUID;

import src.*;
import src.Property.PropertyListing.Facilities;
import src.Property.PropertyListing.Facility;
import src.Users.*;
import src.SystemComponents.*;

public class PropertyListing implements ISerializable<PropertyListing> {
    
    public static Resource Resource(){
        return Resource.instance();
    }

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

        Type(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }

        public static String[] stringNameValues(){
            var types = Type.values();
            String[] strings = new String[types.length];
            for (int i = 0; i < strings.length; i++){
                strings[i] = types[i].getName();
            }
            return strings;
        }

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
        Facility(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }

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
    public static class Facilities{
        public ArrayList<Facility> facilities = new ArrayList<Facility>();

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
    private String size;
    private int rooms;
    private int bathrooms;
    private Facilities facilities = new Facilities();
    private int rent;

    private UUID ownerAgentID;

    public UUID getID(){
        return id;
    }
    public void newPropertyGenerateUUID(){
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

    public void setSize(String size){
        this.size = size;
    }
    public String getSize(){
        return size;
    }

    public void setRooms(int rooms){
        this.rooms = rooms;
    }
    public int getRooms(){
        return rooms;
    }

    public void setBathrooms(int bathrooms){
        this.bathrooms = bathrooms;
    }
    public int getBathrooms(){
        return bathrooms;
    }

    public void setRent(int rent){
        this.rent = rent;
    }
    public int getRent(){
        return rent;
    }

    public void setOwnerAgent(OwnerAgentUser ownerAgent){
        ownerAgentID = ownerAgent.getID();
    }
    public UUID getOwnerAgentID(){
        return ownerAgentID;
    }
    
    public void removeFacility(Facility facility){
        facilities.remove(facility);
    }
    public void addFacility(Facility facility){
        facilities.add(facility);
    }

    public Facilities getFacilities(){
        return facilities;
    }

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

    public PropertyListing newPropertyCreate(){
        PropertyListing prop = createProperty();
        newPropertyGenerateUUID();
        return prop;
    }



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

    @Override
    public String getFilePath() {
        return Resource().propertyFile;
    }
    @Override
    public String getSaveableText() {
        return Input.combineData(id.toString(), name, address, Boolean.toString(isActive), type.toString(), size, Integer.toString(rooms), Integer.toString(bathrooms), facilities.toString(), Integer.toString(rent), ownerAgentID.toString());
    }
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

    public static PropertyListing createProperty(){
        return new PropertyListing();
    }

    public static int compareStrings(String a, String b){
        int aSize = a.length();
        int bSize = b.length();
        int min = Math.min(aSize, bSize);
        for (int i = 0; i < min; i++) {
            int aChar = (int)a.charAt(i);
            int bChar = (int)b.charAt(i);
            if (aChar != bChar) {
                return aChar - bChar;
            }
        }
        if (aSize != bSize) {
            return aSize - bSize;
        }
        return 0;
    }

}

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
class PLName implements Comparator<PropertyListing>{
    @Override
    public int compare(PropertyListing o1, PropertyListing o2) {
        String a = o1.getName();
        String b = o2.getName();

        return a.compareTo(b);
        //return PropertyListing.compareStrings(a, b);
    }
}
class PLAddress implements Comparator<PropertyListing>{
    @Override
    public int compare(PropertyListing o1, PropertyListing o2) {
        String a = o1.getAddress();
        String b = o2.getAddress();

        return a.compareTo(b);
        //return PropertyListing.compareStrings(a, b);
    }
}
class PLType implements Comparator<PropertyListing>{
    @Override
    public int compare(PropertyListing o1, PropertyListing o2) {
        PropertyListing.Type a = o1.getType();
        PropertyListing.Type b = o2.getType();
        return a.compareTo(b);
    }
}
class PLSize implements Comparator<PropertyListing>{
    @Override
    public int compare(PropertyListing o1, PropertyListing o2) {
        String a = o1.getSize();
        String b = o2.getSize();

        return a.compareTo(b);
        //return PropertyListing.compareStrings(a, b);
    }
}
class PLRooms implements Comparator<PropertyListing>{
    @Override
    public int compare(PropertyListing o1, PropertyListing o2) {
        int a = o1.getRooms();
        int b = o2.getRooms();

        return a - b;
    }
}
class PLBathrooms implements Comparator<PropertyListing>{
    @Override
    public int compare(PropertyListing o1, PropertyListing o2) {
        int a = o1.getBathrooms();
        int b = o2.getBathrooms();

        return a - b;
    }
}
class PLRent implements Comparator<PropertyListing>{
    @Override
    public int compare(PropertyListing o1, PropertyListing o2) {
        int a = o1.getRent();
        int b = o2.getRent();

        return a - b;
    }
}
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

