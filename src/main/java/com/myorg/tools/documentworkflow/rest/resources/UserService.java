package com.myorg.tools.documentworkflow.rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.myorg.tools.documentworkflow.model.DocumentDetail;
import com.myorg.tools.documentworkflow.model.User;
@Provider
@Path("/UserService")
public interface UserService {
	
	   
	   @POST
	   @Path("/login")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
<<<<<<< HEAD
	   public Response login(User user);
	   
	   @POST
	   @Path("/logout")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response logout();
=======
	   public Response login( User   user );
>>>>>>> branch 'master' of https://github.com/toolsrepo/docflowtool.git

}
