package io.javamasters.spring.web.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import io.javamasters.spring.web.persistence.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Boolean existsByEmail(String email);

	User findByEmail(String string);

	User findByEmailAndPassword(String email, String password);

}
