package yuboktae.models;

import yuboktae.singleton.Logger;


public class JetPlane extends Aircraft{

    public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        if (weatherTower == null) {
            throw new IllegalStateException("WeatherTower is not set");
        }
        String weather = weatherTower.getWeather(this.coordinates);
        int latitude = this.coordinates.getLatitude();
        int longitude = this.coordinates.getLongitude();
        int height = this.coordinates.getHeight();
        String message = "Jetplane#" + this.name + "(" + this.id + "): ";
        switch (weather) {
            case "SUN" -> {
                latitude += 10;
                height += 2;
                message += "SUN";
                break;
            }
            case "RAIN" -> {
                latitude += 5;
                message += "RAIN";
                break;
            }
            case "FOG" -> {
                latitude += 1;
                message += "FOG";
                break;
            }
            case "SNOW" -> {
                height -= 7;
                message += "SNOW";
                break;
            }
        }
        Logger.getLogger(message);
        this.coordinates.setLongitude(longitude);
        this.coordinates.setHeight(height);
        this.coordinates.setLatitude(latitude);
        if (this.coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
            Logger.getLogger(String.format("Jetplane#%s(%d) landing.",
                this.name,
                this.id
            ));
            Logger.getLogger(String.format("Tower says: Jetplane#%s(%d) unregistered from weather tower.",
                this.name,
                this.id
            ));
        }
    }
}
