package gr.prog.clirpg.services;

import gr.prog.clirpg.model.characters.Hero;
import gr.prog.clirpg.model.world.World;
import gr.prog.clirpg.repository.Repository;

import java.util.List;

/**
 * Hero Service
 */
public class HeroService {
	private final Repository<Hero> heroRepository;

	public HeroService(Repository<Hero> heroRepository) {
		this.heroRepository = heroRepository;
	}

	/**
	 * Save Hero
	 *
	 * @param hero Hero
	 */
	public void save(Hero hero) {
		if (hero == null) {
			return;
		}
		heroRepository.save(hero);
	}

	/**
	 * Load hero by index
	 *
	 * @param index Index
	 * @return Loaded Hero or Null
	 */
	public Hero load(int index) {
		return heroRepository.findById(index);
	}

	/**
	 * Create new Hero
	 *
	 * @param name new Hero name
	 * @return new hero
	 */
	public Hero createHero(String name) {
		return new Hero(name, 100, 10, 0, new World(10));
	}

	/**
	 * Get all saved Heroes
	 * @return All Heroes
	 */
	public List<Hero> getAllHeroes() {
		return heroRepository.findAll();
	}

}
