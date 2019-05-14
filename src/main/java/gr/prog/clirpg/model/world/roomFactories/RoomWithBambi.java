package gr.prog.clirpg.model.world.roomFactories;

import gr.prog.clirpg.model.characters.Friend;
import gr.prog.clirpg.model.world.Room;

import java.util.Collections;
import java.util.Random;

public class RoomWithBambi extends RoomFactory {

	private final Random rndGenerator = new Random();

	public RoomWithBambi() {
		super("rooms/roomsWithBambi.txt");
	}

	@Override
	public Room createRoom() {
		String description = getDescriptions().get(rndGenerator.nextInt(getDescriptions().size()));
		return new Room(description, Collections.singletonList(new Friend(50, 10, "Bambi")));
	}
}
