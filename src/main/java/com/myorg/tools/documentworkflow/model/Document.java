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
	private String docLocation;
	private String isBadLinkReported;
	private String targetDocLocation;
	private String isDeleted;
	private String createdBy;
	private Date creationDt;
	
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
	 * @return the targetLocation
	 */
	public String getTargetDocLocation() {
		return targetDocLocation;
	}
	/**
	 * @param targetLocation the targetLocation to set
	 */
	public void setTargetDocLocation(String targetDocLocation) {
		this.targetDocLocation = targetDocLocation;
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
	
}
