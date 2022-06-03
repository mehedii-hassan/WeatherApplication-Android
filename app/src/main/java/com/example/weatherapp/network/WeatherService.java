package com.example.weatherapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherService {
    public static com.example.weatherapp.network.WeatherServiceApi getService() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(com.example.weatherapp.network.WeatherServiceApi.class);
    }

}
