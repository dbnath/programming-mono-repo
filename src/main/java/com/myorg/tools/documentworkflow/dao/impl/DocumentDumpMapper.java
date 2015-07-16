package com.myorg.tools.documentworkflow.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.DocumentTagReport;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class DocumentDumpMapper implements RowMapper<DocumentTagReport> {
	
	public DocumentTagReport mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocumentTagReport docRepo = new DocumentTagReport();
      if (! DocumentWorkflowToolUtility.isEmpty(rs)) {
    	  
    	  docRepo.setDocId(rs.getInt("ID_DOC"));
    	  docRepo.setDocName(rs.getString("NM_DOC"));
    	  docRepo.setDocTagDesc(rs.getString("TX_DOC_TAG"));
    	  docRepo.setDocSubTagDesc(rs.getString("TX_DOC_SUB_TAG"));
      }
      return docRepo;
    }

}
