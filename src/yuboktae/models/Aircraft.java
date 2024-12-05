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

    @Override
    public void updateConditions(){
        System.out.println("Aircraft is updating its conditions.");
    }
}
