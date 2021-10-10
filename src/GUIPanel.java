package src;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * 
 * This GUIPanel serves as a GUI container (like a page container below menu bar)
 * 
 */
public abstract class GUIPanel extends JPanel implements IObservableViewable, IViewable, IPanelHandler {
    
    //This serves as a quick lookup for the Resource singleton
    public Resource Resource(){
        return Resource.instance();
    }

    public GUIPanel self(){
        return this;
    }





    public final GUIWindow parentWindow;

    private ArrayList<GUIPanel> panels = new ArrayList<GUIPanel>();

    public GUIPanel(GUIWindow parentWindow){
        this.parentWindow = parentWindow;
    }

    /**
     * Call this in onCreatePanel
     */
    @Override
    public void attachPanel(GUIPanel panel){
        panels.add(panel);
        panel.onCreate();
        panel.onCreatePanel();
    }
    @Override
    public void detachPanel(GUIPanel panel){
        panels.remove(panel);
    }

    /**
     * This is for child classes to initialize panel based values, such as size
     */
    public abstract void onCreate();
    public final void onCreateInternal(){
        onCreate();
        created();
    }

    /**
     * This is for child classes to create the panel, and attach to the view
     * Panels can attach as many panels down the hierarchy, but will cause a stack overflow if is attached together
     * Creation of panels will go down the hierarchy, then view will go down again
     */
    public abstract void onCreatePanel();
    
    /**
     * This is when everything is initialized and can view (setVisible here)
     */
    public abstract void onView();
    public final void onViewInternal(){
        onView();
        onViewAttachPanels();
        viewed();
    }

    @Override
    public void onViewAttachPanels(){
        for (GUIPanel panel : panels){
            add(panel);
            panel.onViewInternal();
        }
    }

    /**
     * Panel is going to switch.
     * This is called before the new panel is created (before onCreate)
     */
    public abstract void onPreparingToSwitch();

    /**
     * Panel is switched (changed to another panel) in the navigation stack
     * This is called after the new panel is viewed (onCreate and onView)
     */
    public abstract void onSwitchedOff();

    /**
     * Viewable is switched to this (changed to this viewable) in the navigation stack
     */
    public abstract void onSwitchedIn();

    /**
     * Panel is destroted (back button)
     */
    public abstract void onDestroy();
    public final void onDestroyInternal(){
        onDestroy();
        destroyed();
    }


    protected void navigatePanelTo(GUIPanel window){
        //TODO
    }


    /**
     * Observer implementation
     */
    public HashSet<IObserverViewable> observers = new HashSet<IObserverViewable>();
    @Override
    public IObservableViewable getObservable(){
        return this;
    }
    @Override
    public void observe(IObserverViewable observer){
        observers.add(observer);
    }
    @Override
    public void stopObserving(IObserverViewable observer){
        observers.remove(observer);
    }
    @Override
    public void contentUpdated(){
        notifyObservers(ViewableObservableComponents.none);
    }
    @Override
    public void notifyObservers(Object o){
        
        ViewableObservableComponents c = (ViewableObservableComponents)o;
        //Command implementation
        Command<IObserverViewable> command = new Command<IObserverViewable>(){
            @Override
            public boolean execute(IObserverViewable observer){
                switch (c){
                    case none:
                        observer.contentUpdated();
                        break;
                    case creation:
                        observer.created(self());
                        break;
                    case view:
                        observer.viewed(self());
                        break;
                    case destroy:
                        observer.destroyed(self());
                        break;
                }
                observer.contentUpdated();
                return false;
            }
        };
        for (IObserverViewable observer : observers){
            command.execute(observer);
        }
    }
    @Override
    public void created(){
        notifyObservers(ViewableObservableComponents.creation);
    }
    @Override
    public void viewed(){
        notifyObservers(ViewableObservableComponents.view);
    }
    @Override
    public void destroyed(){
        notifyObservers(ViewableObservableComponents.destroy);
    }


}
