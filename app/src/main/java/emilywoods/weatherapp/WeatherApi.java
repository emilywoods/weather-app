package emilywoods.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by emilywoods on 08/11/2016.
 */

public interface WeatherApi {

    @GET("")
    Call<CurrentWeather> getName();

}
