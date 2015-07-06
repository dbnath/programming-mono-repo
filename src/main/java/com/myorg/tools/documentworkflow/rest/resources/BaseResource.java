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
		String loggedInUser = null;
		Cookie[] cookies = request.getCookies();
		
		System.out.println("cookies="+cookies);
		loggedInUser = request.getHeader("Cookie");
	    if (loggedInUser != null) {
	        System.out.println("Using from cookie user sid:" + loggedInUser);
	    } else {
	    	loggedInUser = "DEBASISH";
	    	 System.out.println("Using hardcoded sid:" + loggedInUser);
	    }
	    return loggedInUser;
	}

}
