package com.myorg.tools.documentworkflow.dao.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import com.myorg.tools.documentworkflow.dao.BaseDAO;

public class BaseJDBCTemplate implements BaseDAO {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private PlatformTransactionManager transactionManager;
	
	public DataSource getDataSource() {
		return this.dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
	    this.dataSource = dataSource;
	    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	
	public JdbcTemplate getJdbcTemplateObject(){
		return this.jdbcTemplateObject;
	}

	public PlatformTransactionManager getTransactionManager() {
		return this.transactionManager;
	}

}
