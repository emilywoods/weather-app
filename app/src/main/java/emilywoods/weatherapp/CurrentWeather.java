package emilywoods.weatherapp;

/**
 * Created by emilywoods on 08/11/2016.
 */

public class CurrentWeather {
    private String description;
    private String name;
    private String temperature;
    private float latitude;
    private float longitude;

    public CurrentWeather(String description, String name, String temperature, float latitude, float longitude) {
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

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }


}
