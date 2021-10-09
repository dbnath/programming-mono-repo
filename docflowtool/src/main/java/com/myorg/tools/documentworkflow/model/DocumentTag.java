package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;

public class DocumentTag implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1518388687498845568L;
	
	private Integer docTagId;
	private String docTagDesc;
	
	public DocumentTag(){
		
	}

	/**
	 * @return the docTagId
	 */
	public Integer getDocTagId() {
		return docTagId;
	}

	/**
	 * @param docTagId the docTagId to set
	 */
	public void setDocTagId(Integer docTagId) {
		this.docTagId = docTagId;
	}

	/**
	 * @return the docTagDesc
	 */
	public String getDocTagDesc() {
		return docTagDesc;
	}

	/**
	 * @param docTagDesc the docTagDesc to set
	 */
	public void setDocTagDesc(String docTagDesc) {
		this.docTagDesc = docTagDesc;
	}
	
}
