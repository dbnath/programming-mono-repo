package com.myorg.tools.documentworkflow.rest.resources;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.myorg.tools.documentworkflow.config.DocumentWorkflowConfiguration;
import com.myorg.tools.documentworkflow.model.User;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public abstract class BaseResource {
	
	static Logger log = Logger.getLogger(BaseResource.class.getName());
	
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
		String customHeader = request.getHeader("x-docwrkflow-auth");
		log.info("Custom Header="+customHeader);
		String loggedInUserId = null;
		String loggedInUserName = null;
		String selectedRoleId = null;
	    if (customHeader != null) {
	    	String[] headerSplits = customHeader.split("\\|");
	    	for (int i = 0; i < headerSplits.length; i++) {
	    		log.info(i+":"+headerSplits[i]);
			}
	    	loggedInUserId = headerSplits[0];
	    	selectedRoleId = headerSplits[1];
	    	loggedInUserName = null;
	    	log.info("Using from header user sid:" + loggedInUserId);
	    } else {
	    	loggedInUserId = "1";
	    	selectedRoleId = "1";
	    	loggedInUserName = "DEBASISH";
	    	log.info("Using hardcoded sid:" + loggedInUserId);
	    }
	    return new User(loggedInUserId, loggedInUserName, selectedRoleId);
	}
	
	protected boolean validateUserSession(){
		boolean isValidSession = false;
		
		User dto  = (User)request.getSession().getAttribute("userDetails");
		
		log.debug("Validate Session "+dto);
		if(!DocumentWorkflowToolUtility.isEmpty(dto) && !DocumentWorkflowToolUtility.isEmpty(dto.getUserId())){
			isValidSession = true;
		}
		
		return isValidSession;
	}

}
