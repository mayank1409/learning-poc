package io.javamasters.spring.web;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import io.javamasters.spring.web.persistence.dao.PrivilegeRepository;
import io.javamasters.spring.web.persistence.dao.RoleRepository;
import io.javamasters.spring.web.persistence.dao.UserRepository;
import io.javamasters.spring.web.persistence.model.Privilege;
import io.javamasters.spring.web.persistence.model.Role;
import io.javamasters.spring.web.persistence.model.User;

@Component
public class StartupApplicationListenerExample implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(StartupApplicationListenerExample.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		LOGGER.info("Creating Admin User");
		if (userRepository.findByEmail("admin@admin.com") == null) {
			List<Role> roles = createRoles();
			User user = new User();
			user.setFirstName("admin");
			user.setLastName("admin");
			user.setEmail("admin@admin.com");
			Encoder passwordEncoder = Base64.getEncoder();
			user.setPassword(passwordEncoder.encodeToString("admin123".getBytes()));
			user.setRoles(roles);
			user.setEnabled(true);
			userRepository.save(user);
		} else {
			LOGGER.info("Admin User Exists");
		}

	}

	private List<Role> createRoles() {

		List<Privilege> privileges = createPrivileges();

		Role admin = new Role();
		admin.setName("ROLE_ADMIN");
		admin.setPrivileges(privileges);

		Role user = new Role();
		user.setName("ROLE_USER");
		user.setPrivileges(privileges);

		List<Role> roles = List.of(admin, user);
		return roleRepository.saveAll(roles);
	}

	private List<Privilege> createPrivileges() {
		Privilege addPrivileges = new Privilege();
		addPrivileges.setName("ADD");
		Privilege updatePrivileges = new Privilege();
		updatePrivileges.setName("UPDATE");
		Privilege deletePrivileges = new Privilege();
		deletePrivileges.setName("DELETE");
		Privilege readPrivileges = new Privilege();
		readPrivileges.setName("READ");
		List<Privilege> privileges = List.of(addPrivileges, updatePrivileges, deletePrivileges, readPrivileges);
		privilegeRepository.saveAll(privileges);
		return privileges;
	}
}