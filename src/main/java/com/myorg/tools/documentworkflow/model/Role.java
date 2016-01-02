package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;

public class Role implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1518388687498835578L;
	
	public static Integer ID_ROLE_MAKER = 1;
	public static Integer ID_ROLE_CHECKER = 2;
	public static Integer ID_ROLE_QC = 3;
	public static Integer ID_ROLE_SYSADMIN = 4;
	
	
	private Integer roleId;
	private String roleName;
	private String roleDesc;
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
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * @return the roleDesc
	 */
	public String getRoleDesc() {
		return roleDesc;
	}
	/**
	 * @param roleDesc the roleDesc to set
	 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
}
