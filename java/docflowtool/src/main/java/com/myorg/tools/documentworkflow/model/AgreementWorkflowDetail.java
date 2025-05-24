package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;
import java.util.Date;

public class AgreementWorkflowDetail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1718388687498835668L;
	
	private String agreementId;
	private String comment;
	private Integer commentUserRole;
	private Integer errorReasonId;
	private String createdBy;
	private Date creationDt;
	private String lastUpdatedBy;
	private Date lastUpdatedDt;
	private Integer wfStatusId;
	private String wfStatusDesc;
	
	public AgreementWorkflowDetail(){
		
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getCommentUserRole() {
		return commentUserRole;
	}

	public void setCommentUserRole(Integer commentUserRole) {
		this.commentUserRole = commentUserRole;
	}

	public Integer getErrorReasonId() {
		return errorReasonId;
	}

	public void setErrorReasonId(Integer errorReasonId) {
		this.errorReasonId = errorReasonId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDt() {
		return creationDt;
	}

	public void setCreationDt(Date creationDt) {
		this.creationDt = creationDt;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdatedDt() {
		return lastUpdatedDt;
	}

	public void setLastUpdatedDt(Date lastUpdatedDt) {
		this.lastUpdatedDt = lastUpdatedDt;
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

	
}
