package com.myorg.tools.documentworkflow.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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
	public List<DocumentWorkflow> getAllDocuments(String userId) throws SQLException, Exception {
		List <DocumentWorkflow> docWflProcess = null;
	    String SQL = DocumentWorkflowToolConstant.All_Doc_WKFL_Population_SQL;
	    if (! DocumentWorkflowToolUtility.isEmptyValue(userId)) {
	    	SQL = SQL.concat(" and a.ASSIGNED_TO = ?");
		    docWflProcess = this.getJdbcTemplateObject().query(SQL, new Object[]{userId}, new DocumentWorkflowMapper()); //new Object[]{userId}
	    } else {
		    docWflProcess = this.getJdbcTemplateObject().query(SQL, new DocumentWorkflowMapper());
	    }
	    return docWflProcess;
	}
	
	private Document getDocumentMetaInformation(Integer docId) throws SQLException, Exception {
	    String SQL = DocumentWorkflowToolConstant.WKFL_DTL_DOC_SQL;
	    Document document = null;
	    try{
	    	document = this.getJdbcTemplateObject().queryForObject(SQL, new Object[]{docId}, new DocumentMapper());
	    } catch(EmptyResultDataAccessException e){
	    	document = null;
	    }
		return document;
	}
	
	private List<DocumentTagRelationship> getDocTagRelationshipList(Integer docId) throws SQLException, Exception {
	    String SQL = DocumentWorkflowToolConstant.WKFL_DTL_DOC_TAG_REL_SQL;
	    List<DocumentTagRelationship> docTagRelationships = null;
	    try{
	    	docTagRelationships = this.getJdbcTemplateObject().query(SQL, new Object[]{docId}, new DocumentTagRelationshipMapper());
	    } catch(EmptyResultDataAccessException e) {
	    	docTagRelationships = null;
	    }
	    return docTagRelationships;
	}
	 
	/**
	  * 
	  * @param docId
	  * @return DocumentDetail object corresponding to a particular document
	  */
	public DocumentWorkflowDetail getDocumentDetail(Integer docId) throws SQLException, Exception {
		DocumentWorkflowDetail docWflDetail = null;
	    Document document = getDocumentMetaInformation(docId);
	    List<DocumentTagRelationship> docTagRelationships = getDocTagRelationshipList(docId);
	    String SQL = DocumentWorkflowToolConstant.WKFL_DTL_DOC_TAG_OVERRIDE_SQL;
		try{
			this.getJdbcTemplateObject().queryForObject(SQL, new Object[]{docId}, new DocumentWorkflowDetailMapper());
		} catch(EmptyResultDataAccessException e){
			docWflDetail = new DocumentWorkflowDetail();
			if (! DocumentWorkflowToolUtility.isEmpty(document)) {
				docWflDetail.setDocId(document.getDocId());
			} else {
				docWflDetail.setDocId(null);
			}
			docWflDetail.setTagOverrideReason(null);
			docWflDetail.setTargetDocLocation(null);
		}
		docWflDetail.setDocument(document);
		docWflDetail.setDocTagRelationship(docTagRelationships);
		return docWflDetail;
	}
	
	private boolean isTagAssociatedWithDocument(Integer docId) throws Exception {
		List<DocumentTagRelationship> docTagRelationships = getDocTagRelationshipList(docId);
		if (DocumentWorkflowToolUtility.isEmptyList(docTagRelationships)) {
			return false;
		}
		return true;
	}
	
	 /**
	  * 
	  * @param docObj
	  * @param docDetailObj
	  * @return true if workflow can be done successfully, false if not
	  */
	 public boolean submitWorkflow(DocumentWorkflow docObj, DocumentWorkflowDetail docDetailObj) throws Exception{
		 Integer docId = docObj.getDocId();
		 JdbcTemplate jdbcTemplate = this.getJdbcTemplateObject();
		 
		 if(! DocumentWorkflowToolUtility.isEmpty(docId)){
			TransactionDefinition def = new DefaultTransactionDefinition();
			TransactionStatus status = this.getTransactionManager().getTransaction(def);
			 try {
				 submitDocumentTagRelationship(jdbcTemplate, docId, docDetailObj.getDocTagRelationship(), status);
				 submitDocument(jdbcTemplate, docDetailObj.getDocument(), status);
				 submitDocWorkflowDetail(jdbcTemplate, docDetailObj, status);
				 submitWorkflowProcess(jdbcTemplate, docObj, status);
				 this.getTransactionManager().commit(status);
				 return Boolean.TRUE;
			} catch (SQLException e) {
				e.printStackTrace();
				this.getTransactionManager().rollback(status);
				return Boolean.FALSE;
			}
		 }
		 return Boolean.FALSE;
	 }
	 
	 private void submitDocumentTagRelationship(JdbcTemplate jdbcTemplate, Integer docId, List<DocumentTagRelationship> docTagList, TransactionStatus status) throws SQLException, Exception {
		 String DEL_SQL = DocumentWorkflowToolConstant.DEL_DOC_TAG_REL_SQL;
		 String INS_SQL = DocumentWorkflowToolConstant.INS_DOC_TAG_REL_SQL;
		 String INS_AUDIT_SQL = DocumentWorkflowToolConstant.INS_DOC_TAG_REL_AUDIT_SQL;
		 String SEL_VER_AUDIT_SQL = DocumentWorkflowToolConstant.SEL_VER_DOC_TAG_REL_AUDIT_SQL;
		 if (isTagAssociatedWithDocument(docId)) {
			 jdbcTemplate.update(DEL_SQL, docId);
		 }
		 Integer versionId = jdbcTemplate.queryForObject(SEL_VER_AUDIT_SQL, Integer.class, docId);
		 if (! DocumentWorkflowToolUtility.isEmptyList(docTagList)) {
			 for (DocumentTagRelationship docTag : docTagList) {
				 jdbcTemplate.update(INS_SQL, docTag.getDocId(), docTag.getDocTypeId(), docTag.getDocTagId(), docTag.getDocSubTagId(), docTag.getCreatedBy(), docTag.getCreationDt(), docTag.getLastUpdatedBy(), docTag.getLastUpdatedDt());
				 jdbcTemplate.update(INS_AUDIT_SQL, docTag.getDocId(), versionId+1, docTag.getDocTypeId(), docTag.getDocTagId(), docTag.getDocSubTagId(), docTag.getCreatedBy(), docTag.getCreationDt(), docTag.getLastUpdatedBy(), docTag.getLastUpdatedDt());
			 }
		 }
	 }
	 
	 private void submitDocument(JdbcTemplate jdbcTemplate, Document document, TransactionStatus status) throws SQLException, Exception {
		 String UPD_SQL = DocumentWorkflowToolConstant.UPD_DOCUMENT_BAD_LINK_SQL;
		 jdbcTemplate.update(UPD_SQL, document.getIsBadLinkReported(), document.getDocId());
		 String SEL_VER_AUDIT_SQL = DocumentWorkflowToolConstant.SEL_VER_DOCUMENT_AUDIT_SQL;
		 Integer versionId = jdbcTemplate.queryForObject(SEL_VER_AUDIT_SQL, Integer.class, document.getDocId());
		 if(versionId == null){
			 versionId = 0;
		 }
		 String INS_AUDIT_SQL = DocumentWorkflowToolConstant.INS_DOCUMENT_AUDIT_SQL;
		 jdbcTemplate.update(INS_AUDIT_SQL, document.getDocId(), versionId+1, document.getDocName(), document.getDocTypeId(), document.getDocRepoId(), document.getDocHyperlink(), document.getDocLocation(), document.getIsDeleted(), document.getIsBadLinkReported(), document.getCreatedBy(), document.getCreationDt(), document.getLastUpdatedBy(), document.getLastUpdatedDt());
	 }
	 
	 private void submitDocWorkflowDetail(JdbcTemplate jdbcTemplate, DocumentWorkflowDetail docDetailObj, TransactionStatus status) throws SQLException, Exception {
		 String DEL_SQL = DocumentWorkflowToolConstant.DEL_DOC_WFL_DTL_SQL;
		 jdbcTemplate.update(DEL_SQL, docDetailObj.getDocId());
		 String INS_SQL = DocumentWorkflowToolConstant.INS_DOC_WFL_DTL_SQL;
		 jdbcTemplate.update(INS_SQL, docDetailObj.getDocId(), docDetailObj.getTagOverrideReason(), docDetailObj.getTargetDocLocation(), docDetailObj.getLastUpdatedBy(), docDetailObj.getLastUpdatedDt());
		 String SEL_VER_AUDIT_SQL = DocumentWorkflowToolConstant.SEL_VER_DOC_WFL_DTL_AUDIT_SQL;
		 Integer versionId = jdbcTemplate.queryForObject(SEL_VER_AUDIT_SQL, Integer.class, docDetailObj.getDocId());
		 if(versionId == null){
			 versionId = 0;
		 }
		 String INS_AUDIT_SQL = DocumentWorkflowToolConstant.INS_DOC_WFL_DTL_AUDIT_SQL;
		 jdbcTemplate.update(INS_AUDIT_SQL, docDetailObj.getDocId(), versionId+1, docDetailObj.getTagOverrideReason(), docDetailObj.getTargetDocLocation(), docDetailObj.getLastUpdatedBy(), docDetailObj.getLastUpdatedDt());
	 }
	 
	 private void submitWorkflowProcess(JdbcTemplate jdbcTemplate, DocumentWorkflow docObj, TransactionStatus status) throws SQLException, Exception {
		 String DEL_SQL = DocumentWorkflowToolConstant.DEL_DOC_WFL_PROCESS_SQL;
		 jdbcTemplate.update(DEL_SQL, docObj.getDocId());
		 String INS_SQL = DocumentWorkflowToolConstant.INS_DOC_WFL_PROCESS_SQL;
		 jdbcTemplate.update(INS_SQL, docObj.getDocId(), docObj.getWfStatusId(), docObj.getIsReworked(), docObj.getAssignedTo(), docObj.getAssignedDt(), docObj.getUserRole(), docObj.getLastUpdatedBy(), docObj.getLastUpdateDt());
		 String SEL_VER_AUDIT_SQL = DocumentWorkflowToolConstant.SEL_VER_DOC_WFL_PROCESS_AUDIT_SQL;
		 Integer versionId = jdbcTemplate.queryForObject(SEL_VER_AUDIT_SQL, Integer.class, docObj.getDocId());
		 if(versionId == null){
			 versionId = 0;
		 }
		 String INS_AUDIT_SQL = DocumentWorkflowToolConstant.INS_DOC_WFL_PROCESS_AUDIT_SQL;
		 jdbcTemplate.update(INS_AUDIT_SQL, docObj.getDocId(), versionId+1, docObj.getWfStatusId(), docObj.getIsReworked(), docObj.getAssignedTo(), docObj.getAssignedDt(), docObj.getUserRole(), docObj.getLastUpdatedBy(), docObj.getLastUpdateDt());
	 }
	 
	 /**
	  * 
	  * @param userId
	  * @param docIds
	  * @return true if assignment can be done successfully, else false
	  */
	public boolean assignWorkflow(List<DocumentWorkflow> docIds)	throws Exception {

		JdbcTemplate jdbcTemplate = this.getJdbcTemplateObject();
		System.out.println("###### getTransactionManager "+this.getTransactionManager());
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = this.getTransactionManager().getTransaction(def);

		try {
			for (DocumentWorkflow doc : docIds) {
				submitWorkflowProcess(jdbcTemplate, doc, status);
			}
			this.getTransactionManager().commit(status);
			return Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			this.getTransactionManager().rollback(status);
			return Boolean.FALSE;
		}

	}

}
