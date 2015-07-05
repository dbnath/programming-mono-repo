package com.myorg.tools.documentworkflow.dao;

import java.sql.SQLException;
import java.util.List;

import com.myorg.tools.documentworkflow.model.DocumentRepository;
import com.myorg.tools.documentworkflow.model.DocumentSubTagValues;
import com.myorg.tools.documentworkflow.model.DocumentTag;
import com.myorg.tools.documentworkflow.model.DocumentTagSubTagMapping;
import com.myorg.tools.documentworkflow.model.DocumentType;
import com.myorg.tools.documentworkflow.model.DocumentTypeTagMapping;

public interface DocumentAdminDAO {
	
	public List<DocumentType> populateDocumentTypes() throws SQLException, Exception;

	public List<DocumentRepository> populateDocumentRepos() throws SQLException, Exception;

	public List<DocumentTag> populateDocumentTagsMasterList() throws SQLException, Exception;

	public List<DocumentSubTagValues> populateDocumentSubTagsMasterList() throws SQLException, Exception;

	public DocumentTypeTagMapping populateDocumentTypeTagMapping(Integer docTypeId) throws SQLException, Exception;

	public List<DocumentTag> populateUnmappedDocTypeTags(Integer docTypeId) throws SQLException, Exception;

	public DocumentTagSubTagMapping populateDocumentTagSubTagMapping(Integer docTagId) throws SQLException, Exception;

	public List<DocumentSubTagValues> populateUnmappedDocTagSubTags(Integer docTagId) throws SQLException, Exception;

	/*public Boolean updateDocumentTypes(List<DocumentType> docTypeList) throws SQLException, Exception;

	public Boolean updateDocumentRepos(List<DocumentRepository> doRepoList) throws SQLException, Exception;

	public Boolean updateDocumentTagsMasterList(List<DocumentTag> docTagList) throws SQLException, Exception;

	public Boolean updateDocumentSubTagsMasterList(List<DocumentSubTagValues> docSubTagsList) throws SQLException, Exception;

	public Boolean updateDocumentTypeTagMap(DocumentTypeTagMapping  docTypeTagMapping) throws SQLException, Exception;

	public Boolean updateDocumentTagSubTagMap(DocumentTagSubTagMapping  docTagSubTagMapping) throws SQLException, Exception;
	*/
}
