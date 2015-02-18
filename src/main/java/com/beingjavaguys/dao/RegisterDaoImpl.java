package com.beingjavaguys.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.beingjavaguys.domain.Login;

public class RegisterDaoImpl implements RegisterDao {

	@Autowired
	DataSource dataSource;

	@Override
	@Transactional
	public void registerUser(Login login) {
		String sql = "insert into users values(?,?,?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate
				.update(sql,
						new Object[] { login.getUsername(),
								login.getPassword(), true });

		String sqlRole = "insert into user_roles(username,role) values(?,?)";

		jdbcTemplate.update(sqlRole,
				new Object[] { login.getUsername(), login.getUserRole() });

	}
}