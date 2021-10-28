package src;

//import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.*;

/**
 * Raven
 * 
 * This GUIWindow serves as a base parent class to handle all windows, including popup windows
 * 
 */
public abstract class GUIWindow extends JFrame implements IObservableViewable, IViewable, IPanelHandler {


    /**
     * This serves as a quick lookup for the Resource singleton
     */
    public static Resource Resource(){
        return Resource.instance();
    }

    /**
     * Returns itself
     * @return
     */
    public GUIWindow self(){
        return this;
    }






    public final Resource.Theme theme;
    /**
     * Returns the current theme
     * @return
     */
    public Resource.Theme Theme(){
        return theme;
    }

    private ArrayList<GUIPanel<?>> panels = new ArrayList<GUIPanel<?>>();

    /**
     * Returns the attached panels
     * @return
     */
    public ArrayList<GUIPanel<?>> getPanels(){
        return panels;
    }

    /**
     * Constructor accepting a theme
     */
    public GUIWindow(Resource.Theme theme){
        this.theme = theme;
    }

    /**
     * Gets the title of the window
     * @return
     */
    public abstract String getWindowTitle();

    /**
     * Attachs a panel to this window
     */
    @Override
    public void attachPanel(GUIPanel<?> panel){
        panels.add(panel);
        panel.onCreateInternal();
        panel.onCreatePanel();
    }
    /**
     * Dettachs a panel from this panel
     */
    @Override
    public void detachPanel(GUIPanel<?> panel){
        panels.remove(panel);
    }

    /**
     * This is for child classes to initialize window based values, such as size
     */
    public abstract void onCreate();
    /**
     * Calls its default settings and callback to create
     */
    public final void onCreateInternal(){
        defaultWindowSettings();
        defaultCreation();
        onCreate();
        created();
    }

    /**
     * Sets the default window settings including the window name and background color
     */
    private void defaultWindowSettings(){
        setTitle(getWindowTitle());
        //setResizable(false);
        setBackground(Theme().window_background_color);
    }

    /**
     * Creates the default close behaviour
     */
    private void defaultCreation(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowListener(){


            @Override
            public void windowClosing(WindowEvent e) {
                windowClosedDestroy();
                e.getWindow().dispose();
            }

            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {}
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
    }

    /**
     * This is for child classes to create the panel, and attach to the view
     */
    public abstract void onCreatePanel();
    
    /**
     * This is when everything is initialized and can view (setVisible here)
     */
    public abstract void onView();
    /**
     * Calls on view, attach and viewed callback
     */
    public final void onViewInternal(){
        onView();
        onViewAttachPanels();
        viewed();
    }

    @Override
    public void onViewAttachPanels(){
        for (GUIPanel<?> panel : panels){
            add(panel);
            panel.onViewInternal();
        }
    }

    /**
     * Window is going to switch.
     * This is called before the new window is created (before onCreate)
     */
    public abstract void onPreparingToFreeze();

    /**
     * Window is switched (changed to another window) in the navigation stack
     * This is called after the NEW window is viewed (onCreate, onCreatePanel and onView)
     * Set Invisible here
     */
    public abstract void onFrozen();


    /**
     * Viewable is switched to this (changed to this viewable) in the navigation stack
     * Set Visible here
     */
    public abstract void onThawed();

    /**
     * Window is destroyed (back button)
     */
    public abstract void onDestroy();
    /**
     * Calls the callback after calling destroy
     */
    public final void onDestroyInternal(){
        onDestroy();
        destroyed();
    }
    /**
     * Calls the internal destroy when the window is closed
     */
    private final void windowClosedDestroy(){
        onDestroyInternal();
    }
    /**
     * Calls the internal destroy and dispose of the window
     */
    public final void disposeWindow(){
        onDestroyInternal();
        dispose();
    }

    /**
     * Navigate the window to another window
     * @param window
     */
    protected void navigateWindowTo(GUIWindow window){
        Main.instance().nav.newWindow(window);
    }

    /**
     * Observer implementation
     */
    public HashSet<IObserverViewable> observers = new HashSet<IObserverViewable>();
    /**
     * Returns this observable
     */
    @Override
    public IObservableViewable getObservable(){
        return this;
    }
    /**
     * Observe this
     */
    @Override
    public void observe(IObserverViewable observer){
        observers.add(observer);
    }
    /**
     * Stop observing this
     */
    @Override
    public void stopObserving(IObserverViewable observer){
        observers.remove(observer);
    }
    /**
     * Content is updated, notify observers
     */
    @Override
    public void contentUpdated(){
        notifyObservers(ViewableObservableComponents.none);
    }
    /**
     * Notify observers based on an object
     */
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
    /**
     * Notify observers after creation
     */
    @Override
    public void created(){
        notifyObservers(ViewableObservableComponents.creation);
    }
    /**
     * Notify observers after view
     */
    @Override
    public void viewed(){
        notifyObservers(ViewableObservableComponents.view);
    }
    /**Notify observers after destruction */
    @Override
    public void destroyed(){
        notifyObservers(ViewableObservableComponents.destroy);
    }

}
