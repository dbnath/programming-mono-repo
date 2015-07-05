package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;
import java.util.List;

public class DocumentTagSubTagMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1518488687498835668L;
	
	private Integer docTagId;
	private List<DocumentSubTagValues> docSubTags;
	
	/**
	 * 
	 */
	public DocumentTagSubTagMapping() {
		// TODO Auto-generated constructor stub
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
	 * @return the docSubTags
	 */
	public List<DocumentSubTagValues> getDocSubTags() {
		return docSubTags;
	}

	/**
	 * @param docSubTags the docSubTags to set
	 */
	public void setDocSubTags(List<DocumentSubTagValues> docSubTags) {
		this.docSubTags = docSubTags;
	}
	
}
