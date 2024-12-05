package yuboktae.observer;

import java.util.ArrayList;
import java.util.List;


public class Tower {
    private List <Flyable> observers = new ArrayList<>();

    public void register(Flyable p_flyable) {
        observers.add(p_flyable);
        System.out.println("Tower says:" + p_flyable.name  "registered to weather tower");
    }

    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);

    }
    
    protected void conditionChanged() {
        for (Flyable observer : observers) {
            observer.updateConditions();
        }
    }
}
