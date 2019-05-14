package gr.prog.clirpg.view;

import gr.prog.clirpg.repository.InMemoryRepository;
import gr.prog.clirpg.services.CurrentHero;
import gr.prog.clirpg.services.HeroService;

public enum View {

	WELCOME(new WelcomeHandler()),
	MAIN_MENU(new MainMenuHandler(new HeroService(InMemoryRepository.getInstance()), CurrentHero.getInstance())),
	LOAD_GAME(new LoadGameHandler(new HeroService(InMemoryRepository.getInstance()), CurrentHero.getInstance())),
	CREATE_NEW_HERO(new CreateHeroHandler(new HeroService(InMemoryRepository.getInstance()), CurrentHero.getInstance())),
	GAME_VIEW(new GameViewHandler(CurrentHero.getInstance())),
	WOLD_MAP(new WorldMapHandler(CurrentHero.getInstance())),
	FIGHT_VIEW(new FightHandler(CurrentHero.getInstance()));

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
