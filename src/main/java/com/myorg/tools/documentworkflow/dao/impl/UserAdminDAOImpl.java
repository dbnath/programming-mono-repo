package com.myorg.tools.documentworkflow.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.dao.EmptyResultDataAccessException;

import com.myorg.tools.documentworkflow.constant.DocumentWorkflowToolConstant;
import com.myorg.tools.documentworkflow.dao.UserAdminDAO;
import com.myorg.tools.documentworkflow.model.Role;
import com.myorg.tools.documentworkflow.model.RoleUsersMapping;
import com.myorg.tools.documentworkflow.model.User;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;
import com.sun.jersey.api.NotFoundException;

public class UserAdminDAOImpl extends BaseJDBCTemplate implements UserAdminDAO {

	public List<User> populateUserbase() throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.USER_BASE_POPULATE_SQL;
		List<User> usersList = null;
		try{
			usersList = this.getJdbcTemplateObject().query(SQL, new UserMapper());
		} catch(EmptyResultDataAccessException e) {
			usersList = null;
		}
		return usersList;
	}
	
	public User fetchUserDetail(String userId) throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.FETCH_USER_DETAILS_SQL;
		User userDetailObj = null;
		try{
			userDetailObj = this.getJdbcTemplateObject().queryForObject(SQL, new Object[]{userId}, new UserMapper());
		} catch(EmptyResultDataAccessException e) {
			userDetailObj = null;
		}		
		if (! DocumentWorkflowToolUtility.isEmpty(userDetailObj)) {
			List<Role> roleList = populateRoleForUser(userId);
			userDetailObj.setUserRoleList(roleList);
		}
		return userDetailObj;
	}
	
	public User authenticateAndFetchDetails(String userId, String password) throws Exception {
		User userDetailObj = fetchUserDetail(userId);
		if (userDetailObj == null)		 {
			throw new Exception("User Id : "+userId +" doesn't exists in the system. Please Contact Admin.");
		} else if (userDetailObj != null && !userDetailObj.getPassword().equals(password)){
			throw new Exception("Login ID / Password entered is not correct.");
		} else {
			return userDetailObj;
		}
	}

	public List<Role> populateMasterRoleList() throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.MASTER_ROLE_POPULATE_SQL;
		List<Role> masterRoleList = null;
		try{
			masterRoleList = this.getJdbcTemplateObject().query(SQL, new RoleMapper());
		} catch(EmptyResultDataAccessException e) {
			masterRoleList = null;
		}
		return masterRoleList;
	}
	
	private List<Role> populateRoleForUser(String userId) throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.POPULATE_ROLE_FOR_USER_SQL;
		List<Role> roleList = null;
		try{
			roleList = this.getJdbcTemplateObject().query(SQL, new Object[]{userId}, new RoleMapper());
		} catch(EmptyResultDataAccessException e) {
			roleList = null;
		}
		return roleList;
	}

	public RoleUsersMapping populateRoleUserbaseMap(Integer roleId) throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.FETCH_ROLE_USER_MAP_SQL;
		List<User> usersList = null;
		try{
			usersList = this.getJdbcTemplateObject().query(SQL, new Object[]{roleId}, new UserMapper());
		} catch(EmptyResultDataAccessException e) {
			usersList = null;
		}
		RoleUsersMapping roleUsersMapping = new RoleUsersMapping();
		roleUsersMapping.setRoleId(roleId);
		roleUsersMapping.setUserList(usersList);
		return roleUsersMapping;
	}

	public List<User> populateUnmappedRoleUserbase(Integer roleId) throws SQLException, Exception {
		List<User> usersList = populateUserbase();
		List<String> userIdsList = new ArrayList<String>();
		for (User user : usersList){
			userIdsList.add(user.getUserId());
		}
		List<User> roleUsersMap = ((RoleUsersMapping)populateRoleUserbaseMap(roleId)).getUserList();
		for (User user : roleUsersMap) {
			if (userIdsList.contains(user.getUserId())) {
				usersList.remove(findUserElementToRemoveFromList(usersList, user.getUserId()));
			}
		}
		return usersList;
	}
	
	private User findUserElementToRemoveFromList(List<User> userList, String userId){
		if (! DocumentWorkflowToolUtility.isEmptyList(userList)) {
			for (User user : userList) {
				if (userId.equals(user.getUserId())){
					return user;
				}
			}
		}
		return null;
	}
}
