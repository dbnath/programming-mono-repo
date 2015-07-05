package com.myorg.tools.documentworkflow.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.User;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class UserMapper implements RowMapper<User> {
	
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	  User user = new User();
      if (! DocumentWorkflowToolUtility.isEmpty(rs)) {
    	  user.setUserId(rs.getString("ID_USER"));
    	  user.setUserName(rs.getString("NM_USER"));
    	  user.setPassword(rs.getString("PASSWORD"));
    	  user.setPasswordType(rs.getString("PASSWORD_TYPE"));
    	  user.setUserStatus(rs.getString("USER_STATUS"));
    	  user.setLastLoginTime(rs.getDate("LAST_LOGIN"));
    	  user.setCreatedBy(rs.getString("CREATED_BY"));
    	  user.setCreationDate(rs.getDate("CREATION_DT"));
    	  user.setLastUpdatedBy(rs.getString("UPDATED_BY"));
    	  user.setLastUpdatedDate(rs.getDate("UPDATE_DT"));
      }
      return user;
    }

}
