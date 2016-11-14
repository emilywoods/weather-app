package emilywoods.weatherapp.activities;

import java.util.List;

import emilywoods.weatherapp.models.CurrentWeather;
import emilywoods.weatherapp.models.Location;

/**
 * Created by emilywoods on 09/11/2016.
 */

public interface WeatherCallback {

    void onCurrentWeather(CurrentWeather currentWeather);

    void onError();

    void onLocationsFetched(List<Location> locations);
}
