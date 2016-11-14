package emilywoods.weatherapp.activities;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ViewSwitcher;
import android.support.design.widget.Snackbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import emilywoods.weatherapp.views.adapters.LocationsAdapter;
import emilywoods.weatherapp.R;
import emilywoods.weatherapp.models.CurrentWeather;
import emilywoods.weatherapp.models.Locations;
import emilywoods.weatherapp.network.ApiClient;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements WeatherCallback {
    private static final int INDEX_VIEW_LOADING = 0;
    private static final int INDEX_VIEW_CONTENT = 1;

    private ApiClient apiClient;
    private LocationsAdapter lAdapter;

    @BindView(R.id.location_recycler_view)
    protected RecyclerView recyclerView;
    @BindView(R.id.view_switcher)
    protected ViewSwitcher viewSwitcher;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
        apiClient.getLocationInfo(); //Make request
        apiClient.getWeatherInfo();
        viewSwitcher.setDisplayedChild(INDEX_VIEW_LOADING);
    }

    @Override
    public void onCurrentWeather(CurrentWeather currentWeather) {
        Timber.d("Fetched current weather info :%s", currentWeather.getDescription());
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
        Timber.i("Fetched %s locations.", locations.size());
        lAdapter.setLocationsList(locations);

        viewSwitcher.setDisplayedChild(INDEX_VIEW_CONTENT);

    }

}
