package com.iss.gms.basedao;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateDao {
	protected JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}
}