package com.myorg.tools.documentworkflow.dao.impl;

import java.util.List;

import com.myorg.tools.documentworkflow.constant.DocumentWorkflowToolConstant;
import com.myorg.tools.documentworkflow.dao.DocumentWorkflowDAO;
import com.myorg.tools.documentworkflow.model.Document;
import com.myorg.tools.documentworkflow.model.DocumentTagRelationship;
import com.myorg.tools.documentworkflow.model.DocumentWorkflow;
import com.myorg.tools.documentworkflow.model.DocumentWorkflowDetail;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class DocumentWorkflowDAOImpl extends BaseJDBCTemplate implements DocumentWorkflowDAO {
	
	/**
	  * 
	  * @param userId - may be null for Inbox population 
	  * @return List of Documents in a workflow for myInbox and Inbox
	  */
	public List<DocumentWorkflow> getAllDocuments(String userId) {
	    String SQL = DocumentWorkflowToolConstant.All_Doc_WKFL_Population_SQL;
	    if (! DocumentWorkflowToolUtility.isEmptyValue(userId)) {
	    	SQL = SQL + "where userId = ";
	    }
	    List <DocumentWorkflow> docWflProcess = this.getJdbcTemplateObject().query(SQL, new Object[]{userId}, new DocumentWorkflowMapper());
	    return docWflProcess;
	}
	 
	/**
	  * 
	  * @param docId
	  * @return DocumentDetail object corresponding to a particular document
	  */
	public DocumentWorkflowDetail getDocumentDetail(Integer docId) {
	    String SQL = DocumentWorkflowToolConstant.WKFL_DTL_DOC_SQL;
	    Document document = this.getJdbcTemplateObject().queryForObject(SQL, new Object[]{docId}, new DocumentMapper());
	    SQL = DocumentWorkflowToolConstant.WKFL_DTL_DOC_TAG_REL_SQL;
		List<DocumentTagRelationship> docTagRelationships = this.getJdbcTemplateObject().query(SQL, new Object[]{docId}, new DocumentTagRelationshipMapper());
	    SQL = DocumentWorkflowToolConstant.WKFL_DTL_DOC_TAG_OVERRIDE_SQL;
		DocumentWorkflowDetail docWflDetail = this.getJdbcTemplateObject().queryForObject(SQL, new Object[]{docId}, new DocumentWorkflowDetailMapper());
		docWflDetail.setDocument(document);
		docWflDetail.setDocTagRelationship(docTagRelationships);
		return docWflDetail;
	}
	
	private boolean isTagAssociatedWithDocument(Integer docId){
	    String SQL = DocumentWorkflowToolConstant.DOC_TAG_REL_SQL;
		List<DocumentTagRelationship> docTagRelationships = this.getJdbcTemplateObject().query(SQL, new Object[]{docId}, new DocumentTagRelationshipMapper());
		if (DocumentWorkflowToolUtility.isEmptyList(docTagRelationships)) {
			return false;
		}
		return true;
	}
	
	 /**
	  * 
	  * @param isFinalSubmit
	  * @param docDetailObj
	  * @return true if workflow can be done successfully, false if not
	  */
	 public boolean submitWorkflow(boolean isFinalSubmit, DocumentWorkflowDetail docDetailObj) {
		 Integer docId = docDetailObj.getDocId();
		 
		 return Boolean.TRUE;
	 }
	 
	 /**
	  * 
	  * @param userId
	  * @param docIds
	  * @return true if assignment can be done successfully, else false
	  */
	 public boolean assignWorkflow(String userId, List<Integer> docIds) {
		 return Boolean.TRUE;
	 }

}
