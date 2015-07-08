package com.myorg.tools.documentworkflow.rest.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.myorg.tools.documentworkflow.config.DocumentWorkflowConfiguration;
import com.myorg.tools.documentworkflow.model.User;

public abstract class BaseResource {
	private @Autowired HttpServletRequest request;
	private DocumentWorkflowConfiguration appConfig;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public DocumentWorkflowConfiguration getAppConfig() {
		return appConfig;
	}

	public void setAppConfig(DocumentWorkflowConfiguration appConfig) {
		this.appConfig = appConfig;
	}

	protected User getLoggedInUser() {
		String customHeader = request.getHeader("X-DOCWRKFLOW-AUTH");
		System.out.println("Custom Header="+customHeader);
		String loggedInUserId = null;
		String loggedInUser = null;
		String selectedRoleId = null;
		User user = null;
	    if (customHeader != null) {
	    	String[] headerSplits = customHeader.split("\\|");
	    	for (int i = 0; i < headerSplits.length; i++) {
				System.out.println(i+":"+headerSplits[i]);
			}
	    	loggedInUserId = headerSplits[0];
	    	loggedInUser = headerSplits[1];
	    	selectedRoleId = headerSplits[2];
	        System.out.println("Using from header user sid:" + loggedInUser);
	    } else {
	    	loggedInUserId = "1";
	    	loggedInUser = "DEBASISH";
	    	selectedRoleId = "1";
	    	System.out.println("Using hardcoded sid:" + loggedInUser);
	    }
	    return new User(loggedInUserId, loggedInUser, selectedRoleId);
	}

}
