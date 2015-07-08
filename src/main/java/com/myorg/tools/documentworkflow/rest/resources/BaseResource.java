package com.myorg.tools.documentworkflow.rest.resources;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.myorg.tools.documentworkflow.config.DocumentWorkflowConfiguration;

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

	protected String getLoggedInUserId() {
		String customHeader = request.getHeader("X-DOCWRKFLOW-AUTH");
		System.out.println("Custom Header="+customHeader);
		String loggedInUser = null;
	    if (customHeader != null) {
	    	loggedInUser = customHeader.split("|")[1];
	        System.out.println("Using from header user sid:" + loggedInUser);
	    } else {
	    	loggedInUser = "PRATIK";
	    	 System.out.println("Using hardcoded sid:" + loggedInUser);
	    }
	    loggedInUser = "PRATIK";
	    return loggedInUser;
	}

}
