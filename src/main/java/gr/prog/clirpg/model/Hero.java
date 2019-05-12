package gr.prog.clirpg.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Hero {
	private final String name;
	private Integer health;
	private Integer experience;
	private Position currentPosition;
	private final Set<Position> visitedPositions = new HashSet<>();

	public Hero(String name, Integer health, Integer experience) {
		this.name = name;
		this.health = health;
		this.experience = experience;
	}

	public Position getPosition() {
		return currentPosition;
	}

	public void setPosition(Position position) {
		currentPosition = position;
		visitedPositions.add(position);
	}

	public String getName() {
		return name;
	}

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
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
		return Objects.equals(name, hero.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
