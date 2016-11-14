package emilywoods.weatherapp.models;

/**
 * Created by emilywoods on 10/11/2016.
 */

public class Location {
    private String name;
    private float longitude;
    private float latitude;

    public Location(String name, float longitude, float latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }
}
