package ru.geekbrains.homework_6;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class UserInterface {

	private final Controller controller = new Controller();

	public void runApplication() throws SQLException{
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Введите название города на русском или английском языке");
			String city = scanner.nextLine();
			setGlobalCity(city);

			System.out.println("Введите ответ: 1 -  Получить погоду на следующие 5 дней, " +
					"Введите ответ: 2 - Показать историю запросов, " +
					"выход (exit) - завершить работу");
			String result = scanner.nextLine();

			checkIsExit(result);

			try {
				validateUserInput(result);
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}

			try {
				notifyController(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static final void checkIsExit(String result) {
		if ("выход".equalsIgnoreCase(result) || "exit".equalsIgnoreCase(result)) {
			System.out.println("Завершаю работу");
			System.exit(0);
		}
	}

	private static final void setGlobalCity(String city) {
		ApplicationGlobalState.getInstance().setSelectedCity(city);
	}

	private static final void validateUserInput(String userInput) throws IOException {
		if (userInput == null || userInput.length() != 1) {
			throw new IOException("Incorrect user input: expected one digit as answer, but actually get " + userInput);
		}
		try {
			Integer.parseInt(userInput);
		} catch (NumberFormatException e) {
			throw new IOException("Incorrect user input: character is not numeric!");
		}
	}

	private void notifyController(String input) throws IOException, SQLException {
		controller.onUserInput(input);
	}
}
