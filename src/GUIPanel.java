package src;

import javax.swing.*;

/**
 * 
 * This GUIPanel serves as a GUI container (like a page container below menu bar)
 * 
 */
public abstract class GUIPanel extends JPanel {
    
    //This serves as a quick lookup for the Resource singleton
    public Resource Resource(){
        return Resource.instance();
    }




}
