package restful;

import javax.ws.rs.core.Response;

public interface Service<E> {

	Response create(E entity);

	Response get(long id);
	
	Response update(E entity);
	
	Response delete(long id);
	
}
