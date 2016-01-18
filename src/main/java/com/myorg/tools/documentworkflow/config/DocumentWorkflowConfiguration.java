package com.myorg.tools.documentworkflow.config;

import java.io.File;

import org.apache.log4j.Logger;

public class DocumentWorkflowConfiguration { 
	private String userCookieHeaderName;
	private String tempFileLocation;
	
	static Logger log = Logger.getLogger(DocumentWorkflowConfiguration.class.getName());
	
	public void init() {
		
		try {
			if(tempFileLocation != null){
				File file = new File(tempFileLocation);
				
				if(!file.exists()){
					file.mkdirs();
				}
				
				if(file.isDirectory()){
					File[] files = file.listFiles();
					
					for(File f : files){
						if(f != null){
							f.delete();
						}
					}
				}
			}
			log.info("###### Application Document Workflow INITIALIZED SUCCESSFULLY. ");
		} catch (Exception e) {
			log.error("Error occurred during temp file clean up. This needs to be looked at", e);
		}
	}
	
	public String getUserCookieHeaderName() {
		return userCookieHeaderName;
	}
	public void setUserCookieHeaderName(String userCookieHeaderName) {
		this.userCookieHeaderName = userCookieHeaderName;
	}
	public String getTempFileLocation() {
		return tempFileLocation;
	}
	public void setTempFileLocation(String tempFileLocation) {
		this.tempFileLocation = tempFileLocation;
	}
}
