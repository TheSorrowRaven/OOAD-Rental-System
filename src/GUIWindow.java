package src;

import java.util.HashSet;

import javax.swing.*;

/**
 * 
 * This GUIWindow serves as a base parent class to handle all windows, including popup windows
 * 
 */
public abstract class GUIWindow extends JFrame implements IObservableGUIWindow {

    /**
     * This Nav class is to hide functions to other classes except Navigation
     */
    class Nav{
        public Nav(Navigation navigation){}
        public void createCall(){
            onCreateInternal();
        }
        public void createPanelCall(){
            onCreatePanelInternal();
        }
        public void viewCall(){
            onViewInternal();
        }
    }

    //This serves as a quick lookup for the Resource singleton
    public Resource Resource(){
        return Resource.instance();
    }

    public GUIWindow(){

    }

    /**
     * This is for child classes to initialize window based values, such as size
     */
    protected abstract void onCreate();
    private void onCreateInternal(){
        onCreate();
        windowCreated();
    }

    /**
     * This is for child classes to create the panel, and attach to the view
     */
    protected abstract void onCreatePanel();
    private void onCreatePanelInternal(){
        onCreatePanel();
        panelCreated();
    }
    
    /**
     * This is when everything is initialized and can view (setVisible here)
     */
    protected abstract void onView();
    private void onViewInternal(){
        onView();
        viewed();
    }

    /**
     * Window is switched (changed to another window) in the navigation stack
     */
    protected abstract void onSwitched();
    //NOTE TO SELF: Create observer when required TODO delete this if not required

    /**
     * Window is destroted (back button)
     */
    protected abstract void onDestroy();


    protected void navigateWindowTo(GUIWindow window){
        //TODO
    }

    /**
     * Observer implementation
     */
    public HashSet<IObserver> observers;
    @Override
    public IObservable getObservable(){
        return this;
    }
    @Override
    public void observe(IObserver observer){
        observers.add(observer);
    }
    @Override
    public void stopObserving(IObserver observer){
        observers.remove(observer);
    }
    @Override
    public void contentUpdated(){
        notifyObservers(GUIWindowObservableComponents.none);
    }
    @Override
    public void notifyObservers(Object o){
        
        GUIWindowObservableComponents c = (GUIWindowObservableComponents)o;
        //Command implementation
        Command command = new Command(){
            @Override
            public void execute(Object data){
                IObserverGUIWindow observer = (IObserverGUIWindow)data;
                switch (c){
                    case none:
                        observer.contentUpdated();
                        break;
                    case windowCreation:
                        observer.windowCreated();
                        break;
                    case panelCreation:
                        observer.panelCreated();
                        break;
                    case view:
                        observer.viewed();
                        break;
                }
                observer.contentUpdated();
            }
        };
        for (IObserver observer : observers){
            command.execute(observer);
        }
    }
    @Override
    public void windowCreated(){
        notifyObservers(GUIWindowObservableComponents.windowCreation);
    }
    @Override
    public void panelCreated(){
        notifyObservers(GUIWindowObservableComponents.panelCreation);
    }
    @Override
    public void viewed(){
        notifyObservers(GUIWindowObservableComponents.view);
    }

}
