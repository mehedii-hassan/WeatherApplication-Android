package com.example.weatherapp.network;

import com.example.weatherapp.models.current.CurrentResponseModel;
import com.example.weatherapp.models.forecast.ForecastResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WeatherServiceApi {
    @GET()
    Call<CurrentResponseModel> getCurrentData(@Url String endUrl);

    @GET()
    Call<ForecastResponseModel> getForecastData(@Url String endUrl);
}
