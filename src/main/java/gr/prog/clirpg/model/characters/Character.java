package gr.prog.clirpg.model.characters;

import java.io.Serializable;

/**
 * Characters of the World
 */
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

	/**
	 * Name of Character
	 */
	public String getName() {
		return name;
	}

	/**
	 * Current health of Character
	 */
	public Integer getHealth() {
		return health;
	}

	/**
	 * Strength of Character
	 */
	public Integer getStrength() {
		return strength;
	}

	/**
	 * Max level of health
	 */
	public Integer getMaxHealth() {
		return maxHealth;
	}

	/**
	 * Increase health
	 *
	 * @param health value for increase
	 */
	public void increaseHealth(Integer health) {
		if (isAlive()) {
			this.health = this.health + health > maxHealth ? maxHealth : this.health + health;
		}
	}

	/**
	 * Decrease health
	 *
	 * @param health value for decrease
	 */
	public void decreaseHealth(Integer health) {
		if (isAlive()) {
			this.health = this.health - health;
		}
	}

	/**
	 * Check is Character alive
	 *
	 * @return true - if alive, false - otherwise
	 */
	public boolean isAlive() {
		return health > 0;
	}

	/**
	 * Attack Character
	 *
	 * @param character opponent
	 * @return description of Attack result
	 */
	public abstract String attack(Character character);
}
