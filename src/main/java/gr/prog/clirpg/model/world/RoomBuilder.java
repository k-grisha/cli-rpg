package gr.prog.clirpg.model.world;

import gr.prog.clirpg.model.world.roomFactories.EmptyRoom;
import gr.prog.clirpg.model.world.roomFactories.RoomFactory;
import gr.prog.clirpg.model.world.roomFactories.RoomWithBambi;
import gr.prog.clirpg.model.world.roomFactories.RoomWithGoblinsFamily;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RoomBuilder {
	private static RoomBuilder instance;

	private final Random rndGenerator = new Random();
	private final List<RoomFactory> randomizedRoomFactories = Arrays.asList(
			new RoomWithGoblinsFamily(),
			new RoomWithBambi()
	);
	private final RoomFactory emptyRoomFactory = new EmptyRoom();
	private Room initialRoom = new Room("This is initial room. You can explore the world and get fame in battles.", Collections.emptyList());

	private RoomBuilder() {
	}

	public static RoomBuilder getInstance() {
		if (instance == null) {
			instance = new RoomBuilder();
		}
		return instance;
	}

	public Room generateRandomRoom() {
		if (rndGenerator.nextInt(3) == 0) {
			return randomizedRoomFactories.get(rndGenerator.nextInt(randomizedRoomFactories.size())).createRoom();
		}
		return emptyRoomFactory.createRoom();
	}


	public Room getInitialRoom() {
		return initialRoom;
	}
}
