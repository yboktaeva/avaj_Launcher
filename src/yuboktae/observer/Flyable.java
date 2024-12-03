package yuboktae.observer;


public abstract class Flyable {

    protected WeatherTower weatherTower;

    public abstract void updateConditions();

    public registerTower(WeatherTower* p_tower);
}
