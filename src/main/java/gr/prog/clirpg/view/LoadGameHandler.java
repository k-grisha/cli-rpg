package gr.prog.clirpg.view;

import gr.prog.clirpg.model.Hero;
import gr.prog.clirpg.services.HeroService;

import java.util.List;

import static gr.prog.clirpg.view.View.*;

public class LoadGameHandler extends BaseViewHandler {

	private final HeroService heroService;

	public LoadGameHandler(HeroService heroService) {
		super("loadGame.txt");
		this.heroService = heroService;
	}

	@Override
	public View dispatchCommand(String command) {
		if (command.equals("m")) {
			return MAIN_MENU;
		}
		int index = Integer.parseInt(command);
		Hero loadedHero = heroService.load(index);
		if (loadedHero != null) {
			setHero(loadedHero);
			return GAME_VIEW;
		}
		return LOAD_GAME;
	}

	@Override
	public String getTextPresent() {
		List<Hero> heroes = heroService.getAllSavedNames();
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
