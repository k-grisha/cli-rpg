package gr.prog.clirpg.view;

import gr.prog.clirpg.services.HeroService;

import static gr.prog.clirpg.view.View.*;

public class MainMenuHandler extends BaseViewHandler  {

	private final HeroService heroService;

	public MainMenuHandler(HeroService heroService) {
		super("mainMenu.txt");
		this.heroService = heroService;
	}

	@Override
	public View dispatchCommand(String command) {
		if (command.equals("1")) {
			return CREATE_NEW_HERO;
		}
		if (command.equals("2")) {
			return LOAD_GAME;
		}
		if (command.equals("3") && getHero() != null) {
			heroService.save(getHero());
			return GAME_VIEW;
		}
		if (command.equals("4") && getHero() != null) {
			return GAME_VIEW;
		}
		if (command.equals("5")) {
			System.exit(0);
		}
		return MAIN_MENU;
	}

	@Override
	public String getTextPresent() {
		return getContent();
	}
}
