package com.myorg.tools.documentworkflow.model;

import java.util.Date;

public class AgreementWorkflow implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1518388687498835566L;
	
	private String agreementId;
	private String lob;
	private Integer agreementTypeId;
	private String agreementTypeDesc;
	private Integer wfStatusId;
	private String wfStatusDesc;
	private String assignedTo;
	private Date assignedDt;
	private String lastUpdatedBy;
	private Date lastUpdateDt;
	private Integer versionId; //For audit trail purpose
	private AgreementWorkflowDetail detail;
	private String createdBy;
	private Date createdDt;
	private Integer roleId;
	
	/**
	 * 
	 */
	public AgreementWorkflow() {
		// TODO Auto-generated constructor stub
	}
	public String getAgreementId() {
		return agreementId;
	}
	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}
	public String getLob() {
		return lob;
	}
	public void setLob(String lob) {
		this.lob = lob;
	}
	public Integer getAgreementTypeId() {
		return agreementTypeId;
	}
	public void setAgreementTypeId(Integer agreementTypeId) {
		this.agreementTypeId = agreementTypeId;
	}
	public String getAgreementTypeDesc() {
		return agreementTypeDesc;
	}
	public void setAgreementTypeDesc(String agreementTypeDesc) {
		this.agreementTypeDesc = agreementTypeDesc;
	}
	public Integer getWfStatusId() {
		return wfStatusId;
	}
	public void setWfStatusId(Integer wfStatusId) {
		this.wfStatusId = wfStatusId;
	}
	public String getWfStatusDesc() {
		return wfStatusDesc;
	}
	public void setWfStatusDesc(String wfStatusDesc) {
		this.wfStatusDesc = wfStatusDesc;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public Date getAssignedDt() {
		return assignedDt;
	}
	public void setAssignedDt(Date assignedDt) {
		this.assignedDt = assignedDt;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdateDt() {
		return lastUpdateDt;
	}
	public void setLastUpdateDt(Date lastUpdateDt) {
		this.lastUpdateDt = lastUpdateDt;
	}
	public Integer getVersionId() {
		return versionId;
	}
	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}
	public AgreementWorkflowDetail getDetail() {
		return detail;
	}
	public void setDetail(AgreementWorkflowDetail detail) {
		this.detail = detail;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	

}
