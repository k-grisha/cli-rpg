package gr.prog.clirpg.view;

import gr.prog.clirpg.services.WorldService;

import static gr.prog.clirpg.view.View.MAIN_MENU;
import static gr.prog.clirpg.view.View.MAIN_SCREEN;

public class GameViewHandler extends BaseViewHandler {

	private final WorldService worldService;

	public GameViewHandler(WorldService worldService) {
		super("mainScreen.txt");
		this.worldService = worldService;
	}

	private String notification = "";

	@Override
	public View dispatchCommand(String command) {
		if (command.equals("m")) {
			return MAIN_MENU;
		}
		boolean movedSuccess = true;
		switch (command) {
			case "w":
				movedSuccess = worldService.moveUp(getHero());
				break;
			case "s":
				movedSuccess = worldService.moveDown(getHero());
				break;
			case "a":
				movedSuccess = worldService.moveLeft(getHero());
				break;
			case "d":
				movedSuccess = worldService.moveRight(getHero());
				break;
		}
		if (!movedSuccess) {
			notification = "Notification! You cant move there!";
		} else {
			notification = "";
		}
		return MAIN_SCREEN;
	}

	@Override
	public String getTextPresent() {
		return getContent()
				.replace("${notification}", notification)
				.replace("${description}", worldService.getCurrentRom(getHero()).getDescription())
				.replace("${username}", getHero().getName())
				.replace("${health}", getHero().getHealth().toString())
				.replace("${experience}", getHero().getExperience().toString());
	}
}
