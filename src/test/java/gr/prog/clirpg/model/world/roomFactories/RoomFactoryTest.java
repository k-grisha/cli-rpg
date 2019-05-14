package gr.prog.clirpg.model.world.roomFactories;

import gr.prog.clirpg.model.world.Room;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class RoomFactoryTest {
	private final List<RoomFactory> roomFactories = Arrays.asList(
			new RoomWithGoblinsFamily(),
			new RoomWithBambi(),
			new EmptyRoom()
	);

	@Test
	public void createRoom_success() {
		for (RoomFactory roomFactory : roomFactories) {
			Room room = roomFactory.createRoom();
			Assert.assertNotNull(room);
			Assert.assertFalse(room.getDescription().isEmpty());
		}
	}
}