package dao;

import java.util.List;

public interface CRUD<E> {
	
	void create(E entity);
	
	E get(long id);
	
	void update(E entity);
	
	void delete(long id);
	
	List<E> getAll();

}
