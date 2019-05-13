package gr.prog.clirpg.model;

// todo Some specific behaviors for Enemies
public class Enemy extends Character {

	public Enemy(Integer maxHealth, Integer strength, String description) {
		super(maxHealth, strength, description);
	}

	@Override
	public String attack(Character character) {
		character.decreaseHealth(getStrength() );
		return getName() + " hit " + character.getName();
	}

}
