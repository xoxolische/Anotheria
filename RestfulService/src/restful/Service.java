package restful;

import javax.ws.rs.core.Response;

import restful.dto.MagicSquareCreateDTO;
import restful.dto.MagicSquareSearchDTO;
import restful.dto.MagicSquareUpdateDTO;

/**
 * CRUD Service interface
 * 
 * @author Nikita Pavlov
 *
 */
public interface Service {

	/**
	 * Accepts CreateDto entity, map it to Entity and saves to database.
	 * 
	 * @param dto
	 *            to save
	 * @return DtoToUser instance if saved successfully code 200
	 */
	Response create(MagicSquareCreateDTO dto);

	/**
	 * Retrieve entity from database by id.
	 * 
	 * @param id
	 *            of entity
	 * @return DtoToUser if present, otherwise error message.
	 */
	Response get(long id);

	/**
	 * Accepts UpdateDto and update entity in database.
	 * 
	 * @param dto
	 *            to update
	 * @return DtoToUser if updated, otherwise error message.
	 */
	Response update(MagicSquareUpdateDTO dto);

	/**
	 * Delete entity from database.
	 * 
	 * @param id
	 *            of entity
	 * @return
	 */
	Response delete(long id);

	/**
	 * Accepts MagicSquareSearchDTO, convert to MagicSquareEntity and searches by
	 * given pattern;
	 * 
	 * @param dto
	 *            to search
	 * @return List of ToUserDTO if present
	 */
	Response search(MagicSquareSearchDTO dto);

}
