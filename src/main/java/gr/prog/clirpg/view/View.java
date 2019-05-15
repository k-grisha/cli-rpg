package gr.prog.clirpg.view;

import gr.prog.clirpg.repository.InMemoryRepository;
import gr.prog.clirpg.services.CurrentHero;
import gr.prog.clirpg.services.HeroService;
import gr.prog.clirpg.view.handlers.*;

/**
 * View registry
 */
public enum View {

	WELCOME(new WelcomeHandler()),
	MAIN_MENU(new MainMenuHandler(new HeroService(InMemoryRepository.getInstance()), CurrentHero.getInstance())),
	LOAD_GAME(new LoadGameHandler(new HeroService(InMemoryRepository.getInstance()), CurrentHero.getInstance())),
	CREATE_HERO(new CreateHeroHandler(new HeroService(InMemoryRepository.getInstance()), CurrentHero.getInstance())),
	GAME_PLAY(new GamePlayHandler(CurrentHero.getInstance())),
	WOLD_MAP(new WorldMapHandler(CurrentHero.getInstance())),
	FIGHT(new FightHandler(CurrentHero.getInstance())),
	GAME_OVER(new GameOverHandler());

	private final ViewHandler handler;

	View(ViewHandler viewHandler) {
		handler = viewHandler;
	}

	public ViewHandler getHandler() {
		return handler;
	}


}
