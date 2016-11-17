package emilywoods.weatherapp.views.resources;


import android.support.annotation.DrawableRes;

import emilywoods.weatherapp.R;

/**
 * Created by emilywoods on 16/11/2016.
 */

public class IconHelper {

    @DrawableRes
    public static int getWeatherIconFromDescription(String description) {

        switch (description) {
            case "rain":
                return R.drawable.ic_rain;
            case "clear-day":
                return R.drawable.ic_sunny;
            case "fog":
                return R.drawable.ic_fog;
            case "snow":
                return R.drawable.ic_snow;
            case "wind":
                return R.drawable.ic_wind;
            case "cloudy":
                return R.drawable.ic_cloudy;
            case "clear-night":
                return R.drawable.ic_cloudy_night;
            case "partly-cloudy-day":
                return R.drawable.ic_clouds;
            case "partly-cloudy-night":
                return R.drawable.ic_cloudy_night;
            case "sleet":
                return R.drawable.ic_sleet;
            default:
                return R.drawable.ic_question;
        }
    }
}


