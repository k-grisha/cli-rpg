package gr.prog.clirpg.model.characters;

// todo Some specific behaviors for Enemies
public class Enemy extends Character {

	public Enemy(Integer maxHealth, Integer strength, String name) {
		super(maxHealth, strength, name);
	}

	@Override
	public String attack(Character character) {
		character.decreaseHealth(getStrength() );
		return getName() + " hit " + character.getName();
	}

}
