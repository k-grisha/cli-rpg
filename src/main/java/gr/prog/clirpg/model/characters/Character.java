package gr.prog.clirpg.model.characters;

import java.io.Serializable;

public abstract class Character implements Serializable {

	private Integer health;
	private Integer maxHealth;
	private Integer strength;
	private final String name;

	public Character(Integer maxHealth, Integer strength, String name) {
		this.health = maxHealth;
		this.maxHealth = maxHealth;
		this.strength = strength;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Integer getHealth() {
		return health;
	}

	public Integer getStrength() {
		return strength;
	}

	public Integer getMaxHealth() {
		return maxHealth;
	}

	public void increaseHealth(Integer health) {
		if (isAlive()) {
			this.health = this.health + health > maxHealth ? maxHealth : this.health + health;
		}
	}

	public void decreaseHealth(Integer health) {
		if (isAlive()) {
			this.health = this.health - health;
		}
	}

	public boolean isAlive() {
		return health > 0;
	}

	public abstract String attack(Character character);
}
