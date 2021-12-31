package io.javamasters.spring.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import io.javamasters.spring.web.dto.LoginDto;
import io.javamasters.spring.web.dto.UserDto;
import io.javamasters.spring.web.exception.InvalidUserException;
import io.javamasters.spring.web.exception.UserAlreadyExistException;
import io.javamasters.spring.web.service.IUserService;

@Controller
@SessionAttributes("email")
public class RegistrationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	private IUserService userService;

	@GetMapping("/")
	public String redirectToRegistrationPage() {
		return "redirect:/user/registration";
	}

	@GetMapping("/user/registration")
	public String showRegistrationForm(final HttpServletRequest request, final ModelMap model) {
		LOGGER.debug("Rendering registration page.");
		final UserDto userDto = new UserDto();
		model.addAttribute("userDto", userDto);
		return "registration";
	}

	@GetMapping("/user/login")
	public String showLoginForm(final HttpServletRequest request, final ModelMap model) {
		LOGGER.debug("Rendering login page.");
		final LoginDto loginDto = new LoginDto();
		model.addAttribute("loginDto", loginDto);
		return "login";
	}

	@PostMapping("/user/registration")
	public ModelAndView registration(@Valid @ModelAttribute("userDto") final UserDto userDto,
			BindingResult bindingResult) throws UserAlreadyExistException {
		LOGGER.debug("Registering user account with information: {}", userDto);
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("registration", "userDto", userDto);
			mav.addObject("message", "Data Validation Failed");
			return mav;
		}
		try {
			userService.registerNewUserAccount(userDto);
		} catch (final UserAlreadyExistException uaeEx) {
			ModelAndView mav = new ModelAndView("registration", "userDto", userDto);
			mav.addObject("message",
					"An account for that username/email already exists. Please enter a different username.");
			return mav;
		} catch (final RuntimeException ex) {
			LOGGER.warn("Unable to register user", ex);
			return new ModelAndView("error", "userDto", userDto);
		}
		ModelAndView mav = new ModelAndView("registration", "userDto", userDto);
		mav.addObject("message", "You are registered successfully");
		return mav;
	}

	@PostMapping("/user/login")
	public ModelAndView login(@Valid @ModelAttribute("loginDto") LoginDto loginDto, BindingResult bindingResult) {
		LOGGER.debug("Login User called");
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("login", "loginDto", loginDto);
			mav.addObject("message", "Data Validation Failed");
			return mav;
		}
		try {
			userService.login(loginDto);
		} catch (InvalidUserException ex) {
			ModelAndView mav = new ModelAndView("login", "loginDto", loginDto);
			mav.addObject("message", "Invalid User");
			return mav;
		} catch (final RuntimeException ex) {
			LOGGER.warn("Unable to register user", ex);
			return new ModelAndView("error", "loginDto", loginDto);
		}
		ModelAndView mav = new ModelAndView("welcome", "loginDto", loginDto);
		mav.addObject("email", loginDto.getEmail());
		return mav;
	}

	@GetMapping("/user/logout")
	public String logout(final HttpServletRequest request, final ModelMap model) {
		LOGGER.debug("Logout Method Called");
		HttpSession session = request.getSession();
		if (session.getAttribute("email") != null) {
			session.removeAttribute("email");
		}
		model.put("message", "Logout Sucessful");
		return "redirect:/user/login";
	}

}
