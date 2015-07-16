package com.myorg.tools.documentworkflow.rest.resources.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import com.myorg.tools.documentworkflow.dao.DocumentWorkflowDAO;
import com.myorg.tools.documentworkflow.model.DocumentWorkflow;
import com.myorg.tools.documentworkflow.model.DocumentWorkflowDetail;
import com.myorg.tools.documentworkflow.model.DocumentWorkflowProcess;
import com.myorg.tools.documentworkflow.model.User;
import com.myorg.tools.documentworkflow.rest.resources.BaseResource;
import com.myorg.tools.documentworkflow.rest.resources.DocumentWorkflowService;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class DocumentWorkflowServiceImpl extends BaseResource implements DocumentWorkflowService {

	private DocumentWorkflowDAO documentDAO;
	
	/**
	 * @return the documentDAO
	 */
	public DocumentWorkflowDAO getDocumentDAO() {
		return documentDAO;
	}

	/**
	 * @param documentDAO
	 * the documentDAO to set
	 */
	public void setDocumentDAO(DocumentWorkflowDAO documentDAO) {
		this.documentDAO = documentDAO;
	}
	
	private static Map<Integer, Integer> statusGroupMap = new HashMap<Integer, Integer>();
	
	static{
		statusGroupMap.put(1, 1);
		statusGroupMap.put(2, 1);
		statusGroupMap.put(3, 2);
		statusGroupMap.put(4, 2);
		statusGroupMap.put(5, 3);
		statusGroupMap.put(6, 3);
		statusGroupMap.put(7, 1);
		statusGroupMap.put(8, 1);
	}
	
	public Response getAllDocuments(String userId) {
		try {
			System.out.println("Inside getAllDocuments userId = "+userId);
			List<DocumentWorkflow> documentList = documentDAO
					.getAllDocuments(userId);
			return Response.ok().entity(documentList).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	public Response getDocumentDetail(Integer docId) {
		try {
			System.out.println("docId" +docId);
			DocumentWorkflowDetail documentWorkflowDetail = documentDAO
					.getDocumentDetail(docId);
			return Response.ok().entity(documentWorkflowDetail).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	public Response submitWorkflow(DocumentWorkflowProcess docWorkflowProcess) {
		User user = getLoggedInUser();
		String userId = user.getUserId();
		try {
			
			if(docWorkflowProcess != null){
				Boolean isFinalSubmit = docWorkflowProcess.getIsFinalSubmit();
				DocumentWorkflow docObj = docWorkflowProcess.getDocObj();
				DocumentWorkflowDetail docDetailObj = docWorkflowProcess.getDocDetail();
				
				if(isFinalSubmit){
					docObj.setAssignedTo(null);
					docObj.setAssignedDt(null);
					
					docObj.setWfStatusId(docObj.getWfStatusId()+1);
					docObj.setWfStatusDesc(null);
				}
				docObj.setLastUpdatedBy(userId);
				docObj.setLastUpdateDt(new Date());
				
				docDetailObj.setLastUpdatedBy(userId);
				docDetailObj.setLastUpdatedDt(new Date());

				System.out.println("###### isFinalSubmit "+isFinalSubmit);
				System.out.println("###### docObj "+docObj);
				System.out.println("###### docDetailObj "+docDetailObj);
				System.out.println("###### Submitting workflow for DocID "+docObj.getDocId()+",Doc Type "+docObj.getDocTypeId()+", Status "+docObj.getWfStatusId());
				documentDAO.submitWorkflow(docObj, docDetailObj, isFinalSubmit);
				System.out.println("###### Completed workflow for DocID "+docObj.getDocId()+",Doc Type "+docObj.getDocTypeId()+", Status "+docObj.getWfStatusId());
			}
			
			return Response.ok().entity(Boolean.TRUE).build();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	/*public Response assignDocuments(List<DocumentWorkflow> docs) {
		try {
			String	userId = getLoggedInUserId();
			System.out.println("###### user id "+userId+" ###### "+docs);
			for (DocumentWorkflow doc : docs) {
				doc.setAssignedTo(userId);
				doc.setAssignedDt(new Date());
				doc.setLastUpdatedBy(userId);
				doc.setLastUpdateDt(new Date());
				doc.setWfStatusId(doc.getWfStatusId()+1);
				doc.setWfStatusDesc(null);
			}
			documentDAO.assignWorkflow(docs);
			return Response.ok().entity(Boolean.TRUE).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}*/

	public Response assignDocumentsTo(List<Integer> docIds) {
		try {
			List<DocumentWorkflow> docs = documentDAO.fetchDocumentWorkflows(docIds);
			User user = getLoggedInUser();
			String userId = user.getUserId();
			String userRoleId = user.getRoleId();
			//for (DocumentWorkflow doc : docs) {
			for (int i=0; i<docs.size(); i++) {
				DocumentWorkflow doc = docs.get(i);
				if (DocumentWorkflowToolUtility.areAllObjectsNull(doc.getAssignedTo()) && (Integer.valueOf(userRoleId)==4 || statusGroupMap.get(doc.getWfStatusId())==Integer.valueOf(userRoleId))) {
					doc.setAssignedTo(userId);
					doc.setAssignedDt(new Date());
					doc.setUserRole(userRoleId);
					doc.setWfStatusId(doc.getWfStatusId()+1);
					doc.setWfStatusDesc(null);
					doc.setLastUpdatedBy(userId);
					doc.setLastUpdateDt(new Date());
				} else {
					docs.remove(doc);
				}
			}
			documentDAO.assignWorkflow(docs);
			return Response.ok().entity(Boolean.TRUE).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
