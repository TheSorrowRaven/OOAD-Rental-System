package src;

/**
 * Raven
 * 
 * Observer DP - Observer GUIWindow changes
 * 
 */
public abstract interface IObserver {

    /**
     * A content is updated
     */
    void contentUpdated();

}

/**
 * Raven
 * 
 * Observable
 */
abstract interface IObservable<TObservable extends IObservable<TObservable, EObserver>, EObserver extends IObserver>{

    /**
     * Returns the observable
     * @return
     */
    TObservable getObservable();
    /**
     * Observe an observer
     * @param observer
     */
    void observe(EObserver observer);
    /**
     * Stops observing an observer
     */
    void stopObserving(EObserver observer);
    /**
     * Notify when a content is updated
     */
    void contentUpdated();
    /**
     * Notify based on an object
     */
    void notifyObservers(Object o);

}

/**
 * Raven
 * 
 * Defines what function the object needs to notify
 */
enum ViewableObservableComponents{
    none,
    creation,
    view,
    destroy,

}
/**
 * Raven
 * 
 * A Viewable (Window and Panel) observer
 */
interface IObserverViewable extends IObserver{

    /**
     * When the viewable is created
     * @param viewable
     */
    void created(IObservableViewable viewable);
    /**
     * When the viewable is viewed
     * @param viewable
     */
    void viewed(IObservableViewable viewable);
    /**
     * When the viewable is destroyed
     */
    void destroyed(IObservableViewable viewable);

}
/**
 * Raven
 * 
 * A Viewable observable
 */
interface IObservableViewable extends IObservable<IObservableViewable, IObserverViewable>{

    /**
     * Created notification
     */
    void created();
    /**
     * Viewed notification
     */
    void viewed();
    /**
     * Destroyed notification
     */
    void destroyed();

}