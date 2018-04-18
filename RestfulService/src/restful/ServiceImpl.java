package restful;

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

import dao.impl.MagicSquareDaoImpl;
import model.MagicSquareHibernate;

/**
 * MagicSquare service implementation
 * 
 * @author Nikita Pavlov
 *
 */
@Path("/ms")
public class ServiceImpl implements Service<MagicSquareHibernate> {

	MagicSquareDaoImpl dao = new MagicSquareDaoImpl();

	@POST
	@Path("/t")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(T entity) {
		System.out.println("good!");
		return Response.status(200).entity(entity.t).build();
	}

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response create(MagicSquareHibernate entity) {
		dao.create(entity);
		return Response.status(200).entity(null).build();
	}

	@GET
	@Path("/{id}")
	@Override
	public Response get(@PathParam(value = "id") long id) {
		MagicSquareHibernate e = dao.get(id);
		return Response.status(200).entity(e).build();
	}

	@PUT
	@Path("/update")
	@Override
	public Response update(MagicSquareHibernate entity) {
		dao.update(entity);
		return Response.status(200).entity(null).build();
	}

	@DELETE
	@Path("/delete/{id}")
	@Override
	public Response delete(long id) {
		dao.delete(id);
		return Response.status(200).entity(null).build();
	}

	@GET
	@Path("/getAll")
	public Response getAll() {
		dao.getAll();
		return Response.status(200).entity(null).build();
	}

}
