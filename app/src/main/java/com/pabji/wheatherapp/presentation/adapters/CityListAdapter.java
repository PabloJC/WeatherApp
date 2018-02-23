package com.pabji.wheatherapp.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pabji.wheatherapp.R;
import com.pabji.wheatherapp.data.models.CityModel;

import java.util.ArrayList;
import java.util.List;


public class CityListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CityModel> cities = new ArrayList<>();
    private OnItemClickListener mListener;

    public CityListAdapter(OnItemClickListener listener) {
        mListener = listener;
    }

    public void setData(List<CityModel> result) {
        cities = result;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(CityModel model);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_list, parent, false);
        return new CityHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CityHolder viewholder = (CityHolder) holder;
        viewholder.bindItem(cities.get(position));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    private class CityHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final OnItemClickListener mListener;
        private TextView tvCityName;
        private CityModel mModel;

        public CityHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            mListener = listener;

            tvCityName = itemView.findViewById(R.id.tv_item_name);
            tvCityName.setOnClickListener(this);
        }

        public void bindItem(CityModel model){
            mModel = model;
            tvCityName.setText(model.getName());
        }


        @Override
        public void onClick(View v) {
            mListener.onItemClick(mModel);
        }
    }
}
