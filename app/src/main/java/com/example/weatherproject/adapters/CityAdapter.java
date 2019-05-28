package com.example.weatherproject.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weatherproject.DataBase.DBHelper;
import com.example.weatherproject.models.CityItem;
import com.example.weatherproject.R;

import java.util.List;

public class CityAdapter extends BaseAdapter<DBHelper.DBItem, CityAdapter.CityViewHolder> {

    public CityAdapter(List<DBHelper.DBItem> displayData) {
        super(displayData);
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_city,viewGroup,false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder cityViewHolder, int i) {
        cityViewHolder.bind(i);
    }

    public class CityViewHolder extends RecyclerView.ViewHolder{

        private TextView cityName;
        private TextView countryName;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.city_name);
            countryName = itemView.findViewById(R.id.country_name);
            itemView.setOnClickListener(clickListener);
        }

        public void bind(int position) {
            cityName.setText(displayData.get(position).getCityName());
            String coordinate = String.format("Lad: %s; Lon: %s",displayData.get(position).getLat(),displayData.get(position).getLat());
            countryName.setText(coordinate);
        }
        private View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener != null){
                    itemClickListener.onItemClick(v,getAdapterPosition());
                }
            }
        };
    }
}
