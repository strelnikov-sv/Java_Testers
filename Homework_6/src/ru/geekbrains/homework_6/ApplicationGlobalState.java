package ru.geekbrains.homework_6;

public final class ApplicationGlobalState {
    private static final String API_KEY = "lQw0RxZD0yCYFZALzl0pgyJt9UypSBHN";
    private static ApplicationGlobalState INSTANCE;
    private String selectedCity;

    private ApplicationGlobalState() {
    }

    // Непотокобезопасный код для упрощения
    public static ApplicationGlobalState getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApplicationGlobalState();
        }

        return INSTANCE;
    }

    public static String getApiKey() {
        return API_KEY;
    }

    public String getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(String selectedCity) {
        this.selectedCity = selectedCity;
    }

}

