package rest.dao;
import rest.model.Activity;
import rest.model.Person;

public interface PersonDao extends Dao<Person, Integer>{
	public Person update(Person fromPerson, Person toPerson);
}
