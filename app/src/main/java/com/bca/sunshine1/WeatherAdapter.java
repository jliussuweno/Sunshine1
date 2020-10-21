package com.bca.sunshine1;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.sunshine.R;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter {

    private IGeneralCallback callback = null;
    private List<WeatherModel> arrWeather = new ArrayList<>();

    public void setData(List<WeatherModel> weathers) {
        arrWeather = weathers;
    }

    public void addData(WeatherModel weather) {
        arrWeather.add(weather);
    }

    public void setCallback(IGeneralCallback callbackDelegate){
        callback = callbackDelegate;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder tmpHolder = null;

        View tmpView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        tmpHolder = new WeatherHolder(tmpView);

        return tmpHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        double i = Double.parseDouble(arrWeather.get(position).getMaxDegrees());
        double j = Double.parseDouble(arrWeather.get(position).getMinDegrees());

        int value = (int)i;
        int value1 = (int)j;
        final String concateString = " " + arrWeather.get(position).getDay() + " - " + arrWeather.get(position).getWeatherName() + " - " + value + " \u2103" + "/" + value1 + " \u2103" ;

        ((WeatherHolder)holder).setText(concateString);

        ((WeatherHolder)holder).getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.itemPressedCallback(concateString);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrWeather.size();
    }
}
