package gr.prog.clirpg.services;

import gr.prog.clirpg.model.characters.Hero;

public class CurrentHero {
	private static CurrentHero instance;
	private Hero hero;

	private CurrentHero() {
	}

	public static CurrentHero getInstance(){
		if (instance == null) {
			instance = new CurrentHero();
		}
		return instance;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}
}
