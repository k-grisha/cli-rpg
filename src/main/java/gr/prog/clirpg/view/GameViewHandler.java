package gr.prog.clirpg.view;

import gr.prog.clirpg.model.Character;
import gr.prog.clirpg.model.Hero;
import gr.prog.clirpg.services.CurrentHero;
import gr.prog.clirpg.services.HeroService;

import java.util.List;

import static gr.prog.clirpg.view.View.FIGHT_VIEW;
import static gr.prog.clirpg.view.View.GAME_VIEW;
import static gr.prog.clirpg.view.View.MAIN_MENU;
import static gr.prog.clirpg.view.View.WOLD_MAP;

public class GameViewHandler extends BaseViewHandler {

	private final CurrentHero currentHero;
	private String notification = "";

	public GameViewHandler(CurrentHero currentHero) {
		super("gameView.txt");
		this.currentHero = currentHero;
	}

	@Override
	public View dispatchCommand(String command) {
		if (command.equals("m")) {
			return MAIN_MENU;
		}
		if (command.equals("v")) {
			return WOLD_MAP;
		}
		Hero hero = currentHero.getHero();
		if (command.equals("f")) {
			if (hero.getCurrentRoom().getCharacters().isEmpty()) {
				notification = "There is no one to fight";
				return GAME_VIEW;
			}
			return FIGHT_VIEW;
		}
		boolean movedSuccess = true;
		switch (command) {
			case "w":
				movedSuccess = hero.moveUp();
				break;
			case "s":
				movedSuccess = hero.moveDown();
				break;
			case "a":
				movedSuccess = hero.moveLeft();
				break;
			case "d":
				movedSuccess = hero.moveRight();
				break;
		}
		if (!movedSuccess) {
			notification = "You cant move there!";
		} else {
			notification = "";
		}
		return GAME_VIEW;
	}

	@Override
	public String getTextPresent() {
		Hero hero = currentHero.getHero();
		List<Character> characters = hero.getCurrentRoom().getCharacters();
		StringBuilder sb = new StringBuilder();
		if (characters.isEmpty()) {
			sb.append("Nobody here");
		}
		for (Character character : characters) {
			sb.append(character.getName());
			if (character.isAlive()) {
				sb.append(" [health:").append(character.getHealth()).append(", strength:").append(character.getStrength()).append("]\n");
			} else {
				sb.append(Color.ANSI_RED).append(" [dead]").append(Color.ANSI_RESET).append("\n");
			}
		}

		return getContent()
				.replace("${notification}", notification)
				.replace("${description}", hero.getCurrentRoom().getDescription())
				.replace("${characters}", sb.toString())
				.replace("${username}", hero.getName())
				.replace("${health}", hero.getHealth().toString())
				.replace("${strength}", hero.getStrength().toString())
				.replace("${experience}", hero.getExperience().toString());
	}
}
