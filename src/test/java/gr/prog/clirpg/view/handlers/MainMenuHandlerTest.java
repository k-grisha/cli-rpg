package gr.prog.clirpg.view.handlers;

import gr.prog.clirpg.model.characters.Hero;
import gr.prog.clirpg.services.CurrentHero;
import gr.prog.clirpg.services.HeroService;
import gr.prog.clirpg.view.View;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class MainMenuHandlerTest {
	private HeroService heroService = Mockito.mock(HeroService.class);
	private CurrentHero currentHero = Mockito.mock(CurrentHero.class);
	private MainMenuHandler mainMenuHandler = new MainMenuHandler(heroService, currentHero);

	@Test
	public void dispatchCommand_newHero_success() {
		View view = mainMenuHandler.dispatchCommand("n");
		Assert.assertEquals(View.CREATE_HERO, view);
	}

	@Test
	public void dispatchCommand_loadGame_success() {
		View view = mainMenuHandler.dispatchCommand("l");
		Assert.assertEquals(View.LOAD_GAME, view);
	}

	@Test
	public void dispatchCommand_saveGame_success() {
		Mockito.when(currentHero.getHero()).thenReturn(Mockito.mock(Hero.class));
		Mockito.when(currentHero.getHero().isAlive()).thenReturn(true);
		View view = mainMenuHandler.dispatchCommand("s");
		Assert.assertEquals(View.GAME_PLAY, view);
		Mockito.verify(heroService, Mockito.times(1)).save(Mockito.any(Hero.class));
	}

	@Test
	public void dispatchCommand_back_success() {
		Mockito.when(currentHero.getHero()).thenReturn(Mockito.mock(Hero.class));
		Mockito.when(currentHero.getHero().isAlive()).thenReturn(true);
		View view = mainMenuHandler.dispatchCommand("b");
		Assert.assertEquals(View.GAME_PLAY, view);
	}
}