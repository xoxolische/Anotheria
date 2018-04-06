package restful;

import javax.ws.rs.core.Response;

public interface Service<E> {

	void create(E entity);

	Response get(long id);
	
	void update(E entity);
	
	void delete(long id);
	
	void delete(E entity);
	
}
