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
	
	@RequestMapping(headers = {"content-type=application/json"},method = RequestMethod.POST,value = "landingHome")
	public ModelAndView getMakerHomeView(@RequestBody User user) {
		ModelAndView modelView = new ModelAndView("landing/landing");
		return modelView;
	}
	
	@RequestMapping(headers = {"content-type=application/json"},method = RequestMethod.POST,value = "checkerHome")
	public ModelAndView getLandingHomeView(@RequestBody User user) {
		ModelAndView modelView = new ModelAndView("landing/landing");
		return modelView;
	}
	
	@RequestMapping(headers = {"content-type=application/json"},method = RequestMethod.POST,value = "checkerHome")
	public ModelAndView getCheckerHomeView(@RequestBody User user) {
		ModelAndView modelView = new ModelAndView("landing/landing");
		return modelView;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "adminHome")
	public ModelAndView getAdminHomeView() {
		System.out.println("Inside getAdminHomeView");
		ModelAndView modelView = new ModelAndView("admin/adminHome");
		return modelView;
	}
	
	@RequestMapping(headers = {"content-type=application/json"},method = RequestMethod.POST,value = "adminUpdateAgreementView")
	public ModelAndView getAdminUpdateAgreementView(@RequestBody User user) {
		ModelAndView modelView = new ModelAndView("admin/updateAgreement");
		return modelView;
	}
	
	
}
