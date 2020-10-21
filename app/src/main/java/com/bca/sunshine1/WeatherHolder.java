package com.bca.sunshine1;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.sunshine.R;

public class WeatherHolder extends RecyclerView.ViewHolder {

    TextView textWeather;
    View parent;

    public WeatherHolder(@NonNull View itemView) {
        super(itemView);
        textWeather = itemView.findViewById(R.id.weather_text);
        parent = itemView;
    }

    public void setText(String weatherData){
        textWeather.setText(weatherData);
    }

    public View getView(){
        return parent;
    }
}
