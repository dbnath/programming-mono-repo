package com.myorg.tools.documentworkflow.rest.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseResource {
	private @Autowired HttpServletRequest request;
	private @Autowired HttpServletRequest response;

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
	

}
