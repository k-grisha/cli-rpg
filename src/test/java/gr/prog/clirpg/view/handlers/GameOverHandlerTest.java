package gr.prog.clirpg.view.handlers;

import gr.prog.clirpg.view.View;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameOverHandlerTest {

	private GameOverHandler gameOverHandler = new GameOverHandler();

	@Test
	public void dispatchCommand_success() {
		View view = gameOverHandler.dispatchCommand("qwe");
		Assert.assertEquals(View.MAIN_MENU, view);
	}
}