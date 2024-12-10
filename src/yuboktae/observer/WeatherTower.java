package yuboktae.observer;

import yuboktae.models.Coordinates;
import yuboktae.singleton.WeatherProvider;


public class WeatherTower extends Tower {

  
    public String getWeather(Coordinates p_coordinates) {
        return WeatherProvider.getWeatherProvider().getCurrentWeather(p_coordinates);
    }

    public void changeWeather() {
        this.conditionChanged();
    }
}
