package emilywoods.weatherapp;

import java.util.List;

/**
 * Created by emilywoods on 09/11/2016.
 */

public interface WeatherCallback {

    void onCurrentWeather(CurrentWeather currentWeather);

    void onError();

    void onLocations(List<Locations> locations);
}
