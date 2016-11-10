package emilywoods.weatherapp;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity implements WeatherCallback {

    private ApiClient apiClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiClient = new ApiClient();
        apiClient.setCallbackListener(this);
    }

    // onCreate, onPause, onResume, onStart, and onStop

    @Override
    protected void onResume() {
        super.onResume();

        //apiClient.getWeatherInfo();
        apiClient.getLocationInfo();
    }

    @Override
    public void onCurrentWeather(CurrentWeather currentWeather) {
        Log.e(getClass().getCanonicalName(), currentWeather.getDescription());
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLocations(List<Locations> locations) {
        Log.e(getClass().getCanonicalName(), "" + locations.size());
    }
}
