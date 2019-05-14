package gr.prog.clirpg.repository;

import gr.prog.clirpg.model.characters.Hero;
import gr.prog.clirpg.model.world.World;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class InMemoryRepositoryTest {

	private Repository<Hero> heroRepository = InMemoryRepository.getInstance();
	private Hero billi = new Hero("Billi", 100, 10, 5, new World(10));
	private Hero timmi = new Hero("Timmi", 50, 5, 0, new World(100));

	@Before
	public void before() {
		heroRepository.clear();
		heroRepository.save(billi);
		heroRepository.save(timmi);
	}


	@Test
	public void findAll() {
		List<Hero> all = heroRepository.findAll();
		System.out.println(all);
	}

	@Test
	public void findById() {
		Hero hero = heroRepository.findById(0);
		hero.setExperience(1000);
		hero = heroRepository.findById(0);
		System.out.println(hero);
	}
}