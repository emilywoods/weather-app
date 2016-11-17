package emilywoods.weatherapp.network;

import java.util.List;
import java.util.concurrent.TimeUnit;

import emilywoods.weatherapp.activities.LocationCallback;
import emilywoods.weatherapp.models.WeatherInfo;
import emilywoods.weatherapp.models.Location;
import emilywoods.weatherapp.activities.FetchWeatherListener;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class NetworkManagerImpl implements NetworkManager {
    public static final String BASE_URL = "http://10.2.1.6:3000/api/v1/";

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
    public void getWeatherInfo(int locationId, final FetchWeatherListener fetchWeatherListener) {
        Call<WeatherInfo> call = weatherApi.getWeatherInfo(locationId);
        call.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                if (response == null || response.body() == null) {
                    Timber.e("Empty response from location request.");
                    fetchWeatherListener.onCurrentWeatherError();
                    return;
                }
                Timber.i("Success");
                fetchWeatherListener.onCurrentWeatherFetched(response.body());
            }

            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable throwable) {
                Timber.e(throwable, "Error fetching location info.");
                fetchWeatherListener.onCurrentWeatherError();
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
                    locationCallback.onLocationsError();
                } else {
                    Timber.i("Success");
                    locationCallback.onLocationsFetched(locResponse.body());
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable throwable) {
                Timber.e(throwable, "Error fetching location info");
                locationCallback.onLocationsError();
            }
        });
    }
}


