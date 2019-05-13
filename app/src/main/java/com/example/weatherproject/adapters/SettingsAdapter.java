package com.example.weatherproject.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherproject.models.SettingsItem;
import com.example.weatherproject.R;

import java.util.List;

public class SettingsAdapter extends BaseAdapter<SettingsItem, SettingsAdapter.SettingsViewHolder> {

    public SettingsAdapter(List<SettingsItem> displayData) {
        super(displayData);
    }

    @NonNull
    @Override
    public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_settings_main,viewGroup,false);
        return new SettingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsViewHolder settingsViewHolder, int i) {
        settingsViewHolder.bind(i);
    }

    public class SettingsViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;
        private View view;

        public SettingsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(clickListener);
            imageView = itemView.findViewById(R.id.image_settings_group);
            textView = itemView.findViewById(R.id.settings_group);
            view = itemView;
        }
        public void bind(int position) {
            SettingsItem item = displayData.get(position);
            imageView.setImageResource(item.getIdRes());
            textView.setText(item.getName());
            view.setTag(displayData.get(position).getName());
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
