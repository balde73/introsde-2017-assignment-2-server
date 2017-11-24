package rest.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import rest.model.Activity;
import rest.model.Person;

public class PersonDaoImpl extends JpaDao<Person, Integer> implements PersonDao{

	public PersonDaoImpl() {
		super(Person.class);
	}

	@Override
	public Person update(Person fromPerson, Person toPerson) {
		
		// updating only not null fields
		fromPerson.setBirthdate(toPerson.getBirthdate());
		fromPerson.setUsername(toPerson.getUsername());
		fromPerson.setLastname(toPerson.getLastname());
		fromPerson.setFirstname(toPerson.getFirstname());
		fromPerson.setEmail(toPerson.getEmail());
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(fromPerson);
		tx.commit();

		return fromPerson;
	}
}
