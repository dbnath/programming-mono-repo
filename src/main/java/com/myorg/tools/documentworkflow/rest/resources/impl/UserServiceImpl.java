package com.myorg.tools.documentworkflow.rest.resources.impl;

import javax.ws.rs.core.Response;

import com.myorg.tools.documentworkflow.dto.UserDTO;
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
	}

}
