package com.myorg.tools.documentworkflow.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.myorg.tools.documentworkflow.constant.DocumentWorkflowToolConstant;
import com.myorg.tools.documentworkflow.dao.DocumentAdminDAO;
import com.myorg.tools.documentworkflow.model.Document;
import com.myorg.tools.documentworkflow.model.DocumentRepository;
import com.myorg.tools.documentworkflow.model.DocumentSubTagValues;
import com.myorg.tools.documentworkflow.model.DocumentTag;
import com.myorg.tools.documentworkflow.model.DocumentTagSubTagMapping;
import com.myorg.tools.documentworkflow.model.DocumentType;
import com.myorg.tools.documentworkflow.model.DocumentTypeTagMapping;
import com.myorg.tools.documentworkflow.model.ReverseMappable;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class DocumentAdminDAOImpl extends BaseJDBCTemplate implements DocumentAdminDAO {

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


	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.dao.DocumentAdminDAO#uploadDocumentInformation(java.util.List)
	 */
	@Override
	public boolean uploadDocumentInformation(List<Document> docList, String userId) throws SQLException, Exception {
		
		if(docList != null){
			
			HashMap<String, Integer> typeMap = DocumentWorkflowToolUtility.mapByValue(new ArrayList<ReverseMappable>(populateDocumentTypes()));
			HashMap<String, Integer> repoMap = DocumentWorkflowToolUtility.mapByValue(new ArrayList<ReverseMappable>(populateDocumentRepos()));
			
			System.out.println("###### typeMap "+typeMap);
			System.out.println("###### repoMap "+repoMap);
			
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
						
						insertDocumentsIntoDatabase(jdbcCall, doc);
					}
				}
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
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
		
		Map<String, Object> out = jdbcCall.execute(in);
	}
	
	
}
