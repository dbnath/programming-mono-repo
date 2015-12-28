package com.myorg.tools.documentworkflow.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.AgreementStatus;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class AgreementStatusMapper implements RowMapper<AgreementStatus> {
	
	public AgreementStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
		AgreementStatus docType = new AgreementStatus();
      if (! DocumentWorkflowToolUtility.isEmpty(rs)) {
    	  docType.setAgreementStatusId(rs.getInt("ID_WF_STATUS"));
    	  docType.setAgreementStatusName(rs.getString("TX_WF_STATUS"));
    	  docType.setIsClockStart(rs.getString("IS_CLOCK_START"));
    	  docType.setIsClockStop(rs.getString("IS_CLOCK_STOP"));
      }
      return docType;
    }

}
