package rest.dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface Dao<T, I> {
	List<T> findAll();
	T findById(I id);
	EntityManager getEntityManager();
	boolean remove(I id);
	T add(T elem);
}
