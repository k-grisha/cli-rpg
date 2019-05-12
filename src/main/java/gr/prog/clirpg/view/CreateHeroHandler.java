package gr.prog.clirpg.view;

import gr.prog.clirpg.services.HeroService;
import gr.prog.clirpg.services.WorldService;

import static gr.prog.clirpg.view.View.MAIN_MENU;
import static gr.prog.clirpg.view.View.MAIN_SCREEN;

public class CreateHeroHandler extends BaseViewHandler {

	private final HeroService heroService;
	private final WorldService worldService;

	public CreateHeroHandler(HeroService heroService, WorldService worldService) {
		super("newHero.txt");
		this.heroService = heroService;
		this.worldService = worldService;
	}

	@Override
	public View dispatchCommand(String command) {
		if (command.equals("m")) {
			return MAIN_MENU;
		}
		setHero(heroService.create(command));
		worldService.generateNewWorld(getHero(), 10);
		return MAIN_SCREEN;
	}

	@Override
	public String getTextPresent() {
		return getContent();
	}
}
