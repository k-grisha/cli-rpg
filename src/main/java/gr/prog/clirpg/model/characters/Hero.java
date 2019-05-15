package gr.prog.clirpg.model.characters;

import gr.prog.clirpg.model.world.Position;
import gr.prog.clirpg.model.world.Room;
import gr.prog.clirpg.model.world.World;

import java.io.Serializable;
import java.util.*;

public class Hero extends Character implements Serializable {
	private Integer experience;
	private Position currentPosition;
	private final Set<Position> visitedPositions = new HashSet<>();
	// Kicks type with power coefficient
	private final Map<String, Double> kicks = new HashMap<String, Double>() {{
		put("right hand hit", 1.0);
		put("left hand hit", 0.5);
		put("kicked", 1.5);
	}};
	private final World world;


	public Hero(String name, Integer maxHealth, Integer strength, Integer experience, World world) {
		super(maxHealth, strength, name);
		this.experience = experience;
		this.world = world;
		setPosition(world.getInitialPosition());
	}

	/**
	 * Get world of Hero
	 *
	 * @return World
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * Get current room/location of Hero
	 *
	 * @return Room
	 */
	public Room getCurrentRoom() {
		return getWorld().getRoom(getPosition());
	}

	/**
	 * Get current position of Hero
	 *
	 * @return Position
	 */
	public Position getPosition() {
		return currentPosition;
	}

	/**
	 * Get Experience of Hero
	 *
	 * @return Experience
	 */
	public Integer getExperience() {
		return experience;
	}

	/**
	 * Set Experience to Hero
	 *
	 * @param experience new experience value
	 */
	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	/**
	 * Get Set of visited positions
	 *
	 * @return Set of visited positions
	 */
	public Set<Position> getVisitedPositions() {
		return new HashSet<>(visitedPositions);
	}

	/**
	 * Move Hero one step Up
	 *
	 * @return true - if success, false - otherwise
	 */
	public boolean moveUp() {
		Position newPosition = new Position(getPosition().x, getPosition().y + 1);
		return move(newPosition);
	}

	/**
	 * Move Hero one step Down
	 *
	 * @return true - if success, false - otherwise
	 */
	public boolean moveDown() {
		Position newPosition = new Position(getPosition().x, getPosition().y - 1);
		return move(newPosition);
	}

	/**
	 * Move Hero one step Left
	 *
	 * @return true - if success, false - otherwise
	 */
	public boolean moveLeft() {
		Position newPosition = new Position(getPosition().x - 1, getPosition().y);
		return move(newPosition);
	}

	/**
	 * Move Hero one step Right
	 *
	 * @return true - if success, false - otherwise
	 */
	public boolean moveRight() {
		Position newPosition = new Position(getPosition().x + 1, getPosition().y);
		return move(newPosition);
	}

	private boolean move(Position position) {
		World world = getWorld();
		if (!world.isValid(position)) {
			return false;
		}
		setPosition(position);
		return true;
	}

	private void setPosition(Position position) {
		currentPosition = position;
		visitedPositions.add(position);
	}

	@Override
	public String attack(Character character) {
		String randomKick = getRandomKick();
		int power = (int) (getStrength() * kicks.get(randomKick));
		character.decreaseHealth(power);
		experience += character.getStrength();
		return getName() + " " + randomKick + " " + character.getName();
	}

	private String getRandomKick(){
		List<String> keyList = new ArrayList<>(kicks.keySet());
		int randomIndex = new Random().nextInt(keyList.size());
		return keyList.get(randomIndex);
	}

}
