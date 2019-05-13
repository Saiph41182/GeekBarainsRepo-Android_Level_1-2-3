package com.example.weatherproject.settings.subSettings;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherproject.adapters.BaseAdapter;
import com.example.weatherproject.adapters.CityAdapter;
import com.example.weatherproject.models.CityItem;
import com.example.weatherproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CityChooserFragment extends BaseFragment {
    //private static final String TAG = "CityChooserFragment";
    private RecyclerView rvCityList;



    public CityChooserFragment() {
       super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_chooser, container, false);
        rvCityList = view.findViewById(R.id.rv_city_list);

        if(items == null) {
            setItems(initList());
        }
        initCityList();
        return view;
    }
    private void initCityList(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rvCityList.setLayoutManager(layoutManager);
        CityAdapter cityAdapter = new CityAdapter(items);
        cityAdapter.setItemClickListener(itemClickListener);
        rvCityList.setAdapter(cityAdapter);
    }

    private List initList(){
        ArrayList<CityItem> temp = new ArrayList<>();
        temp.add(new CityItem("Омск","Россия"));
        temp.add(new CityItem("Могилев","Беларусь"));
        temp.add(new CityItem("New York","USA"));
        temp.add(new CityItem("London","England"));
        temp.add(new CityItem("Минск","Беларусь"));
        temp.add(new CityItem("Омск","Россия"));
        temp.add(new CityItem("Могилев","Беларусь"));
        temp.add(new CityItem("New York","USA"));
        temp.add(new CityItem("London","England"));
        temp.add(new CityItem("Минск","Беларусь"));
        temp.add(new CityItem("Омск","Россия"));
        temp.add(new CityItem("Могилев","Беларусь"));
        temp.add(new CityItem("New York","USA"));
        temp.add(new CityItem("London","England"));
        temp.add(new CityItem("Минск","Беларусь"));
        temp.add(new CityItem("Омск","Россия"));
        temp.add(new CityItem("Могилев","Беларусь"));
        temp.add(new CityItem("New York","USA"));
        temp.add(new CityItem("London","England"));
        temp.add(new CityItem("Минск","Беларусь"));
        return temp;
    }
    private BaseAdapter.OnItemClickListener itemClickListener = new BaseAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {

        }
    };
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("displayed",getName());
    }
}
