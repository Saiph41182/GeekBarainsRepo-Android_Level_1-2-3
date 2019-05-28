package com.example.weatherproject;


import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weatherproject.DataBase.DBHelper;
import com.example.weatherproject.internetСonection.DataLoader;
import com.example.weatherproject.models.weatherPOJO.Main;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreviewFragment extends Fragment {
    public static final String CITY_NAME = "City name";
    public static final String CITY_COORD = "City coord";
    private FloatingActionButton fab;
    private SensorManager sensorManager;
    private Sensor temperature;
    private Sensor humidity;
    private TextView temperatureViewer;
    private TextView humidityViewer;
    private EditText cityInput;

    @Override
    public void onStart() {
        super.onStart();
        sensorManager.registerListener(eventListener, temperature,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(eventListener, humidity,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preview, container, false);

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(fabOnClickListener);

        temperatureViewer = view.findViewById(R.id.temperature_viewer);
        humidityViewer = view.findViewById(R.id.humidity_viewer);
        cityInput = view.findViewById(R.id.tv_input_city);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        humidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(eventListener);
    }

    private final SensorEventListener eventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            StringBuilder sb = new StringBuilder();
            if(event.sensor.getName().equals(temperature.getName())){
                sb.append(event.values[0])
                        .append("\u2103");
                temperatureViewer.setTextColor(Color.BLACK);
                temperatureViewer.setText(sb);
                sb.delete(0,sb.length());
            }else if(event.sensor.getName().equals(humidity.getName())){
                sb.append(event.values[0])
                        .append("%");
                humidityViewer.setTextColor(Color.BLACK);
                humidityViewer.setText(sb);
                sb.delete(0,sb.length());
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    public void runService(){
        MainActivity activity = (MainActivity) getActivity();
        Log.d("Internet", "runService: starting service ");
        Intent intent = new Intent(activity, DataLoader.class);
        intent.putExtra(CITY_COORD, ((MainActivity)getActivity()).getLastLocation());
        intent.putExtra(CITY_NAME,cityInput.getText().toString());
        activity.startService(intent);
    }
    private View.OnClickListener fabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).runFragment();
            runService();
        }
    };

}
