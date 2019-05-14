package gr.prog.clirpg.view.handlers;

import gr.prog.clirpg.services.CurrentHero;
import gr.prog.clirpg.services.HeroService;
import gr.prog.clirpg.view.View;

import static gr.prog.clirpg.view.View.CREATE_HERO;
import static gr.prog.clirpg.view.View.GAME_PLAY;
import static gr.prog.clirpg.view.View.LOAD_GAME;
import static gr.prog.clirpg.view.View.MAIN_MENU;

public class MainMenuHandler extends BaseViewHandler {

	private final HeroService heroService;
	private final CurrentHero currentHero;

	public MainMenuHandler(HeroService heroService, CurrentHero currentHero) {
		super("mainMenu.txt");
		this.heroService = heroService;
		this.currentHero = currentHero;
	}

	@Override
	public View dispatchCommand(String command) {
		if (command.equals("n")) {
			return CREATE_HERO;
		}
		if (command.equals("l")) {
			return LOAD_GAME;
		}
		if (command.equals("s") && currentHero.getHero() != null) {
			heroService.save(currentHero.getHero());
			return GAME_PLAY;
		}
		if (command.equals("b") && currentHero.getHero() != null) {
			return GAME_PLAY;
		}
		if (command.equals("q")) {
			System.exit(0);
		}
		return MAIN_MENU;
	}

	@Override
	public String getTextPresent() {
		return getContent();
	}
}
