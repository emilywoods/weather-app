package emilywoods.weatherapp.network;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import emilywoods.weatherapp.models.CurrentWeather;
import emilywoods.weatherapp.models.Location;
import emilywoods.weatherapp.activities.WeatherCallback;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class ApiClient {
    public static final String BASE_URL = "http://10.2.1.6:3000/api/v1/";

    private HttpLoggingInterceptor interceptor;
    private OkHttpClient client;
    private Retrofit retrofit;
    private WeatherCallback weatherCallback;
    private WeatherApi weatherApi;

    public ApiClient() {
        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherApi = retrofit.create(WeatherApi.class);
    }

    public void setCallbackListener(WeatherCallback weatherCallback) {
        this.weatherCallback = weatherCallback;
    }

    public void getWeatherInfo() {
        Call<CurrentWeather> call = weatherApi.getWeatherInfo();
        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (response == null || response.body() == null) {
                    Timber.e("Empty response from location request.");
                    weatherCallback.onError();
                    return;
                }
                Timber.i("Success");
                weatherCallback.onCurrentWeather(response.body());
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable throwable) {
                Timber.e(throwable, "Error fetching location info.");
                weatherCallback.onError();
            }
        });
    }

    public void getLocationInfo() {
        Call<List<Location>> call = weatherApi.getLocationInfo();
        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> locResponse) {
                if (locResponse == null || locResponse.body()==null) {
                    Timber.e("Empty response from location request");
                    weatherCallback.onError();
                } else {
                    Timber.i("Success");
                    weatherCallback.onLocationsFetched(locResponse.body());
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable throwable) {
                Timber.e(throwable, "Error fetching location info");
                weatherCallback.onError();
            }
        });
    }
}


