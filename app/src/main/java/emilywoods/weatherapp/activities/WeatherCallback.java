package emilywoods.weatherapp.activities;

import emilywoods.weatherapp.models.CurrentWeather;

/**
 * Created by emilywoods on 09/11/2016.
 */

public interface WeatherCallback {

    void onCurrentWeatherFetched(CurrentWeather currentWeather);

    void onError();
}
