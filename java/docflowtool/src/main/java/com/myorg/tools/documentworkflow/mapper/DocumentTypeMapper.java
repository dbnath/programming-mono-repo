package com.myorg.tools.documentworkflow.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.DocumentType;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class DocumentTypeMapper implements RowMapper<DocumentType> {
	
	public DocumentType mapRow(ResultSet rs, int rowNum) throws SQLException {
      DocumentType docType = new DocumentType();
      if (! DocumentWorkflowToolUtility.isEmpty(rs)) {
    	  docType.setDocTypeId(rs.getInt("ID_DOC_TYPE"));
    	  docType.setDocTypeName(rs.getString("TX_DOC_TYPE"));
      }
      return docType;
    }

}
