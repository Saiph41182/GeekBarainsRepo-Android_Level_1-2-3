package com.example.weatherproject.services;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;

import com.example.weatherproject.controllers.WeatherDataController;

import org.json.JSONException;

import static com.example.weatherproject.MainActivity.PINTENT;
import static com.example.weatherproject.MainActivity.REQUEST;


public class DataLoader extends IntentService {

    public static final String RESPONSE = "responce";

    private WeatherDataController dataController;
    private String request;

    public DataLoader() {
        super("DataLoader");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        request = intent.getStringExtra(REQUEST);
        PendingIntent pendingIntent = intent.getParcelableExtra(PINTENT);
        try {
            dataController = new WeatherDataController(getResponseFrom(request));
        }catch(JSONException jsonExp){

        }
        Intent returningIntent = new Intent();
       // returningIntent.putExtra()
    }

    private String getResponseFrom(String HttpAdress) {
        //Метод для отправки запроса на сервер.
        String response = "";
        return response;
    }

}
