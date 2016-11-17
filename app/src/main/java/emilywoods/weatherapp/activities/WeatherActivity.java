package emilywoods.weatherapp.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import emilywoods.weatherapp.R;
import emilywoods.weatherapp.models.CurrentWeather;
import emilywoods.weatherapp.models.Location;
import emilywoods.weatherapp.network.NetworkManagerImpl;
import emilywoods.weatherapp.views.resources.IconHelper;
import timber.log.Timber;

import static java.security.AccessController.getContext;

/**
 * Created by emilywoods on 15/11/2016.
 */

public class WeatherActivity extends AppCompatActivity implements WeatherCallback {

    private Location mLocation;
    private NetworkManagerImpl networkManager;
    private CurrentWeather currentWeather;

    private IconHelper weatherIconSwitch;

    @BindView(R.id.location_name)
    protected TextView locationname;
    @BindView(R.id.temperature)
    protected TextView temperature;
    @BindView(R.id.weather_summary)
    protected TextView weathersummary;
    @BindView(R.id.weather_icon)
    protected ImageView weatherIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mLocation = getIntent().getExtras().getParcelable("location");
        networkManager = new NetworkManagerImpl();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ButterKnife.bind(this);
        if (currentWeather == null) {
            networkManager.getWeatherInfo(mLocation.getId(), this);
        }
    }

    @Override
    public void onCurrentWeatherFetched(CurrentWeather currentWeather) {
        Timber.i("Fetched %s weather for location", currentWeather.getDescription());
        this.currentWeather = currentWeather;
        locationname.setText(currentWeather.getName());
        temperature.setText(String.valueOf(currentWeather.getTemperature()) + "°C");
        weathersummary.setText(currentWeather.getDescription());

        int drawableRes = IconHelper.getWeatherIconFromDescription(currentWeather.getDescription());
        Drawable icon = VectorDrawableCompat.create(getResources(), drawableRes, this.getTheme());
        weatherIcon.setImageDrawable(icon);
    }

    @Override
    public void onError() {
    }

}
