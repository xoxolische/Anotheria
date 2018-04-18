package restful;

import javax.ws.rs.core.Response;

/**
 * CRUD Service interface
 * 
 * @author Nikita Pavlov
 *
 * @param <E>
 *            entity to operate with
 */
public interface Service<E> {

	/**
	 * Save entity to database.
	 * 
	 * @param entity
	 *            to save
	 * @return TODO
	 */
	Response create(E entity);

	/**
	 * Retrieve entity from database.
	 * 
	 * @param id
	 *            of entity
	 * @return TODO
	 */
	Response get(long id);

	/**
	 * Update entity in database.
	 * 
	 * @param entity
	 *            to update
	 * @return TODO
	 */
	Response update(E entity);

	/**
	 * Delete entity from database.
	 * 
	 * @param id
	 *            of entity
	 * @return TODO
	 */
	Response delete(long id);

}
