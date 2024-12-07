package yuboktae.models;

import yuboktae.observer.WeatherTower;

public class Helicopter extends Aircraft {
    private WeatherTower weatherTower;

    public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }
    
    @Override
    public void updateConditions(){
        String weather = weatherTower.getWeather(coordinates);
        int latitude = coordinates.getLatitude();
        int longitude = coordinates.getLongitude();
        int height = coordinates.getHeight();
        String message = "Helicopter#" + this.name + "(" + this.id + ") ";
        switch (weather) {
            case "SUN" -> {
                longitude += 10;
                height += 2;
                message += "";
                break;
            }
            case "RAIN" -> {
                longitude += 5;
                message += "";
                break;
            }
            case "FOG" -> {
                longitude += 1;
                message += "";
                break;
            }
            case "SNOW" -> {
                height -= 12;
                message += "";
                break;
            }
        }
        weatherTower.logMessage(message);
        if (height <= 0) {
            weatherTower.unregister(this);
            weatherTower.logMessage(String.format("Helicopter#%s(%d) landing.",
                this.name,
                this.id
            ));
            weatherTower.logMessage(String.format("Tower says: Helicopter#%s(%d) unregistered from weather tower.",
                this.name,
                this.id
            ));
        }
    }
}
