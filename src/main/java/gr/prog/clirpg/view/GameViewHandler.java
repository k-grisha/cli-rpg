package gr.prog.clirpg.view;

import gr.prog.clirpg.model.Character;
import gr.prog.clirpg.services.WorldService;

import java.util.List;

import static gr.prog.clirpg.view.View.*;

public class GameViewHandler extends BaseViewHandler {

	private final WorldService worldService;
	private String notification = "";

	public GameViewHandler(WorldService worldService) {
		super("gameView.txt");
		this.worldService = worldService;
	}

	@Override
	public View dispatchCommand(String command) {
		if (command.equals("m")) {
			return MAIN_MENU;
		}
		if (command.equals("v")) {
			return WOLD_MAP;
		}
		if (command.equals("f")) {
			if (worldService.getCurrentRoom(getHero()).getCharacters().isEmpty()) {
				notification = "There is no one to fight";
				return GAME_VIEW;
			}
			return FIGHT_VIEW;
		}
		boolean movedSuccess = true;
		switch (command) {
			case "w":
				movedSuccess = worldService.moveUp(getHero());
				break;
			case "s":
				movedSuccess = worldService.moveDown(getHero());
				break;
			case "a":
				movedSuccess = worldService.moveLeft(getHero());
				break;
			case "d":
				movedSuccess = worldService.moveRight(getHero());
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
		List<Character> characters = worldService.getCurrentRoom(getHero()).getCharacters();
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
				.replace("${description}", worldService.getCurrentRoom(getHero()).getDescription())
				.replace("${characters}", sb.toString())
				.replace("${username}", getHero().getName())
				.replace("${health}", getHero().getHealth().toString())
				.replace("${strength}", getHero().getStrength().toString())
				.replace("${experience}", getHero().getExperience().toString());
	}
}
