package gr.prog.clirpg.repository;

import java.io.Serializable;
import java.util.List;

/**
 * Repository of Entity
 *
 * @param <T> Entity
 */
public interface Repository<T extends Serializable> {
	/**
	 * Save Entity
	 *
	 * @param hero Entity
	 */
	void save(T hero);

	/**
	 * Find Entity by Id
	 *
	 * @param id Id
	 * @return Entity
	 */
	T findById(int id);

	/**
	 * Get all saved Entities
	 *
	 * @return List of Entities
	 */
	List<T> findAll();

	/**
	 * Clear repository
	 */
	void clear();
}
