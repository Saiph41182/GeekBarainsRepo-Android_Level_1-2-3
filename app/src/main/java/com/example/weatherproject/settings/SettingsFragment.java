package com.example.weatherproject.settings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherproject.adapters.BaseAdapter;
import com.example.weatherproject.R;
import com.example.weatherproject.adapters.SettingsAdapter;
import com.example.weatherproject.models.SettingsItem;
import com.example.weatherproject.settings.subSettings.BaseFragment;
import com.example.weatherproject.settings.subSettings.CityChooserFragment;
import com.example.weatherproject.settings.subSettings.WeatherDetailsFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends BaseFragment {

    //private static final String TAG = "SettingsFragment";

    private static SettingsFragment settingsFragment = new SettingsFragment();

    public SettingsFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        if(items == null) {
            ArrayList<SettingsItem> temp = new ArrayList<>();
            temp.add(new SettingsItem(android.R.drawable.ic_menu_agenda, R.string.weather_information));
            temp.add(new SettingsItem(android.R.drawable.ic_menu_compass, R.string.choose_city));
            setItems(temp);
        }
        initRecyclerView(view);
        return view;
    }
    private void initRecyclerView(View parent){
        RecyclerView rvSettingsMain = parent.findViewById(R.id.rv_settings_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rvSettingsMain.setLayoutManager(layoutManager);
        SettingsAdapter settingsAdapter = new SettingsAdapter(items);
        settingsAdapter.setItemClickListener(onItemClickListener);
        rvSettingsMain.setAdapter(settingsAdapter);

    }
    private BaseAdapter.OnItemClickListener onItemClickListener = new SettingsAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if(position != RecyclerView.NO_POSITION){
                switch((int)view.getTag()){
                    case R.string.weather_information:
                        WeatherDetailsFragment detailsFragment = new WeatherDetailsFragment();
                        showFragment(detailsFragment,R.id.settings,detailsFragment.getName());
                        break;
                    case R.string.choose_city:
                        CityChooserFragment chooserFragment = new CityChooserFragment();
                        showFragment(chooserFragment,R.id.settings,chooserFragment.getName());
                        break;
                }
            }
        }
    };
}
