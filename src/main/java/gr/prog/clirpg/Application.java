package gr.prog.clirpg;

import gr.prog.clirpg.view.View;

import java.util.Scanner;

public class Application {

	private static View currentView = View.WELCOME;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String command = "";
		while (!command.equals("q")) {
			System.out.print(currentView.getHandler().getTextPresent());
			command = scanner.next();
			currentView = currentView.getHandler().dispatchCommand(command);
		}
		scanner.close();
	}

	private final static String mainMenu = "\n\n\n\n" +
			"q - Quit\n";
}
