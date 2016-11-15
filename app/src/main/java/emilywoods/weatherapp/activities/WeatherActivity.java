package emilywoods.weatherapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import emilywoods.weatherapp.R;
import emilywoods.weatherapp.models.CurrentWeather;
import emilywoods.weatherapp.models.Location;
import emilywoods.weatherapp.network.NetworkManagerImpl;
import timber.log.Timber;

/**
 * Created by emilywoods on 15/11/2016.
 */

public class WeatherActivity extends AppCompatActivity implements WeatherCallback {

    private Location mLocation;
    private NetworkManagerImpl networkManager;
    private CurrentWeather currentWeather;

    @BindView(R.id.location_name)
    protected TextView locationname;
    @BindView(R.id.temperature)
    protected TextView temperature;
    @BindView(R.id.weather_summary)
    protected TextView weathersummary;


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
        temperature.setText(String.valueOf(currentWeather.getTemperature()));
        weathersummary.setText(currentWeather.getDescription());
    }

    @Override
    public void onError() {
    }
}
