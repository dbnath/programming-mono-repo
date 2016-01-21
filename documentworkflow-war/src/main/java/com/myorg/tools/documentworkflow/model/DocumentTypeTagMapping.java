package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;
import java.util.List;

public class DocumentTypeTagMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1519388688598835567L;
	
	private Integer docTypeId;
	private List<DocumentTag> docTags;
	
	/**
	 * 
	 */
	public DocumentTypeTagMapping() {
		// TODO Auto-generated constructor stub
	}
	
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
	 * @return the docTags
	 */
	public List<DocumentTag> getDocTags() {
		return docTags;
	}

	/**
	 * @param docTags the docTags to set
	 */
	public void setDocTags(List<DocumentTag> docTags) {
		this.docTags = docTags;
	}
	
}
