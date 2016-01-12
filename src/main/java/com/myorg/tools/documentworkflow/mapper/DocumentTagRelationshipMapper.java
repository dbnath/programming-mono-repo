package com.myorg.tools.documentworkflow.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.DocumentTagRelationship;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class DocumentTagRelationshipMapper implements RowMapper<DocumentTagRelationship> {

	public DocumentTagRelationship mapRow(ResultSet rs, int rowNum) throws SQLException {
      DocumentTagRelationship docTagRelationship = new DocumentTagRelationship();
      if (! DocumentWorkflowToolUtility.isEmpty(rs)) {
	      docTagRelationship.setDocId(rs.getInt("ID_DOC"));
	      docTagRelationship.setDocTypeId(rs.getInt("ID_DOC_TYPE"));
	      docTagRelationship.setDocTypeDesc(rs.getString("TX_DOC_TYPE"));
	      docTagRelationship.setDocTagId(rs.getInt("ID_DOC_TAG"));
	      docTagRelationship.setDocTagDesc(rs.getString("TX_DOC_TAG"));
	      docTagRelationship.setDocSubTagId(rs.getInt("ID_DOC_SUB_TAG"));
	      docTagRelationship.setDocSubTagDesc(rs.getString("TX_DOC_SUB_TAG"));
	      docTagRelationship.setCreationDt(rs.getDate("CREATION_DT"));
	      docTagRelationship.setCreatedBy(rs.getString("CREATED_BY"));
	      docTagRelationship.setLastUpdatedDt(rs.getDate("LAST_UPDATE_DT"));
	      docTagRelationship.setLastUpdatedBy(rs.getString("LAST_UPDATED_BY"));
      }
      return docTagRelationship;
    }
}
