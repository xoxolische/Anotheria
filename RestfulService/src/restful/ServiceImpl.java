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

import dao.impl.MagicSquareDaoImpl;
import model.MagicSquareHibernate;

/**
 * MagicSquare service implementation
 * 
 * @author Nikita Pavlov
 *
 */
@Path("/magicSquare")
public class ServiceImpl implements Service<MagicSquareHibernate> {

	MagicSquareDaoImpl dao = new MagicSquareDaoImpl();

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response create(MagicSquareHibernate entity) {
		entity.print();
		entity.getSquareView();
		try {
			dao.create(entity);
			return Response.status(200).entity("Magic square saved!<br>" + entity.squareToPageView()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Something went wrong!").build();
		}
	}

	@GET
	@Path("/{id}")
	@Override
	public Response get(@PathParam(value = "id") long id) {
		MagicSquareHibernate e = dao.get(id);
		if (e != null) {
			return Response.status(200).entity(e.toString()).build();
		} else {
			return Response.status(200).entity("Square with such id does not exists!").build();
		}
	}

	@PUT
	@Path("/update")
	@Override
	public Response update(MagicSquareHibernate entity) {
		try {
			entity.getSquareView();
			dao.update(entity);
			return Response.status(200).entity("Square updated successfuly!").build();
		} catch (Exception e) {
			e.printStackTrace();
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
		List<MagicSquareHibernate> l = dao.getAll();
		if (l != null) {
			String view = "";
			for (MagicSquareHibernate m : l) {
				view += m.toString() + "<br>";
			}
			return Response.status(200).entity(view).build();
		} else {
			return Response.status(200).entity("No squares in database!").build();
		}
	}

}
