package com.myorg.tools.documentworkflow.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.AHTBean;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class AHTRowMapper implements RowMapper<AHTBean> {
	
	public AHTBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		AHTBean docWorkflow = new AHTBean();
	      if (! DocumentWorkflowToolUtility.isEmpty(rs)) { 
				docWorkflow.setAgreementId(rs.getString("ID_AGRMT"));
				docWorkflow.setRoleId(rs.getInt("ID_ROLE"));
				docWorkflow.setStatusCode(rs.getInt("ID_WF_STATUS"));
				docWorkflow.setIsClockStart(rs.getString("IS_CLOCK_START"));
				docWorkflow.setIsClockStop(rs.getString("IS_CLOCK_STOP"));
				//System.out.println("###### MAPPER"+rs.getDate("LAST_UPDATE_DT")+"######"+rs.getTime("LAST_UPDATE_DT"));
				
				/*Calendar cal = Calendar.getInstance();
				cal.setTime(rs.getDate("LAST_UPDATE_DT"));
				cal.setTime(rs.getTime("LAST_UPDATE_DT"));*/
				
				docWorkflow.setLastUpdationDate(rs.getTimestamp("LAST_UPDATE_DT"));
				//System.out.println("###### MAPPER"+docWorkflow.getLastUpdationDate());
	      }
      return docWorkflow;
    }

}
