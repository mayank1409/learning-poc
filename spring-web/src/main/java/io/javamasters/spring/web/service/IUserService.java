package io.javamasters.spring.web.service;

import javax.validation.Valid;

import io.javamasters.spring.web.dto.LoginDto;
import io.javamasters.spring.web.dto.UserDto;
import io.javamasters.spring.web.exception.InvalidUserException;
import io.javamasters.spring.web.exception.UserAlreadyExistException;
import io.javamasters.spring.web.persistence.model.User;

public interface IUserService {

	User registerNewUserAccount(@Valid UserDto userDto) throws UserAlreadyExistException;

	User login(@Valid LoginDto loginDto) throws InvalidUserException;

	User findByEmail(String email);
}
