package io.javamasters.spring.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	private Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		LOGGER.info("request URI -> {}", req.getRequestURI());
		HttpSession session = req.getSession(false);

		boolean loggedIn = false;
		if (session != null && session.getAttribute("email") != null) {
			loggedIn = true;
		}

		String loginURI = req.getContextPath() + "/admin/login";
	    boolean isLoginPageRequest = req.getRequestURI().endsWith("login.jsp");
	    boolean loginRequest = req.getRequestURI().equals(loginURI);

	    if (loggedIn && (isLoginPageRequest || loginRequest)) {
	      String homePage = "/";
	      RequestDispatcher dispatcher = req.getRequestDispatcher(homePage);
	      dispatcher.forward(request, response);
	    } else if (loggedIn || loginRequest) {
	      chain.doFilter(request, response);
	    } else {
	      String loginPage = "/user/login";
	      RequestDispatcher dispatcher = req.getRequestDispatcher(loginPage);
	      dispatcher.forward(request, response);
	    }

	}
}
