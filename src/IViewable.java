package src;


public interface IViewable {

    /**
     * This is for child classes to initialize viewable based values, such as size
     */
    void onCreate();
    
    void onCreatePanel();

    /**
     * This is when everything is initialized and can view (setVisible here)
     */
    void onView();

    /**
     * Viewable is going to switch.
     * This is called before the new viewable is created (before onCreate)
     */
    void onPreparingToSwitch();

    /**
     * Viewable is switched (changed to another viewable) in the navigation stack
     * This is called after the new viewable is viewed (onCreate and onView)
     */
    void onSwitchedOff();

    /**
     * Viewable is switched to this (changed to this viewable) in the navigation stack
     */
    void onSwitchedIn();

    /**
     * Viewable is destroyed (back button)
     */
    void onDestroy();


}
