package com.myorg.tools.documentworkflow.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.AgreementErrorType;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class AgreementErrorTypeMapper implements RowMapper<AgreementErrorType> {
	
	public AgreementErrorType mapRow(ResultSet rs, int rowNum) throws SQLException {
		AgreementErrorType docType = new AgreementErrorType();
      if (! DocumentWorkflowToolUtility.isEmpty(rs)) {
    	  docType.setErrorTypeId(rs.getInt("ID_ERR_REASON"));
    	  docType.setErrorTypeName(rs.getString("TX_ERR_REASON"));
    	  docType.setErrorTypeCode(rs.getString("NM_ERR_REASON"));
      }
      return docType;
    }

}
