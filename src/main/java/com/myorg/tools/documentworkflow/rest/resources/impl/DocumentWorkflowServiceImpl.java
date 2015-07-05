package com.myorg.tools.documentworkflow.rest.resources.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import com.myorg.tools.documentworkflow.dao.DocumentWorkflowDAO;
import com.myorg.tools.documentworkflow.model.DocumentWorkflow;
import com.myorg.tools.documentworkflow.model.DocumentWorkflowDetail;
import com.myorg.tools.documentworkflow.rest.resources.DocumentWorkflowService;

public class DocumentWorkflowServiceImpl implements DocumentWorkflowService{
	
	   private DocumentWorkflowDAO documentDAO;
	
		public Response getAllDocuments(){
		  System.out.println("Inside getAllDocuments");
	      return Response.ok().entity(new ArrayList<DocumentWorkflow>()).build();
	   }	
	
	
	   public Response getDocumentDetail(Integer docId){
	      return Response.ok().entity(new DocumentWorkflowDetail()).build();
	   }	
	   
	   
	   public Response submitWorkflow(Boolean isFinalSubmit, final DocumentWorkflowDetail doc){
	      return Response.ok().entity(Boolean.TRUE).build();
	   }	
	   
	   public Response assignDocuments(String userId, List<Integer> docs){
		   return Response.ok().entity(Boolean.TRUE).build();
	   }

	   
	   

	/**
	 * @return the documentDAO
	 */
	public DocumentWorkflowDAO getDocumentDAO() {
		return documentDAO;
	}


	/**
	 * @param documentDAO the documentDAO to set
	 */
	public void setDocumentDAO(DocumentWorkflowDAO documentDAO) {
		this.documentDAO = documentDAO;
	}
	   
	   
	
}
