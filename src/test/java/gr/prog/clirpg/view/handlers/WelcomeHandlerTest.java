package gr.prog.clirpg.view.handlers;

import gr.prog.clirpg.view.View;
import org.junit.Assert;
import org.junit.Test;

public class WelcomeHandlerTest {

	private WelcomeHandler welcomeHandler = new WelcomeHandler();

	@Test
	public void dispatchCommand_success() {
		View view = welcomeHandler.dispatchCommand("qwe");
		Assert.assertEquals(View.MAIN_MENU, view);
	}
}