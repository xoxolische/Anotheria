package restful;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cache.Cache;
import dao.impl.MagicSquareDaoImpl;
import model.MSMapper;
import model.MagicSquareEntity;
import restful.dto.DTOMapper;
import restful.dto.MagicSquareCreateDTO;
import restful.dto.MagicSquareSearchDTO;
import restful.dto.MagicSquareToUserDTO;
import restful.dto.MagicSquareUpdateDTO;

/**
 * MagicSquare service implementation
 * 
 * @author Nikita Pavlov
 *
 */
@Path("/magicSquare")
public class ServiceImpl implements Service {
	private static final net.anotheria.anoprise.cache.Cache<Long, MagicSquareToUserDTO> HARDWIRED_CACHE = net.anotheria.anoprise.cache.Caches
			.createConfigurableHardwiredCache("test-cache");

	// private static final Cache<Long, MagicSquareToUserDTO> GET_BY_ID_CACHE = new
	// Cache<>("getById", 1000, Long.class,
	// MagicSquareToUserDTO.class);
	@SuppressWarnings("unchecked")
	private static final Cache<Integer, List<MagicSquareToUserDTO>> EXPIRED_CACHE = new Cache<>("search", 1000,
			Integer.class, (Class<List<MagicSquareToUserDTO>>) new LinkedList<MagicSquareToUserDTO>().getClass());

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceImpl.class);

	MagicSquareDaoImpl dao = new MagicSquareDaoImpl();
	MSMapper mapper = new MSMapper();

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response create(MagicSquareCreateDTO dto) {
		try {
			MagicSquareEntity msEntity = DTOMapper.fromCreateDTOtoEntity(dto);
			dao.create(msEntity);
			return Response.status(200).entity(DTOMapper.fromEntityToUserDTO(msEntity)).build();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.warn(e.getMessage());
			return Response.status(500).entity("Something went wrong!").build();
		}

	}

	@GET
	@Path("/{id}")
	@Override
	public Response get(@PathParam(value = "id") long id) {
		// if (GET_BY_ID_CACHE.get(id) != null) {
		if (HARDWIRED_CACHE.get(id) != null) {
			System.out.println("Cache used for id= " + id);
			// return Response.status(200).entity(GET_BY_ID_CACHE.get(id)).build();
			return Response.status(200).entity(HARDWIRED_CACHE.get(id)).build();
		} else {
			MagicSquareEntity e = dao.get(id);
			if (e != null) {
				MagicSquareToUserDTO toUser = DTOMapper.fromEntityToUserDTO(e);
				// GET_BY_ID_CACHE.put(id, toUser);
				HARDWIRED_CACHE.put(id, toUser);
				return Response.status(200).entity(toUser).build();
			} else {
				return Response.status(200).entity("Square with such id does not exists!").build();
			}
		}
	}

	@PUT
	@Path("/update")
	@Override
	public Response update(MagicSquareUpdateDTO dto) {
		try {
			MagicSquareEntity e = DTOMapper.fromUpdateDTOtoEntity(dto);
			dao.update(e);
			return Response.status(200).entity(DTOMapper.fromEntityToUserDTO(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.warn(e.getMessage());
			return Response.status(500).entity("Something went wrong!").build();
		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Override
	public Response delete(@PathParam(value = "id") long id) {
		dao.delete(id);
		return Response.status(200).entity("Square was deleted!").build();
	}

	/**
	 * Retrieves all MagicSquares from database.
	 * 
	 * @return List of MagicSquareToUserDTO if present
	 */
	@GET
	@Path("/getAll")
	public Response getAll() {
		List<MagicSquareEntity> l = dao.getAll();
		if (l != null) {
			List<MagicSquareToUserDTO> list = new LinkedList<>();
			for (MagicSquareEntity m : l) {
				list.add(DTOMapper.fromEntityToUserDTO(m));
			}
			return Response.status(200).entity(list).build();
		} else {
			return Response.status(200).entity("No squares in database!").build();
		}
	}

	@POST
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response search(MagicSquareSearchDTO dto) {
		if (EXPIRED_CACHE.get(dto.getSquarePattern().hashCode()) != null) {
			System.out.println("Cache used for pattern : " + dto.getSquarePattern());
			return Response.status(200).entity(EXPIRED_CACHE.get(dto.getSquarePattern().hashCode())).build();
		} else {
			MagicSquareEntity e = DTOMapper.fromSearchDtoToEntity(dto);
			List<MagicSquareEntity> l = dao.search(e);
			if (l != null) {
				List<MagicSquareToUserDTO> list = new LinkedList<>();
				for (MagicSquareEntity m : l) {
					list.add(DTOMapper.fromEntityToUserDTO(m));
				}
				EXPIRED_CACHE.put(dto.getSquarePattern().hashCode(), list);
				return Response.status(200).entity(list).build();
			} else {
				return Response.status(200).entity("No such squares in database!").build();
			}
		}
	}

}
