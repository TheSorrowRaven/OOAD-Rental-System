package src;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import javax.swing.plaf.FontUIResource;

/**
 * 
 * This GUIPanel serves as a GUI container (like a page container below menu bar)
 * 
 */
public abstract class GUIPanel<T extends GUIWindow> extends JPanel implements IObservableViewable, IViewable, IPanelHandler {
    
    //This serves as a quick lookup for the Resource singleton
    public static Resource Resource(){
        return Resource.instance();
    }

    public GUIPanel<T> self(){
        return this;
    }

    public static void setDefaultUIFont(FontUIResource f){
        Enumeration<?> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource){
                UIManager.put(key, f);
            }
        }
    }

    public static JLabel JLabel(String text){
        JLabel label = new JLabel(text);
        label.setForeground(Resource().general_text_color);

        return label;
    }
    public static JTextField JTextField(){
        JTextField textField = new JTextField();
        textField.setBackground(Resource().general_background_color);
        textField.setForeground(Resource().general_text_color);
        textField.setBorder(Resource().general_border_compound);

        textField.setCaretColor(Resource().general_caret_color);

        return textField;
    }
    public static JPasswordField JPasswordField(){
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBackground(Resource().general_background_color);
        passwordField.setForeground(Resource().general_text_color);
        passwordField.setBorder(Resource().general_border_compound);

        passwordField.setCaretColor(Resource().general_caret_color);

        return passwordField;
    }
    public static Button JButton(String text){
        Button button = new Button(text);
        button.setBackground(Resource().general_background_color);
        button.setForeground(Resource().general_text_color);
        button.setBorder(Resource().general_border_compound);
        button.setFocusPainted(false);

        return button;
    }




    public final T parent;

    private ArrayList<GUIPanel<?>> panels = new ArrayList<GUIPanel<?>>();

    public ArrayList<GUIPanel<?>> getPanels(){
        return panels;
    }

    public GUIPanel(T parent){
        this.parent = parent;
        setDefaultUIFont(Resource().general_font_resource);
    }

    public void generalFont(Component comp){
        comp.setFont(Resource().general_font);
    }

    public void defaultComponentSettings(Component comp){
    }


    @Override
    public void add(Component comp, Object constraints){
        defaultComponentSettings(comp);
        super.add(comp, constraints);
    }
    @Override
    public Component add(Component comp){
        defaultComponentSettings(comp);
        return super.add(comp);
    }
    @Override
    public Component add(Component comp, int index){
        defaultComponentSettings(comp);
        return super.add(comp, index);
    }
    @Override
    public void add(Component comp, Object constraints, int index){
        defaultComponentSettings(comp);
        super.add(comp, constraints, index);
    }
    @Override
    public Component add(String name, Component comp){
        defaultComponentSettings(comp);
        return super.add(name, comp);
    }

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
     * This is for child classes to initialize panel based values, such as size
     */
    public abstract void onCreate();
    public final void onCreateInternal(){
        defaultPanelSettings();
        onCreate();
        created();
    }

    private void defaultPanelSettings(){
        setBackground(Resource().panel_default_background_color);
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
        for (GUIPanel<?> panel : panels){
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


    protected void navigatePanelTo(GUIPanel<T> window){
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
