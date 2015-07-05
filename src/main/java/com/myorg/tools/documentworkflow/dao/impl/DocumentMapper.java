package com.myorg.tools.documentworkflow.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.Document;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class DocumentMapper implements RowMapper<Document> {

	public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
      Document doc = new Document();
      if (! DocumentWorkflowToolUtility.isEmpty(rs)) {
	      doc.setDocId(rs.getInt("ID_DOC"));
	      doc.setDocName(rs.getString("NM_DOC"));
	      doc.setDocTypeId(rs.getInt("ID_DOC_TYPE"));
	      doc.setDocTypeDesc(rs.getString("TX_DOC_TYPE"));
	      doc.setDocRepoId(rs.getInt("ID_DOC_REPO"));
	      doc.setDocRepoDesc(rs.getString("TX_DOC_REPO"));
	      doc.setDocLocation(rs.getString("DOC_LOCATION"));
	      doc.setIsBadLinkReported(rs.getString("IS_VALID_LINK"));
	      doc.setDocHyperlink(rs.getString("DOC_HYPERLINK"));
	      doc.setIsDeleted(rs.getString("IS_DELETED"));
	      doc.setCreationDt(rs.getDate("CREATION_DT"));
	      doc.setCreatedBy(rs.getString("CREATED_BY"));
	      doc.setLastUpdatedDt(rs.getDate("LAST_UPDATE_DT"));
	      doc.setLastUpdatedBy(rs.getString("LAST_UPDATED_BY"));
      }
      return doc;
    }
}
