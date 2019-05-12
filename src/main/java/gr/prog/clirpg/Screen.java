package gr.prog.clirpg;

import gr.prog.clirpg.model.Hero;
import gr.prog.clirpg.services.HeroService;
import gr.prog.clirpg.services.WorldService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public enum Screen {


	WELCOME("welcome.txt") {
		@Override
		public Screen dispatchCommand(String command) {
			return MAIN_MENU;
		}

		@Override
		public String getView() {
			return getContent();
		}
	},
	MAIN_MENU("mainMenu.txt") {
		@Override
		public Screen dispatchCommand(String command) {
			if (command.equals("1")) {
				return CREATE_NEW_HERO;
			}
			if (command.equals("4") && hero != null) {
				return MAIN_SCREEN;
			}
			return this;
		}

		@Override
		public String getView() {
			return getContent();
		}
	},
	CREATE_NEW_HERO("newHero.txt") {
		@Override
		public Screen dispatchCommand(String command) {
			hero = heroService.create(command);
			worldService.generateNewWorld(hero, 10);
			return MAIN_SCREEN;
		}

		@Override
		public String getView() {
			return getContent();
		}
	},
	MAIN_SCREEN("mainScreen.txt") {

		private String notification = "";

		@Override
		public Screen dispatchCommand(String command) {
			if (command.equals("m")) {
				return MAIN_MENU;
			}
			boolean movedSuccess = true;
			switch (command) {
				case "w":
					movedSuccess = worldService.moveUp(hero);
					break;
				case "s":
					movedSuccess = worldService.moveDown(hero);
					break;
				case "a":
					movedSuccess = worldService.moveLeft(hero);
					break;
				case "d":
					movedSuccess = worldService.moveRight(hero);
					break;
			}
			if (!movedSuccess) {
				notification = "Notification! You cant move there!";
			}
			return this;
		}

		@Override
		public String getView() {
			return getContent()
					.replace("${notification}", notification)
					.replace("${description}", worldService.getCurrentRom(hero).getDescription())
					.replace("${username}", hero.getName())
					.replace("${health}", hero.getHealth().toString())
					.replace("${experience}", hero.getExperience().toString());
		}
	};

	private static final HeroService heroService = new HeroService();
	private static final WorldService worldService = new WorldService();
	private static Hero hero;

	private final String content;

	Screen(String filename) {
		InputStream is = getClass().getClassLoader().getResourceAsStream("screens/" + filename);
		if (is == null) {
			throw new RpgException("Screen view file not found");
		}
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		try {
			String line = buf.readLine();
			while (line != null) {
				sb.append(line).append("\n");
				line = buf.readLine();
			}
		} catch (IOException e) {
			throw new RpgException("Fail to read file with screen view", e);
		}
		content = sb.toString();
	}

	String getContent() {
		return content;
	}

	public abstract Screen dispatchCommand(String command);

	public abstract String getView();


}
