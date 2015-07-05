package com.myorg.tools.documentworkflow.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.Role;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class RoleMapper implements RowMapper<Role> {
	
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
	  Role role = new Role();
      if (! DocumentWorkflowToolUtility.isEmpty(rs)) {
    	  role.setRoleId(rs.getInt("ID_ROLE"));
    	  role.setRoleName(rs.getString("NM_ROLE"));
    	  role.setRoleDesc(rs.getString("TX_ROLE"));
      }
      return role;
    }

}
