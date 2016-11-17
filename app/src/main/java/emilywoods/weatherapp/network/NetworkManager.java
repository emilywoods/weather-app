package emilywoods.weatherapp.network;

import emilywoods.weatherapp.activities.LocationCallback;
import emilywoods.weatherapp.activities.FetchWeather;

public interface NetworkManager {
    void getWeatherInfo(int locationId, final FetchWeather fetchWeather);

    void getLocationInfo(final LocationCallback locationCallback);
}
