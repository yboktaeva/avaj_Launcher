package yuboktae.models;

import yuboktae.observer.Flyable;


public class Aircraft extends Flyable {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected String type;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinate;
        this.type = "Default";
    }

    @Override
    public String getFullName() {
        return (String.format("%s#%s(%d)",
                this.type,
                this.name,
                this.id
        ));
    }

    @Override
    public void updateConditions(){}
}