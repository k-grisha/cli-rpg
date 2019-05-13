package gr.prog.clirpg.view;

import gr.prog.clirpg.model.Character;
import gr.prog.clirpg.services.WorldService;

import java.util.List;

import static gr.prog.clirpg.view.View.*;

public class FightHandler extends BaseViewHandler {

	private final WorldService worldService;
	private String notification = "";

	public FightHandler(WorldService worldService) {
		super("fightView.txt");
		this.worldService = worldService;
	}

	@Override
	public View dispatchCommand(String command) {
		if (command.equals("m")) {
			return MAIN_MENU;
		}
		if (command.equals("b")) {
			return GAME_VIEW;
		}
		Character character = null;
		try {
			int index = Integer.parseInt(command);
			character = worldService.getCurrentRoom(getHero()).getCharacters().get(index);
		} catch (NumberFormatException e) {
			// todo Logging
		}
		if (character == null) {
			return FIGHT_VIEW;
		}
		notification = getHero().attack(character);
		if (character.isAlive()) {
			notification += "\n" + character.attack(getHero());
		} else {
			notification += "\n" + character.getName() + " died.";
		}
		if (!getHero().isAlive()) {
			notification += "\n" + getHero().getName() + " died.";
		}
		return FIGHT_VIEW;
	}

	@Override
	public String getTextPresent() {
		List<Character> characters = worldService.getCurrentRoom(getHero()).getCharacters();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < characters.size(); i++) {
			Character character = characters.get(i);
			sb.append("[").append(i).append("] : ")
					.append(character.getName());
			if (character.isAlive()) {
				sb.append(" [health:").append(character.getHealth()).append(", strength:").append(character.getStrength()).append("]\n");
			} else {
				sb.append(Color.ANSI_RED).append(" [dead]").append(Color.ANSI_RESET).append("\n");
			}

		}
		return getContent()
				.replace("${notification}", notification)
				.replace("${characters}", sb.toString())
				.replace("${username}", getHero().getName())
				.replace("${health}", getHero().getHealth().toString())
				.replace("${strength}", getHero().getStrength().toString())
				.replace("${experience}", getHero().getExperience().toString());
	}
}
