package gr.prog.clirpg.view.handlers;

import gr.prog.clirpg.model.characters.Hero;
import gr.prog.clirpg.services.CurrentHero;
import gr.prog.clirpg.services.HeroService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class BaseViewHandlerTest {
	private HeroService heroService = Mockito.mock(HeroService.class, Mockito.RETURNS_DEEP_STUBS);
	private CurrentHero currentHero = Mockito.mock(CurrentHero.class, Mockito.RETURNS_DEEP_STUBS);
	private List<BaseViewHandler> viewHandlers = Arrays.asList(
			new CreateHeroHandler(heroService, currentHero),
			new MainMenuHandler(heroService, currentHero),
			new WelcomeHandler(),
			new WorldMapHandler(currentHero),
			new LoadGameHandler(heroService, currentHero),
			new GameOverHandler()
	);

	//
	@Test
	public void getTextPresent() {
//		Mockito.when(currentHero.getHero()).thenReturn(Mockito.mock(Hero.class, Mockito.RETURNS_DEEP_STUBS));
		for (BaseViewHandler viewHandler : viewHandlers) {
			String textPresent = viewHandler.getTextPresent();
			Assert.assertNotNull(textPresent);
			Assert.assertFalse(textPresent.isEmpty());

		}

	}
}