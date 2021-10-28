package src;

import javax.swing.*;


import java.util.*;
import java.awt.*;
import java.text.NumberFormat;

import javax.swing.table.*;

/**
 * Raven
 * 
 * This GUIPanel serves as a GUI container (like a page container below menu bar)
 * This is a base abstract class that manages theme automatically and consists of the lifecycle of itself
 * 
 */
public abstract class GUIPanel<T extends GUIWindow> extends JPanel implements IObservableViewable, IViewable, IPanelHandler {
    
    /**
     * This serves as a quick lookup for the Resource singleton
     * @return
     */
    public static Resource Resource(){
        return Resource.instance();
    }

    /**
     * Returns itself (required for lambda/internal commands (command dp))
     * @return
     */
    public GUIPanel<T> self(){
        return this;
    }

    /**
     * Creates a new JLabel and assigning its theme
     * @param text
     * @return
     */
    public JLabel JLabel(String text){
        JLabel label = new JLabel(text);
        label.setForeground(Theme().text_color);

        return label;
    }
    /**
     * Creates a new JTextField and assigning its theme
     * @return
     */
    public JTextField JTextField(){
        JTextField textField = new JTextField();
        setTextFieldTheme(textField);

        return textField;
    }
    /**
     * Creates a new JPasswordField and assigning its theme
     * @return
     */
    public JPasswordField JPasswordField(){
        JPasswordField passwordField = new JPasswordField();
        setTextFieldTheme(passwordField);

        return passwordField;
    }
    /**
     * Creates a new JFormattedtextField and assign its theme
     */
    public JFormattedTextField JFormattedTextField(NumberFormat f){
        JFormattedTextField formattedField = new JFormattedTextField(f);
        setTextFieldTheme(formattedField);

        return formattedField;
    }
    /**
     * Sets the general text field theme
     * @param field
     */
    private void setTextFieldTheme(JTextField field){
        field.setBackground(Theme().background_color);
        field.setForeground(Theme().text_color);
        field.setBorder(Theme().border);

        field.setCaretColor(Theme().caret_color);
    }
    /**
     * Creates a JSpinner from a Spinner Model and assign its theme
     * @param model
     * @return
     */
    public JSpinner JSpinner(SpinnerModel model){
        JSpinner spinner = new JSpinner(model);
        spinner.setBackground(Theme().background_color);
        spinner.setForeground(Theme().text_color);
        spinner.setBorder(Theme().border);

        Component c = spinner.getEditor().getComponent(0);
        c.setBackground(Theme().background_color);
        c.setForeground(Theme().text_color);
        
        return spinner;
    }
    /**
     * Creates a JComboBox receiving a generic type E and assign its theme
     * @param <E>
     * @param types
     * @return
     */
    public <E> JComboBox<E> JComboBox(E[] types){
        JComboBox<E> combo = new JComboBox<>(types);
        combo.setBackground(Theme().background_color);
        combo.setForeground(Theme().text_color);
        
        return combo;
    }
    /**
     * Creates a JCheckBox receiving a text name and assign its theme
     */
    public JCheckBox JCheckBox(String text){
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setBackground(Theme().background_color);
        checkBox.setForeground(Theme().text_color);

        return checkBox;
    }
    /**
     * Creates a JTable and assign its theme
     * @return
     */
    public Table JTable(){
        DefaultTableModel model = new DefaultTableModel(1, 1);
        Table table = new Table(model);
        table.setBackground(Theme().background_color);
        table.setForeground(Theme().text_color);
        table.setGridColor(Theme().border_color);
        table.setSelectionBackground(Theme().button_hover_color);
        table.setSelectionBackground(Theme().sub_background_color);
        table.setSelectionForeground(Theme().sub_text_color);

        JTableHeader headerModel = table.getTableHeader();
        headerModel.setBackground(Theme().sub_background_color);
        headerModel.setForeground(Theme().sub_text_color);
        
        return table;
    }
    /**
     * Creates a JScrollPane and assigns its theme, including the scrollbars
     */
    public JScrollPane JScrollPane(Component o){
        JScrollPane scrollPane = new JScrollPane(o);
        scrollPane.setBackground(Theme().background_color);
        scrollPane.setForeground(Theme().text_color);
        scrollPane.setBorder(Theme().border);

        JViewport viewport = scrollPane.getViewport();
        viewport.setBackground(Theme().background_color);
        viewport.setForeground(Theme().text_color);

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setBackground(Theme().sub_background_color);

        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setBackground(Theme().sub_background_color);

        return scrollPane;
    }

