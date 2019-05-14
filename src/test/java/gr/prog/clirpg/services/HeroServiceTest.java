package gr.prog.clirpg.services;

import gr.prog.clirpg.model.characters.Hero;
import gr.prog.clirpg.repository.InMemoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class HeroServiceTest {

	private InMemoryRepository inMemoryRepository = Mockito.mock(InMemoryRepository.class);
	private HeroService heroService = new HeroService(inMemoryRepository);


	@Test
	public void save_success() {
		heroService.save(null);
		Mockito.verify(inMemoryRepository, Mockito.times(0)).save(Mockito.any(Hero.class));
		heroService.save(Mockito.mock(Hero.class));
		Mockito.verify(inMemoryRepository, Mockito.times(1)).save(Mockito.any(Hero.class));
		Mockito.clearInvocations(inMemoryRepository);
	}

	@Test
	public void load_success() {
		heroService.load(100);
		Mockito.verify(inMemoryRepository, Mockito.times(1)).findById(100);
	}

	@Test
	public void createHero_success() {
		Hero tommy = heroService.createHero("Tommy");
		Assert.assertEquals("Tommy", tommy.getName());
	}

	@Test
	public void getAllHeroes_success() {
		heroService.getAllHeroes();
		Mockito.verify(inMemoryRepository, Mockito.times(1)).findAll();
	}
}