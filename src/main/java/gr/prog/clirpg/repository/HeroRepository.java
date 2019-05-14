package gr.prog.clirpg.repository;

import gr.prog.clirpg.model.Hero;

import java.util.List;

public interface HeroRepository {
	void save(Hero hero);
	Hero findById(int id);
	List<Hero> findAll();
}
