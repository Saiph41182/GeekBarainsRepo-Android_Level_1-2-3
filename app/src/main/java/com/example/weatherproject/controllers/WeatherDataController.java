package com.example.weatherproject.controllers;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataController {

    public static final String TEMPERATURE = "";    //
    public static final String HUMIDITY = "";       // Константы для получения конкретных данных
    public static final String WIND_SPEED = "";     // из JSON объекта.
    public static final String PRESSURE = "";       // Доработать под JSON формат данных о погоде

    private final Parcer parcer = new Parcer();
    private String responseData;

    public WeatherDataController(String responseData) throws JSONException {
        this.responseData = responseData;
    }
    public String getStringData(String tipe){
        return parcer.getString(tipe);
    }

    public int getIntData(String tipe){
        return parcer.getInt(tipe);
    }
    /**
     * Парсер для удобочитаемого отображения JSON данных. Доработать под JSON формат данных о погоде*/
    private class Parcer{
        private JSONObject jsonObject;

        private Parcer() throws JSONException {
            this.jsonObject = new JSONObject(responseData);
        }
        private String getString(String item){
            return null;
        }
        private int getInt(String item){
            return -1;
        }
    }

}
