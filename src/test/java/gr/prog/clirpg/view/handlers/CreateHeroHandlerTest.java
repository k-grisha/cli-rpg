package gr.prog.clirpg.view.handlers;

import gr.prog.clirpg.model.characters.Hero;
import gr.prog.clirpg.services.CurrentHero;
import gr.prog.clirpg.services.HeroService;
import gr.prog.clirpg.view.View;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class CreateHeroHandlerTest {
	private HeroService heroService = Mockito.mock(HeroService.class);
	private CurrentHero currentHero = Mockito.mock(CurrentHero.class);
	private CreateHeroHandler createHeroHandler = new CreateHeroHandler(heroService, currentHero);

	@Test
	public void dispatchCommand_heroName_success() {
		Mockito.when(heroService.createHero(Mockito.anyString())).thenReturn(Mockito.mock(Hero.class));
		View view = createHeroHandler.dispatchCommand("Grisha");
		Assert.assertEquals(View.GAME_PLAY, view);
		Mockito.verify(heroService, Mockito.times(1)).createHero("Grisha");
		Mockito.verify(currentHero, Mockito.times(1)).setHero(Mockito.any(Hero.class));
		Mockito.clearInvocations(heroService);
		Mockito.clearInvocations(currentHero);
	}
}