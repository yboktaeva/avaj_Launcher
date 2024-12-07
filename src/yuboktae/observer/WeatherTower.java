package yuboktae.observer;

import java.io.IOException;
import yuboktae.models.Coordinates;
import yuboktae.singleton.WeatherProvider;


public class WeatherTower extends Tower {

    public WeatherTower() throws IOException {
        super();
    }

    public String getWeather(Coordinates p_coordinates) {
        return WeatherProvider.getWeatherProvider().getCurrentWeather(p_coordinates);
    }

    public void changeWeather() {
        this.conditionChanged();
    }

    public void logMessage(String log) {
        writeToFile(log);
    }
}
