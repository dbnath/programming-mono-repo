package com.myorg.tools.documentworkflow.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.DocumentSubTagValues;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class DocumentSubTagValuesMapper implements RowMapper<DocumentSubTagValues> {
	
	public DocumentSubTagValues mapRow(ResultSet rs, int rowNum) throws SQLException {
	  DocumentSubTagValues docSubTagInfo = new DocumentSubTagValues();
      if (! DocumentWorkflowToolUtility.isEmpty(rs)) {
    	  docSubTagInfo.setDocSubTagId(rs.getInt("ID_DOC_SUB_TAG"));
    	  docSubTagInfo.setDocSubTagDesc(rs.getString("TX_DOC_SUB_TAG"));
      }
      return docSubTagInfo;
    }

}
