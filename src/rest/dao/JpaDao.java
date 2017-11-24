package rest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class JpaDao <T, I> implements Dao<T, I>{

	private EntityManager em;
	protected Class<T> entityClass;
	
	public JpaDao(Class<T> entityClass) {
		this.entityClass = entityClass;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("introsde-jpa");
			this.em = emf.createEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}
	
	@Override
	public List<T> findAll() {
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = builder.createQuery(this.entityClass);

        criteriaQuery.from(this.entityClass);

        TypedQuery<T> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getResultList();
	}
	
	@Override
	public T add(T entity) {
		EntityManager em = this.getEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		return entity;
	}

	@Override
	public T findById(I id) {
		return this.getEntityManager().find(this.entityClass, id);
	}
	
	@Override
	public boolean remove(I id) {
		T entity = this.findById(id);
		if(entity == null)
			return false;
		EntityManager em = this.getEntityManager();
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
		return true;
	}
	
}
