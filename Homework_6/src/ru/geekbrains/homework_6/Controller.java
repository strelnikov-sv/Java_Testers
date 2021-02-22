package ru.geekbrains.homework_6;

import ru.geekbrains.homework_6.enums.Functionality;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

   private Map<Integer, Functionality> variantResult = new HashMap<>();

    public Controller() {
        variantResult.put(1, Functionality.GET_WEATHER_IN_NEXT_5_DAYS);
        variantResult.put(2, Functionality.GET_HISTORY);
    }

    public void onUserInput(String input) throws IOException, SQLException {
        int command = Integer.parseInt(input);
        if (!variantResult.containsKey(command)) {
            throw new IOException("There is no command for command-key " + command);
        }

        switch (variantResult.get(command)) {
            case GET_HISTORY:
                getHistory();
                break;
            case GET_WEATHER_IN_NEXT_5_DAYS:
                getWeatherIn5Days();
                break;
        }
    }
    public void getHistory() throws IOException {
        DatabaseRepositorySQLiteImpl databaseConnection;
        databaseConnection = new DatabaseRepositorySQLiteImpl();
        databaseConnection.selectWeather();
    }


    public void getWeatherIn5Days() throws IOException, SQLException {
        List<WeatherResponse> result = WeatherDataSource.getWeatherInCity(
                ApplicationGlobalState.getInstance().getSelectedCity());
        for (WeatherResponse i : result) {
            System.out.println(i.toString());
        }
    }
}
