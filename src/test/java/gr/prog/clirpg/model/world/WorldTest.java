package gr.prog.clirpg.model.world;

import gr.prog.clirpg.RpgException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorldTest {

	private World world = new World(100);

	@Test
	public void getRoom_success() {
		Room room = world.getRoom(new Position(1, 1));
		Assert.assertNotNull(room);
		Assert.assertFalse(room.getDescription().isEmpty());
	}

	@Test(expected = RpgException.class)
	public void getRoom_farPosition_fail() {
		world.getRoom(new Position(500, 500));
	}

	@Test
	public void getInitialPosition_success() {
		Position position = world.getInitialPosition();
		Assert.assertNotNull(position);
	}

	@Test
	public void isValid_success() {
		Assert.assertFalse(world.isValid(new Position(100, 100)));
		Assert.assertFalse(world.isValid(new Position(-1, 0)));
		Assert.assertTrue(world.isValid(new Position(0, 0)));
		Assert.assertTrue(world.isValid(new Position(99, 99)));
	}

	@Test
	public void getSize_success() {
		Assert.assertEquals(100, world.getSize());
	}
}