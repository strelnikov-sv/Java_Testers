package ru.geekbrains.homework_6;

import java.io.IOException;
import java.sql.*;
import java.time.Instant;
import java.util.List;

public class DatabaseRepositorySQLiteImpl {

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private final String filename;
    private static final String createTableQuery = "CREATE TABLE IF NOT EXISTS weather (\n" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "city TEXT NOT NULL,\n" +
            "date_time TEXT NOT NULL,\n" +
            "temperatureMin REAL NOT NULL,\n" +
            "temperatureMax REAL NOT NULL,\n" +
            "weather_text TEXT NOT NULL \n" +
            ");";
    private static final String insertWeatherQuery = "INSERT INTO weather (" +
            "city, date_time, temperatureMin, temperatureMax, weather_text) " +
            "VALUES (?,?,?,?,?)";

    private static final String selectWeatherQuery = "SELECT * FROM weather";

    public DatabaseRepositorySQLiteImpl() {
        filename = ApplicationGlobalState.getInstance().getDbFileName();
    }

    public void selectWeather() {
        try (Connection connection = getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(selectWeatherQuery);
            while (resultSet.next()) {
                System.out.println("Город: " + resultSet.getString("city"));
                System.out.println("Дата: " + WeatherResponse.humanReadableDate
                        ((Instant.ofEpochMilli(resultSet.getLong("date_time")))));
                System.out.println("МинТемп: " + resultSet.getString("temperatureMin"));
                System.out.println("МаксТемп: " + resultSet.getString("temperatureMax"));
                System.out.println("Условия: " + resultSet.getString("weather_text") + "\n");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + filename);
        return connection;
    }

    public void createTableIfNotExists() {
        try (Connection connection = getConnection()) {
            connection.createStatement().execute(createTableQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean saveWeatherData(WeatherResponse weatherData) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement saveWeather = connection.prepareStatement(insertWeatherQuery)) {
            saveWeather.setString(1, ApplicationGlobalState.getInstance().getSelectedCity());
            saveWeather.setTimestamp(2, Timestamp.from(weatherData.getDatestamp()));
            saveWeather.setInt(3, weatherData.getTemperatureMinValue());
            saveWeather.setInt(4, weatherData.getTemperatureMaxValue());
            saveWeather.setString(5, weatherData.getWeatherCondition());
            return saveWeather.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new SQLException("Failure on saving weather object");
    }

    public List<WeatherData> getAllSavedData() throws IOException {
        throw new IOException("Not implemented exception");
    }
}
