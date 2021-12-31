package io.javamasters.spring.web.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import io.javamasters.spring.web.persistence.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

}
