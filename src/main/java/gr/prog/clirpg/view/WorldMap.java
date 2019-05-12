package gr.prog.clirpg.view;

import gr.prog.clirpg.model.Position;
import gr.prog.clirpg.services.WorldService;

import java.util.Set;

import static gr.prog.clirpg.view.View.GAME_VIEW;

public class WorldMap extends BaseViewHandler {

	//	private final HeroService heroService;
	private final WorldService worldService;

	public WorldMap(WorldService worldService) {
		super("worldMap.txt");
		this.worldService = worldService;
	}


	@Override
	public View dispatchCommand(String command) {
		return GAME_VIEW;
	}

	@Override
	public String getTextPresent() {
		StringBuilder sb = new StringBuilder();
		int worldSize = worldService.getWorldSize(getHero());
		Set<Position> visited = getHero().getVisitedPositions();
		for (int y = worldSize - 1; y >= 0; y--) {
			for (int x = 0; x < worldSize; x++) {
				Position position = new Position(x, y);
				if (visited.contains(position)) {
					if (getHero().getPosition().equals(position)) {
						sb.append(Color.ANSI_GREEN + "X" + Color.ANSI_RESET);
					} else {
						sb.append(" ");
					}
				} else {
					sb.append(Color.ANSI_WHITE_BACKGROUND    + " " + Color.ANSI_RESET);
				}
			}
			sb.append("\n");
		}
		return getContent().replace("${worldMap}", sb.toString());
	}
}