package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;
import java.util.List;

public class DocumentTypeTagSubTagsMap implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1518488687498735668L;
	
	private Integer docTypeId;
	private List<DocumentTagSubTagMapping> docTagSubTagMap;
	/**
	 * 
	 */
	public DocumentTypeTagSubTagsMap() {}
	/**
	 * @return the docTypeId
	 */
	public Integer getDocTypeId() {
		return docTypeId;
	}
	/**
	 * @param docTypeId the docTypeId to set
	 */
	public void setDocTypeId(Integer docTypeId) {
		this.docTypeId = docTypeId;
	}
	/**
	 * @return the docTagSubTagMap
	 */
	public List<DocumentTagSubTagMapping> getDocTagSubTagMap() {
		return docTagSubTagMap;
	}
	/**
	 * @param docTagSubTagMap the docTagSubTagMap to set
	 */
	public void setDocTagSubTagMap(List<DocumentTagSubTagMapping> docTagSubTagMap) {
		this.docTagSubTagMap = docTagSubTagMap;
	}
	
}
