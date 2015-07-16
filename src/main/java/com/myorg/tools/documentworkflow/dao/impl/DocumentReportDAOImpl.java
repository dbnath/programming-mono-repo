package com.myorg.tools.documentworkflow.dao.impl;

import java.util.List;

import com.myorg.tools.documentworkflow.constant.DocumentWorkflowToolConstant;
import com.myorg.tools.documentworkflow.dao.DocumentReportDAO;
import com.myorg.tools.documentworkflow.model.DonutReport;
import com.myorg.tools.documentworkflow.model.DocumentWorkflow;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class DocumentReportDAOImpl extends BaseJDBCTemplate implements DocumentReportDAO {

	@Override
	public List<DonutReport> fetchCompletionReportData() 
	{
		List<DonutReport> compRptData = null;
		String SQL = DocumentWorkflowToolConstant.COMPLETION_STATUS_RPT_SQL;
		compRptData = this.getJdbcTemplateObject().query(SQL, new DocumentCompletionReportMapper());
		return compRptData;
	}

}
