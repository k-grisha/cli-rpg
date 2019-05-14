package gr.prog.clirpg.view.handlers;

import gr.prog.clirpg.model.characters.Hero;
import gr.prog.clirpg.services.CurrentHero;
import gr.prog.clirpg.services.HeroService;
import gr.prog.clirpg.view.View;

import java.util.List;

import static gr.prog.clirpg.view.View.GAME_PLAY;
import static gr.prog.clirpg.view.View.LOAD_GAME;
import static gr.prog.clirpg.view.View.MAIN_MENU;

public class LoadGameHandler extends BaseViewHandler {
	private final HeroService heroService;
	private final CurrentHero currentHero;

	public LoadGameHandler(HeroService heroService, CurrentHero currentHero) {
		super("loadGame.txt");
		this.heroService = heroService;
		this.currentHero = currentHero;
	}

	@Override
	public View dispatchCommand(String command) {
		if (command.equals("m")) {
			return MAIN_MENU;
		}
		Hero loadedHero = null;
		try {
			int index = Integer.parseInt(command);
			loadedHero = heroService.load(index);
		} catch (NumberFormatException e) {
			// todo Logging
		}
		if (loadedHero != null) {
			currentHero.setHero(loadedHero);
			return GAME_PLAY;
		}
		return LOAD_GAME;
	}

	@Override
	public String getTextPresent() {
		List<Hero> heroes = heroService.getAllHeroes();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < heroes.size(); i++) {
			sb.append("[").append(i).append("] : ")
					.append(" name: ").append(heroes.get(i).getName())
					.append(" health: ").append(heroes.get(i).getHealth())
					.append(" experience: ").append(heroes.get(i).getExperience()).append("\n");
		}
		return getContent()
				.replace("${savedGames}", sb.toString());
	}
}
