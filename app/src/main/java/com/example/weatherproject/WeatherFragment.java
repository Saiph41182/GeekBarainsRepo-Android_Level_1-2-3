package com.example.weatherproject;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weatherproject.DataBase.DBHelper;
import com.example.weatherproject.models.weatherPOJO.WeatherRequest;
import com.example.weatherproject.settings.subSettings.SettingsParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static java.lang.String.format;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {

    public static final String NAME = "weatherFragment";

    private TextView cityTitle;
    private TextView temperature;
    private TextView windSpeed;
    private TextView pressure;
    private TextView humidity;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        initUI(view);
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void initUI(View view){
        cityTitle = view.findViewById(R.id.city_title);
        temperature = view.findViewById(R.id.temperature);
        temperature.setVisibility(SettingsParams.getSettingsParams().getTemperature() ? View.VISIBLE : View.INVISIBLE);
        windSpeed = view.findViewById(R.id.wind_speed);
        windSpeed.setVisibility(SettingsParams.getSettingsParams().getWindSpeed() ? View.VISIBLE : View.INVISIBLE);
        pressure = view.findViewById(R.id.pressure);
        pressure.setVisibility(SettingsParams.getSettingsParams().getPressure() ? View.VISIBLE : View.INVISIBLE);
        humidity = view.findViewById(R.id.humidity);
        humidity.setVisibility(SettingsParams.getSettingsParams().getHumidity() ? View.VISIBLE : View.INVISIBLE);
    }

    @SuppressLint("DefaultLocale")
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void handleWeatherInfo(WeatherRequest weatherInfo){
        cityTitle.setText(format("%s: %s", getString(R.string.city), weatherInfo.getName()));
        temperature.setText(format("%s: %s", getString(R.string.temperature), weatherInfo.getMain().getTemp()));
        windSpeed.setText(format("%s: %s", getString(R.string.wind_speed), weatherInfo.getWind().getSpeed()));
        pressure.setText(format("%s: %d", getString(R.string.pressure), weatherInfo.getMain().getPressure()));
        humidity.setText(format("%s: %d", getString(R.string.humidity), weatherInfo.getMain().getHumidity()));
    }
}