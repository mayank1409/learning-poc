package io.javamasters.spring.web.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import io.javamasters.spring.web.persistence.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String string);

}
