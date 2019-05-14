package gr.prog.clirpg.view.handlers;

import gr.prog.clirpg.model.characters.Hero;
import gr.prog.clirpg.services.CurrentHero;
import gr.prog.clirpg.services.HeroService;
import gr.prog.clirpg.view.View;

import static gr.prog.clirpg.view.View.GAME_PLAY;
import static gr.prog.clirpg.view.View.MAIN_MENU;

public class CreateHeroHandler extends BaseViewHandler {
	private final HeroService heroService;
	private final CurrentHero currentHero;

	public CreateHeroHandler(HeroService heroService, CurrentHero currentHero) {
		super("newHero.txt");
		this.heroService = heroService;
		this.currentHero = currentHero;
	}

	@Override
	public View dispatchCommand(String command) {
		if (command.equals("m")) {
			return MAIN_MENU;
		}
		// todo validation
		Hero newHero = heroService.createHero(command);
		currentHero.setHero(newHero);
		return GAME_PLAY;
	}
}
