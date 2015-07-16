package com.myorg.tools.documentworkflow.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.DonutReport;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class DocumentCompletionReportMapper implements RowMapper<DonutReport> {
	
	public DonutReport mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonutReport docRepo = new DonutReport();
      if (! DocumentWorkflowToolUtility.isEmpty(rs)) {
    	  docRepo.setValue(rs.getInt("DOC_CNT"));
    	  docRepo.setLabel(rs.getString("QNAME"));
      }
      return docRepo;
    }

}
