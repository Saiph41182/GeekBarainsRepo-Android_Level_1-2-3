package com.example.weatherproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weatherproject.models.weatherPOJO.WeatherRequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
        windSpeed = view.findViewById(R.id.wind_speed);
        pressure = view.findViewById(R.id.pressure);
        humidity = view.findViewById(R.id.humidity);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void handleWeatherInfo(WeatherRequest weatherInfo){
        cityTitle.setText(String.format("%s: %s", getString(R.string.city), weatherInfo.getName()));
        temperature.setText(String.format("%s: %s", getString(R.string.temperature), weatherInfo.getMain().getTemp()));
        windSpeed.setText(String.format("%s: %s", getString(R.string.wind_speed), weatherInfo.getWind().getSpeed()));
        pressure.setText(String.format("%s: %d", getString(R.string.pressure), weatherInfo.getMain().getPressure()));
        humidity.setText(String.format("%s: %d", getString(R.string.humidity), weatherInfo.getMain().getHumidity()));
    }
}