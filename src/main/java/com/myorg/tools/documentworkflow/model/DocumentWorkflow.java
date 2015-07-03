package com.myorg.tools.documentworkflow.model;

import java.util.Date;

public class DocumentWorkflow implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1518388687498835566L;
	
	private Integer docId;
	private String userId;
	private Date lastUpdateDt;
	private String wfStatus;
	private String userRole;
	private String wfGroup;
	private String isReworked;

	public DocumentWorkflow() {

	}

	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getLastUpdateDt() {
		return lastUpdateDt;
	}

	public void setLastUpdateDt(Date lastUpdateDt) {
		this.lastUpdateDt = lastUpdateDt;
	}

	public String getWfStatus() {
		return wfStatus;
	}

	public void setWfStatus(String status) {
		this.wfStatus = status;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String role) {
		this.userRole = role;
	}

	public String getWfGroup() {
		return wfGroup;
	}

	public void setWfGroup(String group) {
		this.wfGroup = group;
	}

	public String isReworked() {
		return isReworked;
	}

	public void setReworked(String isReworked) {
		this.isReworked = isReworked;
	}

}
