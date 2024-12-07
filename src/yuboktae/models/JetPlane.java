package yuboktae.models;

import yuboktae.observer.WeatherTower;

public class JetPlane extends Aircraft{
    private WeatherTower weatherTower;

    public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        int latitude = coordinates.getLatitude();
        int longitude = coordinates.getLongitude();
        int height = coordinates.getHeight();
        String message = "Jetplane#" + this.name + "(" + this.id + ") ";
        switch (weather) {
            case "SUN" -> {
                latitude += 10;
                height += 2;
                message += "";
                break;
            }
            case "RAIN" -> {
                latitude += 5;
                message += "";
                break;
            }
            case "FOG" -> {
                latitude += 1;
                message += "";
                break;
            }
            case "SNOW" -> {
                height -= 7;
                message += "";
                break;
            }
        }
        weatherTower.logMessage(message);
        if (height <= 0) {
            weatherTower.unregister(this);
            weatherTower.logMessage(String.format("Jetplane#%s(%d) landing.",
                this.name,
                this.id
            ));
            weatherTower.logMessage(String.format("Tower says: Jetplane#%s(%d) unregistered from weather tower.",
                this.name,
                this.id
            ));
        }
    }
}
