package com.myorg.tools.documentworkflow.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.DocumentWorkflow;

public class DocumentWorkflowMapper implements RowMapper<DocumentWorkflow> {
	public DocumentWorkflow mapRow(ResultSet rs, int rowNum) throws SQLException {
      DocumentWorkflow docWorkflow = new DocumentWorkflow();
      docWorkflow.setDocId(rs.getInt("docId"));
      docWorkflow.setWfStatus(rs.getString("status"));
      docWorkflow.setWfGroup(rs.getString("group"));
      docWorkflow.setUserId(rs.getString("userId"));
      docWorkflow.setUserRole(rs.getString("roleId"));
      docWorkflow.setLastUpdateDt(rs.getDate("lastUpdateDt"));
      docWorkflow.setReworked(rs.getString("isReworked"));
      return docWorkflow;
    }
}
