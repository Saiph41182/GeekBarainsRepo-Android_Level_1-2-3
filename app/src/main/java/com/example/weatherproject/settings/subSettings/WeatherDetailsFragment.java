package com.example.weatherproject.settings.subSettings;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.weatherproject.R;
import com.example.weatherproject.adapters.DetailsAdapter;
import com.example.weatherproject.models.ParamItem;
import com.example.weatherproject.settings.SettingsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.weatherproject.settings.subSettings.SettingsParams.SHARED_NAME;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherDetailsFragment extends BaseFragment {

    private RecyclerView rvParams;

    public WeatherDetailsFragment() {
        super();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        rvParams = view.findViewById(R.id.rv_params);
        List<ParamItem> list = initDataList();
        if(items == null || items.hashCode() != list.hashCode()) {
            setItems(list);
        }
        initParamsList();
        return view;
    }
    private void initParamsList(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rvParams.setLayoutManager(layoutManager);
        DetailsAdapter adapter = new DetailsAdapter(items);
        adapter.setOnItemCheckListener(itemCheck);
        rvParams.setAdapter(adapter);
    }
    private List<ParamItem> initDataList(){
        Map<String,Boolean> paramItems = SettingsParams.getSettingsParams().getWeatherDetailsDisplayParams();
        Object[] keys = paramItems.keySet().toArray();
        int itemsSize = paramItems.size();
        List<ParamItem> items = new ArrayList<>(itemsSize);
        for (int i = itemsSize - 1; i >= 0; i--) {
            assert keys != null;
            items.add(new ParamItem((String)keys[i],paramItems.get(keys[i])));
        }
        return items;
    }

    private DetailsAdapter.OnItemCheckListener itemCheck = new DetailsAdapter.OnItemCheckListener() {
        @Override
        public void itemCheck(CompoundButton buttonView, boolean isChecked) {
            SettingsParams settingsParams = SettingsParams.getSettingsParams();
            switch (buttonView.getText().toString()){
                case SettingsParams.TEMPERATURE:
                    settingsParams.setTemperature(isChecked);
                    break;
                case SettingsParams.HUMIDITY:
                    settingsParams.setHumidity(isChecked);
                    break;
                case SettingsParams.WIND_SPEED:
                    settingsParams.setWindSpeed(isChecked);
                    break;
                case SettingsParams.PRESSURE:
                    settingsParams.setPressure(isChecked);
                    break;
            }
        }
    };
}
