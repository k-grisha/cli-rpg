package gr.prog.clirpg.view.handlers;

import gr.prog.clirpg.model.characters.Character;
import gr.prog.clirpg.model.characters.Hero;
import gr.prog.clirpg.services.CurrentHero;
import gr.prog.clirpg.view.View;
import gr.prog.clirpg.view.utils.Color;

import java.util.List;

import static gr.prog.clirpg.view.View.FIGHT;
import static gr.prog.clirpg.view.View.GAME_PLAY;
import static gr.prog.clirpg.view.View.MAIN_MENU;

public class FightHandler extends BaseViewHandler {

	private final CurrentHero currentHero;
	private String notification = "";

	public FightHandler(CurrentHero currentHero) {
		super("fightView.txt");
		this.currentHero = currentHero;
	}

	@Override
	public View dispatchCommand(String command) {
		Hero hero = currentHero.getHero();
		if (command.equals("m")) {
			return MAIN_MENU;
		}
		if (command.equals("b")) {
			return GAME_PLAY;
		}
		Character character;
		try {
			int index = Integer.parseInt(command);
			character = hero.getCurrentRoom().getCharacters().get(index);
		} catch (NumberFormatException e) {
			// todo Logging
			return FIGHT;
		}
		notification = hero.attack(character);
		if (character.isAlive()) {
			notification += "\n" + character.attack(hero);
		} else {
			notification += "\n" + character.getName() + " died.";
		}
		// todo handle death
		if (!hero.isAlive()) {
			notification += "\n" + hero.getName() + " died.";
		}
		return FIGHT;
	}

	@Override
	public String getTextPresent() {
		Hero hero = currentHero.getHero();
		List<Character> characters = hero.getCurrentRoom().getCharacters();
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
				.replace("${username}", hero.getName())
				.replace("${health}", hero.getHealth().toString())
				.replace("${strength}", hero.getStrength().toString())
				.replace("${experience}", hero.getExperience().toString());
	}
}
