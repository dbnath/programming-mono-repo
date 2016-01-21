package com.myorg.tools.documentworkflow.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.DocumentWorkflowDetail;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class DocumentWorkflowDetailMapper implements RowMapper<DocumentWorkflowDetail> {
	
	public DocumentWorkflowDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
      DocumentWorkflowDetail docWkflDetail = new DocumentWorkflowDetail();
      if (! DocumentWorkflowToolUtility.isEmpty(rs)) {
	      docWkflDetail.setDocId(rs.getInt("ID_DOC"));
	      docWkflDetail.setTagOverrideReason(rs.getString("TX_OVRWRT_REASON"));
	      docWkflDetail.setTargetDocLocation(rs.getString("TGT_LOCATION"));
	      docWkflDetail.setLastUpdatedBy(rs.getString("LAST_UPDATED_BY"));
	      docWkflDetail.setLastUpdatedDt(rs.getDate("LAST_UPDATE_DT"));
      }
      return docWkflDetail;
    }
}
