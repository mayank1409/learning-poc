package io.javamasters.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.javamasters.model.Person;

@Repository
public interface PersonSpringDataRepository extends JpaRepository<Person, Integer> {

}
