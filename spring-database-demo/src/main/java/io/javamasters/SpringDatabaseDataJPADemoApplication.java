package io.javamasters;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.javamasters.data.PersonSpringDataRepository;
import io.javamasters.model.Person;

@SpringBootApplication
public class SpringDatabaseDataJPADemoApplication implements CommandLineRunner {

	@Autowired
	PersonSpringDataRepository personRepository;

	private static Logger LOGGER = LoggerFactory.getLogger(SpringDatabaseDataJPADemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringDatabaseDataJPADemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("person returned -> {}", personRepository.findAll());
		LOGGER.info("person returned 10001 -> {}", personRepository.findById(10001).isPresent());
		personRepository.deleteById(10003);

		LOGGER.info("saving person -> {}", personRepository.save(new Person("Mayank", "Delhi", new Date())));
		LOGGER.info("saving person -> {}",
				personRepository.save(new Person(1, "Mayank Madhav", "Delhi", new Date())));

	}

}