    /**
     * Sets the theme of a button
     * @param button
     */
    private void setButtonTheme(Button button){
        button.setBackground(Theme().background_color);
        button.setForeground(Theme().text_color);
        button.setPressedColor(Theme().button_pressed_color);
        button.setHoverColor(Theme().button_hover_color);
        button.setBorder(Theme().border);
        button.setFocusPainted(false);
    }
    /**
     * Creates and returns a button with a string as name
     * @param text
     * @return
     */
    public Button Button(String text){
        Button button = new Button(text);
        setButtonTheme(button);
        return button;
    }
    /**
     * Creates and return a button with no name
     * @return
     */
    public Button Button(){
        Button button = new Button();
        setButtonTheme(button);
        return button;
    }


    /**
     * Returns the current theme
     * @return
     */
    public Resource.Theme Theme(){
        return parent.Theme();
    }


    public final T parent;

    public JPanel targetPanel;

    private ArrayList<GUIPanel<?>> panels = new ArrayList<GUIPanel<?>>();
    private HashMap<GUIPanel<?>, Object> panelsToLayout = new HashMap<GUIPanel<?>, Object>();
    private ArrayList<GUIPanel<?>> frozenPanels = new ArrayList<GUIPanel<?>>();

    /**
     * Returns all of the attached panels
     * @return
     */
    public ArrayList<GUIPanel<?>> getPanels(){
        return panels;
    }

    /**
     * Constructor accepting T (GUIWindow) as its parent
     * @param parent
     */
    public GUIPanel(T parent){
        this.parent = parent;
        setTargetPanel(this);
    }

    /**
     * Sets the default font of a component
     * @param comp
     */
    public void generalFont(Component comp){
        comp.setFont(Resource().general_font);
    }

    /**
     * Sets the default attributes of a component (not used)
     * @param comp
     */
    public void defaultComponentSettings(Component comp){
    }

    /**
     * Sets the target adding panel for components or attached panel
     * @param panel
     */
    public void setTargetPanel(JPanel panel){
        targetPanel = panel;
    }

    /**
     * Adds the component with constraints
     */
    @Override
    public void add(Component comp, Object constraints){
        defaultComponentSettings(comp);
        if (targetPanel instanceof GUIPanel<?> guiPanel){
            guiPanel.parentAdd(comp, constraints);
        }
        else{
            targetPanel.add(comp, constraints);
        }
    }
    /**
     * Adds the component
     */
    @Override
    public Component add(Component comp){
        defaultComponentSettings(comp);
        if (targetPanel instanceof GUIPanel<?> guiPanel){
            return guiPanel.parentAdd(comp);
        }
        else{
            return targetPanel.add(comp);
        }
    }
    /**
     * Adds the component with index
     */
    @Override
    public Component add(Component comp, int index){
        defaultComponentSettings(comp);
        if (targetPanel instanceof GUIPanel<?> guiPanel){
            return guiPanel.parentAdd(comp, index);
        }
        else{
            return targetPanel.add(comp, index);
        }
    }
    /**
     * Adds the component with constraints and index
     */
    @Override
    public void add(Component comp, Object constraints, int index){
        defaultComponentSettings(comp);
        if (targetPanel instanceof GUIPanel<?> guiPanel){
            guiPanel.parentAdd(comp, constraints, index);
        }
        else{
            targetPanel.add(comp, constraints, index);
        }
    }
    /**
     * Adds the component with a name
     */
    @Override
    public Component add(String name, Component comp){
        defaultComponentSettings(comp);
        if (targetPanel instanceof GUIPanel<?> guiPanel){
            return guiPanel.parentAdd(name, comp);
        }
        else{
            return targetPanel.add(name, comp);
        }
    }
    /**
     * Class its parent add function with a component and constraints
     * @param comp
     * @param constraints
     */
    private void parentAdd(Component comp, Object constraints){
        super.add(comp, constraints);
    }
    /**
     * Class its parent add function with a component
     * @param comp
     */
    private Component parentAdd(Component comp){
        return super.add(comp);
    }
    /**
     * Class its parent add function with a component and index
     * @param comp
     * @param index
     */
    private Component parentAdd(Component comp, int index){
        return super.add(comp, index);
    }
    /**
     * Class its parent add function with a component, constraints and index
     * @param comp
     * @param constraints
     * @param index
     */
    private void parentAdd(Component comp, Object constraints, int index){
        super.add(comp, constraints, index);
    }
    /**
     * Class its parent add function with a string name and a component
     * @param name
     * @param comp
     */
    private Component parentAdd(String name, Component comp){
        return super.add(name, comp);
    }

