package gr.prog.clirpg.services;

import gr.prog.clirpg.model.Hero;

import java.util.*;

public class HeroService {
	private static HeroService instance;
	private List<Hero> heroes = new ArrayList<>();

	private HeroService() {
	}

	public static HeroService getInstance() {
		if (instance == null) {
			instance = new HeroService();
		}
		return instance;
	}

	public void save(Hero hero) {
		heroes.add(hero);
	}

	public Hero load(int index) {
		// todo обработка ошибок
		return heroes.get(index);
	}

	public Hero create(String name) {
		// todo обработка ошибок
//		Hero hero = new Hero(name, 100, 0);
//		heroes.put(hero.getName(), hero);
		return new Hero(name, 100, 0);
	}

	public List<Hero> getAllSavedNames() {
		return heroes;
	}
}
