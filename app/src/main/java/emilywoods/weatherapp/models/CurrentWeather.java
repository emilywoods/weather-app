package emilywoods.weatherapp.models;

public class CurrentWeather {
    private String name;
    private String description;
    private String temperature;;

    public CurrentWeather(String name, String description, String temperature) {
        this.name = name;
        this.description = description;
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getName() {return name; }
}
