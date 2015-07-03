package com.myorg.tools.documentworkflow.dao.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.myorg.tools.documentworkflow.dao.BaseDAO;

public class BaseJDBCTemplate implements BaseDAO {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public DataSource getDataSource() {
		return this.dataSource;
	}
	   
	public void setDataSource(DataSource dataSource) {
	    this.dataSource = dataSource;
	    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public JdbcTemplate getJdbcTemplateObject(){
		return this.jdbcTemplateObject;
	}

}
