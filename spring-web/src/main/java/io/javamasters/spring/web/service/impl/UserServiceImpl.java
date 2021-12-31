package io.javamasters.spring.web.service.impl;

import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javamasters.spring.web.dto.LoginDto;
import io.javamasters.spring.web.dto.UserDto;
import io.javamasters.spring.web.exception.InvalidUserException;
import io.javamasters.spring.web.exception.UserAlreadyExistException;
import io.javamasters.spring.web.persistence.dao.RoleRepository;
import io.javamasters.spring.web.persistence.dao.UserRepository;
import io.javamasters.spring.web.persistence.model.User;
import io.javamasters.spring.web.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User registerNewUserAccount(@Valid UserDto userDto) throws UserAlreadyExistException {

		boolean isExists = userRepository.existsByEmail(userDto.getEmail());
		if (isExists) {
			throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
		}

		final User user = new User();

		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		Encoder passwordEncoder = Base64.getEncoder();
		user.setPassword(passwordEncoder.encodeToString(userDto.getPassword().getBytes()));
		user.setEmail(userDto.getEmail());
		user.setUsing2FA(userDto.isUsing2FA());
		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
		user.setEnabled(true);
		return userRepository.save(user);
	}

	@Override
	public User login(@Valid LoginDto loginDto) throws InvalidUserException {
		Encoder passwordEncoder = Base64.getEncoder();
		String password = passwordEncoder.encodeToString(loginDto.getPassword().getBytes());
		User user = userRepository.findByEmailAndPassword(loginDto.getEmail(), password);
		if (user == null) {
			throw new InvalidUserException("No User found with email " + loginDto.getEmail());
		}
		return user;
	}

	@Override
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

}
