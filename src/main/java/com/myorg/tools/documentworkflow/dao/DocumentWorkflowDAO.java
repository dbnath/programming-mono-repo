package com.myorg.tools.documentworkflow.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.myorg.tools.documentworkflow.dto.DocumentDTO;
import com.myorg.tools.documentworkflow.model.DocumentWorkflow;
import com.myorg.tools.documentworkflow.model.DocumentWorkflowDetail;

public interface DocumentWorkflowDAO {
	
	 /**
	  * 
	  * @param userId - may be null for Inbox population 
	  * @return List of Documents in a workflow for myInbox and Inbox
	  */
	 public List<DocumentWorkflow> getAllDocuments(String userId) throws SQLException, Exception;
	 
	 /**
	  * 
	  * @param docId
	  * @return DocumentDetail object corresponding to a particular document
	  */
	 public DocumentWorkflowDetail getDocumentDetail(Integer docId) throws SQLException, Exception;
	 
	 /**
	  * 
	  * @param isFinalSubmit
	  * @param docDetailObj
	  * @return true if workflow can be done successfully, false if not
	  */
	 public boolean submitWorkflow(DocumentWorkflow docObj, DocumentWorkflowDetail docDetailObj, Boolean isFinalSubmit) throws SQLException, Exception;
	 
	 /**
	  * 
	  * @param userId
	  * @param docIds
	  * @return true if assignment can be done successfully, else false
	  */
	 public List<DocumentWorkflow> fetchDocumentWorkflows(List<Integer> docIds) throws SQLException, Exception;
	 
	 /**
	  * 
	  * @param userId
	  * @param docIds
	  * @return true if assignment can be done successfully, else false
	  */
	 public boolean assignWorkflow(List<DocumentWorkflow> docIds) throws SQLException, Exception;
	 
	 public DocumentDTO getDocumentsForAllMakers(DocumentDTO documentDTO) throws SQLException, Exception;
	   
     public DocumentDTO getDocumentsForMaker(DocumentDTO documentDTO) throws SQLException, Exception;

	 public DocumentDTO getDocumentsForAllCheckers(DocumentDTO documentDTO) throws SQLException, Exception;

     public DocumentDTO getDocumentsForChecker(DocumentDTO documentDTO) throws SQLException, Exception;

	 public DocumentDTO getDocumentsForAllSMEs(DocumentDTO documentDTO) throws SQLException, Exception;

	 public DocumentDTO getDocumentsForSME(DocumentDTO documentDTO) throws SQLException, Exception;

}
