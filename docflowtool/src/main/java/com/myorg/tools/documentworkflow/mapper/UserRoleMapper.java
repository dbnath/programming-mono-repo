package com.myorg.tools.documentworkflow.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.RoleUser;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class UserRoleMapper implements RowMapper<RoleUser> {
	
	public RoleUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		RoleUser roleUser = new RoleUser();
      if (! DocumentWorkflowToolUtility.isEmpty(rs)) {
    	  roleUser.setUserId(rs.getString("ID_USER"));
    	  roleUser.setUserName(rs.getString("NM_USER"));
    	  roleUser.setUserStatus(rs.getString("USER_STATUS"));
    	  roleUser.setRoleId(rs.getInt("ID_ROLE"));
    	  roleUser.setRoleName(rs.getString("TX_ROLE"));
      }
      return roleUser;
    }

}
