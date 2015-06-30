package com.myorg.tools.documentworkflow.model;

import java.util.Date;

public class Document implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1518388687498835566L;
	
	private Integer docId;
	private String userId;
	private Date lastUpdateDt;
	private String status;
	private String role;
	private String group;
	private boolean isReworked;
	private String overrideReason;

	public Document() {

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public boolean isReworked() {
		return isReworked;
	}

	public void setReworked(boolean isReworked) {
		this.isReworked = isReworked;
	}

	public String getOverrideReason() {
		return overrideReason;
	}

	public void setOverrideReason(String overrideReason) {
		this.overrideReason = overrideReason;
	}

}
