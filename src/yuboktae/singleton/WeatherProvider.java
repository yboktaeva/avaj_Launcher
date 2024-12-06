package yuboktae.singleton;

import yuboktae.models.Coordinates;


public class WeatherProvider {
    private static WeatherProvider weatherProvider;

    private static final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getWeatherProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        int index = (p_coordinates.getLongitude() + p_coordinates.getLatitude() + p_coordinates.getHeight()) % weather.length;
        return weather[index];
    }
}
