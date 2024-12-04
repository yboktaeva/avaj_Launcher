package yuboktae.observer;

import yuboktae.models.Coordinates;
import yuboktae.singleton.WeatherProvider;


public class WeatherTower extends Tower {
    public String getWeather(Coordinates p_coordinates) {
        return WeatherProvider.getInstance().getCurrentWeather(p_coordinates);
    }

    public void changeWeather() {
        
    }
}
