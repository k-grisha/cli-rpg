package gr.prog.clirpg.view.handlers;

import gr.prog.clirpg.model.characters.Hero;
import gr.prog.clirpg.services.CurrentHero;
import gr.prog.clirpg.services.HeroService;
import gr.prog.clirpg.view.View;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class LoadGameHandlerTest {
	private HeroService heroService = Mockito.mock(HeroService.class);
	private CurrentHero currentHero = Mockito.mock(CurrentHero.class);
	private LoadGameHandler loadGameHandlerTest = new LoadGameHandler(heroService, currentHero);

	@Test
	public void dispatchCommand_outOfIndex_null() {
		Mockito.when(heroService.load(Mockito.anyInt())).thenReturn(null);
		View view = loadGameHandlerTest.dispatchCommand("100");
		Assert.assertEquals(View.LOAD_GAME, view);
		Mockito.verify(heroService, Mockito.times(1)).load(Mockito.anyInt());
		Mockito.clearInvocations(heroService);
	}

	@Test
	public void dispatchCommand_value_success() {
		Mockito.when(heroService.load(Mockito.anyInt())).thenReturn(Mockito.mock(Hero.class));
		View view = loadGameHandlerTest.dispatchCommand("0");
		Assert.assertEquals(View.GAME_PLAY, view);
		Mockito.verify(heroService, Mockito.times(1)).load(Mockito.anyInt());
		Mockito.clearInvocations(heroService);
	}
}