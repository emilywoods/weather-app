package emilywoods.weatherapp.network;

import emilywoods.weatherapp.activities.LocationCallback;
import emilywoods.weatherapp.activities.WeatherCallback;

public interface NetworkManager {
    void getWeatherInfo(int locationId, final WeatherCallback weatherCallback);

    void getLocationInfo(final LocationCallback locationCallback);
}
