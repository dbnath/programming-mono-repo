package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;
import java.util.List;

public class RoleUsersMapping implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1518388687498835598L;
	
	private Integer roleId;
	private List<User> userList;
	/**
	 * 
	 */
	public RoleUsersMapping() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the userList
	 */
	public List<User> getUserList() {
		return userList;
	}
	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
