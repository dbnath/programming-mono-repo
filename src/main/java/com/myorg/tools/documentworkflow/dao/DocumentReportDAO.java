package com.myorg.tools.documentworkflow.dao;

import java.util.List;

import com.myorg.tools.documentworkflow.model.DonutReport;

public interface DocumentReportDAO {
	
	public List<DonutReport> fetchCompletionReportData();

}
