package com.myorg.tools.documentworkflow.rest.resources.impl;

import javax.ws.rs.core.Response;

import com.myorg.tools.documentworkflow.model.User;
import com.myorg.tools.documentworkflow.rest.resources.BaseResource;
import com.myorg.tools.documentworkflow.rest.resources.UserService;

public class UserServiceImpl extends BaseResource implements UserService   {

	@Override
	public Response login(User user) {
		// TODO Auto-generated method stub
		System.out.println("user is " + user);
		User  dto = new User();
		dto.setUserId(user.getUserId());
		dto.setPassword(user.getPassword());
		dto.setRoleId(user.getRoleId());
		String customHeader = dto.getUserId()+"|"+dto.getRoleId(); //apply encryption here
		return Response.ok().entity(dto).header("x-docwrkflow-auth", customHeader).build();
	}

}
