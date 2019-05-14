package gr.prog.clirpg.view.handlers;

import gr.prog.clirpg.view.View;

import static gr.prog.clirpg.view.View.MAIN_MENU;

public class WelcomeHandler extends BaseViewHandler {

	public WelcomeHandler() {
		super("welcome.txt");
	}

	@Override
	public View dispatchCommand(String command) {
		return MAIN_MENU;
	}

	@Override
	public String getTextPresent() {
		return getContent();
	}
}
