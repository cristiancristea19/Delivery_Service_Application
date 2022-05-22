package BusinessLogicLayer;

public interface Observable {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(Object o);
}
