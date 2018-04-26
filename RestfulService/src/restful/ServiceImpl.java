package restful;

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

import dao.impl.MagicSquareDaoImpl;
import magic.MagicSquare;
import model.MSMapper;
import model.MagicSquareEntity;
import restful.dto.DTOMapper;
import restful.dto.MagicSquareDTO;

/**
 * MagicSquare service implementation
 * 
 * @author Nikita Pavlov
 *
 */
@Path("/magicSquare")
public class ServiceImpl implements Service<MagicSquareDTO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceImpl.class);
	
	MagicSquareDaoImpl dao = new MagicSquareDaoImpl();
	MSMapper mapper = new MSMapper();

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response create(MagicSquareDTO dto) {
		MagicSquare ms = mapper.getMagicSqaure(DTOMapper.fromDTO(dto));
		try {
			dao.create(DTOMapper.fromDTO(dto));
			return Response.status(200).entity("Magic square saved!<br>" + ms.squareToPageView()).build();
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
		MagicSquareEntity e = dao.get(id);
		if (e != null) {
			MagicSquare ms = mapper.getMagicSqaure(e);
			return Response.status(200).entity(ms.squareToPageView()).build();
		} else {
			return Response.status(200).entity("Square with such id does not exists!").build();
		}
	}

	@PUT
	@Path("/update")
	@Override
	public Response update(MagicSquareDTO dto) {
		try {
			dao.update(DTOMapper.fromDTO(dto));
			return Response.status(200).entity("Square updated successfuly!").build();
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

	@GET
	@Path("/getAll")
	public Response getAll() {
		List<MagicSquareEntity> l = dao.getAll();
		if (l != null) {
			String view = "";
			for (MagicSquareEntity m : l) {
				view += m.toString() + "<br>";
			}
			return Response.status(200).entity(view).build();
		} else {
			return Response.status(200).entity("No squares in database!").build();
		}
	}
	
	
	@POST
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response search(MagicSquareDTO dto) {
		List<MagicSquareEntity> l = dao.search(DTOMapper.fromDTO(dto));
		if (l != null) {
			String view = "Squares: <br>";
			for (MagicSquareEntity m : l) {
				view += m.toString() + "<br>";
			}
			return Response.status(200).entity(view).build();
		} else {
			return Response.status(200).entity("No squares in database!").build();
		}
	}

}
