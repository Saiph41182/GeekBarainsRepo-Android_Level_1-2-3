package com.example.weatherproject;


import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreviewFragment extends Fragment {

    private SensorManager sensorManager;
    private Sensor temperature;
    private Sensor humidity;
    private TextView temperatureViewer;
    private TextView humidityViewer;

    @Override
    public void onStart() {
        super.onStart();
        sensorManager.registerListener(eventListener, temperature,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(eventListener, humidity,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preview, container, false);

        temperatureViewer = view.findViewById(R.id.temperature_viewer);
        humidityViewer = view.findViewById(R.id.humidity_viewer);
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

}
