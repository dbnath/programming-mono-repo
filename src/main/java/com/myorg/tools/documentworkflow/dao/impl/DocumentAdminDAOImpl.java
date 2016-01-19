package com.myorg.tools.documentworkflow.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.myorg.tools.documentworkflow.constant.DocumentWorkflowToolConstant;
import com.myorg.tools.documentworkflow.dao.DocumentAdminDAO;
import com.myorg.tools.documentworkflow.mapper.AHTRowMapper;
import com.myorg.tools.documentworkflow.mapper.AgreementAuditTrailMapper;
import com.myorg.tools.documentworkflow.mapper.AgreementDumpMapper;
import com.myorg.tools.documentworkflow.mapper.AgreementErrorTypeMapper;
import com.myorg.tools.documentworkflow.mapper.AgreementStatusMapper;
import com.myorg.tools.documentworkflow.mapper.AgreementTypeMapper;
import com.myorg.tools.documentworkflow.mapper.DocumentRepositoryMapper;
import com.myorg.tools.documentworkflow.mapper.DocumentSubTagValuesMapper;
import com.myorg.tools.documentworkflow.mapper.DocumentTagMapper;
import com.myorg.tools.documentworkflow.mapper.DocumentTypeMapper;
import com.myorg.tools.documentworkflow.model.AHTBean;
import com.myorg.tools.documentworkflow.model.AHTWrapper;
import com.myorg.tools.documentworkflow.model.AgreementErrorType;
import com.myorg.tools.documentworkflow.model.AgreementStatus;
import com.myorg.tools.documentworkflow.model.AgreementType;
import com.myorg.tools.documentworkflow.model.AgreementWorkflow;
import com.myorg.tools.documentworkflow.model.DocWkflwProcess;
import com.myorg.tools.documentworkflow.model.Document;
import com.myorg.tools.documentworkflow.model.DocumentRepository;
import com.myorg.tools.documentworkflow.model.DocumentSubTagValues;
import com.myorg.tools.documentworkflow.model.DocumentTag;
import com.myorg.tools.documentworkflow.model.DocumentTagSubTagMapping;
import com.myorg.tools.documentworkflow.model.DocumentType;
import com.myorg.tools.documentworkflow.model.DocumentTypeTagMapping;
import com.myorg.tools.documentworkflow.model.DocumentTypeTagSubTagsMap;
import com.myorg.tools.documentworkflow.model.ReverseMappable;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class DocumentAdminDAOImpl extends BaseJDBCTemplate implements DocumentAdminDAO {
	
	static Logger log = Logger.getLogger(DocumentAdminDAOImpl.class.getName());

	public List<DocumentType> populateDocumentTypes() throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.DOC_TYPE_POPULATE_SQL;
		List<DocumentType> docTypeList = null;
		try{
			docTypeList = this.getJdbcTemplateObject().query(SQL, new DocumentTypeMapper());
		} catch(EmptyResultDataAccessException e) {
			docTypeList = null;
		}
		return docTypeList;
	}
	
	public List<AgreementType> populateAgreementTypes() throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.AGR_TYPE_POPULATE_SQL;
		List<AgreementType> docTypeList = null;
		try{
			docTypeList = this.getJdbcTemplateObject().query(SQL, new AgreementTypeMapper());
		} catch(EmptyResultDataAccessException e) {
			docTypeList = null;
		}
		return docTypeList;
	}
	
	public List<String> populateMakerList() throws SQLException, Exception{
		String SQL = DocumentWorkflowToolConstant.GET_ACTIVE_MAKER_LIST;
		List<String> makerList = null;
		try{
			makerList = this.getJdbcTemplateObject().queryForList(SQL, String.class);
		} catch(EmptyResultDataAccessException e) {
			makerList = new ArrayList<String>();
		}
		return makerList;		
	}
	
	public List<AgreementErrorType> populateErrorTypes() throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.ERR_TYPE_POPULATE_SQL;
		List<AgreementErrorType> docTypeList = null;
		try{
			docTypeList = this.getJdbcTemplateObject().query(SQL, new AgreementErrorTypeMapper());
		} catch(EmptyResultDataAccessException e) {
			docTypeList = null;
		}
		return docTypeList;
	}	
	
	public List<AgreementStatus> populateAgreementStatus() throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.AGR_STATUS_POPULATE_SQL;
		List<AgreementStatus> agrStatusList = null;
		try{
			agrStatusList = this.getJdbcTemplateObject().query(SQL, new AgreementStatusMapper());
		} catch(EmptyResultDataAccessException e) {
			agrStatusList = null;
		}
		return agrStatusList;
	}	
	
	public List<DocumentRepository> populateDocumentRepos() throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.DOC_REPO_POPULATE_SQL;
		List<DocumentRepository> docRepoList = null;
		try{
			docRepoList = this.getJdbcTemplateObject().query(SQL, new DocumentRepositoryMapper());
		} catch(EmptyResultDataAccessException e) {
			docRepoList = null;
		}
		return docRepoList;
	}

	public List<DocumentTag> populateDocumentTagsMasterList() throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.DOC_TAG_POPULATE_SQL;
		List<DocumentTag> docTagList = null;
		try{
			docTagList = this.getJdbcTemplateObject().query(SQL, new DocumentTagMapper());
		} catch(EmptyResultDataAccessException e) {
			docTagList = null;
		}
		return docTagList;
	}

	public List<DocumentSubTagValues> populateDocumentSubTagsMasterList() throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.DOC_SUBTAG_POPULATE_SQL;
		List<DocumentSubTagValues> docSubTagList = null;
		try{
			docSubTagList = this.getJdbcTemplateObject().query(SQL, new DocumentSubTagValuesMapper());
		} catch(EmptyResultDataAccessException e) {
			docSubTagList = null;
		}
		return docSubTagList;
	}

	public DocumentTypeTagMapping populateDocumentTypeTagMapping(Integer docTypeId) throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.DOC_TYPE_TAG_MAP_SQL;
		List<DocumentTag> docTagList = null;
		try{
			docTagList = this.getJdbcTemplateObject().query(SQL, new Object[]{docTypeId}, new DocumentTagMapper());
		} catch(EmptyResultDataAccessException e) {
			docTagList = null;
		}
		DocumentTypeTagMapping docTypeTagMapping = new DocumentTypeTagMapping();
		docTypeTagMapping.setDocTypeId(docTypeId);
		docTypeTagMapping.setDocTags(docTagList);
		return docTypeTagMapping;
	}

	public List<DocumentTag> populateUnmappedDocTypeTags(Integer docTypeId) throws SQLException, Exception {
		List<DocumentTag> docTagList = populateDocumentTagsMasterList();
		List<Integer> tagIdList = new ArrayList<Integer>();
		for (DocumentTag tag : docTagList) {
			tagIdList.add(tag.getDocTagId());
		}
		List<DocumentTag> docTypeTagList = ((DocumentTypeTagMapping)populateDocumentTypeTagMapping(docTypeId)).getDocTags();
		for (DocumentTag docTag : docTypeTagList) {
			if(tagIdList.contains(docTag.getDocTagId())) {
				docTagList.remove(findTagElementToRemoveFromList(docTagList, docTag.getDocTagId()));
			}
		}
		return docTagList;
	}

	private DocumentTag findTagElementToRemoveFromList(List<DocumentTag> docTagList, Integer tagId){
		if (! DocumentWorkflowToolUtility.isEmptyList(docTagList)) {
			for (DocumentTag tag : docTagList) {
				if (tagId.equals(tag.getDocTagId())){
					return tag;
				}
			}
		}
		return null;
	}

	public DocumentTagSubTagMapping populateDocumentTagSubTagMapping(Integer docTagId) throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.DOC_TAG_SUB_TAG_MAP_SQL;
		List<DocumentSubTagValues> docSubTagList = null;
		try{
			docSubTagList = this.getJdbcTemplateObject().query(SQL, new Object[]{docTagId}, new DocumentSubTagValuesMapper());
		} catch(EmptyResultDataAccessException e) {
			docSubTagList = null;
		}
		DocumentTagSubTagMapping docTagSubTagMapping = new DocumentTagSubTagMapping();
		docTagSubTagMapping.setDocTagId(docTagId);
		docTagSubTagMapping.setDocSubTags(docSubTagList);
		return docTagSubTagMapping;
	}

	public List<DocumentSubTagValues> populateUnmappedDocTagSubTags(Integer docTagId) throws SQLException, Exception {
		List<DocumentSubTagValues> docSubTagList = populateDocumentSubTagsMasterList();
		List<Integer> subTagIdList = new ArrayList<Integer>();
		for (DocumentSubTagValues docSubTag : docSubTagList) {
			subTagIdList.add(docSubTag.getDocSubTagId());
		}
		List<DocumentSubTagValues> docTagSubTagList = ((DocumentTagSubTagMapping)populateDocumentTagSubTagMapping(docTagId)).getDocSubTags();
		for (DocumentSubTagValues docSubTag : docTagSubTagList) {
			if(subTagIdList.contains(docSubTag.getDocSubTagId())) {
				docSubTagList.remove(findSubTagElementToRemoveFromList(docSubTagList, docSubTag.getDocSubTagId()));
			}
		}
		return docSubTagList;
	}
	
	private DocumentSubTagValues findSubTagElementToRemoveFromList(List<DocumentSubTagValues> docSubTagList, Integer subTagId){
		if (! DocumentWorkflowToolUtility.isEmptyList(docSubTagList)) {
			for (DocumentSubTagValues subTag : docSubTagList) {
				if (subTagId.equals(subTag.getDocSubTagId())){
					return subTag;
				}
			}
		}
		return null;
	}
	
	public DocumentTypeTagSubTagsMap populateDocumentTypeTagSubTagsMap(Integer docTypeId) throws SQLException, Exception {
		DocumentTypeTagMapping docTypeTagMapping = populateDocumentTypeTagMapping(docTypeId);
		List<DocumentTag> docTags = docTypeTagMapping.getDocTags();
		List<DocumentTagSubTagMapping> docTagSubTagMaps = new ArrayList<DocumentTagSubTagMapping>();
		for (DocumentTag docTag : docTags) {
			DocumentTagSubTagMapping docTagSubTagMap = populateDocumentTagSubTagMapping(docTag.getDocTagId());
			docTagSubTagMap.setDocTagDesc(docTag.getDocTagDesc());
			docTagSubTagMaps.add(docTagSubTagMap);
		}
		DocumentTypeTagSubTagsMap docTypeTagSubTagsMap = new DocumentTypeTagSubTagsMap();
		docTypeTagSubTagsMap.setDocTypeId(docTypeId);
		docTypeTagSubTagsMap.setDocTagSubTagMap(docTagSubTagMaps);
		return docTypeTagSubTagsMap;
	}


	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.dao.DocumentAdminDAO#uploadDocumentInformation(java.util.List)
	 */
	@Override
	public boolean uploadDocumentInformation(List<Document> docList, String userId) throws SQLException, Exception {
		
		if(docList != null){
			
			HashMap<String, Integer> typeMap = DocumentWorkflowToolUtility.mapByValue(new ArrayList<ReverseMappable>(populateDocumentTypes()));
			HashMap<String, Integer> repoMap = DocumentWorkflowToolUtility.mapByValue(new ArrayList<ReverseMappable>(populateDocumentRepos()));
			
			log.debug("###### typeMap "+typeMap);
			log.debug("###### repoMap "+repoMap);
			
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(this.getDataSource()).withProcedureName("addDocument");
			
			
			try {
				for(Document doc : docList){
					if(doc != null){
						doc.setDocTypeId(typeMap.get(doc.getDocTypeDesc()));
						doc.setDocRepoId(repoMap.get(doc.getDocRepoDesc()));
						doc.setCreatedBy(userId);
						doc.setCreationDt(new java.util.Date());
						doc.setIsBadLinkReported("N");
						doc.setIsDeleted("N");
						
						log.debug("###### doc Repo "+doc.getDocRepoDesc()+" ###### "+doc.getDocRepoId());
						log.debug("###### doc Repo "+doc.getDocTypeDesc()+" ###### "+doc.getDocTypeId());
						
						insertDocumentsIntoDatabase(jdbcCall, doc);
					}
				}
				return true;
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
				return false;
			}			
		}
		return false;
	}
	
	private void insertDocumentsIntoDatabase(SimpleJdbcCall jdbcCall, Document doc) throws SQLException, Exception {
		
		Set<String> params = new HashSet<String>();		
		params.add("ID_DOC");
		params.add("NM_DOC");
		params.add("ID_DOC_TYPE");
		params.add("ID_DOC_REPO");
		params.add("DOC_HYPERLINK");
		params.add("DOC_LOCATION");
		params.add("IS_DELETED");
		params.add("IS_VALID_LINK");
		params.add("CREATED_BY");
		params.add("CREATION_DT");
		params.add("LAST_UPDATED_BY");
		params.add("LAST_UPDATE_DT");
		
		jdbcCall.setInParameterNames(params);
		
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("ID_DOC",500);
		in.addValue("NM_DOC", doc.getDocName());		
		in.addValue("ID_DOC_TYPE", doc.getDocTypeId());
		in.addValue("ID_DOC_REPO", doc.getDocRepoId());
		in.addValue("DOC_HYPERLINK", doc.getDocHyperlink());
		in.addValue("DOC_LOCATION", doc.getDocLocation());
		in.addValue("IS_DELETED", doc.getIsDeleted());
		in.addValue("IS_VALID_LINK", doc.getIsBadLinkReported());
		in.addValue("CREATED_BY", doc.getCreatedBy());
		in.addValue("CREATION_DT", doc.getCreationDt());
		in.addValue("LAST_UPDATED_BY", doc.getLastUpdatedBy());
		in.addValue("LAST_UPDATE_DT", doc.getLastUpdatedDt());
		
		@SuppressWarnings("unused")
		Map<String, Object> out = jdbcCall.execute(in);
	}
	
	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.dao.DocumentAdminDAO#uploadDocumentInformation(java.util.List)
	 */
	@Override
	public boolean uploadAgreementInformation(List<AgreementWorkflow> docList, String userId) throws SQLException, Exception {
		
		if(docList != null){
			
			HashMap<String, Integer> typeMap = DocumentWorkflowToolUtility.mapByValue(new ArrayList<ReverseMappable>(populateAgreementTypes()));
			HashMap<String, Integer> statusMap = DocumentWorkflowToolUtility.mapByValue(new ArrayList<ReverseMappable>(populateAgreementStatus()));
			
			log.debug("###### typeMap "+typeMap);
			log.debug("###### statusMap "+statusMap);
			
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(this.getDataSource()).withProcedureName("addAgreement");
			
			
			try {
				for(AgreementWorkflow doc : docList){
					if(doc != null){
						doc.setAgreementTypeId(typeMap.get(doc.getAgreementTypeDesc()));
						doc.setWfStatusId(statusMap.get(DocumentWorkflowToolUtility.isEmpty(doc.getWfStatusDesc())?"New":doc.getWfStatusDesc()));
						doc.setCreatedBy(userId);
						doc.setLastUpdatedBy(userId);
						doc.setCreatedDt(new java.util.Date());
						doc.setLastUpdateDt(doc.getCreatedDt());
						doc.setAssignedDt(doc.getCreatedDt());
						
						insertAgreementIntoDataBase(jdbcCall, doc);
					}
				}
				return true;
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
				return false;
			}			
		}
		return false;
	}
	
	private boolean insertAgreementIntoDataBase(SimpleJdbcCall jdbcCall, AgreementWorkflow doc) throws SQLException{
		
		Set<String> params = new HashSet<String>();		
		params.add("IN_ID_AGRMT");
		params.add("ID_AGREEMENT_TYPE");
		params.add("LOB");
		params.add("ID_WF_STATUS");
		params.add("IN_ASSIGNED_TO");
		params.add("CREATED_BY");
		params.add("CREATED_DT");
		params.add("LAST_UPDATED_BY");
		params.add("LAST_UPDATE_DT");
		
		jdbcCall.setInParameterNames(params);
		
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("IN_ID_AGRMT",doc.getAgreementId());
		in.addValue("ID_AGREEMENT_TYPE", doc.getAgreementTypeId());		
		in.addValue("LOB", doc.getLob());
		in.addValue("ID_WF_STATUS", doc.getWfStatusId());
		in.addValue("IN_ASSIGNED_TO", doc.getAssignedTo());
		in.addValue("CREATED_BY", doc.getCreatedBy());
		in.addValue("CREATED_DT", doc.getCreatedDt());
		in.addValue("LAST_UPDATED_BY", doc.getLastUpdatedBy());
		in.addValue("LAST_UPDATE_DT", doc.getLastUpdateDt());
		
		@SuppressWarnings("unused")
		Map<String, Object> out = jdbcCall.execute(in);
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.dao.DocumentAdminDAO#uploadDocumentInformation(java.util.List)
	 */
	@Override
	public boolean uploadErrorReasons(List<AgreementErrorType> docList, String userId) throws SQLException, Exception {
		
		if(docList != null){
			JdbcTemplate jdbcTemplate = this.getJdbcTemplateObject();
			TransactionDefinition def = new DefaultTransactionDefinition();
			TransactionStatus status = this.getTransactionManager().getTransaction(def);
			int counter = 0;
			try {
				for(AgreementErrorType doc : docList){
					counter++;
					if(doc != null){
						if(!DocumentWorkflowToolUtility.isEmptyValue(doc.getErrorTypeId())){
							log.debug("###### Updating row "+counter);
							updateErrorReason(doc, jdbcTemplate);
						} else {
							log.debug("###### Inserting row "+counter);
							insertErrorReason(doc, jdbcTemplate);
						}
					}
				}
				this.getTransactionManager().commit(status);
				return true;
			} catch (SQLException e) {
				log.error("Error occurred while processing entry "+(counter+1),e);
				this.getTransactionManager().rollback(status);
				return false;
			}			
		}
		return false;
	}
	
	@Override
	public boolean uploadAgreementTypes(List<AgreementType> docList, String userId) throws SQLException, Exception {
		
		if(docList != null){
			JdbcTemplate jdbcTemplate = this.getJdbcTemplateObject();
			TransactionDefinition def = new DefaultTransactionDefinition();
			TransactionStatus status = this.getTransactionManager().getTransaction(def);
			int counter = 0;
			try {
				for(AgreementType agrType : docList){
					counter++;
					if(agrType != null){
						if(!DocumentWorkflowToolUtility.isEmptyValue(agrType.getAgreementTypeId())){
							log.debug("###### Updating row "+counter);
							updateAgreementType(agrType, jdbcTemplate);
						} else {
							log.debug("###### Inserting row "+counter);
							insertAgreementType(agrType, jdbcTemplate);
						}
					}
				}
				this.getTransactionManager().commit(status);
				return true;
			} catch (SQLException e) {
				log.error("Error occurred while processing entry "+(counter+1),e);
				this.getTransactionManager().rollback(status);
				return false;
			}			
		}
		return false;
	}	
	
	
	private void updateErrorReason(AgreementErrorType type,JdbcTemplate jdbcTemplate ) throws SQLException, Exception{
		String UPD_SQL = DocumentWorkflowToolConstant.UPD_ERR_REASON;
		jdbcTemplate.update(UPD_SQL, type.getErrorTypeName(),type.getErrorTypeId());
	}
	
	@SuppressWarnings("deprecation")
	private void insertErrorReason(AgreementErrorType type,JdbcTemplate jdbcTemplate ) throws SQLException, Exception{
		String INS_SQL = DocumentWorkflowToolConstant.INS_ERR_REASON;
		String SEL_SQL = DocumentWorkflowToolConstant.SEL_ERR_REASON_CD;
		
		Integer errTypId = jdbcTemplate.queryForInt(SEL_SQL);
		
		if(errTypId != null) {
			jdbcTemplate.update(INS_SQL, errTypId +1, type.getErrorTypeName());
		}
	}
	
	private void updateAgreementType(AgreementType type,JdbcTemplate jdbcTemplate ) throws SQLException, Exception{
		String UPD_SQL = DocumentWorkflowToolConstant.UPD_AGR_TYPE;
		jdbcTemplate.update(UPD_SQL, type.getAgrementTypeName(),type.getAgreementTypeId());
	}
	
	@SuppressWarnings("deprecation")
	private void insertAgreementType(AgreementType type,JdbcTemplate jdbcTemplate ) throws SQLException, Exception{
		String INS_SQL = DocumentWorkflowToolConstant.INS_AGR_TYPE;
		String SEL_SQL = DocumentWorkflowToolConstant.SEL_AGR_TYPE_CD;
		
		Integer agrTypeId = jdbcTemplate.queryForInt(SEL_SQL);
		
		if(agrTypeId != null) {
			jdbcTemplate.update(INS_SQL, agrTypeId +1, type.getAgrementTypeName());
		}
	}	
	
	public AHTWrapper extractAgreementAHTInfo(String rptFromDate, String rptToDate) throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.AGRMT_DATA_DUMP;
		String SQL1 = DocumentWorkflowToolConstant.QUERY_FOR_AHT;
		
		AHTWrapper ahtWrapper = new AHTWrapper();
		List<DocWkflwProcess> docRepoList = null;
		List<DocWkflwProcess> updatedAgreementDump = null;
		List<AHTBean> ahtList = null;
		Map<String, AHTBean> ahtMap = null;
		try{
			docRepoList = this.getJdbcTemplateObject().query(SQL, new Object[]{rptFromDate, rptToDate}, new AgreementDumpMapper());
			ahtList = this.getJdbcTemplateObject().query(SQL1, new Object[]{rptFromDate, rptToDate}, new AHTRowMapper());
			
			if (!DocumentWorkflowToolUtility.isEmptyList(docRepoList)) {
				updatedAgreementDump = populateFinalAgreementDump(docRepoList);
			}
			if (!DocumentWorkflowToolUtility.isEmptyList(ahtList)) {
				ahtMap = calculateAHT(ahtList);
			}
			ahtWrapper.setAhtMap(ahtMap);
			ahtWrapper.setDocRepoList(updatedAgreementDump);
			
		} catch(EmptyResultDataAccessException e) {
			docRepoList = null;
			ahtList = null;
		}
		return ahtWrapper;
	}
	
	private List<DocWkflwProcess> populateFinalAgreementDump(List<DocWkflwProcess> docRepoList) {
		String currAgrId = "";
		String smeComments = "";
		for(int i=0; i< docRepoList.size(); i++){
			
			DocWkflwProcess docWkflwPr = docRepoList.get(i);
			if (DocumentWorkflowToolUtility.equalStrings(currAgrId, docWkflwPr.getAgreementId(), false, true)) {
				smeComments = smeComments + " | "+docWkflwPr.getSmeComments();
				docWkflwPr.setSmeComments(smeComments);
				docRepoList.remove(i-1);
			}
			currAgrId = docWkflwPr.getAgreementId();
			smeComments = docWkflwPr.getSmeComments();
		}
		return docRepoList;
	}
	
	private Map<String, AHTBean> calculateAHT(List<AHTBean> ahtList){
			Date startTime = null;
			Date stopTime = null;
			Integer currentRole = 0;
			Double makerHeldTime = 0.0;
			Double checkerHeldTime = 0.0;
			Double smeHeldTime = 0.0;
			
			Map<String, AHTBean> ahtMap = new HashMap<String, AHTBean>();
			calculateAge(ahtMap, ahtList);
			
			for(int i=0; i< ahtList.size(); i++){
				
				AHTBean aht = ahtList.get(i);
				
				if("Y".equalsIgnoreCase(aht.getIsClockStart())){
					startTime = aht.getLastUpdationDate();
					currentRole = aht.getRoleId();
					continue;
				}
				
				if("Y".equalsIgnoreCase(aht.getIsClockStop())){
					stopTime = aht.getLastUpdationDate();
					
					Double ahtTime = DocumentWorkflowToolUtility.getTimeDifferenceInMin(startTime, stopTime);
					AHTBean ahtTemp = ahtMap.get(aht.getAgreementId());
					
					if (aht.getStatusCode() == 22){
						currentRole = aht.getRoleId();
					}
					log.debug(ahtTemp.getAgreementId()+"...startTime..."+startTime+"...StopTime..."+stopTime);
					setAHTBeanHeldTime(ahtTemp, currentRole, ahtTime, makerHeldTime, checkerHeldTime, smeHeldTime);
					startTime = null;
					stopTime = null;
					currentRole = 0;
				}
				
			}
			return ahtMap;
		
	}
	
	private void setAHTBeanHeldTime(AHTBean ahtTemp, Integer currentRole, Double ahtTime, Double makerHeldTime, Double checkerHeldTime, Double smeHeldTime) {
		switch(currentRole){
		case 1:
			if(ahtTime != null){
				makerHeldTime = ahtTime;
				ahtTemp.setMakerHeldTime(makerHeldTime);
			}
			log.debug(ahtTemp.getAgreementId()+"...MakerHeldTime..."+ahtTemp.getMakerHeldTime());
			break;
		case 2:
			if(ahtTime != null){
				checkerHeldTime = ahtTime;
				ahtTemp.setCheckerHeldTime(checkerHeldTime);
			}
			log.debug(ahtTemp.getAgreementId()+"...MakerHeldTime..."+ahtTemp.getMakerHeldTime()+"...CheckerHeldTime..."+ahtTemp.getCheckerHeldTime());
			break;
		case 3:
			if(ahtTime != null){
				smeHeldTime = ahtTime;
				//if (Double.valueOf(smeHeldTime).doubleValue()>0.0) {
					ahtTemp.setSmeHeldTime(smeHeldTime);
			}
			log.debug(ahtTemp.getAgreementId()+"...MakerHeldTime..."+ahtTemp.getMakerHeldTime()+"...CheckerHeldTime..."+ahtTemp.getCheckerHeldTime()+"...SMEHeldTime..."+ahtTemp.getSmeHeldTime());
			break;
		}
		ahtTemp.setTotalHeldTime(ahtTemp.getMakerHeldTime()+ahtTemp.getCheckerHeldTime()+ahtTemp.getSmeHeldTime());
		log.debug(ahtTemp.getAgreementId()+"...TotalHoldTime..."+ahtTemp.getTotalHeldTime());
		resetMakerCheckerSmeHeldTime(ahtTemp, makerHeldTime, checkerHeldTime, smeHeldTime);
	}
	
	private void resetMakerCheckerSmeHeldTime(AHTBean ahtTemp, Double makerHeldTime, Double checkerHeldTime, Double smeHeldTime) {
		if (ahtTemp.getStatusCode()==18 || ahtTemp.getStatusCode()==22) {
			makerHeldTime = 0.0;
			checkerHeldTime = 0.0;
			if (ahtTemp.getStatusCode()==22){
				smeHeldTime = 0.0;
			}
		}
	}
	
	private void calculateAge(Map<String, AHTBean> ahtMap, List<AHTBean> ahtList) {
		Date ageStartTime = null;
		Date ageStopTime = null;
		Double ahtAge = 0.0;
		AHTBean aht = null;
		AHTBean ahtTemp = null;
		for(int i=0; i< ahtList.size(); i++){
			
			aht = ahtList.get(i);
			
			if(!ahtMap.containsKey(aht.getAgreementId())) {
				ageStartTime = aht.getLastUpdationDate();
				aht.setAgeStartTime(ageStartTime);
				ahtMap.put(aht.getAgreementId(), aht);
				if (i>0){
					ahtTemp = ahtList.get(i-1);
					ageStopTime = ahtTemp.getLastUpdationDate();
					ahtAge = DocumentWorkflowToolUtility.getTimeDifferenceInMin(ageStartTime, ageStopTime);
					ahtMap.get(ahtTemp.getAgreementId()).setAge(ahtAge);
					log.debug("AgreementID ..."+ahtTemp.getAgreementId()+":: AgeStartTime :: "+ageStartTime+"::AgeStopTime :: "+ageStopTime+" Age..."+ahtAge);
				}
		    }
			if (i==(ahtList.size()-1)){
				ageStopTime = aht.getLastUpdationDate();
				ahtAge = DocumentWorkflowToolUtility.getTimeDifferenceInMin(ahtMap.get(aht.getAgreementId()).getAgeStartTime(), ageStopTime);
				ahtMap.get(aht.getAgreementId()).setAge(ahtAge);
				log.debug("AgreementID ..."+aht.getAgreementId()+":: AgeStartTime :: "+ageStartTime+"::AgeStopTime :: "+ageStopTime+" Age..."+ahtAge);
			}
			
		}
	}
	
	public List<DocWkflwProcess> extractAgreementsAuditTrail(String rptFromDate, String rptToDate) throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.QUERY_FOR_AUDITTRAIL;
		
		List<DocWkflwProcess> docRepoList = null;
		try{
			docRepoList = this.getJdbcTemplateObject().query(SQL, new Object[]{rptFromDate, rptToDate}, new AgreementAuditTrailMapper());
		} catch(EmptyResultDataAccessException e) {
			docRepoList = null;
		}
		return docRepoList;
	}
	
}
