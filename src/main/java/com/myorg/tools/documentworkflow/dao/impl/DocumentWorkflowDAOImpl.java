package com.myorg.tools.documentworkflow.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.myorg.tools.documentworkflow.constant.DocumentWorkflowToolConstant;
import com.myorg.tools.documentworkflow.dao.DocumentWorkflowDAO;
import com.myorg.tools.documentworkflow.dto.DocumentDTO;
import com.myorg.tools.documentworkflow.mapper.CheckerAgreementWorkflowMapper;
import com.myorg.tools.documentworkflow.mapper.DocumentMapper;
import com.myorg.tools.documentworkflow.mapper.DocumentTagRelationshipMapper;
import com.myorg.tools.documentworkflow.mapper.DocumentWorkflowDetailMapper;
import com.myorg.tools.documentworkflow.mapper.DocumentWorkflowMapper;
import com.myorg.tools.documentworkflow.mapper.DocumentWorkflowStatusMapper;
import com.myorg.tools.documentworkflow.mapper.MakerAgreementWkflwMapper;
import com.myorg.tools.documentworkflow.mapper.OnshoreSMEAgreementWorkflowMapper;
/*import com.myorg.tools.documentworkflow.mapper.CheckerAgreementWorkflowMapper;
import com.myorg.tools.documentworkflow.mapper.MakerAgreementWkflwMapper;
import com.myorg.tools.documentworkflow.mapper.OnshoreSMEAgreementWorkflowMapper;*/
import com.myorg.tools.documentworkflow.model.DocWkflwProcess;
import com.myorg.tools.documentworkflow.model.Document;
import com.myorg.tools.documentworkflow.model.DocumentTagRelationship;
import com.myorg.tools.documentworkflow.model.DocumentWorkflow;
import com.myorg.tools.documentworkflow.model.DocumentWorkflowDetail;
import com.myorg.tools.documentworkflow.model.DocumentWorkflowStatus;
import com.myorg.tools.documentworkflow.model.User;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class DocumentWorkflowDAOImpl extends BaseJDBCTemplate implements DocumentWorkflowDAO {
	
	static Logger log = Logger.getLogger(DocumentWorkflowDAOImpl.class.getName());
	
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
			docWflDetail = this.getJdbcTemplateObject().queryForObject(SQL, new Object[]{docId}, new DocumentWorkflowDetailMapper());
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
	
	/*private boolean isTagAssociatedWithDocument(Integer docId) throws Exception {
		List<DocumentTagRelationship> docTagRelationships = getDocTagRelationshipList(docId);
		if (DocumentWorkflowToolUtility.isEmptyList(docTagRelationships)) {
			return false;
		}
		return true;
	}*/
	
	 /**
	  * 
	  * @param docObj
	  * @param docDetailObj
	  * @return true if workflow can be done successfully, false if not
	  */
	 public boolean submitWorkflow(DocumentWorkflow docObj, DocumentWorkflowDetail docDetailObj, Boolean isFinalSubmit) throws Exception{
		 Integer docId = docObj.getDocId();
		 JdbcTemplate jdbcTemplate = this.getJdbcTemplateObject();
		 //DocWorkflowSubmitStatus wfstatus = null;
		 Boolean isSubmitSuccess = false;
		 
		 if(! DocumentWorkflowToolUtility.isEmpty(docId)){
			TransactionDefinition def = new DefaultTransactionDefinition();
			TransactionStatus status = this.getTransactionManager().getTransaction(def);
			 try {
				 submitDocumentTagRelationship(jdbcTemplate, docId, docDetailObj.getDocTagRelationship(), status, isFinalSubmit);
				 if (! DocumentWorkflowToolUtility.isEmpty(docDetailObj.getDocument())) {
					 submitDocument(jdbcTemplate, docDetailObj.getDocument(), status, isFinalSubmit);
				 }
				 if (! DocumentWorkflowToolUtility.isEmpty(docDetailObj.getTagOverrideReason()) || ! DocumentWorkflowToolUtility.isEmpty(docDetailObj.getTargetDocLocation())) {
					 submitDocWorkflowDetail(jdbcTemplate, docDetailObj, status, isFinalSubmit);
			 	 }
				 //assignWorkflowProcess(jdbcTemplate, docObj, status, isFinalSubmit);
				 this.getTransactionManager().commit(status);
				 isSubmitSuccess = true;
			} catch (SQLException e) {
				log.error(e.getMessage(),e);
				this.getTransactionManager().rollback(status);
			} /*finally {
				if (isSubmitSuccess) {
					wfstatus = fetchModifiedDocWorkflowProcess(docId);
					wfstatus.setStatusCode("SUCCESS");
				} else {
					wfstatus = new DocWorkflowSubmitStatus();
					wfstatus.setStatusCode("FAILURE");
					wfstatus.setErrorDescription("An error has occurred while saving...Please check!!!");
				}
			}*/
		 }
		return isSubmitSuccess;
	 }
	 
	 private void submitDocumentTagRelationship(JdbcTemplate jdbcTemplate, Integer docId, List<DocumentTagRelationship> docTagList, TransactionStatus status, Boolean isFinalSubmit) throws SQLException, Exception {
		 String DEL_SQL = DocumentWorkflowToolConstant.DEL_DOC_TAG_REL_SQL;
		 String INS_SQL = DocumentWorkflowToolConstant.INS_DOC_TAG_REL_SQL;
		 String INS_AUDIT_SQL = DocumentWorkflowToolConstant.INS_DOC_TAG_REL_AUDIT_SQL;
		 String SEL_VER_AUDIT_SQL = DocumentWorkflowToolConstant.SEL_VER_DOC_TAG_REL_AUDIT_SQL;
		 //if (isTagAssociatedWithDocument(docId)) {
			 jdbcTemplate.update(DEL_SQL, docId);
		 //}
		 if (! DocumentWorkflowToolUtility.isEmptyList(docTagList)) {
			 Integer versionId = jdbcTemplate.queryForObject(SEL_VER_AUDIT_SQL, Integer.class, docId);
			 if(versionId == null){
				 versionId = 0;
			 }		 					 
			 for (DocumentTagRelationship docTag : docTagList) {
				 jdbcTemplate.update(INS_SQL, docTag.getDocId(), docTag.getDocTypeId(), docTag.getDocTagId(), docTag.getDocSubTagId(), docTag.getCreatedBy(), docTag.getCreationDt(), docTag.getLastUpdatedBy(), docTag.getLastUpdatedDt());
				 if(isFinalSubmit){
					 jdbcTemplate.update(INS_AUDIT_SQL, docTag.getDocId(), versionId+1, docTag.getDocTypeId(), docTag.getDocTagId(), docTag.getDocSubTagId(), docTag.getCreatedBy(), docTag.getCreationDt(), docTag.getLastUpdatedBy(), docTag.getLastUpdatedDt());
				 }
			 }
		 }
	 }
	 
	 private void submitDocument(JdbcTemplate jdbcTemplate, Document document, TransactionStatus status, Boolean isFinalSubmit) throws SQLException, Exception {
		 String UPD_SQL = DocumentWorkflowToolConstant.UPD_DOCUMENT_BAD_LINK_SQL;
		 jdbcTemplate.update(UPD_SQL, document.getIsBadLinkReported(), document.getDocId());
		 
		 if(isFinalSubmit){
			 String SEL_VER_AUDIT_SQL = DocumentWorkflowToolConstant.SEL_VER_DOCUMENT_AUDIT_SQL;
			 Integer versionId = jdbcTemplate.queryForObject(SEL_VER_AUDIT_SQL, Integer.class, document.getDocId());
			 if(versionId == null){
				 versionId = 0;
			 }
			 String INS_AUDIT_SQL = DocumentWorkflowToolConstant.INS_DOCUMENT_AUDIT_SQL;
			 jdbcTemplate.update(INS_AUDIT_SQL, document.getDocId(), versionId+1, document.getDocName(), document.getDocTypeId(), document.getDocRepoId(), document.getDocHyperlink(), document.getDocLocation(), document.getIsDeleted(), document.getIsBadLinkReported(), document.getCreatedBy(), document.getCreationDt(), document.getLastUpdatedBy(), document.getLastUpdatedDt());
		 }
	 }
	 
	 private void submitDocWorkflowDetail(JdbcTemplate jdbcTemplate, DocumentWorkflowDetail docDetailObj, TransactionStatus status, Boolean isFinalSubmit) throws SQLException, Exception {
		 String DEL_SQL = DocumentWorkflowToolConstant.DEL_DOC_WFL_DTL_SQL;
		 jdbcTemplate.update(DEL_SQL, docDetailObj.getDocId());
		 String INS_SQL = DocumentWorkflowToolConstant.INS_DOC_WFL_DTL_SQL;
		 jdbcTemplate.update(INS_SQL, docDetailObj.getDocId(), docDetailObj.getTagOverrideReason(), docDetailObj.getTargetDocLocation(), docDetailObj.getLastUpdatedBy(), docDetailObj.getLastUpdatedDt());
		 
		 if(isFinalSubmit){
			 String SEL_VER_AUDIT_SQL = DocumentWorkflowToolConstant.SEL_VER_DOC_WFL_DTL_AUDIT_SQL;
			 Integer versionId = jdbcTemplate.queryForObject(SEL_VER_AUDIT_SQL, Integer.class, docDetailObj.getDocId());
			 if(versionId == null){
				 versionId = 0;
			 }
			 String INS_AUDIT_SQL = DocumentWorkflowToolConstant.INS_DOC_WFL_DTL_AUDIT_SQL;
			 jdbcTemplate.update(INS_AUDIT_SQL, docDetailObj.getDocId(), versionId+1, docDetailObj.getTagOverrideReason(), docDetailObj.getTargetDocLocation(), docDetailObj.getLastUpdatedBy(), docDetailObj.getLastUpdatedDt());
		 }
	 }
	 

	 
	 /*private DocWorkflowSubmitStatus fetchModifiedDocWorkflowProcess(Integer docId) throws SQLException, Exception {
		 DocWorkflowSubmitStatus status =  null;
		 String SEL_SQL = DocumentWorkflowToolConstant.Doc_WKFL_Population_SQL;
		 DocumentWorkflow docWkflow = this.getJdbcTemplateObject().queryForObject(SEL_SQL, new Object[]{docId}, new DocumentWorkflowMapper());
		 if ( !DocumentWorkflowToolUtility.isEmpty(docWkflow)) {
			 status = new DocWorkflowSubmitStatus();
			 status.setDocWorkflow(docWkflow);
		 }
		 return status;
	 }*/
	 
	 public List<DocWkflwProcess> fetchDocumentWorkflows(List<String> docIds) throws SQLException, Exception {
		 String SQL = DocumentWorkflowToolConstant.FETCH_DOC_WFL_SQL + "("+DocumentWorkflowToolUtility.joinString(docIds, "?")+")";
		 List<DocWkflwProcess> docWorkflowList = null;
		 if (! DocumentWorkflowToolUtility.isEmptyList(docIds)){
			 docWorkflowList = new ArrayList<DocWkflwProcess>();
			 Object[] documentIds = docIds.toArray();
			 docWorkflowList = this.getJdbcTemplateObject().query(SQL, documentIds, new MakerAgreementWkflwMapper());
		 }
		 return docWorkflowList;
	 }
	 
	 /**
	  * 
	  * @param userId
	  * @param docIds
	  * @return true if assignment can be done successfully, else false
	  */
	public boolean assignWorkflow(List<DocWkflwProcess> docIds, User user)	throws Exception {

		JdbcTemplate jdbcTemplate = this.getJdbcTemplateObject();
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = this.getTransactionManager().getTransaction(def);

		try {
			for (DocWkflwProcess doc : docIds) {
				assignWorkflowProcess(jdbcTemplate, doc, status, user);
			}
			this.getTransactionManager().commit(status);
			return Boolean.TRUE;
		} catch (SQLException e) {
			log.error(e.getMessage(),e);
			this.getTransactionManager().rollback(status);
			return Boolean.FALSE;
		}

	}

	private DocumentDTO getDocumentsForAdminAsMaker() throws SQLException, Exception {
		List <DocWkflwProcess> docList = null;
		DocumentDTO documentDTO = new DocumentDTO();
	    String SQL = DocumentWorkflowToolConstant.FETCH_ADMIN_MAKER_AGREEMENTS_SQL;
	    MakerAgreementWkflwMapper mapper = new MakerAgreementWkflwMapper();
	    docList = this.getJdbcTemplateObject().query(SQL, mapper);
	    log.debug(docList);
	    documentDTO.setDocList(docList);
		return documentDTO;
	}

	@Override
	public DocumentDTO getDocumentsForMaker(DocumentDTO documentDTO) throws SQLException, Exception {
		List <DocWkflwProcess> docList = null;
		String userId = "";
		String SQL = "";
		Object[] inputParameters = null;
		MakerAgreementWkflwMapper mapper = null;
		if(documentDTO.getUser() != null){
			userId = documentDTO.getUser().getUserId();
		    SQL = DocumentWorkflowToolConstant.FETCH_MAKER_AGREEMENTS_SQL;
		    inputParameters = new Object[]{userId};
		    mapper = new MakerAgreementWkflwMapper();
		    docList = this.getJdbcTemplateObject().query(SQL, inputParameters, mapper);
		    documentDTO.setDocList(docList);
		}
		return documentDTO;
	}

	@Override
	public DocumentDTO getDocumentsForAllCheckers() throws SQLException, Exception {
		List <DocWkflwProcess> docList = null;
		DocumentDTO documentDTO = new DocumentDTO();
	    String SQL = DocumentWorkflowToolConstant.FETCH_ALL_CHECKERS_AGREEMENT_SQL;
	    CheckerAgreementWorkflowMapper mapper = new CheckerAgreementWorkflowMapper();
	    docList = this.getJdbcTemplateObject().query(SQL, mapper);
	    documentDTO.setDocList(docList);
		return documentDTO;
	}

	@Override
	public DocumentDTO getDocumentsForChecker(DocumentDTO documentDTO) throws SQLException, Exception {
		List <DocWkflwProcess> docList = null;
		String userId = "";
		String SQL = "";
		Object[] inputParameters = null;
		CheckerAgreementWorkflowMapper mapper = null;
		if(documentDTO.getUser() != null){
			userId = documentDTO.getUser().getUserId();
		    SQL = DocumentWorkflowToolConstant.FETCH_CHECKER_AGREEMENTS_SQL;
		    inputParameters = new Object[]{userId};
		    mapper = new CheckerAgreementWorkflowMapper();
		    docList = this.getJdbcTemplateObject().query(SQL, inputParameters, mapper);
		    documentDTO.setDocList(docList);
		}
		return documentDTO;
	}

	private DocumentDTO getDocumentsForAdminAsChecker() throws SQLException, Exception {
		List <DocWkflwProcess> docList = null;
		DocumentDTO documentDTO = new DocumentDTO();
	    String SQL = DocumentWorkflowToolConstant.FETCH_ADMIN_CHECKERS_AGREEMENT_SQL;
	    CheckerAgreementWorkflowMapper mapper = new CheckerAgreementWorkflowMapper();
	    docList = this.getJdbcTemplateObject().query(SQL, mapper);
	    documentDTO.setDocList(docList);
		return documentDTO;
	}

	@Override
	public DocumentDTO getDocumentsForAllSMEs() throws SQLException, Exception {
		List <DocWkflwProcess> docList = null;
		DocumentDTO documentDTO = new DocumentDTO();
	    String SQL = DocumentWorkflowToolConstant.FETCH_ALL_ONSHORE_SMES_AGREEMENT_SQL;
	    OnshoreSMEAgreementWorkflowMapper mapper = new OnshoreSMEAgreementWorkflowMapper();
	    docList = this.getJdbcTemplateObject().query(SQL, mapper);
	    documentDTO.setDocList(docList);
		return documentDTO;
	}

	@Override
	public DocumentDTO getDocumentsForSME(DocumentDTO documentDTO) throws SQLException, Exception {
		List <DocWkflwProcess> docList = null;
		String userId = "";
		String SQL = "";
		Object[] inputParameters = null;
		OnshoreSMEAgreementWorkflowMapper mapper = null;
		if(documentDTO.getUser() != null){
			userId = documentDTO.getUser().getUserId();
		    SQL = DocumentWorkflowToolConstant.FETCH_ONSHORE_SME_AGREEMENTS_SQL;
		    inputParameters = new Object[]{userId};
		    mapper = new OnshoreSMEAgreementWorkflowMapper();
		    docList = this.getJdbcTemplateObject().query(SQL, inputParameters, mapper);
		    documentDTO.setDocList(docList);
		}
		return documentDTO;
	}

	private DocumentDTO getDocumentsForAdminAsSME() throws SQLException, Exception {
		List <DocWkflwProcess> docList = null;
		DocumentDTO documentDTO = new DocumentDTO();
	    String SQL = DocumentWorkflowToolConstant.FETCH_ADMIN_ONSHORE_SMES_AGREEMENT_SQL;
	    OnshoreSMEAgreementWorkflowMapper mapper = new OnshoreSMEAgreementWorkflowMapper();
	    docList = this.getJdbcTemplateObject().query(SQL, mapper);
	    documentDTO.setDocList(docList);
		return documentDTO;
	}
	
	private DocumentDTO getLatestAgreement(DocumentDTO documentDTO, Integer roleId) throws SQLException, Exception {
		List<DocWkflwProcess> docList = null;
	    String agreementId = null;
	    String SQL = null;
	    Object[] inputParameters = null;
	    MakerAgreementWkflwMapper mapper = null;
	    CheckerAgreementWorkflowMapper cMapper = null;
	    OnshoreSMEAgreementWorkflowMapper smeMapper = null;
		if (! DocumentWorkflowToolUtility.isEmptyValue(documentDTO.getAgreementId())) {
	    	agreementId = documentDTO.getAgreementId();
	    	inputParameters = new Object[]{agreementId};
	    	switch(roleId){
		    	case 1: 
		    		SQL = DocumentWorkflowToolConstant.FETCH_MAKER_SPECIFIC_ONE_AGREEMENT_SQL;
		    		mapper = new MakerAgreementWkflwMapper();
				    docList = this.getJdbcTemplateObject().query(SQL, inputParameters, mapper);
		    		break;
		    	case 2: 
		    		SQL = DocumentWorkflowToolConstant.FETCH_CHECKER_SPECIFIC_ONE_AGREEMENT_SQL;
		    		cMapper = new CheckerAgreementWorkflowMapper();
				    docList = this.getJdbcTemplateObject().query(SQL, inputParameters, cMapper);
		    		break;
		    	case 3: 
		    		SQL = DocumentWorkflowToolConstant.FETCH_ONSHORE_SME_SPECIFIC_ONE_AGREEMENT_SQL;
		    		smeMapper = new OnshoreSMEAgreementWorkflowMapper();
				    docList = this.getJdbcTemplateObject().query(SQL, inputParameters, smeMapper);
		    		break;
	    	}
		    documentDTO.setDocList(docList);
	    }
		return documentDTO;
	}
	
	@Override
	public DocumentDTO getAgreementsForAdminUsers(Integer viewId) throws SQLException, Exception {
		
		DocumentDTO dto = new DocumentDTO();
		switch(viewId){
		case 1:
			dto = getDocumentsForAdminAsMaker();
			break;
		case 2:
			dto = getDocumentsForAdminAsChecker();
			break;
		case 3:
			dto = getDocumentsForAdminAsSME();
			break;
		}
		return dto;
	}

	@Override
	public DocumentDTO startProcess(DocumentDTO documentDTO) throws SQLException, Exception {
		
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = this.getTransactionManager().getTransaction(transactionDefinition);
		boolean success = false;
		try {
			startProcessExecuteQueries(documentDTO);
			this.getTransactionManager().commit(transactionStatus);
			success = true;
			documentDTO = getUpdatedAgreementWorkflow(documentDTO, success);
			documentDTO.setMessage("Agreement "+documentDTO.getAgreementId()+" marked Started. This also starts your Clock!!!");
			return documentDTO;
			//return Boolean.TRUE;
		} catch (SQLException e) {
			log.error(e.getMessage(),e);
			this.getTransactionManager().rollback(transactionStatus);
			documentDTO.setSuccess(false);
			documentDTO.setMessage("Error marking the agreement "+documentDTO.getAgreementId()+" as Started. \n Error Msg:"+e.getMessage()+"\nPlease try after some time. !!!");
			return documentDTO;			
			//return Boolean.FALSE;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			this.getTransactionManager().rollback(transactionStatus);
			documentDTO.setSuccess(false);
			documentDTO.setMessage("Error marking the agreement "+documentDTO.getAgreementId()+" as Started. \n Error Msg:"+e.getMessage()+"\nPlease try after some time. !!!");
			return documentDTO;			
			//return Boolean.FALSE;
		}
	}
	
	private void startProcessExecuteQueries(DocumentDTO documentDTO) throws SQLException, Exception { 
		Integer statusCode = documentDTO.getStatusCode();
		String agreementId = documentDTO.getAgreementId();
		Integer roleId = documentDTO.getRoleId();
		String userId = documentDTO.getUser().getUserId();
		Integer numPages = documentDTO.getNumPages();
		Integer numFields = documentDTO.getNumFields();
		
		JdbcTemplate jdbcTemplate = this.getJdbcTemplateObject();
		
		Date currentDate = Calendar.getInstance().getTime();
		
		String assignedTo = DocumentWorkflowToolUtility.equalStrings("4", documentDTO.getUser().getRoleId(), false, true)? (!DocumentWorkflowToolUtility.isEmptyValue(documentDTO.getAssignedTo())? documentDTO.getAssignedTo() : userId) : userId;
		
		Date timeStampFromUI = DocumentWorkflowToolUtility.getDateforTimestampMatch(documentDTO.getLastUpdationDate());
		Date timeStampFromDB = null;
		
		documentDTO =  getLatestAgreement(documentDTO, roleId);
		if(documentDTO.getDocList() != null){
			timeStampFromDB = documentDTO.getDocList().get(0).getLastUpdationDate();
		}
		
		if(!DocumentWorkflowToolUtility.compareDates(timeStampFromUI, timeStampFromDB)){
			documentDTO.setMessage("Agreement version outdated. Please Refresh your grid and redo your action.");
			throw new Exception("Agreement version outdated. Please Refresh your grid and redo your action.");
		}		
		
		String UPDATE_STATUS_IN_WKF_PROCESS_SQL = DocumentWorkflowToolConstant.UPDATE_STATUS_IN_WKF_PROCESS_SQL;
		jdbcTemplate.update(UPDATE_STATUS_IN_WKF_PROCESS_SQL, statusCode, userId, currentDate, agreementId);
						
		String SELECT_MAX_ID_VER_FROM_WF_PROCESS_AUDIT_SQL = DocumentWorkflowToolConstant.SELECT_MAX_ID_VER_FROM_WF_PROCESS_AUDIT_SQL;
		Integer versionId = jdbcTemplate.queryForObject(SELECT_MAX_ID_VER_FROM_WF_PROCESS_AUDIT_SQL, Integer.class, agreementId);
		
		log.debug(" ###### versionId "+versionId);
		
		String INSERT_INTO_WKF_PROCESS_AUDIT_SQL = DocumentWorkflowToolConstant.INSERT_INTO_WKF_PROCESS_AUDIT_SQL;
		jdbcTemplate.update(INSERT_INTO_WKF_PROCESS_AUDIT_SQL, versionId+1, roleId, statusCode, assignedTo, userId, currentDate, numPages, numFields, agreementId);
	}
	
	private DocumentDTO getUpdatedAgreementWorkflow(DocumentDTO documentDTO, boolean success) throws SQLException, Exception {
		Integer roleId = documentDTO.getRoleId();
		documentDTO =  getLatestAgreement(documentDTO, roleId);
		documentDTO.setSuccess(success);
		return documentDTO;
	}

	@Override
	public DocumentDTO completeProcess(DocumentDTO documentDTO) throws SQLException, Exception {
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = this.getTransactionManager().getTransaction(transactionDefinition);
		boolean success = false;
		try {
			completeProcessExecuteQueries(documentDTO);
			this.getTransactionManager().commit(transactionStatus);
			success = true;
			documentDTO = getUpdatedAgreementWorkflow(documentDTO, success);
			documentDTO.setMessage("Agreement "+documentDTO.getAgreementId()+" marked Complete. This also stops your Clock!!!");
			return documentDTO;
			//return Boolean.TRUE;
		} catch (SQLException e) {
			log.error(e.getMessage(),e);
			this.getTransactionManager().rollback(transactionStatus);
			documentDTO.setSuccess(false);
			documentDTO.setMessage("Error marking the agreement "+documentDTO.getAgreementId()+" as Complete. \n Error Msg:"+e.getMessage()+"\nPlease try after some time. !!!");
			return documentDTO;			
			//return Boolean.FALSE;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			this.getTransactionManager().rollback(transactionStatus);
			documentDTO.setSuccess(false);
			documentDTO.setMessage("Error marking the agreement "+documentDTO.getAgreementId()+" as Complete. \n Error Msg:"+e.getMessage()+"\nPlease try after some time. !!!");
			return documentDTO;			
			//return Boolean.FALSE;
		}
		
	}
	
	private void completeProcessExecuteQueries(DocumentDTO documentDTO) throws SQLException, Exception{
		Integer statusCode = documentDTO.getStatusCode();
		String agreementId = documentDTO.getAgreementId();
		Integer roleId = documentDTO.getRoleId(); //Integer.valueOf(documentDTO.getUser().getRoleId());
		String userId = documentDTO.getUser().getUserId();
		String comment = documentDTO.getComment();
		Integer errReasonCode = documentDTO.getErrorReasonCode();
		Integer numPages = documentDTO.getNumPages();
		Integer numFields = documentDTO.getNumFields();
		Date currentDate = Calendar.getInstance().getTime();
		Date timeStampFromUI = DocumentWorkflowToolUtility.getDateforTimestampMatch(documentDTO.getLastUpdationDate());
		Date timeStampFromDB = null;
		
		documentDTO =  getLatestAgreement(documentDTO, roleId);
		if(documentDTO.getDocList() != null){
			timeStampFromDB = documentDTO.getDocList().get(0).getLastUpdationDate();
		}
		
		if(!DocumentWorkflowToolUtility.compareDates(timeStampFromUI, timeStampFromDB)){
			documentDTO.setMessage("Agreement version outdated. Please Refresh your grid and redo your action.");
			throw new Exception("Agreement version outdated. Please Refresh your grid and redo your action.");
		}
		
		JdbcTemplate jdbcTemplate = this.getJdbcTemplateObject();
		
		Integer newRoleId = ((roleId==2 && statusCode==18 ) || (roleId==3 && statusCode == 22))?roleId : roleId+1;
		
		String UPDATE_STATUS_ASSIGNED_TO_IN_WKF_PROCESS_SQL = DocumentWorkflowToolConstant.UPDATE_STATUS_ASSIGNED_TO_IN_WKF_PROCESS_SQL;
		jdbcTemplate.update(UPDATE_STATUS_ASSIGNED_TO_IN_WKF_PROCESS_SQL, newRoleId, statusCode, null,userId,currentDate, numPages, numFields, agreementId);
		
		String SELECT_MAX_ID_VER_FROM_WF_PROCESS_AUDIT_SQL = DocumentWorkflowToolConstant.SELECT_MAX_ID_VER_FROM_WF_PROCESS_AUDIT_SQL;
		Integer versionId = jdbcTemplate.queryForObject(SELECT_MAX_ID_VER_FROM_WF_PROCESS_AUDIT_SQL, Integer.class, agreementId);
		
		String INSERT_INTO_WKF_PROCESS_AUDIT_SQL = DocumentWorkflowToolConstant.INSERT_INTO_WKF_PROCESS_AUDIT_SQL;
		jdbcTemplate.update(INSERT_INTO_WKF_PROCESS_AUDIT_SQL, versionId+1, newRoleId, statusCode, null, userId,currentDate, numPages, numFields, agreementId);
		
		//if (!DocumentWorkflowToolUtility.isEmpty(comment)) {
			jdbcTemplate.update(DocumentWorkflowToolConstant.INSERT_INTO_WF_COMMENT_AUDIT_SQL, versionId+1, comment, roleId, errReasonCode, statusCode, agreementId);
		//}
	}

	@Override
	public DocumentDTO holdProcess(DocumentDTO documentDTO) throws SQLException, Exception {
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = this.getTransactionManager().getTransaction(transactionDefinition);
		boolean success = false;
		try {
			holdProcessExecuteQueries(documentDTO);
			this.getTransactionManager().commit(transactionStatus);
			success = true;
			documentDTO = getUpdatedAgreementWorkflow(documentDTO, success);
			documentDTO.setMessage("Agreement "+documentDTO.getAgreementId()+" marked Held. This also halts your Clock!!!");
			return documentDTO;
			//return Boolean.TRUE;
		} catch (SQLException e) {
			log.error(e.getMessage(),e);
			this.getTransactionManager().rollback(transactionStatus);
			documentDTO.setSuccess(false);
			documentDTO.setMessage("Error marking the agreement "+documentDTO.getAgreementId()+" as Held. \n Error Msg:"+e.getMessage()+"\nPlease try after some time. !!!");
			return documentDTO;
			//return Boolean.FALSE;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			this.getTransactionManager().rollback(transactionStatus);
			documentDTO.setSuccess(false);
			documentDTO.setMessage("Error marking the agreement "+documentDTO.getAgreementId()+" as Held. \n Error Msg:"+e.getMessage()+"\nPlease try after some time. !!!");
			return documentDTO;
			//return Boolean.FALSE;
		}
		
	}
	
	private void holdProcessExecuteQueries(DocumentDTO documentDTO) throws SQLException, Exception{
		Integer statusCode = documentDTO.getStatusCode();
		String agreementId = documentDTO.getAgreementId();
		String comment = documentDTO.getComment();
		Integer errReasonCode = documentDTO.getErrorReasonCode();
		Integer roleId = documentDTO.getRoleId();
		String userId = documentDTO.getUser().getUserId();
		Date currentDate = Calendar.getInstance().getTime();
		Integer numPages = documentDTO.getNumPages();
		Integer numFields = documentDTO.getNumFields();
		
		Integer newRoleId = (roleId==3)?roleId : roleId+1;
		
		String assignedTo = (roleId==3) ? (DocumentWorkflowToolUtility.equalStrings("4", documentDTO.getUser().getRoleId(), false, true)? (! DocumentWorkflowToolUtility.isEmptyValue(documentDTO.getAssignedTo())? documentDTO.getAssignedTo() : userId) : userId) : null;
		
		Date timeStampFromUI = DocumentWorkflowToolUtility.getDateforTimestampMatch(documentDTO.getLastUpdationDate());
		Date timeStampFromDB = null;
		
		documentDTO =  getLatestAgreement(documentDTO, roleId);
		if(documentDTO.getDocList() != null){
			timeStampFromDB = documentDTO.getDocList().get(0).getLastUpdationDate();
		}
		
		if(!DocumentWorkflowToolUtility.compareDates(timeStampFromUI, timeStampFromDB)){
			documentDTO.setMessage("Agreement version outdated. Please Refresh your grid and redo your action.");
			throw new Exception("Agreement version outdated. Please Refresh your grid and redo your action.");
		}
		
		JdbcTemplate jdbcTemplate = this.getJdbcTemplateObject();
		
		String UPDATE_STATUS_ASSIGNED_TO_IN_WKF_PROCESS_SQL = DocumentWorkflowToolConstant.UPDATE_STATUS_ASSIGNED_TO_IN_WKF_PROCESS_SQL;
		jdbcTemplate.update(UPDATE_STATUS_ASSIGNED_TO_IN_WKF_PROCESS_SQL, newRoleId, statusCode, assignedTo,userId,currentDate, numPages, numFields, agreementId);
		
		String SELECT_MAX_ID_VER_FROM_WF_PROCESS_AUDIT_SQL = DocumentWorkflowToolConstant.SELECT_MAX_ID_VER_FROM_WF_PROCESS_AUDIT_SQL;
		Integer versionId = jdbcTemplate.queryForObject(SELECT_MAX_ID_VER_FROM_WF_PROCESS_AUDIT_SQL, Integer.class, agreementId);
		
		String INSERT_INTO_WKF_PROCESS_AUDIT_SQL = DocumentWorkflowToolConstant.INSERT_INTO_WKF_PROCESS_AUDIT_SQL;
		jdbcTemplate.update(INSERT_INTO_WKF_PROCESS_AUDIT_SQL, versionId+1, newRoleId, statusCode, assignedTo, userId,currentDate, numPages, numFields, agreementId);
		
		//if (!DocumentWorkflowToolUtility.isEmpty(comment)) {
			jdbcTemplate.update(DocumentWorkflowToolConstant.INSERT_INTO_WF_COMMENT_AUDIT_SQL, versionId+1, comment, roleId, errReasonCode, statusCode, agreementId);
		//}
	}
	
	 private void assignWorkflowProcess(JdbcTemplate jdbcTemplate, DocWkflwProcess docObj, TransactionStatus status, User user) throws SQLException, Exception {
			Integer statusCode = docObj.getStatusCode();
			String agreementId = docObj.getAgreementId();
			Integer roleId = DocumentWorkflowToolUtility.equalStrings("4", user.getRoleId(), false, true)? docObj.getRoleId() : Integer.valueOf(user.getRoleId()); //Integer.valueOf(documentDTO.getUser().getRoleId());
			Date currentDate = Calendar.getInstance().getTime();
			String userId = user.getUserId();
			Integer numPages = docObj.getNumPages();
			Integer numFields = docObj.getNumFields();
			
			switch(roleId){
				case 2: statusCode = 16;//FIXME Replace with constants
				break;
				case 3: statusCode = 19;
				break;
			}
					
			String UPDATE_STATUS_ASSIGNED_TO_IN_WKF_PROCESS_SQL = DocumentWorkflowToolConstant.UPDATE_STATUS_ASSIGNED_TO_IN_WKF_PROCESS_SQL;
			jdbcTemplate.update(UPDATE_STATUS_ASSIGNED_TO_IN_WKF_PROCESS_SQL, roleId, statusCode, userId,userId,currentDate, numPages, numFields, agreementId);
			
			String SELECT_MAX_ID_VER_FROM_WF_PROCESS_AUDIT_SQL = DocumentWorkflowToolConstant.SELECT_MAX_ID_VER_FROM_WF_PROCESS_AUDIT_SQL;
			Integer versionId = jdbcTemplate.queryForObject(SELECT_MAX_ID_VER_FROM_WF_PROCESS_AUDIT_SQL, Integer.class, agreementId);
			
			String INSERT_INTO_WKF_PROCESS_AUDIT_SQL = DocumentWorkflowToolConstant.INSERT_INTO_WKF_PROCESS_AUDIT_SQL;
			jdbcTemplate.update(INSERT_INTO_WKF_PROCESS_AUDIT_SQL, versionId+1, roleId, statusCode, userId, userId,currentDate, numPages, numFields, agreementId);
	
	 }
	 
	@Override
	public List<DocumentWorkflowStatus> getWorkflowStatusListByRole(int roleId)
			throws SQLException, Exception {
	
		List <DocumentWorkflowStatus> statusList = null;
	    String SQL = DocumentWorkflowToolConstant.FETCH_STATUS_BY_ROLE;
	    statusList = this.getJdbcTemplateObject().query(SQL, new Object[]{roleId}, new DocumentWorkflowStatusMapper());
		return statusList;
	}
	
}
