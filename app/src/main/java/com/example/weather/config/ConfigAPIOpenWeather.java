package com.example.weather.config;


public class ConfigAPIOpenWeather {

    /**
     * openWeather - weather data
     */
    public static final String OPEN_WEATHER_ONE_CALL_MAIN_QUERY = "http://api.openweathermap.org/data/2.5/forecast?q=";
    public static final String EXCLUDE_PARAMETERS = "&exclude=daily";
    public static final String BEFORE_API_KEY = "&appid=";
    public static final String UNITS_PARAMETER = "&units=metric";
    public static final String LANGUAGE_PREFIX = "&lang=";

    /**
     *  openWeather - icon data
     */
    public static final String OPEN_WEATHER_URL_ICON_CALL = "http://openweathermap.org/img/wn/";
    public static final String ICON_CALL_LAST_PARAMETER = "@2x.png";


}
