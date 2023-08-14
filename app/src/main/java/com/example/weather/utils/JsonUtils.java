package com.example.weather.utils;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.weather.config.ConfigConstants;
import com.example.weather.model.WeathersDay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static long getTimestampFromJsonObject(JSONObject jsonObject) throws JSONException {
        return jsonObject.getLong(ConfigConstants.NAME_DT);
    }

    @NonNull
    public static String getIconNameFromJsonObject(JSONObject jsonObject) throws JSONException {
        JSONObject iconObject = jsonObject.getJSONArray(ConfigConstants.NAME_WEATHER).getJSONObject(0);
        return iconObject.getString(ConfigConstants.NAME_ICON);
    }

    public static String getPressureFromJsonObject(JSONObject jsonObject) throws JSONException {
        JSONObject pressureObject = jsonObject.getJSONObject(ConfigConstants.NAME_MAIN);
        return pressureObject.getString(ConfigConstants.NAME_PRESSURE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String getTemperatureFromJsonObject(JSONObject jsonObject) throws JSONException {
        JSONObject temperatureObject = jsonObject.getJSONObject(ConfigConstants.NAME_MAIN);
        return Utils.setDecimalNumberFormatting().format(temperatureObject.getDouble(ConfigConstants.NAME_TEMP));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<WeathersDay> getWeathersDay(JSONObject jsonObject) throws JSONException {
        ArrayList<WeathersDay> weatherList = new ArrayList<>();
        JSONArray jsonArrayListsWithObjects = jsonObject.getJSONArray(ConfigConstants.NAME_LIST);

        for (int i = 0; i < jsonArrayListsWithObjects.length(); i++) {
            JSONObject jsonObjectWithWeatherData = jsonArrayListsWithObjects.getJSONObject(i);

            String dtTxt = jsonObjectWithWeatherData.getString(ConfigConstants.NAME_DT_TXT);
            if (dtTxt.endsWith(ConfigConstants.NAME_12_00_00)){
                String dateFromTimestamp = Utils.getLocalDateTimeFromTimestamp(JsonUtils.getTimestampFromJsonObject(jsonObjectWithWeatherData))
                        .format(Utils.setDateFormat());

                JSONObject weatherElementObject = jsonObjectWithWeatherData.getJSONArray(ConfigConstants.NAME_WEATHER).getJSONObject(0);
                String iconToDay = weatherElementObject.getString(ConfigConstants.NAME_ICON);
                String description = weatherElementObject.getString(ConfigConstants.NAME_DESCRIPTION);

                String temperatureForDay = JsonUtils.getTemperatureFromJsonObject(jsonObjectWithWeatherData);
                WeathersDay weathersDay = new WeathersDay(dateFromTimestamp, iconToDay, description, temperatureForDay);
                weatherList.add(weathersDay);
            }
        }
        return weatherList;
    }
}
