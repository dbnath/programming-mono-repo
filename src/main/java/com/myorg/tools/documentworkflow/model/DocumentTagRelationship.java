package com.myorg.tools.documentworkflow.model;

import java.util.Date;

public class DocumentTagRelationship {
	
	private Integer docId;
	private Integer tagTypeId;
	private Integer tagSubTypeId;
	private String createdBy;
	private Date creationDt;
	private String lastUpdatedBy;
	private Date lastUpdatedDt;
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
	 * @return the tagTypeId
	 */
	public Integer getTagTypeId() {
		return tagTypeId;
	}
	/**
	 * @param tagTypeId the tagTypeId to set
	 */
	public void setTagTypeId(Integer tagTypeId) {
		this.tagTypeId = tagTypeId;
	}
	/**
	 * @return the tagSubTypeId
	 */
	public Integer getTagSubTypeId() {
		return tagSubTypeId;
	}
	/**
	 * @param tagSubTypeId the tagSubTypeId to set
	 */
	public void setTagSubTypeId(Integer tagSubTypeId) {
		this.tagSubTypeId = tagSubTypeId;
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
	 * @return the lastUpdatdDt
	 */
	public Date getLastUpdatedDt() {
		return lastUpdatedDt;
	}
	/**
	 * @param lastUpdatdDt the lastUpdatdDt to set
	 */
	public void setLastUpdatedDt(Date lastUpdatedDt) {
		this.lastUpdatedDt = lastUpdatedDt;
	}

}
