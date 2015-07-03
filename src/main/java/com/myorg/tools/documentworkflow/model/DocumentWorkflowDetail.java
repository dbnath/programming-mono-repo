package com.myorg.tools.documentworkflow.model;

import java.util.Date;
import java.util.List;

public class DocumentWorkflowDetail {
	
	private Integer docId;
	private Document document;
	private List<DocumentTagRelationship> docTagRelationship;
	private String tagOverrideReason;
	private String targetDocLocation;
	private String createdBy;
	private Date creationDt;
	private String lastUpdatedBy;
	private Date lastUpdatedDt;
	
	public DocumentWorkflowDetail(){
		
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
	 * @return the document
	 */
	public Document getDocument() {
		return document;
	}
	/**
	 * @param document the document to set
	 */
	public void setDocument(Document document) {
		this.document = document;
	}

	/**
	 * @return the newTagValues
	 */
	public List<DocumentTagRelationship> getDocTagRelationship() {
		return docTagRelationship;
	}

	/**
	 * @param newTagValues the newTagValues to set
	 */
	public void setDocTagRelationship(List<DocumentTagRelationship> tagValues) {
		this.docTagRelationship = tagValues;
	}

	/**
	 * @return the tagOverrideReason
	 */
	public String getTagOverrideReason() {
		return tagOverrideReason;
	}

	/**
	 * @param tagOverrideReason the tagOverrideReason to set
	 */
	public void setTagOverrideReason(String tagOverrideReason) {
		this.tagOverrideReason = tagOverrideReason;
	}

	/**
	 * @return the targetDocLocation
	 */
	public String getTargetDocLocation() {
		return targetDocLocation;
	}

	/**
	 * @param targetDocLocation the targetDocLocation to set
	 */
	public void setTargetDocLocation(String targetDocLocation) {
		this.targetDocLocation = targetDocLocation;
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
