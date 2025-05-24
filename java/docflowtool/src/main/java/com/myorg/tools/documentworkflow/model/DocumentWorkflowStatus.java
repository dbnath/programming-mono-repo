package com.myorg.tools.documentworkflow.model;


public class DocumentWorkflowStatus implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1059223083811463508L;

	private Integer statusCode;
	
	private String statusDescription;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	
	
}
