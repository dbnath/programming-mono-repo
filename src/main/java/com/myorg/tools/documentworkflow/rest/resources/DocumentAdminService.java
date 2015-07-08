package com.myorg.tools.documentworkflow.rest.resources;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Provider
@Path("/docadmin")
public interface DocumentAdminService {

	   /* To populate Doc Types*/
	   @GET
	   @Path("/doctype")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response populateDocType();
	   
	   /* To populate Doc Repos*/
	   @GET
	   @Path("/docRepo")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response populateDocRepository();
	  
	   /* To populate Doc Tags Master List */
	   @GET
	   @Path("/doctags")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response populateDocTagsMaster();

	   /* To populate Doc SubTags Master List */
	   @GET
	   @Path("/docsubtags")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response populateDocSubTagsMaster();

	   /* To populate Doc Tags based on doc types */
	   @GET
	   @Path("/doctypetags")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response populateDocTypeTagsMapping(@QueryParam("docTypeId") Integer docTypeId);
	   
	   /* To populate Unmapped Doc Tags based on doc types */
	   @GET
	   @Path("/docunmappedtypetags")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response populateUnmappedDocTypeTags(@QueryParam("docTypeId") Integer docTypeId);

	   /* To populate Doc SubTags based on doc tags */
	   @GET
	   @Path("/doctagsubtags")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response populateDocTagSubTagMapping(@QueryParam("docTagId") Integer docTagId);
	   
	   /* To populate Unmapped Doc SubTags based on doc tags */
	   @GET
	   @Path("/docunmappedtagsubtags")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response populateUnmappedDocTagSubTags(@QueryParam("docTagId") Integer docTagId);
	   
	   @POST
	   @Path("/uploaddoc")
	   @Consumes(MediaType.MULTIPART_FORM_DATA)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response uploadDocuments(@FormDataParam("file") InputStream uploadedInputStream,  @FormDataParam("file") FormDataContentDisposition fileDetail, @FormDataParam("path") String path);
	   
	   @GET
	   @Path("/template")
	   @Produces("application/vnd.ms-excel")	   
	   public Response getTemplate();

	   /*@POST
	   @Path("/updatedoctypes")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response updateDocTypes(List<DocumentType> docTypeList);
	   
	   @POST
	   @Path("/updatedocrepos")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response updateDocRepos(List<DocumentRepository> docRepoList);
	   
	   @POST
	   @Path("/updatedoctags")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response updateDocTagsMasterList(List<DocumentTag> docTagsList);

	   @POST
	   @Path("/updatedoctags")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response updateDocSubTagsMasterList(List<DocumentSubTagValues> docSubTagsList);
	   
	   @POST
	   @Path("/updatedoctypetags")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response updateDocTypeTagMap(List<DocumentTypeTagMapping> docTypeTagRelList);
	   
	   @POST
	   @Path("/updatedoctagsubtags")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response updateDocTagSubTagMap(List<DocumentTagSubTagMapping> docTagSubTagRelList);
       */
	   /*@POST
	   @Path("/assigndoc")
	   @QueryParam("docs")
	   @Produces(MediaType.APPLICATION_JSON)*/	   
	   //public Response assignDocuments(String userId, List<Integer> docs);
}
