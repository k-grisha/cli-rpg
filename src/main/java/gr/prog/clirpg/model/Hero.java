package gr.prog.clirpg.model;

import javax.annotation.PostConstruct;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class Hero extends Character implements Serializable {
	private Integer experience;
	private Position currentPosition;
	private final Set<Position> visitedPositions = new HashSet<>();
	private final Map<String, Double> kicks = new HashMap<String, Double>() {{
		put("right hand hit", 1.0);
		put("left hand hit", 0.5);
		put("kicked", 1.5);
	}};
	private World world;


	public Hero(String name, Integer maxHealth, Integer strength, Integer experience, World world) {
		super(maxHealth, strength, name);
		this.experience = experience;
		this.world = world;
		setPosition(world.getInitialPosition());
	}


	public World getWorld() {
		return world;
	}

	public Room getCurrentRoom() {
		return getWorld().getRoom(getPosition());
	}

	public Position getPosition() {
		return currentPosition;
	}

	private void setPosition(Position position) {
		currentPosition = position;
		visitedPositions.add(position);
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Set<Position> getVisitedPositions() {
		return new HashSet<>(visitedPositions);
	}

	public boolean moveUp() {
		Position newPosition = new Position(getPosition().x, getPosition().y + 1);
		return move(newPosition);
	}

	public boolean moveDown() {
		Position newPosition = new Position(getPosition().x, getPosition().y - 1);
		return move(newPosition);
	}

	public boolean moveLeft() {
		Position newPosition = new Position(getPosition().x - 1, getPosition().y);
		return move(newPosition);
	}

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

	@Override
	public String attack(Character character) {
		List<String> keyList = new ArrayList<>(kicks.keySet());
		int randomIndex = new Random().nextInt(keyList.size());
		String randomKick = keyList.get(randomIndex);
		int power = (int) (getStrength() * kicks.get(randomKick));
		character.decreaseHealth(power);
		experience += power;
		return getName() + " " + randomKick + " " + character.getName();
	}


	// todo REMOVE
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Hero hero = (Hero) o;
		return Objects.equals(getName(), hero.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName());
	}


}
