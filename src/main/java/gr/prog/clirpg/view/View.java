package gr.prog.clirpg.view;

import gr.prog.clirpg.services.HeroService;
import gr.prog.clirpg.services.WorldService;

public enum View {

	WELCOME(new WelcomeHandler()),
	MAIN_MENU(new MainMenuHandler(HeroService.getInstance())),
	LOAD_GAME(new LoadGameHandler(HeroService.getInstance())),
	CREATE_NEW_HERO(new CreateHeroHandler(HeroService.getInstance(), WorldService.getInstance())),
	MAIN_SCREEN(new GameViewHandler(WorldService.getInstance()));

	private final ViewHandler handler;

	View(ViewHandler viewHandler) {
		handler = viewHandler;
	}

	public ViewHandler getHandler() {
		return handler;
	}

	public interface ViewHandler {
		View dispatchCommand(String command);
		String getTextPresent();
	}

}
