package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;
import java.util.Date;

public class DocWkflwProcess implements Serializable{
	
	private static final long serialVersionUID = 1518388687498835566L;
	
	private String agreementId;
	
	private Integer versionId;
	
	private Integer agreementTypeCode;
	
	private String agreementTypeDesc;
	
	private String lob;
	
	private Integer statusCode;
	
	private String statusDescription;
	
	private String createdBy;
	
	private Date creationDate;
	
	private String assignedTo;
	
	private String lastUpdatedBy;
	
	private Date lastUpdationDate;
	
	private String lastUpdationDateStr;
	
	private Integer roleId;
	
	private String makerComments;
	private String makerStatus;
	private Integer makerStatusId;
	private String checkerComments;
	private String checkerStatus;
	private Integer checkerStatusId;
	private String smeComments;
	private Integer errorReasonCd;
	private String errorReason;
	
	private Integer numPages;
	private Integer numFields;
	
	private String comments;
	

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public Integer getAgreementTypeCode() {
		return agreementTypeCode;
	}

	public void setAgreementTypeCode(Integer agreementTypeCode) {
		this.agreementTypeCode = agreementTypeCode;
	}

	public String getLob() {
		return lob;
	}

	public void setLob(String lob) {
		this.lob = lob;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdationDate() {
		return lastUpdationDate;
	}

	public void setLastUpdationDate(Date lastUpdationDate) {
		this.lastUpdationDate = lastUpdationDate;
	}

	public String getAgreementTypeDesc() {
		return agreementTypeDesc;
	}

	public void setAgreementTypeDesc(String agreementTypeDesc) {
		this.agreementTypeDesc = agreementTypeDesc;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public String getMakerComments() {
		return makerComments;
	}

	public void setMakerComments(String makerComments) {
		this.makerComments = makerComments;
	}

	public String getCheckerComments() {
		return checkerComments;
	}

	public void setCheckerComments(String checkerComments) {
		this.checkerComments = checkerComments;
	}

	public String getSmeComments() {
		return smeComments;
	}

	public void setSmeComments(String smeComments) {
		this.smeComments = smeComments;
	}

	public Integer getErrorReasonCd() {
		return errorReasonCd;
	}

	public void setErrorReasonCd(Integer errorReasonCd) {
		this.errorReasonCd = errorReasonCd;
	}

	public String getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getMakerStatus() {
		return makerStatus;
	}

	public void setMakerStatus(String makerStatus) {
		this.makerStatus = makerStatus;
	}

	public String getCheckerStatus() {
		return checkerStatus;
	}

	public void setCheckerStatus(String checkerStatus) {
		this.checkerStatus = checkerStatus;
	}

	public Integer getMakerStatusId() {
		return makerStatusId;
	}

	public void setMakerStatusId(Integer makerStatusId) {
		this.makerStatusId = makerStatusId;
	}

	public Integer getCheckerStatusId() {
		return checkerStatusId;
	}

	public void setCheckerStatusId(Integer checkerStatusId) {
		this.checkerStatusId = checkerStatusId;
	}

	public Integer getNumPages() {
		return numPages;
	}

	public void setNumPages(Integer numPages) {
		this.numPages = numPages;
	}

	public Integer getNumFields() {
		return numFields;
	}

	public void setNumFields(Integer numFields) {
		this.numFields = numFields;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getLastUpdationDateStr() {
		return lastUpdationDateStr;
	}

	public void setLastUpdationDateStr(String lastUpdationDateStr) {
		this.lastUpdationDateStr = lastUpdationDateStr;
	}
	
	

}
