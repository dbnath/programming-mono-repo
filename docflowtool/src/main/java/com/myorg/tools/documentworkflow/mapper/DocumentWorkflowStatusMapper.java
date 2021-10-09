package com.myorg.tools.documentworkflow.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.DocumentWorkflowStatus;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class DocumentWorkflowStatusMapper implements RowMapper<DocumentWorkflowStatus> {
	public DocumentWorkflowStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocumentWorkflowStatus docWorkflowStatus = new DocumentWorkflowStatus();
		if (!DocumentWorkflowToolUtility.isEmpty(rs)) {
			docWorkflowStatus.setStatusCode(rs.getInt("ID_WF_STATUS"));
			docWorkflowStatus.setStatusDescription(rs.getString("TX_WF_STATUS"));
		}
		return docWorkflowStatus;
    }

}
