package com.example.weatherproject.internetСonection;

import com.example.weatherproject.models.weatherPOJO.WeatherRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherApiProvider {
    @GET("data/2.5/weather")
    Call<WeatherRequest> loadData(@Query("q") String city, @Query("appid") String ApiKey);
}
