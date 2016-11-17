package emilywoods.weatherapp.activities;

import emilywoods.weatherapp.models.WeatherInfo;

/**
 * Created by emilywoods on 09/11/2016.
 */

public interface FetchWeatherListener {

    void onCurrentWeatherFetched(WeatherInfo weatherInfo);

    void onCurrentWeatherError();
}
