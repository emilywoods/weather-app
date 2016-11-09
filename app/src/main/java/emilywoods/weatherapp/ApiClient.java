package emilywoods.weatherapp;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by emilywoods on 08/11/2016.
 */

public class ApiClient {
    public static final String BASE_URL = "http://10.2.1.6:3000/api/v1/";

    private HttpLoggingInterceptor interceptor;
    private OkHttpClient client;
    private Retrofit retrofit;

    private WeatherApi weatherApi;

    public ApiClient() {
        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherApi = retrofit.create(WeatherApi.class);
    }

    public void getWeatherInfo() {
        Call<CurrentWeather> call = weatherApi.getWeatherInfo();
        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                Log.d("API", "Success!");
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                Log.d("API", "Oh noes!");
            }
        });
    }


}


