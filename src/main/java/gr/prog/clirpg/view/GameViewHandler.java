package gr.prog.clirpg.view;

import gr.prog.clirpg.model.Character;
import gr.prog.clirpg.services.WorldService;

import java.util.List;

import static gr.prog.clirpg.view.View.GAME_VIEW;
import static gr.prog.clirpg.view.View.MAIN_MENU;
import static gr.prog.clirpg.view.View.WOLD_MAP;

public class GameViewHandler extends BaseViewHandler {

	private final WorldService worldService;

	public GameViewHandler(WorldService worldService) {
		super("gameView.txt");
		this.worldService = worldService;
	}

	private String notification = "";

	@Override
	public View dispatchCommand(String command) {
		if (command.equals("m")) {
			return MAIN_MENU;
		}
		if (command.equals("v")) {
			return WOLD_MAP;
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
			notification = "Notification! You cant move there!";
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
			sb.append(character.getDescription()).append(" [health:").append(character.getHealth())
					.append(", strength:").append(character.getStrength()).append("]\n");
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
