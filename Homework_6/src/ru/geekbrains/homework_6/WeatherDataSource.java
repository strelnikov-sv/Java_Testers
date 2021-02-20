package ru.geekbrains.homework_6;

// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

import okhttp3.*;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherDataSource {

    private static final String LOCATION_URL_BASE = "dataservice.accuweather.com";
    private static final String LOCATION = "locations";
    private static final String API_VERSION = "v1";
    private static final String CITIES = "cities";

    private static final String FORECAST_URL_BASE = "dataservice.accuweather.com";
    private static final String FORECAST = "forecasts";
    private static final String FORECAST_TYPE = "daily";
    private static final String FORECAST_PERIOD = "5day";

    private static final String API_KEY = "lQw0RxZD0yCYFZALzl0pgyJt9UypSBHN";

    private static final OkHttpClient client = new OkHttpClient();

    private static ResponseBody validateResponse(Response response) throws IOException {
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            throw new IOException("Вернулось пустое тело запроса");
        }
        if (response.isSuccessful()) {
            return responseBody;
        }
        Matcher matcher = Pattern.compile("(?:\"Message\":\")([\\w\\s]+)").matcher(responseBody.string());
        if (matcher.find()) {
            throw new IOException(matcher.group(1));
        } else {
            throw new IOException("Неизвестная ошибка. HTTP запрос вернулся с кодом " + response.code());
        }
    }

    private static String encodeCity(String city) throws IOException {
        HttpUrl url = new HttpUrl.Builder()
            .scheme("http")
            .host(LOCATION_URL_BASE)
            .addPathSegment(LOCATION)
            .addPathSegment(API_VERSION)
            .addPathSegment(CITIES)
            .addPathSegment("RU")
            .addPathSegment("search")
            .addQueryParameter("apikey", API_KEY)
            .addQueryParameter("q", city)
                .addQueryParameter("language", "ru-ru")
                .build();

        Request requesthttp = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(url)
                .build();
        Response response = client.newCall(requesthttp).execute();
        ResponseBody responseBody = validateResponse(response);
        String jsonResponse = responseBody.string();

        Matcher matcher = Pattern.compile("(?:\"Key\":\")(\\d{2,})").matcher(jsonResponse);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new IOException("Город не найден");
        }
    }

    public static String getWeather(String location_code) throws IOException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(FORECAST_URL_BASE)
                .addPathSegment(FORECAST)
                .addPathSegment(API_VERSION)
                .addPathSegment(FORECAST_TYPE)
                .addPathSegment(FORECAST_PERIOD)
                .addPathSegment(location_code)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("language", "ru-ru")
                .addQueryParameter("metric", "true")
                .build();

        Request requesthttp = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(url)
                .build();

        Response response = client.newCall(requesthttp).execute();
        ResponseBody responseBody = validateResponse(response);

        return responseBody.string();
    }

    public static String getWeatherInCity(String city) throws IOException {
                return getWeather(encodeCity(city));
    }
}
