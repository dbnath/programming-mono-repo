package com.myorg.tools.documentworkflow.dao;

import java.sql.SQLException;
import java.util.List;

import com.myorg.tools.documentworkflow.model.Role;
import com.myorg.tools.documentworkflow.model.RoleUsersMapping;
import com.myorg.tools.documentworkflow.model.User;

public interface UserAdminDAO {
	
	public List<User> populateUserbase() throws SQLException, Exception;
	
	public User fetchUserDetail(String userId) throws SQLException, Exception;
	
	public List<Role> populateMasterRoleList() throws SQLException, Exception;
	
	//public List<Role> populateRoleForUser(String userId) throws SQLException, Exception;
	
	public RoleUsersMapping populateRoleUserbaseMap(Integer roleId) throws SQLException, Exception;

	public List<User> populateUnmappedRoleUserbase(Integer roleId) throws SQLException, Exception;
}
