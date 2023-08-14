package com.example.weather.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.weather.config.ConfigConstants;
import com.example.weather.service.OpenWeatherApiService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Utils {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String getUserCountryCode(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        Locale locale = configuration.getLocales().get(0);
        return locale.getCountry();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static DecimalFormat setDecimalNumberFormatting(){
        return new DecimalFormat("#");
    }

    public static void setWeatherIcon(String icon, ImageView iconWeather) {
        Glide.with(iconWeather.getContext())
                .load(OpenWeatherApiService.getUrlToOpenWeatherIconImageAPI(icon))
                .into(iconWeather);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static DateTimeFormatter setDateFormat() {
        return DateTimeFormatter
                .ofPattern(ConfigConstants.DATE_PATTERN, new Locale(ConfigConstants.COUNTRY_CODE));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LocalDateTime getLocalDateTimeFromTimestamp(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneOffset.UTC);
    }

}