    /**
     * Removes the component from this panel
     */
    @Override
    public void remove(Component comp){
        if (targetPanel instanceof GUIPanel<?> guiPanel){
            guiPanel.parentRemove(comp);
        }
        else{
            targetPanel.remove(comp);
        }
    }
    /**
     * Calls its parent's remove function
     * @param comp
     */
    private void parentRemove(Component comp){
        super.remove(comp);
        repaint();
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
    /**
     * Does not add the panel but executes its entire lifecycle
     * @param panel
     */
    public void executePanelLifecycle(GUIPanel<?> panel){
        panel.onCreateInternal();
        panel.onCreatePanel();
        panel.onViewInternal();
    }
    /**
     * Attachs the a new panel with a layout
     */
    public void attachPanel(GUIPanel<?> panel, Object layout){
        attachPanel(panel);
        panelsToLayout.put(panel, layout);
    }
    /**
     * Attachs the a new panel that is frozen (created, but not displayed)
     */
    public void attachFrozenPanel(GUIPanel<?> panel){
        attachPanel(panel);
        frozenPanels.add(panel);
    }
    /**
     * Attachs the a new panel that is frozen (created, but not displayed) with layout
     */
    public void attachFrozenPanel(GUIPanel<?> panel, Object layout){
        attachPanel(panel, layout);
        frozenPanels.add(panel);
    }
    /**
     * Removes and Calls destroy when detaching panel
     */
    @Override
    public void detachPanel(GUIPanel<?> panel){
        panels.remove(panel);
        panel.onDestroyInternal();
        panelsToLayout.remove(panel);
        remove(panel);
    }
    /**
     * Swaps to a new panel while destroying the old one
     * @param oldPanel
     * @param newPanel
     */
    public void swapDestroyPanel(GUIPanel<?> oldPanel, GUIPanel<?> newPanel){
        detachPanel(oldPanel);
        attachPanel(newPanel);
        viewAttachedPanel(newPanel);
    }
    /**
     * Swaps to a new panel while destroying the old one but the new one has a layout
     * @param oldPanel
     * @param newPanel
     * @param layout
     */
    public void swapDestroyPanel(GUIPanel<?> oldPanel, GUIPanel<?> newPanel, String layout){
        detachPanel(oldPanel);
        attachPanel(newPanel, layout);
        viewAttachedPanel(newPanel);
    }
    /**
     * Switches existing panels (frozen and thawed) so they don't get destroyed and can be reused
     * @param freezingPanel
     * @param thawingPanel
     */
    public void switchExistingPanels(GUIPanel<?> freezingPanel, GUIPanel<?> thawingPanel){
        freezingPanel.onPreparingToFreeze();

        frozenPanels.add(freezingPanel);
        frozenPanels.remove(thawingPanel);

        remove(freezingPanel);
        freezingPanel.onFrozen();
        addAttachedPanel(thawingPanel);
        thawingPanel.onThawed();
    }

    /**
     * This is for child classes to initialize panel based values, such as size, components etc
     */
    public abstract void onCreate();
    /**
     * Internally call its default settings and created callback
     */
    public final void onCreateInternal(){
        defaultPanelSettings();
        onCreate();
        created();
    }

    /**
     * Sets the this panel's theme
     */
    private void defaultPanelSettings(){
        setBackground(Theme().panel_background_color);
    }

    /**
     * This is for child classes to create the panel, and attach to the view
     * Panels can attach as many panels down the hierarchy
     * Creation of panels will go down the hierarchy, then view will go down again
     */
    public abstract void onCreatePanel();
    
    /**
     * This is when everything is initialized and can view (setVisible here)
     */
    public abstract void onView();
    /**
     * Internally calls the finalizing attach panels and revalidation of the panel
     */
    public final void onViewInternal(){
        onView();
        onViewAttachPanels();
        viewed();
        revalidate();
    }

    /**
     * Calls the onView (lifecycle) of the attached panels
     */
    @Override
    public void onViewAttachPanels(){
        for (GUIPanel<?> panel : panels){
            viewAttachedPanel(panel);
        }
    }
    /**
     * Calls the on view of a panel
     * @param panel
     */
    protected void viewAttachedPanel(GUIPanel<?> panel){
        //Skip adding if is frozen
        if (!frozenPanels.contains(panel)){
            addAttachedPanel(panel);
        }
        panel.onViewInternal();
    }
    /**
     * Adds the attached panel to itself with optional layouts
     * @param panel
     */
    private void addAttachedPanel(GUIPanel<?> panel){
        JPanel initialPanel = targetPanel;
        setTargetPanel(this);
        if (panelsToLayout.containsKey(panel)){
            Object layout = panelsToLayout.get(panel);
            add(panel, layout);
        }
        else{
            add(panel);
        }
        setTargetPanel(initialPanel);
    }

    /**
     * Panel is going to switch.
     * This is called before the new panel is created (before onCreate)
     */
    public abstract void onPreparingToFreeze();

    /**
     * Panel is switched (changed to another panel) in the navigation stack
     * This is called after the new panel is viewed (onCreate and onView)
     */
    public abstract void onFrozen();

    /**
     * Viewable is switched to this (changed to this viewable) in the navigation stack
     */
    public abstract void onThawed();

    /**
     * Panel is destroted (back button)
     */
    public abstract void onDestroy();
    /**
     * Calls destroy and the callback
     */
    public final void onDestroyInternal(){
        onDestroy();
        destroyed();
    }


    /**
     * Observer implementation
     */
    public HashSet<IObserverViewable> observers = new HashSet<IObserverViewable>();
    /**
     * Returns itself as the observable
     */
    @Override
    public IObservableViewable getObservable(){
        return this;
    }
    /**
     * Observe this panel
     */
    @Override
    public void observe(IObserverViewable observer){
        observers.add(observer);
    }
    /**
     * Stop observing this panel
     */
    @Override
    public void stopObserving(IObserverViewable observer){
        observers.remove(observer);
    }
    /**
     * Notify observers when a content is updated
     */
    @Override
    public void contentUpdated(){
        notifyObservers(ViewableObservableComponents.none);
    }
    /**
     * Notify observers based on the object
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
     * Notifies the observers of the creation
     */
    @Override
    public void created(){
        notifyObservers(ViewableObservableComponents.creation);
    }
    /**
     * Notifies the observers when viewed
     */
    @Override
    public void viewed(){
        notifyObservers(ViewableObservableComponents.view);
    }
    /**
     * Notifies the observers when it is destroyed
     */
    @Override
    public void destroyed(){
        notifyObservers(ViewableObservableComponents.destroy);
    }


}
