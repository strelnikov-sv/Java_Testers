package ru.geekbrains.homework_6;

import ru.geekbrains.homework_6.enums.Functionality;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

   private Map<Integer, Functionality> variantResult = new HashMap<>();

    public Controller() {
        variantResult.put(1, Functionality.GET_WEATHER_IN_NEXT_5_DAYS);
    }

    public void onUserInput(String input) throws IOException {
        int command = Integer.parseInt(input);
        if (!variantResult.containsKey(command)) {
            throw new IOException("There is no command for command-key " + command);
        }

        switch (variantResult.get(command)) {
            case GET_CURRENT_WEATHER:
                System.out.println("Исключена реализация");
                break;
            case GET_WEATHER_IN_NEXT_5_DAYS:
                getWeatherIn5Days();
                break;
        }
    }

    public void getWeatherIn5Days() throws IOException {
        List<WeatherResponse> result = WeatherDataSource.getWeatherInCity(
                ApplicationGlobalState.getInstance().getSelectedCity());
        for (WeatherResponse i : result) {
            System.out.println(i.toString());
        }
    }
}
