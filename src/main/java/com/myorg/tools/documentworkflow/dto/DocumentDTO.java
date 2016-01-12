package com.myorg.tools.documentworkflow.dto;

import java.io.Serializable;
import java.util.List;

import com.myorg.tools.documentworkflow.model.DocWkflwProcess;
import com.myorg.tools.documentworkflow.model.User;

public class DocumentDTO extends BaseDTO implements Serializable { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6503083677284094709L;

	private User user;
	
	private String assignedTo;
	
	private Integer statusCode;
	
	private String agreementId;
	
	private String docStatus;
	
	private String comment;
	
	private Integer errorReasonCode;
	
	private Integer roleId;
	
	private List<DocWkflwProcess> docList;
	
	private boolean isSuccess;
	
	private Integer numPages;
	
	private Integer numFields;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public List<DocWkflwProcess> getDocList() {
		return docList;
	}

	public void setDocList(List<DocWkflwProcess> docList) {
		this.docList = docList;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getDocStatus() {
		return docStatus;
	}

	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getErrorReasonCode() {
		return errorReasonCode;
	}

	public void setErrorReasonCode(Integer errorReasonCode) {
		this.errorReasonCode = errorReasonCode;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
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

}
