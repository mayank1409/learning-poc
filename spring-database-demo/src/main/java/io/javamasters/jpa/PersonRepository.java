package io.javamasters.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import io.javamasters.model.Person;

@Repository
@Transactional
public class PersonRepository {

	@PersistenceContext
	EntityManager entityManager;

	public Person findById(Integer id) {
		return entityManager.find(Person.class, id);
	}

	public Person update(Person person) {
		return entityManager.merge(person);
	}

	public Person insert(Person person) {
		return entityManager.merge(person);
	}

	public void deleteById(Integer id) {
		Person person = findById(id);
		entityManager.remove(person);
	}

	public List<Person> findAll() {
		TypedQuery<Person> namedQuery = entityManager.createNamedQuery("findAll.Persons", Person.class);
		return namedQuery.getResultList();
	}

}
