package com.example.weatherproject.internetСonection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherDataProvider {
    private static WeatherDataProvider mInstance;
    private static final String BASE_URL = "http://api.openweathermap.org/";
    private Retrofit retrofit;

    private WeatherDataProvider(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static WeatherDataProvider getInstance(){
        if(mInstance == null){
            mInstance = new WeatherDataProvider();
        }
        return mInstance;
    }
    public OpenWeatherApiProvider getOpenWeatherApi(){
        return retrofit.create(OpenWeatherApiProvider.class);
    }
}
