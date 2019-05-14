package gr.prog.clirpg.model.world;

import org.junit.Assert;
import org.junit.Test;

public class RoomBuilderTest {

	private RoomBuilder roomBuilder = RoomBuilder.getInstance();

	@Test
	public void generateRoom_success() {
		Room room = roomBuilder.generateRandomRoom();
		Assert.assertNotNull(room);
		Assert.assertFalse(room.getDescription().isEmpty());
	}

	@Test
	public void getInitialRoom() {
		Room room = roomBuilder.getInitialRoom();
		Assert.assertNotNull(room);
		Assert.assertFalse(room.getDescription().isEmpty());
	}
}