package com.myorg.tools.documentworkflow.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.AuthenticationException;

import com.myorg.tools.documentworkflow.model.Role;
import com.myorg.tools.documentworkflow.model.RoleUser;
import com.myorg.tools.documentworkflow.model.RoleUsersMapping;
import com.myorg.tools.documentworkflow.model.User;
import com.sun.jersey.api.NotFoundException;

public interface UserAdminDAO {
	
	public List<User> populateUserbase() throws SQLException, Exception;
	
	public User fetchUserDetail(String userId) throws SQLException, Exception;
	
	public User authenticateAndFetchDetails(String userId, String password) throws Exception;
	
	public List<Role> populateMasterRoleList() throws SQLException, Exception;
	
	//public List<Role> populateRoleForUser(String userId) throws SQLException, Exception;
	
	public RoleUsersMapping populateRoleUserbaseMap(Integer roleId) throws SQLException, Exception;

	public List<User> populateUnmappedRoleUserbase(Integer roleId) throws SQLException, Exception;
	
	public List<RoleUser> populateAllUserRole() throws SQLException, Exception;
	
	public boolean uploadUserRoleMappings(List<RoleUser> roleUserList, String userId) throws SQLException, Exception;
	
}
