package com.myorg.tools.documentworkflow.rest.resources.impl;

import javax.ws.rs.core.NewCookie;
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
		dto.setUserName(user.getUserName());
		NewCookie cookie = new NewCookie(getAppConfig().getUserCookieHeaderName(), dto.getUserName());
		return Response.ok().entity(dto).cookie(cookie).build();
	}

}
