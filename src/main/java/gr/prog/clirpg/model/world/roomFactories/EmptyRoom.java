package gr.prog.clirpg.model.world.roomFactories;

import gr.prog.clirpg.model.world.Room;

import java.util.Collections;
import java.util.Random;

/**
 * Empty room factory
 */
public class EmptyRoom extends RoomFactory {

	private final Random rndGenerator = new Random();

	public EmptyRoom() {
		super("rooms/emptyRooms.txt");
	}

	@Override
	public Room createRoom() {
		String description = getDescriptions().get(rndGenerator.nextInt(getDescriptions().size()));
		return new Room(description, Collections.emptyList());
	}
}
