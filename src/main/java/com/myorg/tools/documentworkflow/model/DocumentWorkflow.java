package com.myorg.tools.documentworkflow.model;

import java.util.Date;

public class DocumentWorkflow implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1518388687498835566L;
	
	private Integer docId;
	private String docName;
	private Integer docTypeId;
	private String docTypeDesc;
	private Integer docRepoId;
	private String docRepoDesc;
	private String docHyperlink;
	private String docLocation;
	private Integer wfStatusId;
	private String wfStatusDesc;
	private String userRole;
	private Integer wfAssignmentGroupId;
	private String wfAssignmentGroupName;
	private String wfActivityDesc;
	private String isReworked;
	private String assignedTo;
	private Date assignedDt;
	private String lastUpdatedBy;
	private Date lastUpdateDt;
	/**
	 * 
	 */
	public DocumentWorkflow() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the docId
	 */
	public Integer getDocId() {
		return docId;
	}
	/**
	 * @param docId the docId to set
	 */
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	/**
	 * @return the docName
	 */
	public String getDocName() {
		return docName;
	}
	/**
	 * @param docName the docName to set
	 */
	public void setDocName(String docName) {
		this.docName = docName;
	}
	/**
	 * @return the docTypeId
	 */
	public Integer getDocTypeId() {
		return docTypeId;
	}
	/**
	 * @param docTypeId the docTypeId to set
	 */
	public void setDocTypeId(Integer docTypeId) {
		this.docTypeId = docTypeId;
	}
	/**
	 * @return the docTypeDesc
	 */
	public String getDocTypeDesc() {
		return docTypeDesc;
	}
	/**
	 * @param docTypeDesc the docTypeDesc to set
	 */
	public void setDocTypeDesc(String docTypeDesc) {
		this.docTypeDesc = docTypeDesc;
	}
	/**
	 * @return the docRepoId
	 */
	public Integer getDocRepoId() {
		return docRepoId;
	}
	/**
	 * @param docRepoId the docRepoId to set
	 */
	public void setDocRepoId(Integer docRepoId) {
		this.docRepoId = docRepoId;
	}
	/**
	 * @return the docRepoDesc
	 */
	public String getDocRepoDesc() {
		return docRepoDesc;
	}
	/**
	 * @param docRepoDesc the docRepoDesc to set
	 */
	public void setDocRepoDesc(String docRepoDesc) {
		this.docRepoDesc = docRepoDesc;
	}
	/**
	 * @return the docHyperlink
	 */
	public String getDocHyperlink() {
		return docHyperlink;
	}
	/**
	 * @param docHyperlink the docHyperlink to set
	 */
	public void setDocHyperlink(String docHyperlink) {
		this.docHyperlink = docHyperlink;
	}
	/**
	 * @return the docLocation
	 */
	public String getDocLocation() {
		return docLocation;
	}
	/**
	 * @param docLocation the docLocation to set
	 */
	public void setDocLocation(String docLocation) {
		this.docLocation = docLocation;
	}
	/**
	 * @return the wfStatusId
	 */
	public Integer getWfStatusId() {
		return wfStatusId;
	}
	/**
	 * @param wfStatusId the wfStatusId to set
	 */
	public void setWfStatusId(Integer wfStatusId) {
		this.wfStatusId = wfStatusId;
	}
	/**
	 * @return the wfStatus
	 */
	public String getWfStatusDesc() {
		return wfStatusDesc;
	}
	/**
	 * @param wfStatus the wfStatus to set
	 */
	public void setWfStatusDesc(String wfStatusDesc) {
		this.wfStatusDesc = wfStatusDesc;
	}
	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}
	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	/**
	 * @return the wfAssignmentGroupId
	 */
	public Integer getWfAssignmentGroupId() {
		return wfAssignmentGroupId;
	}
	/**
	 * @param wfAssignmentGroupId the wfAssignmentGroupId to set
	 */
	public void setWfAssignmentGroupId(Integer wfAssignmentGroupId) {
		this.wfAssignmentGroupId = wfAssignmentGroupId;
	}
	/**
	 * @return the wfAssignmentGroup
	 */
	public String getWfAssignmentGroupName() {
		return wfAssignmentGroupName;
	}
	/**
	 * @param wfAssignmentGroup the wfAssignmentGroup to set
	 */
	public void setWfAssignmentGroupName(String wfAssignmentGroupName) {
		this.wfAssignmentGroupName = wfAssignmentGroupName;
	}
	/**
	 * @return the wfActivityDesc
	 */
	public String getWfActivityDesc() {
		return wfActivityDesc;
	}
	/**
	 * @param wfActivityDesc the wfActivityDesc to set
	 */
	public void setWfActivityDesc(String wfActivityDesc) {
		this.wfActivityDesc = wfActivityDesc;
	}
	/**
	 * @return the isReworked
	 */
	public String getIsReworked() {
		return isReworked;
	}
	/**
	 * @param isReworked the isReworked to set
	 */
	public void setIsReworked(String isReworked) {
		this.isReworked = isReworked;
	}
	/**
	 * @return the assignedTo
	 */
	public String getAssignedTo() {
		return assignedTo;
	}
	/**
	 * @param assignedTo the assignedTo to set
	 */
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	/**
	 * @return the assignedDt
	 */
	public Date getAssignedDt() {
		return assignedDt;
	}
	/**
	 * @param assignedDt the assignedDt to set
	 */
	public void setAssignedDt(Date assignedDt) {
		this.assignedDt = assignedDt;
	}
	/**
	 * @return the lastUpdatedBy
	 */
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	/**
	 * @param lastUpdatedBy the lastUpdatedBy to set
	 */
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	/**
	 * @return the lastUpdateDt
	 */
	public Date getLastUpdateDt() {
		return lastUpdateDt;
	}
	/**
	 * @param lastUpdateDt the lastUpdateDt to set
	 */
	public void setLastUpdateDt(Date lastUpdateDt) {
		this.lastUpdateDt = lastUpdateDt;
	}

}
