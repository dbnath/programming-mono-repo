package com.myorg.tools.documentworkflow.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myorg.tools.documentworkflow.dao.DocumentAdminDAO;
import com.myorg.tools.documentworkflow.dao.DocumentWorkflowDAO;
import com.myorg.tools.documentworkflow.dto.DocumentDTO;
import com.myorg.tools.documentworkflow.model.AgreementErrorType;
import com.myorg.tools.documentworkflow.model.DocWkflwProcess;
import com.myorg.tools.documentworkflow.model.DocumentWorkflowStatus;
import com.myorg.tools.documentworkflow.model.Role;
import com.myorg.tools.documentworkflow.model.User;

@Controller
@RequestMapping("/")
public class ViewController {
	
	static Logger log = Logger.getLogger(ViewController.class.getName());
	
	@Autowired
	private DocumentWorkflowDAO documentDAO;
	
	@Autowired
	private DocumentAdminDAO documentAdminDAO;
	
	public DocumentWorkflowDAO getDocumentDAO() {
		return documentDAO;
	}

	public void setDocumentDAO(DocumentWorkflowDAO documentDAO) {
		this.documentDAO = documentDAO;
	}
	
	public DocumentAdminDAO getDocumentAdminDAO() {
		return documentAdminDAO;
	}

	public void setDocumentAdminDAO(DocumentAdminDAO documentAdminDAO) {
		this.documentAdminDAO = documentAdminDAO;
	}

	@RequestMapping(value = "login")
	public ModelAndView getInitialView() {
		ModelAndView modelView = new ModelAndView("login");
		return modelView;
	}

