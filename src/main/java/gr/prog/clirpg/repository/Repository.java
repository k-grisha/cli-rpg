package gr.prog.clirpg.repository;

import java.io.Serializable;
import java.util.List;

public interface Repository<T extends Serializable> {
	void save(T hero);

	T findById(int id);

	List<T> findAll();

	void clear();
}
