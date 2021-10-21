package src;

import java.util.ArrayList;

/**
 * This Input class handles string inputs like login details, property names etc for easier saving and loading
 */
public class Input {
    
    public static Resource Resource(){
        return Resource.instance();
    }

    /**
     * Combines an array of Strings into one by inserting the split character
     */
    public static String combineData(String... data){
        if (data == null || data.length == 0){
            return null;
        }
        StringBuilder str = new StringBuilder(checkNull(data[0]));
        for (int i = 1; i < data.length; i++){
            str.append(Resource().splittingInputCharacter);
            str.append(checkNull(data[i]));
        }
        return str.toString();
    }

    public static String[] separateIntoData(String combined){
        String[] split = combined.split(Resource().splittingInputCharacterStr());
        for (int i = 0; i < split.length; i++){
            if (split[i].equals(Character.toString(Resource().nullPlacementCharacter))){
                split[i] = null;
            }
        }
        return split;
    }

    public static <T> String combineArray(ArrayList<T> array){
        if (array == null || array.size() == 0){
            return "";
        }
        StringBuilder str = new StringBuilder(checkNull(array.get(0).toString()));
        for (int i = 1; i < array.size(); i++){
            T value = array.get(i);
            String text = value.toString();
            text = checkNull(text);

            str.append(Resource().arraySplittingCharacter);
            str.append(text);
        }
        return str.toString();
    }


    public static ArrayList<String> separateArray(String arrayText){
        ArrayList<String> array = new ArrayList<String>();
        if (arrayText == null || arrayText.equals("")){
            return array;
        }
        String[] split = arrayText.split(Resource().arraySplittingCharacterStr());
        for (String text : split){
            array.add(text);
        }
        return array;
    }

    /**
     * For empty inputs, if ended if null, may cause splitting inconsistencies/wrong ordering so this exists
     * @param text
     * @return
     */
    private static String checkNull(String text){
        if (text == null){
            return "";
        }
        return text;
    }


}
