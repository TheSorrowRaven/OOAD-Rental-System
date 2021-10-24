package src.Property;

import java.awt.event.*;

/**
 * Property interface to get and set data and call functions
 */
public interface IProperty {
    
    /**
     * Gets the property controller
     */
    PropertyController getPropertyController();
    
    /**
     * Gets the facility columns for displaying (auto rows)
     */
    int getFacilityIntendedColumns();
    /**
     * Gets the event when a facility is changed
     */
    ItemListener getOnFacilityChangedFor(PropertyListing.Facility facility);

    /**
     * Snaps to the top when an edit is required
     */
    void snapScrollToTop();
    /**
     * Gets the facility filter panel
     */
    PropertyFacilityFilterGUIPanel getFacilityFilterPanel();

}
