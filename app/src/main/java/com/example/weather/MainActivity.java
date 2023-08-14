package com.example.weather;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.weather.adapters.DayAdapter;
import com.example.weather.config.ConfigConstants;
import com.example.weather.model.WeathersDay;
import com.example.weather.service.OpenWeatherApiService;
import com.example.weather.utils.JsonUtils;
import com.example.weather.utils.SpaceItemDecoration;
import com.example.weather.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterDays;

    EditText enteredLocationEt;
    Button buttonGetB;
    TextView countryTV, cityTV, mainDateTV, pressureTV, temperatureTV;
    ImageView weatherImageIV, iconIV;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();

        enteredLocationEt = findViewById(R.id.enteredLocation);
        buttonGetB = findViewById(R.id.buttonGet);
        countryTV = findViewById(R.id.country);
        cityTV = findViewById(R.id.city);
        mainDateTV = findViewById(R.id.mainDate);
        pressureTV = findViewById(R.id.pressure);
        temperatureTV = findViewById(R.id.temperature);
        weatherImageIV = findViewById(R.id.weatherImage);
        iconIV = findViewById(R.id.icon);
        buttonGetB.setOnClickListener(this::onClick);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void onClick(View view) {
        String cityName = enteredLocationEt.getText().toString().trim();
        String url = OpenWeatherApiService.getUrlToOpenWeatherAPI(cityName, ConfigConstants.COUNTRY_CODE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonWeatherGlobalObject = new JSONObject(response);
                setMainViewFields(jsonWeatherGlobalObject);
                ArrayList<WeathersDay> weathersDayList = JsonUtils.getWeathersDay(jsonWeatherGlobalObject);
                ((DayAdapter) adapterDays).updateData(weathersDayList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_LONG).show());
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMainViewFields(JSONObject jsonWeatherGlobalObject) throws JSONException {
        JSONArray jsonArrayListsWithObjects = jsonWeatherGlobalObject.getJSONArray(ConfigConstants.NAME_LIST);
        JSONObject jsonObjectWithLocationData = jsonWeatherGlobalObject.getJSONObject(ConfigConstants.NAME_CITY);
        JSONObject jsonObjectWithWeatherData = jsonArrayListsWithObjects.getJSONObject(0);

        countryTV.setText(jsonObjectWithLocationData.getString(ConfigConstants.NAME_COUNTRY) + ConfigConstants.COMMA_SEPARATOR);
        cityTV.setText(jsonObjectWithLocationData.getString(ConfigConstants.NAME_NAME));
        mainDateTV.setText(Utils.getLocalDateTimeFromTimestamp(
                        JsonUtils.getTimestampFromJsonObject(jsonObjectWithWeatherData))
                .format(Utils.setDateFormat()));
        pressureTV.setText(JsonUtils.getPressureFromJsonObject(jsonObjectWithWeatherData) + ConfigConstants.PRESSURE_UNIT);
        temperatureTV.setText(JsonUtils.getTemperatureFromJsonObject(jsonObjectWithWeatherData) + ConfigConstants.TEMPERATURE_UNIT);
        Utils.setWeatherIcon(
                JsonUtils.getIconNameFromJsonObject(jsonObjectWithWeatherData),
                weatherImageIV);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initRecyclerView() {
        RecyclerView recyclerView;
        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(ConfigConstants.SPACING_BETWEEN_ELEMENTS_DAY);
        ArrayList<WeathersDay> items = new ArrayList<>();

        // default data
        items.add(new WeathersDay("default day", "03d", "default clouds", "21"));
        items.add(new WeathersDay("default day", "04d", " clouds", "23"));
        items.add(new WeathersDay("default day", "03d", " default rain", "24"));
        items.add(new WeathersDay("default day", "03d", "deafault smooth", "25"));
        items.add(new WeathersDay("default day", "03d", "smooth", "25"));

        recyclerView = findViewById(R.id.dayRecyclerView);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapterDays = new DayAdapter(items);
        recyclerView.setAdapter(adapterDays);
    }


}