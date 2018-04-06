package restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/test")
public class ServiceImpl implements Service<TestEntity> {

//	@GET
//	@Path("/{param}")
//	public Response getMsg(@PathParam("param") String msg) {
//
//		String output = "Jersey say : " + msg;
//
//		return Response.status(200).entity(output).build();
//
//	}

	@Override
	public void create(TestEntity entity) {
		// TODO Auto-generated method stub

	}

	@GET
	@Path("/{id}")
	@Override
	public Response get(@PathParam(value = "id") long id) {
		TestEntity t = new TestEntity(String.valueOf(id));
		return Response.status(200).entity(t.getM()).build();
	}

	@Override
	public void update(TestEntity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(TestEntity entity) {
		// TODO Auto-generated method stub

	}

}
