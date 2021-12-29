package io.javamasters;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.javamasters.jdbc.PersonJdbcDAO;
import io.javamasters.model.Person;

//@SpringBootApplication
public class SpringDatabaseDemoApplication implements CommandLineRunner {

	@Autowired
	PersonJdbcDAO personJdbcDAO;

	private static Logger LOGGER = LoggerFactory.getLogger(SpringDatabaseDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringDatabaseDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("person returned -> {}", personJdbcDAO.findAll());
		LOGGER.info("person returned 10001 -> {}", personJdbcDAO.findById(10001));
		LOGGER.info("deleting 10003 -> {}", personJdbcDAO.deleteById(10003));

		LOGGER.info("saving person -> {}", personJdbcDAO.insert(new Person(10004, "Mayank", "Delhi", new Date())));
		LOGGER.info("saving person -> {}",
				personJdbcDAO.update(new Person(10004, "Mayank Madhav", "Delhi", new Date()), 10004));

	}

}
