package gr.prog.clirpg.model;

public abstract class Character {

	private Integer health;
	private Integer maxHealth;
	private Integer strength;
	private final String description;

	public Character(Integer maxHealth, Integer strength, String description) {
		this.health = maxHealth;
		this.maxHealth = maxHealth;
		this.strength = strength;
		this.description = description;
	}

	public String getDescription() {
		return description;
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

	void increaseHealth(Integer health) {
		if (isAlive()) {
			this.health = this.health + health > maxHealth ? maxHealth : this.health + health;
		}
	}

	void decreaseHealth(Integer health) {
		if (isAlive()) {
			this.health = this.health - health;
		}
	}

	boolean isAlive() {
		return health > 0;
	}
}
