package gr.prog.clirpg.model.characters;

// todo Some specific behaviors for Friends
public class Friend extends Character {

	public Friend(Integer maxHealth, Integer strength, String name) {
		super(maxHealth, strength, name);
	}

	@Override
	public String attack(Character character) {
		return getName() + " asks not to kill him ";
	}

}
