package ru.geekbrains.homework_6;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Long.parseLong;

public final class WeatherDataSource {

    private static final String API_KEY = ApplicationGlobalState.getInstance().getApiKey();

    private static final String LOCATION_URL_BASE = "dataservice.accuweather.com";
    private static final String LOCATION = "locations";
    private static final String API_VERSION = "v1";
    private static final String CITIES = "cities";

    private static final String FORECAST_URL_BASE = "dataservice.accuweather.com";
    private static final String FORECAST = "forecasts";
    private static final String FORECAST_TYPE = "daily";
    private static final String FORECAST_PERIOD = "5day";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final OkHttpClient client = new OkHttpClient();

    private WeatherDataSource() {
        throw new IllegalStateException("WeatherDataSource");
    }

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

    public static List<WeatherResponse> getWeather5D(String location_code) throws IOException, SQLException {
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

        String jsonBody = responseBody.string();

        JsonNode dailyForecasts = objectMapper.readTree(jsonBody).at("/DailyForecasts");

        List<WeatherResponse> responses = new ArrayList<>();
        DatabaseRepositorySQLiteImpl databaseRepository = new DatabaseRepositorySQLiteImpl();
        for (JsonNode resp : dailyForecasts) {
            Instant datestamp = Instant.ofEpochSecond(parseLong(resp.at("/EpochDate").asText()));
            Integer temperatureMinValue = resp.at("/Temperature/Minimum/Value").asInt();
            Integer temperatureMaxValue = resp.at("/Temperature/Maximum/Value").asInt();
            String weatherCondition = resp.at("/Day/IconPhrase").asText();
            WeatherResponse weatherResponse = new  WeatherResponse(datestamp, temperatureMinValue, temperatureMaxValue, weatherCondition);
            databaseRepository.saveWeatherData(weatherResponse);
            responses.add(weatherResponse);
        }
        return responses;
    }

    public static List<WeatherResponse> getWeatherInCity(String city) throws IOException, SQLException {
        return getWeather5D(encodeCity(city));
    }
}
