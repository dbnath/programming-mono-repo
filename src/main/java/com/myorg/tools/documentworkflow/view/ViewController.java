package com.myorg.tools.documentworkflow.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ViewController {

	@RequestMapping(value = "login")
	public ModelAndView getInitialView() {
		ModelAndView modelView = new ModelAndView("login");
		modelView.addObject("testspringmvc", "tested");
		return modelView;
	}
}
