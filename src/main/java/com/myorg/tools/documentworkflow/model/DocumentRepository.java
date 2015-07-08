package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;

public class DocumentRepository implements Serializable, ReverseMappable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1518388687598835567L;
	
	private Integer docRepoId;
	private String docRepoName;
	
	/**
	 * 
	 */
	public DocumentRepository() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the docRepoId
	 */
	public Integer getDocRepoId() {
		return docRepoId;
	}
	/**
	 * @param docRepoId the docRepoId to set
	 */
	public void setDocRepoId(Integer docRepoId) {
		this.docRepoId = docRepoId;
	}
	/**
	 * @return the docRepoName
	 */
	public String getDocRepoName() {
		return docRepoName;
	}
	/**
	 * @param docRepoName the docRepoName to set
	 */
	public void setDocRepoName(String docRepoName) {
		this.docRepoName = docRepoName;
	}

	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.model.Mappable#getMapKey()
	 */
	@Override
	public String getMapKey() {
		return docRepoName;
	}

	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.model.ReverseMappable#getCode()
	 */
	@Override
	public Integer getCode() {
		return docRepoId;
	}

	
	
}
