package com.example.weatherproject.settings.subSettings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;

public class SettingsParams {

    public static final String SHARED_NAME = "SettingsParams";
    public static final int AMOUNT_OF_ALL_PARAMS = 4;
    public static final String TEMPERATURE = "Temperature";
    public static final String HUMIDITY = "Humidity";
    public static final String WIND_SPEED = "Wind speed";
    public static final String PRESSURE = "Pressure";

    private static SettingsParams settingsParams = new SettingsParams();

    @SerializedName("city")
    private String city;
    @SerializedName("displayParams")
    private Map<String, Boolean> weatherDetailsDisplayParams;

    private SettingsParams(){
        weatherDetailsDisplayParams = new HashMap<>(AMOUNT_OF_ALL_PARAMS);
        weatherDetailsDisplayParams.put(TEMPERATURE,true);
        weatherDetailsDisplayParams.put(HUMIDITY,true);
        weatherDetailsDisplayParams.put(WIND_SPEED,false);
        weatherDetailsDisplayParams.put(PRESSURE,false);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemperature(boolean visible){
        weatherDetailsDisplayParams.put(TEMPERATURE,visible);
    }
    public void setHumidity(boolean visible){
        weatherDetailsDisplayParams.put(HUMIDITY,visible);
    }
    public void setWindSpeed(boolean visible){
        weatherDetailsDisplayParams.put(WIND_SPEED,visible);
    }
    public void setPressure(boolean visible){
        weatherDetailsDisplayParams.put(PRESSURE,visible);
    }

    public boolean getTemperature(){
        return weatherDetailsDisplayParams.get(TEMPERATURE);
    }
    public boolean getHumidity(){
        return weatherDetailsDisplayParams.get(HUMIDITY);
    }
    public boolean getWindSpeed(){
        return weatherDetailsDisplayParams.get(WIND_SPEED);
    }
    public boolean getPressure(){
        return weatherDetailsDisplayParams.get(PRESSURE);
    }

    public Map<String, Boolean> getWeatherDetailsDisplayParams() {
        return weatherDetailsDisplayParams;
    }

    public static SettingsParams getSettingsParams() {
        return settingsParams;
    }

    public static String getJsonSettingsParams() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(settingsParams);
    }

    public static void loadDataFromPreferences(String sharePreferencesStringRepresentation){
        if(sharePreferencesStringRepresentation != null) {
            Gson gson = new Gson();
            SettingsParams params = gson.fromJson(sharePreferencesStringRepresentation, SettingsParams.class);
            settingsParams.setTemperature(params.getTemperature());
            settingsParams.setHumidity(params.getHumidity());
            settingsParams.setWindSpeed(params.getWindSpeed());
            settingsParams.setPressure(params.getPressure());
            settingsParams.setCity(params.getCity());
        }
    }
}
