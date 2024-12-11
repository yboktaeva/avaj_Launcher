package yuboktae.models;

import yuboktae.Tracker;


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
                message += ": Clear skies ahead! Time to show off my jet-fueled tan!";
                break;
            }
            case "RAIN" -> {
                latitude += 5;
                message += ": It\'s raining. Better watch out for lightning-my hair can\'t handle that!";
                break;
            }
            case "FOG" -> {
                latitude += 1;
                message += ": Flying through fog-where\'s my GPS? I can\'t see a thing!";
                break;
            }
            case "SNOW" -> {
                height -= 7;
                message += ": OMG! Winter is coming! Can someone get me a hot chocolate?";
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
