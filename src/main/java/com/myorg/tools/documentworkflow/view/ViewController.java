package com.myorg.tools.documentworkflow.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myorg.tools.documentworkflow.model.User;

@Controller
@RequestMapping("/")
public class ViewController {

	@RequestMapping(value = "login")
	public ModelAndView getInitialView() {
		ModelAndView modelView = new ModelAndView("login");
		return modelView;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "landingHome")
	public ModelAndView getMakerHomeView() {
		ModelAndView modelView = new ModelAndView("landing/landing");
		return modelView;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "checkerHome")
	public ModelAndView getLandingHomeView() {
		ModelAndView modelView = new ModelAndView("landing/landing");
		return modelView;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "checkerHome")
	public ModelAndView getCheckerHomeView() {
		ModelAndView modelView = new ModelAndView("landing/landing");
		return modelView;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "adminHome")
	public ModelAndView getAdminHomeView() {
		System.out.println("Inside getAdminHomeView");
		ModelAndView modelView = new ModelAndView("admin/adminHome");
		return modelView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "adminUpdateAgreementView")
	public ModelAndView getAdminUpdateAgreementView() {
		ModelAndView modelView = new ModelAndView("admin/updateAgreement");
		return modelView;
	}
	
	
}
