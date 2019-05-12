package gr.prog.clirpg.services;

import gr.prog.clirpg.model.Hero;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HeroService {
	private Map<String, Hero> heroes = new HashMap<>();

	public void save(Hero hero) {
		heroes.put(hero.getName(), hero);
	}

	public Hero load(String name) {
		// todo обработка ошибок
		return heroes.get(name);
	}

	public Hero create(String name) {
		// todo обработка ошибок
		Hero hero = new Hero(name, 100, 0);
//		heroes.put(hero.getName(), hero);
		return hero;
	}

	public Collection<Hero> getAllSavedNames() {
		return heroes.values();
	}
}
