package com.example.weatherproject.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.weatherproject.R;
import com.example.weatherproject.models.ParamItem;

import java.util.List;

public class DetailsAdapter extends BaseAdapter<ParamItem, DetailsAdapter.DetailsViewHolder> {
    public interface OnItemCheckListener{
        void itemCheck(CompoundButton buttonView, boolean isChecked);
    }

    private OnItemCheckListener checkListener;

    public DetailsAdapter(List<ParamItem> displayData) {
        super(displayData);
    }

    public void setOnItemCheckListener(OnItemCheckListener checkListener){
        this.checkListener = checkListener;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_settings_details,viewGroup,false);
        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder detailsViewHolder, int i) {
        detailsViewHolder.bind(i);
    }

    class DetailsViewHolder extends RecyclerView.ViewHolder {
        private CheckBox paramItem;

        public DetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            paramItem = itemView.findViewById(R.id.cb_item_name);
            paramItem.setOnCheckedChangeListener(changeListener);
        }

        private void bind(int i) {
            ParamItem item = displayData.get(i);
            paramItem.setText(item.getItemName());
            paramItem.setChecked(item.isChecked());
        }
        private CompoundButton.OnCheckedChangeListener changeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkListener != null){
                    checkListener.itemCheck(buttonView,isChecked);
                }
            }
        };
    }
}
