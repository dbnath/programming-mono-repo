package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;

public class Tag implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1518388687498845568L;
	
	private Integer tagId;
	private String tagDesc;
	
	public Tag(){
		
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
	 * @return the tagDesc
	 */
	public String getTagDesc() {
		return tagDesc;
	}
	/**
	 * @param tagDesc the tagDesc to set
	 */
	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}
	
	

}
