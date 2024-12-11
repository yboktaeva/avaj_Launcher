package yuboktae.models;

import yuboktae.Tracker;


public class Helicopter extends Aircraft {
    public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
        this.type = "Helicopter";
    }

    @Override
    public void updateConditions(){
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
                longitude += 10;
                height += 2;
                message += ": It\'s so sunny, even the helicopter\'s blades are getting a tan!";
                break;
            }
            case "RAIN" -> {
                longitude += 5;
                message += ": Rainy days: when your helicopter becomes a flying water park!";
                break;
            }
            case "FOG" -> {
                longitude += 1;
                message += ": Foggy days: when your helicopter thinks it\'s auditioning for a horror movie!";
                break;
            }
            case "SNOW" -> {
                height -= 12;
                message += ": My rotor is going to freeze! Can we get some heated blankets up here?";
                break;
            }
        }
        Tracker.log(message);
        this.coordinates.setLongitude(longitude);
        this.coordinates.setHeight(height);
        this.coordinates.setLatitude(latitude);
        if (this.coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
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
