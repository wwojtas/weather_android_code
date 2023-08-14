package com.example.weather.service;

import com.example.weather.config.ConfigAPIOpenWeather;
import com.example.weather.config.ConfigApiKey;

public class OpenWeatherApiService {

    public static String getUrlToOpenWeatherAPI(String cityName, String countryCode) {
        return ConfigAPIOpenWeather.OPEN_WEATHER_ONE_CALL_MAIN_QUERY
                + cityName
                + ConfigAPIOpenWeather.EXCLUDE_PARAMETERS
                + ConfigAPIOpenWeather.UNITS_PARAMETER
                + ConfigAPIOpenWeather.LANGUAGE_PREFIX
                + countryCode
                + ConfigAPIOpenWeather.BEFORE_API_KEY
                + ConfigApiKey.getOpenWeatherApiKey();
    }

    public static String getUrlToOpenWeatherIconImageAPI(String icon){
        return ConfigAPIOpenWeather.OPEN_WEATHER_URL_ICON_CALL + icon + ConfigAPIOpenWeather.ICON_CALL_LAST_PARAMETER;
    }

}
