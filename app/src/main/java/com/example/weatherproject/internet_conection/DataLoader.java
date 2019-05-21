package com.example.weatherproject.internet_conection;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.weatherproject.R;
import com.example.weatherproject.models.weatherPOJO.WeatherRequest;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DataLoader extends IntentService {

    public DataLoader() {
        super("DataLoader");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Internet", "onHandleIntent:");
        WeatherDataProvider.getInstance()
                .getOpenWeatherApi()
                .loadData("Minsk",getResources().getString(R.string.open_weather_api_key))
                .enqueue(new Callback<WeatherRequest>() {
                    @Override
                    public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {
                        Log.d("Internet", "onResponse: ");
                        if(response.isSuccessful() && response.body() != null){
                            Log.d("Internet", "Successful");
                            WeatherRequest info = response.body();
                            EventBus.getDefault().postSticky(info);
                        }else{
                            try {
                                Log.d("Internet", "Unsuccessful" + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<WeatherRequest> call, Throwable t) {
                        Log.d("Internet", "request failed", t);
                    }
                });
    }

}
