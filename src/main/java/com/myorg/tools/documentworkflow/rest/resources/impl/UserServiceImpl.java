package com.myorg.tools.documentworkflow.rest.resources.impl;

import javax.ws.rs.core.Response;

import org.springframework.http.HttpHeaders;

import com.myorg.tools.documentworkflow.dto.UserDTO;
import com.myorg.tools.documentworkflow.model.User;
import com.myorg.tools.documentworkflow.rest.resources.BaseResource;
import com.myorg.tools.documentworkflow.rest.resources.UserService;

public class UserServiceImpl extends BaseResource implements UserService   {

	@Override
	public Response login(User user) {
		// TODO Auto-generated method stub
		System.out.println("user is " + user);
		User  dto = new User();
		dto.setUserId("1");
		dto.setUserName("DEBASISH");
		HttpHeaders headers = new HttpHeaders();
		return Response.ok().entity(dto).header("Set-Cookie", getAppConfig().getUserCookieHeaderName()+"="+dto.getUserName()).build();
	}

}
