package yuboktae.models;

import yuboktae.observer.WeatherTower;


public class Baloon extends Aircraft {
    private WeatherTower weatherTower;

    public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        int latitude = coordinates.getLatitude();
        int longitude = coordinates.getLongitude();
        int height = coordinates.getHeight();
        String message = "Baloon#" + this.name + "(" + this.id + ") ";
        switch (weather) {
            case "SUN" -> {
                longitude += 2;
                height += 4;
                message += "";
                break;
            }
            case "RAIN" -> {
                height -= 5;
                message += "";
                break;
            }
            case "FOG" -> {
                height -= 3;
                message += "";
                break;
            }
            case "SNOW" -> {
                height -= 15;
                message += "";
                break;
            }
        }
        weatherTower.logMessage(message);
        if (height <= 0) {
            weatherTower.unregister(this);
            weatherTower.logMessage(String.format("Baloon#%s(%d) landing.",
                this.name,
                this.id
            ));
            weatherTower.logMessage(String.format("Tower says: Baloon#%s(%d) unregistered from weather tower.",
                this.name,
                this.id
            ));
        }
    }
}
