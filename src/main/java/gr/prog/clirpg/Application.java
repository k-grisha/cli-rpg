package gr.prog.clirpg;

import gr.prog.clirpg.services.HeroService;

import java.util.Scanner;

public class Application {

	private static Screen currentScreen = Screen.WELCOME;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String command = "";
		while (!command.equals("q")) {
			System.out.print(currentScreen.getView());
//			System.out.print(mainMenu);
//			System.out.print("Input a command: ");
			command = scanner.next();
			currentScreen = currentScreen.dispatchCommand(command);

			System.out.println(command);
		}
		scanner.close();
	}

	private final static String mainMenu = "\n\n\n\n" +
			"q - Quit\n";
}
