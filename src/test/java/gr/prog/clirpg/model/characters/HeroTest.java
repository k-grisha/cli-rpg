package gr.prog.clirpg.model.characters;

import gr.prog.clirpg.model.world.Position;
import gr.prog.clirpg.model.world.Room;
import gr.prog.clirpg.model.world.World;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroTest {

	private Hero hero;
	private World world;

	@Before
	public void before() {
		world = new World(5);
		hero = new Hero("TestHero", 100, 200, 300, world);
	}

	@Test
	public void getWorld_success() {
		Assert.assertEquals(world, hero.getWorld());
	}

	@Test
	public void getCurrentRoom_success() {
		Room room = hero.getCurrentRoom();
		Assert.assertNotNull(room);
		Assert.assertFalse(room.getDescription().isEmpty());
	}

	@Test
	public void getPosition_success() {
		Position initialPosition = hero.getWorld().getInitialPosition();
		Position position = hero.getPosition();
		Assert.assertEquals(initialPosition, position);
	}

	@Test
	public void getVisitedPositions_success() {
		Assert.assertEquals(1, hero.getVisitedPositions().size());
		Assert.assertTrue(hero.getVisitedPositions().contains(hero.getPosition()));
		hero.moveUp();
		Assert.assertEquals(2, hero.getVisitedPositions().size());
	}

	@Test
	public void moveUp_success() {
		Position positionBefore = hero.getPosition();
		hero.moveUp();
		Position positionAfter = hero.getPosition();
		Assert.assertEquals(positionBefore.x, positionAfter.x);
		Assert.assertEquals(positionBefore.y + 1, positionAfter.y);
	}

	@Test
	public void moveDown_fail() {
		Position positionBefore = hero.getPosition();
		hero.moveDown();
		Position positionAfter = hero.getPosition();
		Assert.assertEquals(positionBefore, positionAfter);
	}

	@Test
	public void moveLeft_fail() {
		Position positionBefore = hero.getPosition();
		hero.moveLeft();
		Position positionAfter = hero.getPosition();
		Assert.assertEquals(positionBefore, positionAfter);
	}

	@Test
	public void moveRight_success() {
		Position positionBefore = hero.getPosition();
		hero.moveRight();
		Position positionAfter = hero.getPosition();
		Assert.assertEquals(positionBefore.x + 1, positionAfter.x);
		Assert.assertEquals(positionBefore.y, positionAfter.y);
	}

	@Test
	public void attack_success() {
		Enemy goblin = new Enemy(100, 10, "Goblin");
		Integer healthBefore = goblin.getHealth();
		hero.attack(goblin);
		Integer healthAfter = goblin.getHealth();
		Assert.assertTrue(healthBefore > healthAfter);
	}
}