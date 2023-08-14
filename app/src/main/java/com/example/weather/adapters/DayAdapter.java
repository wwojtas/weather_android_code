package com.example.weather.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;
import com.example.weather.config.ConfigConstants;
import com.example.weather.model.WeathersDay;
import com.example.weather.utils.Utils;

import java.util.ArrayList;


public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder> {
    ArrayList<WeathersDay> weathersDays;
    Context context;

    public DayAdapter(ArrayList<WeathersDay> weathersDays) {
        this.weathersDays = weathersDays;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_day, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dateTxt.setText(weathersDays.get(position).getDate());
        String icon = weathersDays.get(position).getIcon();
        ImageView iconWeather = holder.icon;
        Utils.setWeatherIcon(icon, iconWeather);
        holder.description.setText(weathersDays.get(position).getDescription());
        holder.dayTemperature.setText(weathersDays.get(position).getDayTemperature() + ConfigConstants.TEMPERATURE_UNIT);
    }

    public void updateData(ArrayList<WeathersDay> newItems) {
        weathersDays.clear();
        weathersDays.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return weathersDays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTxt;
        ImageView icon;
        TextView description;
        TextView dayTemperature;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTxt = itemView.findViewById(R.id.dateTxt);
            icon = itemView.findViewById(R.id.icon);
            description = itemView.findViewById(R.id.description);
            dayTemperature = itemView.findViewById(R.id.dayTemperature);
        }
    }
}
