package com.myorg.tools.documentworkflow.dto;

import java.io.Serializable;
import java.util.List;

import com.myorg.tools.documentworkflow.model.DocWkflwProcess;
import com.myorg.tools.documentworkflow.model.DocumentWorkflow;
import com.myorg.tools.documentworkflow.model.User;

public class DocumentDTO extends BaseDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6503083677284094709L;

	private User user;
	
	private String docStatus;
	
	private List<DocWkflwProcess> docList;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDocStatus() {
		return docStatus;
	}

	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}

	public List<DocWkflwProcess> getDocList() {
		return docList;
	}

	public void setDocList(List<DocWkflwProcess> docList) {
		this.docList = docList;
	}

	
}
