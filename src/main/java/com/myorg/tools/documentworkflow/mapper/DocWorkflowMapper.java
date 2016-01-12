package com.myorg.tools.documentworkflow.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.DocumentWorkflow;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class DocWorkflowMapper implements RowMapper<DocumentWorkflow> {
	public DocumentWorkflow mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocumentWorkflow docWorkflow = new DocumentWorkflow();
		if (!DocumentWorkflowToolUtility.isEmpty(rs)) {
			docWorkflow.setDocId(rs.getInt("ID_DOC"));
			docWorkflow.setWfStatusId(rs.getInt("ID_WF_STATUS"));
		    docWorkflow.setIsReworked(rs.getString("IS_REWORKED"));
		    docWorkflow.setAssignedTo(rs.getString("ASSIGNED_TO"));
		    docWorkflow.setUserRole(rs.getString("ID_ROLE"));
		    docWorkflow.setAssignedDt(rs.getDate("ASSIGNED_DT"));
		    docWorkflow.setLastUpdatedBy(rs.getString("LAST_UPDATED_BY"));
		    docWorkflow.setLastUpdateDt(rs.getDate("LAST_UPDATE_DT"));
		}
		return docWorkflow;
    }

}