	@RequestMapping(method = RequestMethod.POST,value = "landingHome")
	public ModelAndView getLandingHomeView(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView("landing/landing");
		return modelView;
	}
	
	
	@RequestMapping(method = RequestMethod.GET,value = "teamDocs")
	public ModelAndView getTeamDocumentList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = null;
		DocumentDTO dto = new DocumentDTO();
		User user = (User)request.getSession().getAttribute("userDetails");
		dto.setUser(user);
		Integer primaryRole = new Integer(user.getRoleId());
		try {
			if (Role.ID_ROLE_CHECKER.intValue() == primaryRole.intValue()) {
				dto = documentDAO.getDocumentsForAllCheckers();
				modelView = new ModelAndView("common/checkerTeamList");
			} else if (Role.ID_ROLE_QC.intValue() == primaryRole.intValue()) {
				dto = documentDAO.getDocumentsForAllSMEs();
				modelView = new ModelAndView("common/qcTeamList");
			}
			log.info("For role : "+primaryRole +", user="+user.getUserId()+", documentlist="+dto.getDocList());
			modelView.addObject("teamDocumentList", dto.getDocList() == null ? new ArrayList<DocWkflwProcess>() : dto.getDocList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return modelView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "myDocs")
	public ModelAndView getMyDocumentList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = null;
		DocumentDTO dto = new DocumentDTO();
		User user = (User)request.getSession().getAttribute("userDetails");
		dto.setUser(user);
		Integer primaryRole = new Integer(user.getRoleId());
		try {
			List<DocumentWorkflowStatus> workflowStatusList = documentDAO.getWorkflowStatusListByRole(primaryRole);
			DocumentWorkflowStatus blankStatus = new DocumentWorkflowStatus();
			blankStatus.setStatusCode(-1);
			blankStatus.setStatusDescription("");
			workflowStatusList.add(0, blankStatus);
			
			List<AgreementErrorType> errorList = null;
			if (Role.ID_ROLE_MAKER.intValue() == primaryRole.intValue()) {
				dto = documentDAO.getDocumentsForMaker(dto);
				modelView = new ModelAndView("common/makerList");
			} else if (Role.ID_ROLE_CHECKER.intValue() == primaryRole.intValue()) {
				dto = documentDAO.getDocumentsForChecker(dto);
				errorList = documentAdminDAO.populateErrorTypes();
				AgreementErrorType blankErrorType = new AgreementErrorType();
				blankErrorType.setErrorTypeId(-1);
				blankErrorType.setErrorTypeName("");
				errorList.add(0, blankErrorType);
				
				modelView = new ModelAndView("common/checkerList");
				modelView.addObject("errorList", errorList);
			} else if (Role.ID_ROLE_QC.intValue() == primaryRole.intValue()) {
				dto = documentDAO.getDocumentsForSME(dto);
				modelView = new ModelAndView("common/qcList");
			}
			log.info("For role : "+primaryRole +", user="+user.getUserId()+", documentlist="+dto.getDocList());
			modelView.addObject("workflowStatusList", workflowStatusList);
			modelView.addObject("myDocumentList", dto.getDocList() == null ? new ArrayList<DocWkflwProcess>() : dto.getDocList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return modelView;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "adminHome")
	public ModelAndView getAdminHomeView() {
		log.debug("Inside getAdminHomeView");
		ModelAndView modelView = new ModelAndView("admin/adminHome");
		return modelView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "adminHome")
	public ModelAndView backToAdminHomeView() {
		log.debug("Inside getAdminHomeView");
		ModelAndView modelView = new ModelAndView("admin/adminHome");
		return modelView;
	}	
	
	@RequestMapping(method = RequestMethod.GET,value = "adminUpdateAgreementView")
	public ModelAndView getAdminUpdateAgreementView() {
		ModelAndView modelView = new ModelAndView("admin/updateAgreement");
		return modelView;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "adminlandingHome")
	public ModelAndView getAdminView(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView("landing/adminlanding");
		return modelView;
	}
		
	@RequestMapping(method = RequestMethod.GET,value = "myAdminDocs")
	public ModelAndView getAdminDocumentList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = null;
		DocumentDTO dto = new DocumentDTO();
		
		Enumeration<String> params = request.getParameterNames();
		
		while(params.hasMoreElements()){
			log.debug("###### Param "+params.nextElement());
		}
		
		Integer primaryRole = Integer.valueOf(request.getParameter("u8_input"));
		log.info("###### primaryRole "+primaryRole);
		
		try {
			List<DocumentWorkflowStatus> workflowStatusList = documentDAO.getWorkflowStatusListByRole(primaryRole);
			DocumentWorkflowStatus blankStatus = new DocumentWorkflowStatus();
			blankStatus.setStatusCode(-1);
			blankStatus.setStatusDescription("");
			workflowStatusList.add(0, blankStatus);
			
			List<AgreementErrorType> errorList = null;
			dto = documentDAO.getAgreementsForAdminUsers(primaryRole);
			
			if (Role.ID_ROLE_MAKER.intValue() == primaryRole.intValue()) {
				//dto = documentDAO.getDocumentsForMaker(dto);
				modelView = new ModelAndView("common/makerList");
			} else if (Role.ID_ROLE_CHECKER.intValue() == primaryRole.intValue()) {
				//dto = documentDAO.getDocumentsForChecker(dto);
				errorList = documentAdminDAO.populateErrorTypes();
				AgreementErrorType blankErrorType = new AgreementErrorType();
				blankErrorType.setErrorTypeId(-1);
				blankErrorType.setErrorTypeName("");
				errorList.add(0, blankErrorType);
				
				modelView = new ModelAndView("common/checkerList");
				modelView.addObject("errorList", errorList);
			} else if (Role.ID_ROLE_QC.intValue() == primaryRole.intValue()) {
				//dto = documentDAO.getDocumentsForSME(dto);
				modelView = new ModelAndView("common/qcList");
			}
			log.info("For Admin View role : "+primaryRole +", documentlist="+dto.getDocList());
			modelView.addObject("workflowStatusList", workflowStatusList);
			modelView.addObject("myDocumentList", dto.getDocList() == null ? new ArrayList<DocWkflwProcess>() : dto.getDocList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return modelView;
	}
	
	
}
