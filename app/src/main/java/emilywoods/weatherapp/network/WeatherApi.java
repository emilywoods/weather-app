package emilywoods.weatherapp.network;

import java.util.List;

import emilywoods.weatherapp.models.CurrentWeather;
import emilywoods.weatherapp.models.Location;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherApi {
    @GET("locations/id/update_weather/")
    Call<CurrentWeather> getWeatherInfo();

    @GET("locations")
    Call<List<Location>> getLocationInfo();
}
