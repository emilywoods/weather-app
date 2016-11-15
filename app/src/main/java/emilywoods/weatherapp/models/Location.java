package emilywoods.weatherapp.models;

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
