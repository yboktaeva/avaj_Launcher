package yuboktae.models;

import yuboktae.observer.Flyable;


public class Aircraft extends Flyable {
    protected long id;
    protected String name;
    protected Coordinates coordinates;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinate;
    }

    public String getName() {
        return (name);
    }

    public long getId() {
        return (id);
    }

    @Override
    public void updateConditions(){}
}
