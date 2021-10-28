package src;

import java.util.Stack;

/**
 * Raven
 * 
 * This Navigation class handles windows and panel flow
 * 
 */
public class Navigation implements IObserverViewable {

    /**
     * Starts a navigation class that handles navigation between windows and frames
     */
    public Navigation(){
    }
    /**
     * Start
     * @param entryWindow First window, that its life cycle has not been started
     */
    public void startNavigation(GUIWindow entryWindow){
        newWindow(entryWindow);
    }



    private Stack<GUIWindow> windowsStack = new Stack<GUIWindow>();

    /**
     * Startup lifecycle and switching of a viewable
     */
    private <T extends IViewable> void newViewable(T viewable, Stack<T> stack, ICommand<T> startCreationCommand, ICommand<T> viewCommand){
        IViewable removingWindow = null;
        if (!stack.empty()){
            removingWindow = stack.peek();
            removingWindow.onPreparingToFreeze();
        }

        stack.add(viewable);

        startCreationCommand.execute(viewable);

        if (removingWindow != null){
            removingWindow.onFrozen();
        }

        viewCommand.execute(viewable);
    }

    /**
     * Startups a new window
     */
    public void newWindow(GUIWindow window){
        newViewable(window, windowsStack, new Command<GUIWindow>(){
            @Override
            public boolean execute(GUIWindow window){
                startWindowCreation(window);
                return false;
            }
        },
        new Command<GUIWindow>(){
            @Override
            public boolean execute(GUIWindow window){
                startWindowView(window);
                return false;
            }
        });
    }

    /**
     * Sets the viewable as an observable of this
     */
    private void attachObserverToViewable(IObservableViewable viewable){
        viewable.observe(this);
    }

    /**
     * Unsets the viewable as an observable from this
     * @param viewable
     */
    private void detachObserverFromViewable(IObservableViewable viewable){
        viewable.stopObserving(this);
    }

    /**
     * Starts the creation life cycle of a window
     */
    private void startWindowCreation(GUIWindow window){
        attachObserverToViewable(window);
        window.onCreateInternal();
        window.onCreatePanel();
    }
    /**
     * Starts the internal viewing of the window
     * @param window
     */
    private void startWindowView(GUIWindow window){
        window.onViewInternal();
    }

    /**
     * Backs up the windows stack
     */
    public void backToLastWindow(){
        if (windowsStack.empty()){
            return;
        }
        GUIWindow destroyingWindow = windowsStack.pop();
        destroyingWindow.disposeWindow();

        if (windowsStack.empty()){
            return;
        }
        GUIWindow thawingWindow = windowsStack.peek();
        thawingWindow.onThawed();
    }

    /**
     * Flushes the stack, removing it from memory (sent to garbage collector)
     * This will reset the back button
     * This will also flush the panel stack
     * NOTE: When transitioning to a new stack, flush before navigating to a new window
     */
    public void flushWindowStack(){
        while (!windowsStack.isEmpty()){
            GUIWindow window = windowsStack.pop();
            destroyWindow(window);
        }
    }

    /**
     * Ends the lifecycle of a window
     * Will also flush the panel stack
     */
    private void destroyWindow(GUIWindow window){
        window.onDestroy();
        detachObserverFromViewable(window);
        //flushPanelStack();
    }

    //OBSERVER

    @Override
    public void contentUpdated() {
        
    }
    @Override
    public void created(IObservableViewable viewable) {
        
    }
    @Override
    public void viewed(IObservableViewable viewable) {
        
    }
    @Override
    public void destroyed(IObservableViewable viewable) {

    }


}
