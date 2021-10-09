package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;

public class DocumentSubTagValues implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1518388687498835668L;
	
	private Integer docSubTagId;
	private String docSubTagDesc;
	
	/**
	 * 
	 */
	public DocumentSubTagValues() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the docSubTagId
	 */
	public Integer getDocSubTagId() {
		return docSubTagId;
	}

	/**
	 * @param docSubTagId the docSubTagId to set
	 */
	public void setDocSubTagId(Integer docSubTagId) {
		this.docSubTagId = docSubTagId;
	}

	/**
	 * @return the docSubTagDesc
	 */
	public String getDocSubTagDesc() {
		return docSubTagDesc;
	}

	/**
	 * @param docSubTagDesc the docSubTagDesc to set
	 */
	public void setDocSubTagDesc(String docSubTagDesc) {
		this.docSubTagDesc = docSubTagDesc;
	}
	
}
