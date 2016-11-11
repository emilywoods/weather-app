package emilywoods.weatherapp;

import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;


/**
 * Created by emilywoods on 08/11/2016.
 */

public class ApiClient {
    public static final String BASE_URL = "http://10.10.0.6:3000/api/v1/";

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
                    return;
                }
                Timber.e("Success");
                weatherCallback.onCurrentWeather(response.body());
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                Timber.e("API Error");
                weatherCallback.onError();
            }
        });
    }

    public void getLocationInfo(){
        Call<List<Locations>> call = weatherApi.getLocationInfo();
        call.enqueue(new Callback<List<Locations>>() {
            @Override
            public void onResponse(Call<List<Locations>> call, Response<List<Locations>> locResponse) {
                if (locResponse == null || locResponse.body()==null){
                    return;
                }
                weatherCallback.onLocations(locResponse.body());

            }

            @Override
            public void onFailure(Call<List<Locations>> call, Throwable t) {
                weatherCallback.onError();
            }
        });
    }
}


