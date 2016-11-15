package emilywoods.weatherapp.network;

import java.util.List;
import java.util.concurrent.TimeUnit;

import emilywoods.weatherapp.activities.LocationCallback;
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

public class NetworkManagerImpl implements NetworkManager {
    public static final String BASE_URL = "http://10.10.0.55:3000/api/v1/";

    private HttpLoggingInterceptor interceptor;
    private OkHttpClient client;
    private Retrofit retrofit;
    private WeatherApi weatherApi;

    public NetworkManagerImpl() {
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

    @Override
    public void getWeatherInfo(int locationId, final WeatherCallback weatherCallback) {
        Call<CurrentWeather> call = weatherApi.getWeatherInfo(locationId);
        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (response == null || response.body() == null) {
                    Timber.e("Empty response from location request.");
                    weatherCallback.onError();
                    return;
                }
                Timber.i("Success");
                weatherCallback.onCurrentWeatherFetched(response.body());
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable throwable) {
                Timber.e(throwable, "Error fetching location info.");
                weatherCallback.onError();
            }
        });
    }

    @Override
    public void getLocationInfo(final LocationCallback locationCallback) {
        Call<List<Location>> call = weatherApi.getLocationInfo();
        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> locResponse) {
                if (locResponse == null || locResponse.body()==null) {
                    Timber.e("Empty response from location request");
                    locationCallback.onError();
                } else {
                    Timber.i("Success");
                    locationCallback.onLocationsFetched(locResponse.body());
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable throwable) {
                Timber.e(throwable, "Error fetching location info");
                locationCallback.onError();
            }
        });
    }
}


