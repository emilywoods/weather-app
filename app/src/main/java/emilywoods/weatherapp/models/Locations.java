package emilywoods.weatherapp.models;

/**
 * Created by emilywoods on 10/11/2016.
 */

public class Locations {
    private String name;
    private String longitude;
    private String latitude;

    public Locations(String name, String longitude, String latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }
}
