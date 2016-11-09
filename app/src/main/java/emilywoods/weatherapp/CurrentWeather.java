package emilywoods.weatherapp;

/**
 * Created by emilywoods on 08/11/2016.
 */

public class CurrentWeather {
    private String description;
    private String name;
    private String temperature;
    private String latitude;
    private String longitude;


    public CurrentWeather(String description, String name, String temperature, String latitude, String longitude) {
        this.description = description;
        this.name = name;
        this.temperature = temperature;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }


}
