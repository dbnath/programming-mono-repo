package com.myorg.tools.documentworkflow.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.AgreementType;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class AgreementTypeMapper implements RowMapper<AgreementType> {
	
	public AgreementType mapRow(ResultSet rs, int rowNum) throws SQLException {
		AgreementType docType = new AgreementType();
      if (! DocumentWorkflowToolUtility.isEmpty(rs)) {
    	  docType.setAgreementTypeId(rs.getInt("ID_AGREEMENT_TYPE"));
    	  docType.setAgrementTypeName(rs.getString("TX_AGREEMENT_TYPE"));
      }
      return docType;
    }

}
