package com.myorg.tools.documentworkflow.view;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myorg.tools.documentworkflow.dao.DocumentWorkflowDAO;
import com.myorg.tools.documentworkflow.dto.DocumentDTO;
import com.myorg.tools.documentworkflow.model.DocumentWorkflow;
import com.myorg.tools.documentworkflow.model.User;

@Controller
@RequestMapping("/")
public class ViewController {
	
	@Autowired
	private DocumentWorkflowDAO documentDAO;
	
	public DocumentWorkflowDAO getDocumentDAO() {
		return documentDAO;
	}

	public void setDocumentDAO(DocumentWorkflowDAO documentDAO) {
		this.documentDAO = documentDAO;
	}
	

	@RequestMapping(value = "login")
	public ModelAndView getInitialView() {
		ModelAndView modelView = new ModelAndView("login");
		return modelView;
	}

	@RequestMapping(method = RequestMethod.POST,value = "landingHome")
	public ModelAndView getLandingHomeView(HttpServletRequest request, HttpServletResponse response) {
		
		DocumentDTO dto = new DocumentDTO();
		ModelAndView modelView = new ModelAndView("landing/landing");
		
		/*User user = (User)request.getSession().getAttribute("userDetails");
		dto.setUser(user);
		try {
			dto = documentDAO.getDocumentsForMaker(dto);
			modelView.addObject("myDocumentList", dto.getDocList());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return modelView;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "makerHome")
	public ModelAndView getMakerHomeView() {
		ModelAndView modelView = new ModelAndView("landing/landing");
		return modelView;
	}
	
	
	@RequestMapping(method = RequestMethod.POST,value = "checkerHome")
	public ModelAndView getCheckerHomeView() {
		ModelAndView modelView = new ModelAndView("landing/landing");
		return modelView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "teamDocs")
	public ModelAndView getTeamDocumentList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView("common/teamlist");
		DocumentDTO dto = new DocumentDTO();
		User user = (User)request.getSession().getAttribute("userDetails");
		dto.setUser(user);
		try {
			dto = documentDAO.getDocumentsForMaker(dto);
			modelView.addObject("myDocumentList", dto.getDocList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "myDocs")
	public ModelAndView getMyDocumentList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView("common/mylist");
		DocumentDTO dto = new DocumentDTO();
		User user = (User)request.getSession().getAttribute("userDetails");
		dto.setUser(user);
		try {
			dto = documentDAO.getDocumentsForMaker(dto);
			modelView.addObject("myDocumentList", dto.getDocList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
