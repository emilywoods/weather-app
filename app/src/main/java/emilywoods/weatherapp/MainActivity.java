package emilywoods.weatherapp;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import android.support.design.widget.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements WeatherCallback {
    private static final int INDEX_VIEW_LOADING = 0;
    private static final int INDEX_VIEW_CONTENT = 1;

    private ApiClient apiClient;
    private RecyclerView recyclerView;
    private LocationsAdapter lAdapter;
    private ViewSwitcher viewSwitcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewSwitcher = (ViewSwitcher) findViewById(R.id.ViewSwitcher);

        recyclerView = (RecyclerView) findViewById(R.id.location_recycler_view);
        lAdapter = new LocationsAdapter();
        RecyclerView.LayoutManager lLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(lLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(lAdapter);

        apiClient = new ApiClient();
        apiClient.setCallbackListener(this);

    }

    // onCreate, onPause, onResume, onStart, and onStop

    @Override
    protected void onResume() {
        super.onResume();
        //apiClient.getWeatherInfo();
        apiClient.getLocationInfo(); //Make request
        viewSwitcher.setDisplayedChild(INDEX_VIEW_LOADING);
    }

    @Override
    public void onCurrentWeather(CurrentWeather currentWeather) {
        Log.e(getClass().getCanonicalName(), currentWeather.getDescription());
    }

    @Override
    public void onError() {
        Snackbar snackbar =
                Snackbar.make(recyclerView, R.string.snackbar_error,
                Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiClient.getLocationInfo();
            }
        });
        snackbar.show();

    }

    @Override
    public void onLocations(List<Locations> locations) {
        Log.e(getClass().getCanonicalName(), "" + locations.size());
        lAdapter.setLocationsList(locations);
        viewSwitcher.setDisplayedChild(INDEX_VIEW_CONTENT);

    }
}
