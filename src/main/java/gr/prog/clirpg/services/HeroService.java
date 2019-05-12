package gr.prog.clirpg.services;

import gr.prog.clirpg.model.Hero;
import gr.prog.clirpg.model.Position;

import java.util.HashMap;
import java.util.Map;

public class HeroService {
	private Map<String, Hero> characters = new HashMap<>();

	public void save(Hero hero) {
		characters.put(hero.getName(), hero);
	}

	public Hero load(String name) {
		// todo обработка ошибок
		return characters.get(name);
	}

	public Hero create(String name) {
		// todo обработка ошибок
		Hero hero = new Hero(name, 100, 0);
		characters.put(hero.getName(), hero);
		return hero;
	}
}
