package com.myorg.tools.documentworkflow.rest.resources.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import com.myorg.tools.documentworkflow.dao.impl.DocumentDAOImpl;
import com.myorg.tools.documentworkflow.model.Document;
import com.myorg.tools.documentworkflow.model.DocumentDetail;
import com.myorg.tools.documentworkflow.rest.resources.DocumentWorkflowService;

public class DocumentWorkflowServiceImpl implements DocumentWorkflowService{
	
	   private DocumentDAOImpl documentDAO;
	
		public Response getAllDocuments(){
		  System.out.println("=Inside getAllDocuments");
	      return Response.ok().entity(new ArrayList<Document>()).build();
	   }	
	
	
	   public Response getDocumentDetail(Integer docId){
	      return Response.ok().entity(new DocumentDetail()).build();
	   }	
	   
	   
	   public Response submitWorkflow(final DocumentDetail doc){
	      return Response.ok().entity(Boolean.TRUE).build();
	   }	
	   
	   public Response workflowInterimSave(final DocumentDetail doc){
		   return Response.ok().entity(Boolean.TRUE).build();
	   }
	   
	   public Response assignDocuments(List<Integer> docs){
		   return Response.ok().entity(Boolean.TRUE).build();
	   }


	/**
	 * @return the documentDAO
	 */
	public DocumentDAOImpl getDocumentDAO() {
		return documentDAO;
	}


	/**
	 * @param documentDAO the documentDAO to set
	 */
	public void setDocumentDAO(DocumentDAOImpl documentDAO) {
		this.documentDAO = documentDAO;
	}
	   
	   
	
}
