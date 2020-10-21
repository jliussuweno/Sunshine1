package com.bca.sunshine1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bca.sunshine.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IGeneralCallback {

    RecyclerView recyclerview;

    final WeatherAdapter tmpAdapter = new WeatherAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.obj_recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);

        tmpAdapter.setCallback(this);
        initData();
        recyclerview.setAdapter(tmpAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            initDataRefresh();
        }
        return super.onOptionsItemSelected(item);
    }


    void initData() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://andfun-weather.udacity.com/weather";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("JEJE", "Response is: " + response);

                        List<WeatherModel> weatherModelList = new ArrayList<>();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("list");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jObject = jsonArray.getJSONObject(i);

                                String time = jObject.getString("dt");

                                JSONObject jsonObject1 = jObject.getJSONObject("temp");
                                String max = jsonObject1.getString("max");
                                String min = jsonObject1.getString("min");


                                JSONArray jsonObject2 = jObject.getJSONArray("weather");
                                String weatherName = jsonObject2.getJSONObject(0).getString("main");

                                weatherModelList.add(new WeatherModel(time, weatherName, max, min));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        tmpAdapter.setData(weatherModelList);
                        tmpAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JEJE", "That didn't work!");
            }
        });

        queue.add(stringRequest);
    }


    void initDataRefresh() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://andfun-weather.udacity.com/weather";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("JEJE", "Response is: " + response);

                        List<WeatherModel> weatherModelList = new ArrayList<>();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("list");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jObject = jsonArray.getJSONObject(i);

                                String time = jObject.getString("dt");

                                JSONObject jsonObject1 = jObject.getJSONObject("temp");
                                String max = jsonObject1.getString("max");
                                String min = jsonObject1.getString("min");


                                JSONArray jsonObject2 = jObject.getJSONArray("weather");
                                String weatherName = jsonObject2.getJSONObject(0).getString("main");

                                weatherModelList.add(new WeatherModel(time, weatherName, max, min));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        tmpAdapter.setData(weatherModelList);
                        tmpAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JEJE", "That didn't work!");
            }
        });

        queue.add(stringRequest);
    }

    @Override
    public void itemPressedCallback(String weatherData) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, weatherData, duration);
        toast.show();
    }

}