package src;

//import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.*;

/**
 * 
 * This GUIWindow serves as a base parent class to handle all windows, including popup windows
 * 
 */
public abstract class GUIWindow extends JFrame implements IObservableViewable, IViewable, IPanelHandler {


    //This serves as a quick lookup for the Resource singleton
    public static Resource Resource(){
        return Resource.instance();
    }

    public GUIWindow self(){
        return this;
    }






    public final Resource.Theme theme;
    public Resource.Theme Theme(){
        return theme;
    }

    private ArrayList<GUIPanel<?>> panels = new ArrayList<GUIPanel<?>>();

    public ArrayList<GUIPanel<?>> getPanels(){
        return panels;
    }

    public GUIWindow(Resource.Theme theme){
        this.theme = theme;
    }

    public abstract String getWindowTitle();

    /**
     * Call this in onCreatePanel
     */
    @Override
    public void attachPanel(GUIPanel<?> panel){
        panels.add(panel);
        panel.onCreateInternal();
        panel.onCreatePanel();
    }
    @Override
    public void detachPanel(GUIPanel<?> panel){
        panels.remove(panel);
    }

    /**
     * This is for child classes to initialize window based values, such as size
     */
    public abstract void onCreate();
    public final void onCreateInternal(){
        defaultWindowSettings();
        defaultCreation();
        onCreate();
        created();
    }

    private void defaultWindowSettings(){
        setTitle(getWindowTitle());
        //setResizable(false);
        setBackground(Theme().window_background_color);
    }

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
    public final void onDestroyInternal(){
        onDestroy();
        destroyed();
    }
    private final void windowClosedDestroy(){
        onDestroyInternal();
    }
    public final void disposeWindow(){
        onDestroyInternal();
        dispose();
    }

    protected void navigateWindowTo(GUIWindow window){
        Main.instance().nav.newWindow(window);
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
