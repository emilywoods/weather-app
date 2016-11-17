package emilywoods.weatherapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import emilywoods.weatherapp.R;
import emilywoods.weatherapp.models.WeatherInfo;
import emilywoods.weatherapp.models.Location;
import emilywoods.weatherapp.network.NetworkManagerImpl;
import emilywoods.weatherapp.views.resources.IconHelper;
import timber.log.Timber;


/**
 * Created by emilywoods on 15/11/2016.
 */

public class FetchWeatherActivity extends AppCompatActivity implements FetchWeather {
    private static final String EXTRA_LOCATIONS = "locations";

    private Location mLocation;
    private NetworkManagerImpl networkManager;
    private WeatherInfo weatherInfo;

    private IconHelper weatherIconSwitch;

    @BindView(R.id.location_name)
    protected TextView locationName;
    @BindView(R.id.temperature)
    protected TextView temperature;
    @BindView(R.id.weather_summary)
    protected TextView weatherSummary;
    @BindView(R.id.weather_icon)
    protected ImageView weatherIcon;

    public static void launch(Activity activity, Location location) {
        Intent intent = new Intent(activity, FetchWeatherActivity.class);
        intent.putExtra(FetchWeatherActivity.EXTRA_LOCATIONS, location);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        mLocation = getIntent().getExtras().getParcelable(EXTRA_LOCATIONS);
        networkManager = new NetworkManagerImpl();
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (weatherInfo == null) {
            getWeather();
        }
    }

    @Override
    public void onCurrentWeatherFetched(WeatherInfo weatherInfo) {
        Timber.i("Fetched %s weather for location", weatherInfo.getDescription());
        this.weatherInfo = weatherInfo;
        locationName.setText(weatherInfo.getName());
        temperature.setText(String.valueOf(weatherInfo.getTemperature()) + getString(R.string.celsius));
        weatherSummary.setText(weatherInfo.getDescription());

        int drawableRes = IconHelper.getWeatherIconFromDescription(weatherInfo.getDescription());
        Drawable icon = VectorDrawableCompat.create(getResources(), drawableRes, this.getTheme());
        weatherIcon.setImageDrawable(icon);
    }

    @Override
    public void onCurrentWeatherError() {
        Snackbar snackbar =
                Snackbar.make(weatherSummary, R.string.weather_error_message,
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.retry, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getWeather();
                            }
                        });
        snackbar.show();
    }

    private void getWeather() {
        networkManager.getWeatherInfo(mLocation.getId(), this);
    }
}
