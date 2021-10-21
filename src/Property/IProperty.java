package src.Property;

import java.awt.event.*;

public interface IProperty {
    
    PropertyController getPropertyController();
    boolean isEditable();

    int getFacilityIntendedColumns();
    ItemListener getOnFacilityChangedFor(PropertyListing.Facility facility);

    void snapScrollToTop();
    PropertyFacilityFilterGUIPanel getFacilityFilterPanel();

}
