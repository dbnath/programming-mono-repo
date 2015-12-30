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

import com.myorg.tools.documentworkflow.dto.DocumentDTO;
import com.myorg.tools.documentworkflow.model.DocumentWorkflowProcess;

@Provider
@Path("/WflService")
public interface DocumentWorkflowService {
	
	@POST
	   @Path("/ping")
	   @Produces(MediaType.TEXT_PLAIN)	   
  	   public String ping();
	   
	
	   /* To populate Doc list in Workflow Landing page*/
	   @GET
	   @Path("/docs")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response getAllDocuments(@QueryParam("userId") String userId);
	   
	   /* To populate Doc Detail scrren for Tagging*/
	   @GET
	   @Path("/getdetail")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response getDocumentDetail(@QueryParam("docId") Integer docId);
	   
	   
	   @POST
	   @Path("/submitwf")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response submitWorkflow(DocumentWorkflowProcess docWorkflowProcess);
	   
	   /*@POST
	   @Path("/assigndoc")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response assignDocuments(List<DocumentWorkflow> docs);*/

	
	   @POST
	   @Path("/assignWorkflows")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response assignWorkflows(List<Integer> docIds);
	   
	   @POST
	   @Path("/getDocumentsForAllMakers")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response getDocumentsForAllMakers(DocumentDTO documentDTO);
	   

	   @POST
	   @Path("/getDocumentsForMaker")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response getDocumentsForMaker(DocumentDTO documentDTO);
	   

	   @POST
	   @Path("/getDocumentsForAllCheckers")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public DocumentDTO getDocumentsForAllCheckers(DocumentDTO documentDTO);

	   @POST
	   @Path("/getDocumentsForChecker")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public DocumentDTO getDocumentsForChecker(DocumentDTO documentDTO);

	   @POST
	   @Path("/getDocumentsForAllSMEs")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public DocumentDTO getDocumentsForAllSMEs(DocumentDTO documentDTO);

	   @POST
	   @Path("/getDocumentsForSME")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public DocumentDTO getDocumentsForSME(DocumentDTO documentDTO);
	   
	   @POST
	   @Path("/startProcess")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response startProcess(DocumentDTO documentDTO);
	   
	   @POST
	   @Path("/completeProcess")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response completeProcess(DocumentDTO documentDTO);
	   
	   @POST
	   @Path("/holdProcess")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response holdProcess(DocumentDTO documentDTO);

}
