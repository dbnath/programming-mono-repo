package com.myorg.tools.documentworkflow.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.DocWkflwProcess;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class CheckerAgreementWorkflowMapper implements RowMapper<DocWkflwProcess> {
	public DocWkflwProcess mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocWkflwProcess docWorkflow = new DocWkflwProcess();
		if (!DocumentWorkflowToolUtility.isEmpty(rs)) {
			docWorkflow.setAgreementId(rs.getInt("ID_AGRMT"));
			docWorkflow.setAgreementTypeCode(rs.getInt("ID_AGREEMENT_TYPE"));
			docWorkflow.setAgreementTypeDesc(rs.getString("TX_AGREEMENT_TYPE"));
			docWorkflow.setRoleId(rs.getInt("ID_ROLE"));
			docWorkflow.setStatusCode(rs.getInt("ID_WF_STATUS"));
			docWorkflow.setStatusDescription(rs.getString("TX_WF_STATUS"));
			docWorkflow.setMakerStatusId(rs.getInt("ID_WF_MAKER_STATUS"));
			docWorkflow.setMakerStatus(rs.getString("TX_WF_MAKER_STATUS"));
			docWorkflow.setMakerComments(rs.getString("MAKER_COM"));
			docWorkflow.setLob(rs.getString("LOB"));
		    docWorkflow.setAssignedTo(rs.getString("ASSIGNED_TO"));
		    docWorkflow.setCreatedBy(rs.getString("CREATED_BY"));
		    docWorkflow.setCreationDate(rs.getDate("CREATED_DT"));
		    docWorkflow.setLastUpdatedBy(rs.getString("LAST_UPDATED_BY"));
		    docWorkflow.setLastUpdationDate(rs.getDate("LAST_UPDATE_DT"));
		    docWorkflow.setCheckerComments(DocumentWorkflowToolUtility.isEmptyValue(rs.getString("CHECKER_COM"))?"":rs.getString("CHECKER_COM"));
		    docWorkflow.setErrorReason(DocumentWorkflowToolUtility.isEmptyValue(rs.getString("TX_ERR_REASON"))?"":rs.getString("TX_ERR_REASON"));
		    docWorkflow.setNumPages(rs.getInt("NUM_PAGES"));
		    docWorkflow.setNumFields(rs.getInt("NUM_FIELDS"));
		}
		return docWorkflow;
    }

}
