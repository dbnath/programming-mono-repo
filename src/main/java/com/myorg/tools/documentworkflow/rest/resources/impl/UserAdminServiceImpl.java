package com.myorg.tools.documentworkflow.rest.resources.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import com.myorg.tools.documentworkflow.dao.UserAdminDAO;
import com.myorg.tools.documentworkflow.model.Role;
import com.myorg.tools.documentworkflow.model.RoleUsersMapping;
import com.myorg.tools.documentworkflow.model.User;
import com.myorg.tools.documentworkflow.rest.resources.UserAdminService;

public class UserAdminServiceImpl implements UserAdminService {
	
	private UserAdminDAO userAdminDAO;

	/**
	 * @return the userAdminDAO
	 */
	public UserAdminDAO getUserAdminDAO() {
		return userAdminDAO;
	}

	/**
	 * @param userAdminDAO the userAdminDAO to set
	 */
	public void setUserAdminDAO(UserAdminDAO userAdminDAO) {
		this.userAdminDAO = userAdminDAO;
	}
	
	public Response populateUsersList() {
		try{
			List<User> usersList = userAdminDAO.populateUserbase();
			return Response.ok().entity(usersList).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	public Response populateMasterRoleList() {
		try{
			List<Role> masterRoleList = userAdminDAO.populateMasterRoleList();
			return Response.ok().entity(masterRoleList).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();
		}
		
	}
	
	public Response populateUserDetail(String userId) {
		try{
			User user = userAdminDAO.fetchUserDetail(userId);
			return Response.ok().entity(user).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	public Response populateRoleUserMapping(Integer roleId) {
		try{
			RoleUsersMapping users = userAdminDAO.populateRoleUserbaseMap(roleId);
			return Response.ok().entity(users).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	public Response populateUnmappedRoleUserbase(Integer roleId) {
		try{
			List<User> usersList = userAdminDAO.populateUnmappedRoleUserbase(roleId);
			return Response.ok().entity(usersList).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

}
