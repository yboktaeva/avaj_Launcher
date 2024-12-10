package yuboktae.models;

import yuboktae.singleton.Logger;

public class Baloon extends Aircraft {
    public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
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
        String message = "Baloon#" + this.name + "(" + this.id + "): ";
        switch (weather) {
            case "SUN" -> {
                longitude += 2;
                height += 4;
                message += "SUN";
                break;
            }
            case "RAIN" -> {
                height -= 5;
                message += "RAIN";
                break;
            }
            case "FOG" -> {
                height -= 3;
                message += "FOG";
                break;
            }
            case "SNOW" -> {
                height -= 15;
                message += "SNOW";
                break;
            }
        }
        Logger.getLogger(message);
        this.coordinates.setLongitude(longitude);
        this.coordinates.setHeight(height);
        this.coordinates.setLatitude(latitude);
        if (this.coordinates.getHeight() <= 0) {
            this.weatherTower.unregister(this);
            Logger.getLogger(String.format("Baloon#%s(%d) landing.",
                this.name,
                this.id
            ));
            Logger.getLogger(String.format("Tower says: Baloon#%s(%d) unregistered from weather tower.",
                this.name,
                this.id
            ));
        }
    }
}
