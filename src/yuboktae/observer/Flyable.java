package yuboktae.observer;


public abstract class Flyable {
    protected long id;
    protected String name;
    protected WeatherTower weatherTower;

    public abstract void updateConditions();

    public String getName() {
        return (name);
    }

    public long getId() {
        return (id);
    }

    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
    }
}
