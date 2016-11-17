package emilywoods.weatherapp.network;

import emilywoods.weatherapp.activities.LocationCallback;
import emilywoods.weatherapp.activities.FetchWeatherListener;

public interface NetworkManager {
    void getWeatherInfo(int locationId, final FetchWeatherListener fetchWeatherListener);

    void getLocationInfo(final LocationCallback locationCallback);
}
