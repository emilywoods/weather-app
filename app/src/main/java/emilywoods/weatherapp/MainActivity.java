package emilywoods.weatherapp;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ApiClient apiClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiClient = new ApiClient();
    }

    // onCreate, onPause, onResume, onStart, and onStop

    @Override
    protected void onResume() {
        super.onResume();

        apiClient.getWeatherInfo();
    }
}
