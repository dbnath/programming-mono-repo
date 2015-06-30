package com.myorg.tools.documentworkflow.model;

public class TagValues {

	private Integer tagId;
	private Integer tagValueId;
	private String tagValueDesc;
	
	
	
	public TagValues() {
		
	}
	
	/**
	 * @return the tagId
	 */
	public Integer getTagId() {
		return tagId;
	}
	/**
	 * @param tagId the tagId to set
	 */
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	/**
	 * @return the tagValueId
	 */
	public Integer getTagValueId() {
		return tagValueId;
	}
	/**
	 * @param tagValueId the tagValueId to set
	 */
	public void setTagValueId(Integer tagValueId) {
		this.tagValueId = tagValueId;
	}
	/**
	 * @return the tagValueDesc
	 */
	public String getTagValueDesc() {
		return tagValueDesc;
	}
	/**
	 * @param tagValueDesc the tagValueDesc to set
	 */
	public void setTagValueDesc(String tagValueDesc) {
		this.tagValueDesc = tagValueDesc;
	}
	
	
	
}
