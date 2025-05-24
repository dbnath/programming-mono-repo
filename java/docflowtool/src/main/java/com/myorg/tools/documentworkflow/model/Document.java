package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;
import java.util.Date;

public class Document implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1518388687498835567L;

	private Integer docId;
	private String docName;
	private Integer docTypeId;
	private String docTypeDesc;
	private Integer docRepoId;
	private String docRepoDesc;
	private String docHyperlink;
	private String docLocation;
	private String isBadLinkReported;
	private String isDeleted;
	private String createdBy;
	private Date creationDt;
	private String lastUpdatedBy;
	private Date lastUpdatedDt;
	
	/**
	 * 
	 */
	public Document() {
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
	 * @return the isBadLinkReported
	 */
	public String getIsBadLinkReported() {
		return isBadLinkReported;
	}
	/**
	 * @param isBadLinkReported the isBadLinkReported to set
	 */
	public void setIsBadLinkReported(String isBadLinkReported) {
		this.isBadLinkReported = isBadLinkReported;
	}
	/**
	 * @return the isDeleted
	 */
	public String getIsDeleted() {
		return isDeleted;
	}
	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the creationDt
	 */
	public Date getCreationDt() {
		return creationDt;
	}
	/**
	 * @param creationDt the creationDt to set
	 */
	public void setCreationDt(Date creationDt) {
		this.creationDt = creationDt;
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
	 * @return the lastUpdatedDt
	 */
	public Date getLastUpdatedDt() {
		return lastUpdatedDt;
	}
	/**
	 * @param lastUpdatedDt the lastUpdatedDt to set
	 */
	public void setLastUpdatedDt(Date lastUpdatedDt) {
		this.lastUpdatedDt = lastUpdatedDt;
	}
	
}
