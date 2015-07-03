package com.myorg.tools.documentworkflow.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.DocumentWorkflowDetail;

public class DocumentWorkflowDetailMapper implements RowMapper<DocumentWorkflowDetail> {
	
	public DocumentWorkflowDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
      DocumentWorkflowDetail docWkflDetail = new DocumentWorkflowDetail();
      docWkflDetail.setDocId(rs.getInt("docId"));
      docWkflDetail.setTagOverrideReason(rs.getString("txTagOverrideReason"));
      docWkflDetail.setTargetDocLocation(rs.getString("targetDocLocation"));
      docWkflDetail.setCreatedBy(rs.getString("createdBy"));
      docWkflDetail.setCreationDt(rs.getDate("creationDt"));
      docWkflDetail.setLastUpdatedBy(rs.getString("lastUpdatedBy"));
      docWkflDetail.setLastUpdatedDt(rs.getDate("lastUpdatedDt"));
      return docWkflDetail;
    }
}
