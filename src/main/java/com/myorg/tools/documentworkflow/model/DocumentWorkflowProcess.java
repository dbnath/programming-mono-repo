package com.myorg.tools.documentworkflow.model;

public class DocumentWorkflowProcess {
	
	private Boolean isFinalSubmit; 
	private DocumentWorkflow docObj; 
	private DocumentWorkflowDetail docDetail;
	
	/**
	 * @return the isFinalSubmit
	 */
	public Boolean getIsFinalSubmit() {
		return isFinalSubmit;
	}
	/**
	 * @param isFinalSubmit the isFinalSubmit to set
	 */
	public void setIsFinalSubmit(Boolean isFinalSubmit) {
		this.isFinalSubmit = isFinalSubmit;
	}
	/**
	 * @return the docObj
	 */
	public DocumentWorkflow getDocObj() {
		return docObj;
	}
	/**
	 * @param docObj the docObj to set
	 */
	public void setDocObj(DocumentWorkflow docObj) {
		this.docObj = docObj;
	}
	/**
	 * @return the doc
	 */
	public DocumentWorkflowDetail getDocDetail() {
		return docDetail;
	}
	/**
	 * @param doc the doc to set
	 */
	public void setDocDetail(DocumentWorkflowDetail docDetail) {
		this.docDetail = docDetail;
	}
	
	

}
