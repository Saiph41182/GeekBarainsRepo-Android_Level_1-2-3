package com.example.weatherproject.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public abstract class BaseAdapter<E, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    protected List<E> displayData;

    protected OnItemClickListener itemClickListener;

    public BaseAdapter(List<E> displayData) {
        this.displayData = displayData;
    }


    @Override
    public void onBindViewHolder(@NonNull V holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return displayData.size();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

}
