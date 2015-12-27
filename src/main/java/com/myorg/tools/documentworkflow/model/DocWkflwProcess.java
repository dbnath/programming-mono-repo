package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;
import java.util.Date;

public class DocWkflwProcess implements Serializable{
	
	private Integer agreementId;
	
	private Integer agreementTypeCode;
	
	private String lob;
	
	private Integer statusCode;
	
	private String createdBy;
	
	private Date creationDate;
	
	private String assignedTo;
	
	private String lastUpdatedBy;
	
	private Date lastUpdationDate;

	public Integer getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
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
	
	

}
