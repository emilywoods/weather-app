package emilywoods.weatherapp.models;

/**
 * Created by emilywoods on 08/11/2016.
 */


public class CurrentWeather {
    private String description;
    private String temperature;;


    public CurrentWeather(String description, String temperature) {
        this.description = description;
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public String getTemperature() {
        return temperature;
    }

}
