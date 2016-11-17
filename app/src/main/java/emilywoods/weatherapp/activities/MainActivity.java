package emilywoods.weatherapp.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ViewSwitcher;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import emilywoods.weatherapp.network.NetworkManager;
import emilywoods.weatherapp.network.NetworkManagerImpl;
import emilywoods.weatherapp.views.adapters.LocationClickListener;
import emilywoods.weatherapp.views.adapters.LocationsAdapter;
import emilywoods.weatherapp.R;
import emilywoods.weatherapp.models.Location;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements LocationCallback, LocationClickListener {
    private static final int INDEX_VIEW_LOADING = 0;
    private static final int INDEX_VIEW_CONTENT = 1;

    private NetworkManager networkManager;
    private LocationClickListener locationClickListener;
    private LocationsAdapter locationsAdapter;

    @BindView(R.id.location_recycler_view)
    protected RecyclerView recyclerView;
    @BindView(R.id.view_switcher)
    protected ViewSwitcher viewSwitcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        locationsAdapter = new LocationsAdapter(this);
        RecyclerView.LayoutManager lLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(lLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(locationsAdapter);

        networkManager = new NetworkManagerImpl();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLocations();
        viewSwitcher.setDisplayedChild(INDEX_VIEW_LOADING);
    }

    @Override
    public void onLocationsFetched(List<Location> locations) {
        Timber.i("Fetched %s locations.", locations.size());
        locationsAdapter.setLocationsList(locations);
        viewSwitcher.setDisplayedChild(INDEX_VIEW_CONTENT);
    }

    @Override
    public void onLocationsError() {
        Snackbar snackbar =
                Snackbar.make(recyclerView, R.string.location_error_message,
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.retry, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getLocations();
                            }
                        });
        snackbar.show();
    }

    private void getLocations() {
        networkManager.getLocationInfo(this);
    }

    @Override
    public void onLocationClicked(Location location) {
        FetchWeatherActivity.launch(this, location);
    }
}
