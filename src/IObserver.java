package src;

/**
 * 
 * Observer DP - Observer GUIWindow changes
 * 
 */
public abstract interface IObserver {

    void contentUpdated();

}

abstract interface IObservable<TObservable extends IObservable<TObservable, EObserver>, EObserver extends IObserver>{

    TObservable getObservable();
    void observe(EObserver observer);
    void stopObserving(EObserver observer);
    void contentUpdated();
    void notifyObservers(Object o);

}

enum ViewableObservableComponents{
    none,
    creation,
    view,
    destroy,

}
interface IObserverViewable extends IObserver{

    void created(IObservableViewable viewable);
    void viewed(IObservableViewable viewable);
    void destroyed(IObservableViewable  viewable);

}
interface IObservableViewable extends IObservable<IObservableViewable, IObserverViewable>{

    void created();
    void viewed();
    void destroyed();

}