package com.myorg.tools.documentworkflow.rest.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.myorg.tools.documentworkflow.config.DocumentWorkflowConfiguration;

public abstract class BaseResource {
	private @Autowired HttpServletRequest request;
	private @Autowired HttpServletRequest response;
	private DocumentWorkflowConfiguration config;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getResponse() {
		return response;
	}

	public void setResponse(HttpServletRequest response) {
		this.response = response;
	}
	
	public DocumentWorkflowConfiguration getConfig() {
		return config;
	}

	public void setConfig(DocumentWorkflowConfiguration config) {
		this.config = config;
	}

	protected String getLoggedInUserId() {
		String loggedInUser = null;
		loggedInUser = request.getHeader(config.getUserCookieHeaderName());
	    if (loggedInUser != null) {
	        System.out.println("Using from cookie user sid:" + loggedInUser);
	    } else {
	    	loggedInUser = "DEBASISH";
	    	 System.out.println("Using hardcoded sid:" + loggedInUser);
	    }
	    return loggedInUser;
	}

}
