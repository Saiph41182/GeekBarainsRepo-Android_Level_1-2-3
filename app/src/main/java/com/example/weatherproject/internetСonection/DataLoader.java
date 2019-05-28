package com.example.weatherproject.internetСonection;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.example.weatherproject.DataBase.DBHelper;
import com.example.weatherproject.PreviewFragment;
import com.example.weatherproject.R;
import com.example.weatherproject.models.weatherPOJO.Weather;
import com.example.weatherproject.models.weatherPOJO.WeatherRequest;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DataLoader extends IntentService {

    private WeatherRequest foreign;

    public DataLoader() {
        super("DataLoader");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Internet", "onHandleIntent:");

        OpenWeatherApiProvider openWeatherApi = WeatherDataProvider.getInstance().getOpenWeatherApi();
        Call<WeatherRequest> call;
        if(intent.getExtras().getString(PreviewFragment.CITY_NAME) != null){
            call = openWeatherApi.loadData(intent.getExtras().getString(PreviewFragment.CITY_NAME),
                    getResources().getString(R.string.open_weather_api_key));
            try {
                Response<WeatherRequest> response = call.execute();
                foreign = response.body();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("Internet", "get by name executing exception");
            }
            Log.d("Internet", "get by name. Foreign: " + foreign);
            if(foreign == null){
                Location location = (Location)intent.getExtras().get(PreviewFragment.CITY_COORD);
                call = openWeatherApi.loadData(
                        String.valueOf(location.getLatitude()),
                        String.valueOf(location.getLongitude()),
                        getResources().getString(R.string.open_weather_api_key));
                try {
                    Response<WeatherRequest> response = call.execute();
                    foreign = response.body();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("Internet", "get by coord executing exception");
                }
                Log.d("Internet", "get by coord. Foreign: " + foreign);
            }
        }
        DBHelper database = DBHelper.getInstance();
        if(database.isContains(foreign.getName())){
            Log.d("Internet", "Item is exist ");
            database.update(foreign);
            Log.d("Internet", "Item was upgrade ");
        }else{
            Log.d("Internet", "Item added ");
            database.add(foreign);
        }
        Log.d("Internet", "DB stores " + database.getItemCount() + " entries");
        Log.d("Internet", "All db items " + database.getAll());

        /*Обработать ошибки*/
        EventBus.getDefault().postSticky(foreign);

    }

}
