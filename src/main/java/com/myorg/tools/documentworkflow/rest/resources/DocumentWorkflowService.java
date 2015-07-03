package com.myorg.tools.documentworkflow.rest.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.myorg.tools.documentworkflow.model.DocumentWorkflowDetail;

@Provider
@Path("/WflService")
public interface DocumentWorkflowService {
	
	   /* To populate Doc list in Workflow Landing page*/
	   @GET
	   @Path("/docs")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response getAllDocuments();
	   
	   /* To populate Doc Detail scrren for Tagging*/
	   @GET
	   @Path("/getdetail")
	   @QueryParam("docId")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response getDocumentDetail(Integer docId);
	   
	   
	   @POST
	   @Path("/submit")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response submitWorkflow(Boolean isFinalSubmit, final DocumentWorkflowDetail doc);
	   
	   @POST
	   @Path("/assigndoc")
	   @QueryParam("docs")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response assignDocuments(String userId, List<Integer> docs);

	
	
}
