package gr.prog.clirpg.model.world.roomFactories;

import gr.prog.clirpg.model.characters.Character;
import gr.prog.clirpg.model.characters.Enemy;
import gr.prog.clirpg.model.world.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class RoomWithGoblinsFamily extends RoomFactory {

	private final Random rndGenerator = new Random();

	public RoomWithGoblinsFamily() {
		super("rooms/roomsWithGoblin.txt");
	}

	@Override
	public Room createRoom() {
		String description = getDescriptions().get(rndGenerator.nextInt(getDescriptions().size()));

		List<Character> goblins = new ArrayList<>();
		int parentsCount = rndGenerator.nextInt(2) + 1;
		IntStream.range(0, parentsCount).forEach($ -> goblins.add(new Enemy(
				50 + rndGenerator.nextInt(50),
				5 + rndGenerator.nextInt(5),
				"Big goblin")));
		int babyCount = rndGenerator.nextInt(3) + 1;
		IntStream.range(0, babyCount).forEach($ -> goblins.add(new Enemy(
				10 + rndGenerator.nextInt(30),
				1 + rndGenerator.nextInt(3),
				"Baby goblin")));

		return new Room(description, goblins);
	}
}
