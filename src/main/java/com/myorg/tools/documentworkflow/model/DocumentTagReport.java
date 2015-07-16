package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;
import java.util.Date;

public class DocumentTagReport implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1518488689498835668L;
	
	private Integer docId;
	private String docName;
	private String docTagDesc;
	private String docSubTagDesc;
	
	/**
	 * 
	 */
	public DocumentTagReport() {
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
