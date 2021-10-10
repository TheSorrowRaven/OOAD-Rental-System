package src;

import java.util.Stack;
import src.*;

/**
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
    // private Stack<GUIPanel> panelStack = new Stack<GUIPanel>();

    //Startup lifecycle and switching of a viewable
    private <T extends IViewable> void newViewable(T viewable, Stack<T> stack, ICommand<T> startCreationCommand, ICommand<T> viewCommand){
        IViewable removingWindow = null;
        if (!stack.empty()){
            removingWindow = stack.peek();
            removingWindow.onPreparingToSwitch();
        }

        stack.add(viewable);

        startCreationCommand.execute(viewable);

        if (removingWindow != null){
            removingWindow.onSwitchedOff();
        }

        viewCommand.execute(viewable);
    }

    //Startups a new window
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

    // //Startups a new panel
    // public void newPanel(GUIPanel panel){
    //     newViewable(panel, panelStack, new Command<GUIPanel>(){
    //         @Override
    //         public boolean execute(GUIPanel panel){
    //             startPanelCreation(panel);
    //             return false;
    //         }
    //     });
    // }

    private void attachObserverToViewable(IObservableViewable viewable){
        viewable.observe(this);
    }

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
    private void startWindowView(GUIWindow window){
        window.onViewInternal();
    }

    // private void startPanelCreation(GUIPanel panel){
    //     attachObserverToViewable(panel);
    //     panel.onCreate();
    // }

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

    // /**
    //  * Flushes the stack, removing it from memory (sent to garbage collector)
    //  * This will reset the back button
    //  */
    // public void flushPanelStack(){
    //     while (!panelStack.isEmpty()){
    //         GUIPanel panel = panelStack.pop();
    //         destroyPanel(panel);
    //     }
    // }

    /**
     * Ends the lifecycle of a window
     * Will also flush the panel stack
     */
    private void destroyWindow(GUIWindow window){
        window.onDestroy();
        detachObserverFromViewable(window);
        //flushPanelStack();
    }

    // /**
    //  * Ends the lifecycle of a panel
    //  */
    // private void destroyPanel(GUIPanel panel){
    //     panel.onDestroy();
    //     detachObserverFromViewable(panel);
    // }

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
