package com.myorg.tools.documentworkflow.rest.resources.impl;

import javax.ws.rs.core.Response;
<<<<<<< HEAD
import javax.ws.rs.core.Response.Status;

import com.myorg.tools.documentworkflow.dao.UserAdminDAO;
import com.myorg.tools.documentworkflow.model.DocumentWorkflowResponse;
import com.myorg.tools.documentworkflow.model.User;
import com.myorg.tools.documentworkflow.rest.resources.BaseResource;
import com.myorg.tools.documentworkflow.rest.resources.UserService;

public class UserServiceImpl extends BaseResource implements UserService   {
	
	private UserAdminDAO userAdminDAO;
	
	public UserAdminDAO getUserAdminDAO() {
		return userAdminDAO;
	}

	public void setUserAdminDAO(UserAdminDAO userAdminDAO) {
		this.userAdminDAO = userAdminDAO;
	}

	@Override
	public Response login(User user) {
		// TODO Auto-generated method stub
		System.out.println("user is " + user);
		User dto  = null;		
		try {
			dto = userAdminDAO.authenticateAndFetchDetails(user.getUserId(), user.getPassword());
			if (dto != null) {
				String customHeader = dto.getUserId()+"|"+dto.getRoleId(); //apply encryption here
				return Response.ok().entity(dto).header("x-docwrkflow-auth", customHeader).build();
			} else {
				DocumentWorkflowResponse dr = new DocumentWorkflowResponse();
				dr.setResponseCode(Status.FORBIDDEN.getStatusCode());
				dr.setResponseMessage("Login not successful.");
				return Response.status(Status.FORBIDDEN).entity(dr).build();
			}
		} catch (Exception e) {		
			String message = e.getMessage();
			System.out.println("Exception message = "+ message);
			DocumentWorkflowResponse dr = new DocumentWorkflowResponse();
			dr.setResponseCode(Status.FORBIDDEN.getStatusCode());
			dr.setResponseMessage(message);
			return Response.status(Status.FORBIDDEN).entity(dr).build();
		}
		
	}
	
	@Override
	public Response logout() {
		getRequest().getSession().invalidate();
		return Response.ok().build();
=======

import com.myorg.tools.documentworkflow.dto.UserDTO;
import com.myorg.tools.documentworkflow.model.DocumentDetail;
import com.myorg.tools.documentworkflow.model.User;
import com.myorg.tools.documentworkflow.rest.resources.UserService;

public class UserServiceImpl implements UserService   {

	@Override
	public Response login(User user) {
		// TODO Auto-generated method stub
		System.out.println("user is " + user);
		UserDTO  dto = new UserDTO();
		dto.setUserid(1);
		
		return Response.ok().entity(dto).build();
>>>>>>> branch 'master' of https://github.com/toolsrepo/docflowtool.git
	}

}
