package dao;

import java.util.List;

/**
 * Basic CRUD interface
 * 
 * @author Nikita Pavlov
 *
 * @param <E>
 *            Entity to work with
 */
public interface CRUD<E> {

	/**
	 * Saves entity in database
	 * 
	 * @param entity
	 *            to save
	 */
	void create(E entity);

	/**
	 * Get entity from database by id
	 * 
	 * @param id
	 * @return <E> if entity present else null
	 */
	E get(long id);

	/**
	 * Update entity in database
	 * 
	 * @param entity
	 */
	void update(E entity);

	/**
	 * Delete entity from database
	 * 
	 * @param id
	 */
	void delete(long id);

	/**
	 * Get all entities
	 * 
	 * @return List of entities or null if no entities present
	 */
	List<E> getAll();

}
