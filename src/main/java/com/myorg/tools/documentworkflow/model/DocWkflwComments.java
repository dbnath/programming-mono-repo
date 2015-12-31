package com.myorg.tools.documentworkflow.model;

public class DocWkflwComments {

	private static final long serialVersionUID = 1518388687498835566L;
	
	private Integer agreementId;
	private String comments;
	private Integer errorReasonCd;
	private String errorReason;
	private Integer idRole;
	
	public Integer getAgreementId() {
		return agreementId;
	}
	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public Integer getIdRole() {
		return idRole;
	}
	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}
	
	
	
}
