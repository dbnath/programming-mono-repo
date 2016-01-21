package com.myorg.tools.documentworkflow.rest.resources.impl;

import javax.servlet.http.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.myorg.tools.documentworkflow.dao.UserAdminDAO;
import com.myorg.tools.documentworkflow.model.DocumentWorkflowResponse;
import com.myorg.tools.documentworkflow.model.User;
import com.myorg.tools.documentworkflow.rest.resources.BaseResource;
import com.myorg.tools.documentworkflow.rest.resources.UserService;

public class UserServiceImpl extends BaseResource implements UserService   {
	
	static Logger log = Logger.getLogger(UserServiceImpl.class.getName());
	
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
		log.debug("user is " + user);
		User dto  = null;		
		try {
			dto = userAdminDAO.authenticateAndFetchDetails(user.getUserId(), user.getPassword());
			if (dto != null) {
				String customHeader = dto.getUserId()+"|"+dto.getRoleId(); //apply encryption here
				getRequest().getSession().setAttribute("userDetails", dto);
				return Response.ok().entity(dto).header("x-docwrkflow-auth", customHeader).build();
			} else {
				DocumentWorkflowResponse dr = new DocumentWorkflowResponse();
				dr.setResponseCode(Status.FORBIDDEN.getStatusCode());
				dr.setResponseMessage("Login not successful.");
				return Response.status(Status.FORBIDDEN).entity(dr).build();
			}
		} catch (Exception e) {		
			String message = e.getMessage();
			log.error("Exception message = "+ message,e);
			DocumentWorkflowResponse dr = new DocumentWorkflowResponse();
			dr.setResponseCode(Status.FORBIDDEN.getStatusCode());
			dr.setResponseMessage(message);
			return Response.status(Status.FORBIDDEN).entity(dr).build();
		}
		
	}
	
	@Override
	public Response logout() {
		getRequest().getSession().invalidate();
		String cookieName = "JSESSIONID";
		Cookie cookies[] = getRequest().getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				log.debug(cookies[i].getName() + "###### COOKIE " + cookies[i].getMaxAge());
				if (cookies [i].getName().equals (cookieName)){
					//cookies [i].setMaxAge(0);
					log.debug("Expiring cookie "+cookieName);
				}
			}
		}
		
		return Response.ok().build();
	}

}
