package gr.prog.clirpg.model;

import java.util.*;

public class Hero extends Character {
	private Integer experience;
	private Position currentPosition;
	private final Set<Position> visitedPositions = new HashSet<>();
	private final Map<String, Double> kicks = new HashMap<String, Double>() {{
		put("right hand hit", 1.0);
		put("left hand hit", 0.5);
		put("kicked", 1.5);
	}};

	public Hero(String name, Integer maxHealth, Integer strength, Integer experience) {
		super(maxHealth, strength, name);
		this.experience = experience;
	}

	public Position getPosition() {
		return currentPosition;
	}

	public void setPosition(Position position) {
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
}
