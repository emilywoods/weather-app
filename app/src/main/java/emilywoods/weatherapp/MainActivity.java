package emilywoods.weatherapp;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements WeatherCallback {

    private ApiClient apiClient;
    private RecyclerView recyclerView;
    private LocationsAdapter lAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.location_recycler_view);

        lAdapter = new LocationsAdapter();
        RecyclerView.LayoutManager lLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(lLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(lAdapter);

        apiClient = new ApiClient();
        apiClient.setCallbackListener(this);
        apiClient.getLocationInfo();


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
        lAdapter.setLocationsList(locations);
    }
}
