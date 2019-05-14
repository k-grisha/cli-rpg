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
		heroRepository.save(hero);
	}

	public Hero load(int index) {
		// todo обработка ошибок
		return heroRepository.findById(index);
	}

	public Hero createHero(String name) {
		// todo обработка ошибок
		return new Hero(name, 100, 10, 0, new World(10));
	}

	// todo rename
	public List<Hero> getAllHeroes() {
		return heroRepository.findAll();
	}


}
