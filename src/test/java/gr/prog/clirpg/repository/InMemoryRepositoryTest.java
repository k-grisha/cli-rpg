package gr.prog.clirpg.repository;

import gr.prog.clirpg.model.characters.Hero;
import gr.prog.clirpg.model.world.World;
import org.junit.Assert;
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
	public void findAll_success() {
		List<Hero> heroes = heroRepository.findAll();
		Assert.assertEquals(2, heroes.size());
	}

	@Test
	public void findById(){
		Hero hero = heroRepository.findById(0);
		Assert.assertEquals(billi.getName(), hero.getName());
	}

	@Test
	public void findById_outOfIndex_null(){
		Assert.assertNull(heroRepository.findById(2));
	}

	@Test
	public void findById_differentObjects() {
		Hero hero1 = heroRepository.findById(0);
		Hero hero2 = heroRepository.findById(0);
		hero1.setExperience(1000);
		Assert.assertNotEquals(hero1.getExperience(), hero2.getExperience());
	}
}