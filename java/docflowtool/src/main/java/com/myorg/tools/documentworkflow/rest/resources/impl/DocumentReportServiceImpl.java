package com.myorg.tools.documentworkflow.rest.resources.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import com.myorg.tools.documentworkflow.dao.DocumentReportDAO;
import com.myorg.tools.documentworkflow.model.DonutReport;
import com.myorg.tools.documentworkflow.rest.resources.BaseResource;
import com.myorg.tools.documentworkflow.rest.resources.DocumentReportService;

public class DocumentReportServiceImpl extends BaseResource implements DocumentReportService{
	
	private DocumentReportDAO documentReportDAO;
	
	/**
	 * @return the documentReportDAO
	 */
	public DocumentReportDAO getDocumentReportDAO() {
		return documentReportDAO;
	}

	/**
	 * @param documentReportDAO the documentReportDAO to set
	 */
	public void setDocumentReportDAO(DocumentReportDAO documentReportDAO) {
		this.documentReportDAO = documentReportDAO;
	}



	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.rest.resources.DocumentReportService#getCompletionDocumentReport()
	 */
	@Override
	public Response getCompletionDocumentReport() {
		try {
			List<DonutReport> compRptData = documentReportDAO.fetchCompletionReportData();
			return Response.ok().entity(compRptData).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	

}
