package com.myorg.tools.documentworkflow.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Path("/report")
public interface DocumentReportService {
	
	   @GET
	   @Path("/completion")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response getCompletionDocumentReport();
	
	

}
