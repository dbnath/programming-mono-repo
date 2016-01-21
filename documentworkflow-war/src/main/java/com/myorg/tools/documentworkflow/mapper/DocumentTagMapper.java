package com.myorg.tools.documentworkflow.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.DocumentTag;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class DocumentTagMapper implements RowMapper<DocumentTag> {
	
	public DocumentTag mapRow(ResultSet rs, int rowNum) throws SQLException {
	  DocumentTag docTagInfo = new DocumentTag();
      if (! DocumentWorkflowToolUtility.isEmpty(rs)) {
    	  docTagInfo.setDocTagId(rs.getInt("ID_DOC_TAG"));
    	  docTagInfo.setDocTagDesc(rs.getString("TX_DOC_TAG"));
      }
      return docTagInfo;
    }

}
