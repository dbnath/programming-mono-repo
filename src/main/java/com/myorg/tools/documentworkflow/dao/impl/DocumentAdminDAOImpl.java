package com.myorg.tools.documentworkflow.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.myorg.tools.documentworkflow.constant.DocumentWorkflowToolConstant;
import com.myorg.tools.documentworkflow.dao.DocumentAdminDAO;
import com.myorg.tools.documentworkflow.model.DocumentRepository;
import com.myorg.tools.documentworkflow.model.DocumentSubTagValues;
import com.myorg.tools.documentworkflow.model.DocumentTag;
import com.myorg.tools.documentworkflow.model.DocumentTagSubTagMapping;
import com.myorg.tools.documentworkflow.model.DocumentType;
import com.myorg.tools.documentworkflow.model.DocumentTypeTagMapping;
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
}
