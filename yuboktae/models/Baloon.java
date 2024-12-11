package yuboktae.models;

import yuboktae.Tracker;


public class Baloon extends Aircraft {
    public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
        this.type = "Baloon";
    }

    @Override
    public void updateConditions() {
        if (weatherTower == null) {
            throw new IllegalStateException("WeatherTower is not set");
        }
        String weather = this.weatherTower.getWeather(this.coordinates);
        int latitude = this.coordinates.getLatitude();
        int longitude = this.coordinates.getLongitude();
        int height = this.coordinates.getHeight();
        String message = this.getFullName();
        switch (weather) {
            case "SUN" -> {
                longitude += 2;
                height += 4;
                message += ": Let\'s soak up the sun and capture those Instagram moments!";
                break;
            }
            case "RAIN" -> {
                height -= 5;
                message += ": Damn you rain! You\'ve turned my balloon into a soggy sponge!";
                break;
            }
            case "FOG" -> {
                height -= 3;
                message += ": Is this fog or am I just lost in my own thoughts?";
                break;
            }
            case "SNOW" -> {
                height -= 15;
                message += ": Snow in the air? Time to see if my balloon can float like a snowflake!";
                break;
            }
        }
        Tracker.log(message);
        this.coordinates.setLongitude(longitude);
        this.coordinates.setLatitude(latitude);
        this.coordinates.setHeight(height);
        if (this.coordinates.getHeight() <= 0) {
            this.weatherTower.unregister(this);
            Tracker.log(String.format("%s landing. At %s.",
                this.getFullName(),
                this.coordinates.getCoordinates()
            ));
            Tracker.log(String.format("Tower says: %s unregistered from weather tower.",
                this.getFullName()
            ));
        }
    }
}
