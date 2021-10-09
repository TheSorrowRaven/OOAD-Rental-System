package src;

/**
 * 
 * Observer DP
 * 
 */
public interface IObserver {

    void contentUpdated();

}

interface IObservable{

    IObservable getObservable();
    void observe(IObserver observer);
    void stopObserving(IObserver observer);
    void contentUpdated();
    void notifyObservers(Object o);

}

enum GUIWindowObservableComponents{
    none,
    windowCreation,
    panelCreation,
    view,

}
interface IObserverGUIWindow extends IObserver{

    void windowCreated();
    void panelCreated();
    void viewed();

}
interface IObservableGUIWindow extends IObservable{

    void windowCreated();
    void panelCreated();
    void viewed();

}