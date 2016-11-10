package emilywoods.weatherapp;

/**
 * Created by emilywoods on 08/11/2016.
 */


public class CurrentWeather {
    private String description;
    private String locName;
    private String temperature;
    private String locLatitude;
    private String locLongitude;


    public CurrentWeather(String description, String locName, String temperature, String locLatitude, String locLongitude) {
        this.description = description;
        this.locName = locName;
        this.temperature = temperature;
        this.locLatitude = locLatitude;
        this.locLongitude = locLongitude;
    }

    public String getDescription() {
        return description;
    }

    public String getLocName() {
        return locName;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getLocLatitude() {
        return locLatitude;
    }

    public String getLocLongitude() {
        return locLongitude;
    }


}
