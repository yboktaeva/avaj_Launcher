package yuboktae.models;

import yuboktae.Logger;


public class JetPlane extends Aircraft {

    public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
        this.type = "Jetplane";
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
        String message = this.getFullName();
        switch (weather) {
            case "SUN" -> {
                latitude += 10;
                height += 2;
                message += ": SUN";
                break;
            }
            case "RAIN" -> {
                latitude += 5;
                message += ": RAIN";
                break;
            }
            case "FOG" -> {
                latitude += 1;
                message += ": FOG";
                break;
            }
            case "SNOW" -> {
                height -= 7;
                message += ": SNOW";
                break;
            }
        }
        Logger.log(message);
        this.coordinates.setLongitude(longitude);
        this.coordinates.setHeight(height);
        this.coordinates.setLatitude(latitude);
        if (this.coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
            Logger.log(String.format("%s landing.",
                this.getFullName()
            ));
            Logger.log(String.format("Tower says: %s unregistered from weather tower.",
                this.getFullName()
            ));
        }
    }
}
