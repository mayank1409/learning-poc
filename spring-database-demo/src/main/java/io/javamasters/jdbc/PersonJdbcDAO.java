package io.javamasters.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import io.javamasters.model.Person;

@Repository
public class PersonJdbcDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	class PersonRowMapper implements RowMapper<Person> {

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4));
		}

	}

	public List<Person> findAll() {
		return jdbcTemplate.query("select * from person", new PersonRowMapper());
	}

	public Person findById(Integer id) {
		return jdbcTemplate.queryForObject("select * from person where id=?", new PersonRowMapper(),
				new Object[] { id });
	}

	public int deleteById(Integer id) {
		return jdbcTemplate.update("delete from person where id=?", new Object[] { id });
	}

	public int insert(Person person) {
		return jdbcTemplate.update("insert into person(id, name, location, birth_date) values (?, ?, ?, ?)",
				new Object[] { person.getId(), person.getName(), person.getLocation(),
						new Timestamp(person.getBirthDate().getTime()) });
	}

	public int update(Person person, Integer id) {
		return jdbcTemplate.update("update person set name=?, location=?, birth_date=? where id=?",
				new Object[] { person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()),
						person.getId() });
	}

}
