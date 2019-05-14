package gr.prog.clirpg.view.handlers;

import gr.prog.clirpg.services.CurrentHero;
import gr.prog.clirpg.view.View;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class GamePlayHandlerTest {

	private CurrentHero currentHero = Mockito.mock(CurrentHero.class, Mockito.RETURNS_DEEP_STUBS);
	private GamePlayHandler gamePlayHandler = new GamePlayHandler(currentHero);

	@Test
	public void dispatchCommand_up_success() {
		View view = gamePlayHandler.dispatchCommand("w");
		Assert.assertEquals(View.GAME_PLAY, view);
		Mockito.verify(currentHero.getHero(), Mockito.times(1)).moveUp();
		Mockito.clearInvocations(currentHero);
	}

	@Test
	public void dispatchCommand_down_success() {
		View view = gamePlayHandler.dispatchCommand("s");
		Assert.assertEquals(View.GAME_PLAY, view);
		Mockito.verify(currentHero.getHero(), Mockito.times(1)).moveDown();
		Mockito.clearInvocations(currentHero);
	}

	@Test
	public void dispatchCommand_left_success() {
		View view = gamePlayHandler.dispatchCommand("a");
		Assert.assertEquals(View.GAME_PLAY, view);
		Mockito.verify(currentHero.getHero(), Mockito.times(1)).moveLeft();
		Mockito.clearInvocations(currentHero);
	}

	@Test
	public void dispatchCommand_right_success() {
		View view = gamePlayHandler.dispatchCommand("d");
		Assert.assertEquals(View.GAME_PLAY, view);
		Mockito.verify(currentHero.getHero(), Mockito.times(1)).moveRight();
		Mockito.clearInvocations(currentHero);
	}


	@Test
	public void dispatchCommand_fight_noBody() {
		Mockito.when(currentHero.getHero().getCurrentRoom().getCharacters().isEmpty()).thenReturn(true);
		View view = gamePlayHandler.dispatchCommand("f");
		Assert.assertEquals(View.GAME_PLAY, view);
	}

	@Test
	public void dispatchCommand_fight_success() {
		Mockito.when(currentHero.getHero().getCurrentRoom().getCharacters().isEmpty()).thenReturn(false);
		View view = gamePlayHandler.dispatchCommand("f");
		Assert.assertEquals(View.FIGHT, view);
	}

	@Test
	public void dispatchCommand_showMap_success() {
		View view = gamePlayHandler.dispatchCommand("v");
		Assert.assertEquals(View.WOLD_MAP, view);
	}

}