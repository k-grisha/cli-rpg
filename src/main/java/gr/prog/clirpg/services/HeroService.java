package gr.prog.clirpg.services;

import gr.prog.clirpg.model.characters.Hero;
import gr.prog.clirpg.model.world.World;
import gr.prog.clirpg.repository.Repository;

import java.util.List;

public class HeroService {
	private final Repository<Hero> heroRepository;

	public HeroService(Repository<Hero> heroRepository) {
		this.heroRepository = heroRepository;
	}

	public void save(Hero hero) {
		if (hero == null) {
			return;
		}
		heroRepository.save(hero);
	}

	public Hero load(int index) {
		return heroRepository.findById(index);
	}

	// todo make adjustable
	public Hero createHero(String name) {
		return new Hero(name, 100, 10, 0, new World(10));
	}


	public List<Hero> getAllHeroes() {
		return heroRepository.findAll();
	}

}
