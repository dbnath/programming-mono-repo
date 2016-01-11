package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;
import java.util.List;

public class RoleUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1518388687498835598L;
	
	private Integer roleId;
	private String roleName;
	private String userId;
	private String userName;
	private String userStatus;
	/**
	 * 
	 */
	public RoleUser() {
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

	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

}
