package com.myorg.tools.documentworkflow.model;

import java.util.List;

public class DocumentDetail {
	
	private Integer docId;
	private String docName;
	private String docHyperlink;
	private String docType;
	private String docRepository;
	private String targetLocation;	
	private List<Tag> tags;
	private List<TagValues> existingTagValues;
	private List<TagValues> newTagValues;
	private String overrideReason;
	 
	public DocumentDetail(){
		
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
	 * @return the docType
	 */
	public String getDocType() {
		return docType;
	}

	/**
	 * @param docType the docType to set
	 */
	public void setDocType(String docType) {
		this.docType = docType;
	}

	/**
	 * @return the docRepository
	 */
	public String getDocRepository() {
		return docRepository;
	}

	/**
	 * @param docRepository the docRepository to set
	 */
	public void setDocRepository(String docRepository) {
		this.docRepository = docRepository;
	}

	/**
	 * @return the targetLocation
	 */
	public String getTargetLocation() {
		return targetLocation;
	}

	/**
	 * @param targetLocation the targetLocation to set
	 */
	public void setTargetLocation(String targetLocation) {
		this.targetLocation = targetLocation;
	}

	/**
	 * @return the tags
	 */
	public List<Tag> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * @return the existingTagValues
	 */
	public List<TagValues> getExistingTagValues() {
		return existingTagValues;
	}

	/**
	 * @param existingTagValues the existingTagValues to set
	 */
	public void setExistingTagValues(List<TagValues> existingTagValues) {
		this.existingTagValues = existingTagValues;
	}

	/**
	 * @return the newTagValues
	 */
	public List<TagValues> getNewTagValues() {
		return newTagValues;
	}

	/**
	 * @param newTagValues the newTagValues to set
	 */
	public void setNewTagValues(List<TagValues> newTagValues) {
		this.newTagValues = newTagValues;
	}

	/**
	 * @return the overrideReason
	 */
	public String getOverrideReason() {
		return overrideReason;
	}

	/**
	 * @param overrideReason the overrideReason to set
	 */
	public void setOverrideReason(String overrideReason) {
		this.overrideReason = overrideReason;
	}
	
	

}
