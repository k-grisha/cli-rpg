package gr.prog.clirpg.view.handlers;

import gr.prog.clirpg.model.characters.Hero;
import gr.prog.clirpg.model.world.Position;
import gr.prog.clirpg.services.CurrentHero;
import gr.prog.clirpg.view.View;
import gr.prog.clirpg.view.utils.Color;

import java.util.Set;

import static gr.prog.clirpg.view.View.GAME_PLAY;

/**
 * Show Map view handler
 */
public class WorldMapHandler extends BaseViewHandler {

	private final CurrentHero currentHero;

	public WorldMapHandler(CurrentHero currentHero) {
		super("worldMap.txt");
		this.currentHero = currentHero;
	}


	@Override
	public View dispatchCommand(String command) {
		return GAME_PLAY;
	}

	@Override
	public String getTextPresent() {
		return getContent().replace("${worldMap}", buildTextMap());
	}

	private String buildTextMap() {
		Hero hero = currentHero.getHero();
		StringBuilder sb = new StringBuilder();
		int worldSize = hero.getWorld().getSize();
		Set<Position> visited = hero.getVisitedPositions();
		for (int y = worldSize - 1; y >= 0; y--) {
			for (int x = 0; x < worldSize; x++) {
				addMapElement(x, y, sb, visited);
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	private void addMapElement(int x, int y, StringBuilder sb, Set<Position> visited) {
		Position position = new Position(x, y);
		if (visited.contains(position)) {
			Hero hero = currentHero.getHero();
			if (hero.getPosition().equals(position)) {
				sb.append(Color.ANSI_GREEN + "X" + Color.ANSI_RESET);
			} else if (!hero.getWorld().getRoom(position).getCharacters().isEmpty()) {
				sb.append(Color.ANSI_RED + "E" + Color.ANSI_RESET);
			} else {
				sb.append(" ");
			}
		} else {
			sb.append(Color.ANSI_WHITE_BACKGROUND + " " + Color.ANSI_RESET);
		}
	}
}
