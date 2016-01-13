package com.myorg.tools.documentworkflow.rest.resources.impl;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.myorg.tools.documentworkflow.dao.DocumentAdminDAO;
import com.myorg.tools.documentworkflow.model.AHTBean;
import com.myorg.tools.documentworkflow.model.AHTWrapper;
import com.myorg.tools.documentworkflow.model.AgreementErrorType;
import com.myorg.tools.documentworkflow.model.AgreementType;
import com.myorg.tools.documentworkflow.model.AgreementWorkflow;
import com.myorg.tools.documentworkflow.model.DocWkflwProcess;
import com.myorg.tools.documentworkflow.model.DocumentRepository;
import com.myorg.tools.documentworkflow.model.DocumentSubTagValues;
import com.myorg.tools.documentworkflow.model.DocumentTag;
import com.myorg.tools.documentworkflow.model.DocumentTagSubTagMapping;
import com.myorg.tools.documentworkflow.model.DocumentType;
import com.myorg.tools.documentworkflow.model.DocumentTypeTagMapping;
import com.myorg.tools.documentworkflow.model.DocumentTypeTagSubTagsMap;
import com.myorg.tools.documentworkflow.rest.resources.BaseResource;
import com.myorg.tools.documentworkflow.rest.resources.DocumentAdminService;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;
import com.myorg.tools.documentworkflow.util.ExcelUtil;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

public class DocumentAdminServiceImpl extends BaseResource implements DocumentAdminService {
	
	private DocumentAdminDAO documentAdminDAO;
	
	/**
	 * @return the documentAdminDAO
	 */
	public DocumentAdminDAO getDocumentAdminDAO() {
		return documentAdminDAO;
	}
	/**
	 * @param documentAdminDAO the documentAdminDAO to set
	 */
	public void setDocumentAdminDAO(DocumentAdminDAO documentAdminDAO) {
		this.documentAdminDAO = documentAdminDAO;
	}
	
	public Response populateDocType() {
		try{
			List<DocumentType> docTypeList = documentAdminDAO.populateDocumentTypes();
			return Response.ok().entity(docTypeList).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();			
		}
	}
	public Response populateDocRepository() {
		try{
			List<DocumentRepository> docRepoList = documentAdminDAO.populateDocumentRepos();
			return Response.ok().entity(docRepoList).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();			
		}
	}
	public Response populateDocTagsMaster() {
		try{
			List<DocumentTag> docTagMasterList = documentAdminDAO.populateDocumentTagsMasterList();
			return Response.ok().entity(docTagMasterList).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();			
		}
	}
	public Response populateDocSubTagsMaster() {
		try{
			List<DocumentSubTagValues> docSubTagMasterList = documentAdminDAO.populateDocumentSubTagsMasterList();
			return Response.ok().entity(docSubTagMasterList).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();			
		}
	}
	public Response populateDocTypeTagsMapping(Integer docTypeId){
		try{
			DocumentTypeTagMapping docTypeTagMap = documentAdminDAO.populateDocumentTypeTagMapping(docTypeId);
			return Response.ok().entity(docTypeTagMap).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();			
		}
	}
	public Response populateUnmappedDocTypeTags(Integer docTypeId){
		try{
			List<DocumentTag> docUnmappedTagMasters = documentAdminDAO.populateUnmappedDocTypeTags(docTypeId);
			return Response.ok().entity(docUnmappedTagMasters).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();			
		}
	}
	public Response populateDocTagSubTagMapping(Integer docTagId){
		try{
			DocumentTagSubTagMapping docTagSubTagMap = documentAdminDAO.populateDocumentTagSubTagMapping(docTagId);
			return Response.ok().entity(docTagSubTagMap).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();			
		}
	}
	public Response populateUnmappedDocTagSubTags(Integer docTagId){
		try{
			List<DocumentSubTagValues> docUnmappedSubTagMasters = documentAdminDAO.populateUnmappedDocTagSubTags(docTagId);
			return Response.ok().entity(docUnmappedSubTagMasters).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();			
		}
	}
	
	public Response populateDocTypeTagSubTagsMap(Integer docTypeId) {
		try{
			DocumentTypeTagSubTagsMap docTypeTagSubTagsMap = documentAdminDAO.populateDocumentTypeTagSubTagsMap(docTypeId);
			return Response.ok().entity(docTypeTagSubTagsMap).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();			
		}
	}
	
	//public Response uploadDocuments(@FormDataParam("file") InputStream uploadedInputStream,  @FormDataParam("file") FormDataContentDisposition fileDetail, @FormDataParam("path") String path, @FormDataParam("userId") String userId){
	public Response uploadDocuments(@FormDataParam("file") InputStream uploadedInputStream,  @FormDataParam("userId") String userId){
		
		try {
			
			/*User user = getLoggedInUser();
			userId = user.getUserId();*/
			
			System.out.println("###### User id to upload doc "+userId);
			List<AgreementWorkflow> docList = parseBulkUploadFile(uploadedInputStream);
			//documentAdminDAO.uploadDocumentInformation(docList, userId);
			boolean uploadStatus = documentAdminDAO.uploadAgreementInformation(docList, userId);
			
			if(!uploadStatus){
				throw new Exception("Issue with Bulk Upload");
			}			
			
			return Response.ok().entity("<html><head><script>function refreshParent(){window.close();}</script></head><body><div>Document Uploaded Successfully</div><input type=\"Button\" value=\"Close Window\" onclick=\"refreshParent()\" /></body></html>").build();
			
		} catch (IOException e) {
			e.printStackTrace();
			return Response.serverError().entity("Documents failed to upload").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().entity("Documents failed to upload").build();
		}
		
	}
	
	public Response uploadErrReasons(@FormDataParam("file") InputStream uploadedInputStream,  @FormDataParam("file") FormDataContentDisposition fileDetail, @FormDataParam("path") String path, @FormDataParam("userId") String userId){
		
		try {
			ExcelUtil util = new ExcelUtil();
			List<AgreementErrorType> docList = util.parseErrReasonUploadFile(uploadedInputStream);
			boolean uploadStatus = documentAdminDAO.uploadErrorReasons(docList, userId);
			
			if(!uploadStatus){
				throw new Exception("Issue with Bulk Upload");
			}
			
			return Response.ok().entity("<html><head><script>function refreshParent(){window.close();}</script></head><body><div>Document Uploaded Successfully</div><input type=\"Button\" value=\"Close Window\" onclick=\"refreshParent()\" /></body></html>").build();
			
		} catch (IOException e) {
			e.printStackTrace();
			return Response.serverError().entity("Documents failed to upload").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().entity("Documents failed to upload").build();
		}
	}
	
	public Response uploadAgreementType(@FormDataParam("file") InputStream uploadedInputStream,  @FormDataParam("file") FormDataContentDisposition fileDetail, @FormDataParam("path") String path, @FormDataParam("userId") String userId){
		
		try {
			ExcelUtil util = new ExcelUtil();
			List<AgreementType> docList = util.parseAgreementTypeUploadFile(uploadedInputStream);
			boolean uploadStatus = documentAdminDAO.uploadAgreementTypes(docList, userId);
			
			if(!uploadStatus){
				throw new Exception("Issue with Bulk Upload");
			}
			
			return Response.ok().entity("<html><head><script>function refreshParent(){window.close();}</script></head><body><div>Document Uploaded Successfully</div><input type=\"Button\" value=\"Close Window\" onclick=\"refreshParent()\" /></body></html>").build();
			
		} catch (IOException e) {
			e.printStackTrace();
			return Response.serverError().entity("Documents failed to upload").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().entity("Documents failed to upload").build();
		}
	}	
	
	
	private List<AgreementWorkflow> parseBulkUploadFile(InputStream stream) throws IOException {

		List<AgreementWorkflow> docList = new ArrayList<AgreementWorkflow>();
		if (stream != null) {
			XSSFWorkbook wb = new XSSFWorkbook(stream);

			XSSFSheet sheet = wb.getSheetAt(0);

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				XSSFRow row = sheet.getRow(i);

				if (row != null) {

					AgreementWorkflow doc = new AgreementWorkflow();

					for (int j = 0; j < 5; j++) {
						XSSFCell cell = row.getCell(j);

						if (cell != null) {
							int colNum = cell.getColumnIndex();

							int cellType = cell.getCellType();

							String s = "";

							switch (cellType) {
							case XSSFCell.CELL_TYPE_STRING:
								s = cell.getRichStringCellValue().getString();
								break;
							case XSSFCell.CELL_TYPE_NUMERIC:
								s = String.valueOf(cell.getNumericCellValue());
								break;
							case XSSFCell.CELL_TYPE_BOOLEAN:
								s = String.valueOf(cell.getBooleanCellValue());
								break;
							case XSSFCell.CELL_TYPE_FORMULA:
								s = String.valueOf(cell.getCellFormula());
								break;
							case XSSFCell.CELL_TYPE_ERROR:
								s = cell.getErrorCellString();
								break;

							}
							 

							//String s = cell.getStringCellValue(); // cell.getRichStringCellValue().getString();

							switch (colNum) {
							case 0:
								doc.setAgreementId(s);
								System.out.println("###### agreemen id "+s);
								break;
							case 1:
								doc.setAgreementTypeDesc(s);
								System.out.println("###### agreemen type "+s);
								break;
							case 2:
								doc.setLob(s);
								System.out.println("###### lob "+s);
								break;
							case 3:
								doc.setWfStatusDesc(DocumentWorkflowToolUtility.isEmpty(s) ? "New" : s);
								System.out.println("###### status id "+s);
								break;
							case 4:
								doc.setAssignedTo(s);
								System.out.println("###### assigned to "+s);
								break;
							}

						}
						
					}
					docList.add(doc);
				}

			}
		}

		return docList;
	}
	
	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.rest.resources.DocumentAdminService#getTemplate()
	 */
	@Override
	public Response getDocUploadTemplate() {
		
		try {
			List<AgreementType> agrTypList = documentAdminDAO.populateAgreementTypes();
			ExcelUtil util = new ExcelUtil();
			XSSFWorkbook wb = util.generateDocUploadTemplate(agrTypList);
			
			File file = new File(this.getAppConfig().getTempFileLocation()+"/AgreementUploadTemplate"+System.currentTimeMillis()+".xlsx");
			
			FileOutputStream baos = new FileOutputStream(file);
			wb.write(baos);
			baos.close();			
			
			return Response.ok(file).header("Content-Disposition", "attachment; filename=\"AgreementUploadTemplate.xlsx\"").build();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(404).entity("Template Not Available: ").type("text/plain").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(404).entity("Template Not Available: ").type("text/plain").build();
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.rest.resources.DocumentAdminService#getTemplate()
	 */
	@Override
	public Response getErrTypeUploadTemplate() {
		
		try {
			List<AgreementErrorType> agrTypList = documentAdminDAO.populateErrorTypes();
			ExcelUtil util = new ExcelUtil();
			XSSFWorkbook wb = util.generateErrTypeUploadTemplate(agrTypList);
			
			File file = new File(this.getAppConfig().getTempFileLocation()+"/ErrorTypeUploadTemplate"+System.currentTimeMillis()+".xlsx");
			
			FileOutputStream baos = new FileOutputStream(file);
			wb.write(baos);
			baos.close();			
			
			return Response.ok(file).header("Content-Disposition", "attachment; filename=\"ErrorTypeUploadTemplate.xlsx\"").build();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(404).entity("Template Not Available: ").type("text/plain").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(404).entity("Template Not Available: ").type("text/plain").build();
		}
		
	}	
	
	@Override
	public Response getArgmtTypeUploadTemplate() {
		
		try {
			List<AgreementType> agrTypList = documentAdminDAO.populateAgreementTypes();
			ExcelUtil util = new ExcelUtil();
			XSSFWorkbook wb = util.generateAgreementTypeUploadTemplate(agrTypList);
			
			File file = new File(this.getAppConfig().getTempFileLocation()+"/AgreementTypeUploadTemplate"+System.currentTimeMillis()+".xlsx");
			
			FileOutputStream baos = new FileOutputStream(file);
			wb.write(baos);
			baos.close();			
			
			return Response.ok(file).header("Content-Disposition", "attachment; filename=\"AgreementTypeUploadTemplate.xlsx\"").build();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(404).entity("Template Not Available: ").type("text/plain").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(404).entity("Template Not Available: ").type("text/plain").build();
		}
		
	}	

	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.rest.resources.DocumentAdminService#getTemplate()
	 */
	@Override
	public Response getTemplate() {
		
		try {
			List<DocumentRepository> repoList = documentAdminDAO.populateDocumentRepos();
			List<DocumentType> typeList = documentAdminDAO.populateDocumentTypes();
			
			String[] repoValues = new String[repoList.size()];
			int i=0;
			for(DocumentRepository repo : repoList){
				repoValues[i] = repo.getDocRepoName();
				i++;
			}
			
			i=0;
			String[] typeValues = new String[typeList.size()];
			for(DocumentType type : typeList){
				typeValues[i] = type.getDocTypeName();
				i++;
			}
			
			XSSFWorkbook wb = new XSSFWorkbook();			
			XSSFSheet sheet = wb.createSheet();
			XSSFCellStyle headerStyle = wb.createCellStyle();
			
			Font headerFont = wb.createFont();
			headerFont.setBold(true);
			
			createTemplateHeader(sheet, headerFont, headerStyle);
			createTemplateBody(sheet, repoValues, typeValues);
			
			File file = new File(this.getAppConfig().getTempFileLocation()+"/template"+System.currentTimeMillis()+".xlsx");
			
			FileOutputStream baos = new FileOutputStream(file);
			wb.write(baos);
			baos.close();
			
			return Response.ok(file).header("Content-Disposition", "attachment; filename=\"FileUploadTemplate.xlsx\"").build();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(404).entity("Template Not Available: ").type("text/plain").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(404).entity("Template Not Available: ").type("text/plain").build();
		}
		
	}
	
	private void createTemplateHeader(XSSFSheet sheet, Font headerFont, XSSFCellStyle headerStyle) {

		XSSFRow headerRow = sheet.createRow(0);

		XSSFColor headerColor = new XSSFColor(Color.DARK_GRAY);
		headerStyle.setFillBackgroundColor(headerColor);
		headerStyle.setFont(headerFont);

		for (int i = 0; i < 5; i++) {
			XSSFCell cell = headerRow.createCell(i);
			cell.setCellStyle(headerStyle);

			switch (i) {
			case 0:
				cell.setCellValue("Document Name");
				break;
			case 1:
				cell.setCellValue("Document Type");
				break;
			case 2:
				cell.setCellValue("Document Repository");
				break;
			case 3:
				cell.setCellValue("Document Hyperlink");
				break;
			case 4:
				cell.setCellValue("Document Location");
				break;
			}
		}
		return;
	}
	
	private void createTemplateBody(XSSFSheet sheet, String[] repoValues, String[] typeValues) {

		
		DataValidationHelper validationHelper = new XSSFDataValidationHelper(sheet);
		String[] allowedValues = new String[]{""};
		
        DataValidationConstraint repoConstraint = validationHelper.createExplicitListConstraint(repoValues);
        DataValidationConstraint typeConstraint = validationHelper.createExplicitListConstraint(typeValues);
        DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(allowedValues);
        
        org.apache.poi.ss.util.CellRangeAddressList repoAddressList = new org.apache.poi.ss.util.CellRangeAddressList(1, 1001,2, 2);
        org.apache.poi.ss.util.CellRangeAddressList typeAddressList = new org.apache.poi.ss.util.CellRangeAddressList(1, 1001,1, 1);
        DataValidation repoValidation = validationHelper.createValidation(repoConstraint, repoAddressList);
        DataValidation typeValidation = validationHelper.createValidation(typeConstraint, typeAddressList);
        DataValidation dataValidation = null;
        
        repoValidation.setSuppressDropDownArrow(true);        
        typeValidation.setSuppressDropDownArrow(true);
        //dataValidation.createPromptBox("Valid Values", "The following values are valid for this cell:" +allowedValues); 
        repoValidation.setShowPromptBox(true); 
        repoValidation.setErrorStyle(DataValidation.ErrorStyle.STOP); 
        repoValidation.createErrorBox("Validation Error", "You can only select values from dropdown");
        repoValidation.setShowErrorBox(true);
        sheet.addValidationData(repoValidation); 
        
        typeValidation.setShowPromptBox(true); 
        typeValidation.setErrorStyle(DataValidation.ErrorStyle.STOP); 
        typeValidation.createErrorBox("Validation Error", "You can only select values from dropdown");
        typeValidation.setShowErrorBox(true);
        sheet.addValidationData(typeValidation); 
        
        org.apache.poi.ss.util.CellRangeAddressList addressList = new org.apache.poi.ss.util.CellRangeAddressList(1002, 1048575,0,4);
        dataValidation = validationHelper.createValidation(constraint, addressList); 
        dataValidation.setSuppressDropDownArrow(false);        
        dataValidation.setShowPromptBox(true); 
        dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP); 
        dataValidation.createErrorBox("Validation Error", "You can enter maximum 1000 rows at a time");
        dataValidation.setShowErrorBox(true);
        sheet.addValidationData(dataValidation); 		
		return;
	}
	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.rest.resources.DocumentAdminService#getDocTagDump()
	 */
	@Override
	public Response getAgreementDataDump(Date rptFromDt, Date rptToDt) {
		
		try {
			if (DocumentWorkflowToolUtility.isEmpty(rptFromDt)) {
				rptFromDt = new Date();
			}
			if (DocumentWorkflowToolUtility.isEmpty(rptToDt)) {
				rptToDt = new Date();
			}
			rptFromDt = DocumentWorkflowToolUtility.populateDateTime(rptFromDt, 0, 0, 0);
			String rptFromDate = DocumentWorkflowToolUtility.convertDateToString(rptFromDt);
			rptToDt = DocumentWorkflowToolUtility.populateDateTime(rptToDt, 23, 59, 59);
			String rptToDate = DocumentWorkflowToolUtility.convertDateToString(rptToDt);
			AHTWrapper ahtWrapper = documentAdminDAO.extractAgreementAHTInfo(rptFromDate, rptToDate);
			
			List<DocWkflwProcess> agrmtList = ahtWrapper.getDocRepoList();
			Map<String, AHTBean> ahtMap  = ahtWrapper.getAhtMap();
			System.out.println("agrmtList size..."+agrmtList.size()+"#### ahtMap size..."+ahtMap.size());
			
			XSSFWorkbook wb = new XSSFWorkbook();			
			XSSFSheet sheet = wb.createSheet();
			XSSFCellStyle headerStyle = wb.createCellStyle();
			
			Font headerFont = wb.createFont();
			headerFont.setBold(true);
			
			createExcelReportHeader(sheet, headerFont, headerStyle);
			createExcelReportBody(sheet, agrmtList,ahtMap);
			
			File file = new File(this.getAppConfig().getTempFileLocation()+"/report"+System.currentTimeMillis()+".xlsx");
			
			FileOutputStream baos = new FileOutputStream(file);
			wb.write(baos);
			baos.close();
			
			return Response.ok(file).header("Content-Disposition", "attachment; filename=\"Agreement_Data_Dump.xlsx\"").build();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}	
	
	
	private void createExcelReportHeader(XSSFSheet sheet, Font headerFont, XSSFCellStyle headerStyle) {

		XSSFRow headerRow = sheet.createRow(0);

		XSSFColor headerColor = new XSSFColor(Color.DARK_GRAY);
		headerStyle.setFillBackgroundColor(headerColor);
		headerStyle.setFont(headerFont);

		for (int i = 0; i < 16; i++) {
			XSSFCell cell = headerRow.createCell(i);
			cell.setCellStyle(headerStyle);

			switch (i) {
			case 0:
				cell.setCellValue("Agreement ID");
				break;
			case 1:
				cell.setCellValue("Agreement Type");
				break;
			case 2:
				cell.setCellValue("LOB");
				break;
			case 3:
				cell.setCellValue("Num of Pages");
				break;
			case 4:
				cell.setCellValue("Num of Fields");
				break;				
			case 5:
				cell.setCellValue("Maker Status");
				break;
			case 6:
				cell.setCellValue("Maker Comments");
				break;
			case 7:
				cell.setCellValue("Checker Status");
				break;
			case 8:
				cell.setCellValue("Checker Comments");
				break;	
			case 9:
				cell.setCellValue("Latest Status");
				break;
			case 10:
				cell.setCellValue("Onshore SME Comments");
				break;	
			case 11:
				cell.setCellValue("Maker Hold Time (Min)");
				break;	
			case 12:
				cell.setCellValue("Checker Hold Time (Min)");
				break;
			case 13:
				cell.setCellValue("Onshore Hold Time (Min)");
				break;
			case 14:
				cell.setCellValue("Total Hold Time (Min)");
				break;	
			case 15:
				cell.setCellValue("Age (Min)");
				break;				
			}
		}
		return;
	}
	
	private void createExcelReportBody(XSSFSheet sheet, List<DocWkflwProcess> agrmtList, Map<String, AHTBean> ahtMap) {

		if(agrmtList != null){
			int i = 1;
			for(DocWkflwProcess r : agrmtList){
				XSSFRow row = sheet.createRow(i);
				
				for(int j=0; j<16; j++){
					XSSFCell cell = row.createCell(j);
					
					switch (j) {
					case 0:
						cell.setCellValue(r.getAgreementId());
						break;
					case 1:
						cell.setCellValue(r.getAgreementTypeDesc());
						break;
					case 2:
						cell.setCellValue(r.getLob());
						break;
					case 3:
						cell.setCellValue(r.getNumPages());
						break;
					case 4:
						cell.setCellValue(r.getNumFields());
						break;	
					case 5:
						cell.setCellValue(r.getMakerStatus());
						break;
					case 6:
						cell.setCellValue(r.getMakerComments());
						break;	
					case 7:
						cell.setCellValue(r.getCheckerStatus());
						break;
					case 8:
						cell.setCellValue(r.getCheckerComments());
						break;	
					case 9:
						cell.setCellValue(r.getStatusDescription());
						break;
					case 10:
						cell.setCellValue(r.getSmeComments());
						break;
					case 11:
						cell.setCellValue(ahtMap.get(r.getAgreementId()).getMakerHeldTime());
						break;
					case 12:
						cell.setCellValue(ahtMap.get(r.getAgreementId()).getCheckerHeldTime());
						break;
					case 13:
						cell.setCellValue(ahtMap.get(r.getAgreementId()).getSmeHeldTime());
						break;
					case 14:
						cell.setCellValue(ahtMap.get(r.getAgreementId()).getTotalHeldTime());
						break;	
					case 15:
						cell.setCellValue(ahtMap.get(r.getAgreementId()).getAge());
						break;						
					}
					
				}
				i++;
			}
		}
	
		return;
	}	
	
	
	@Override
	public Response getAgreementsAuditTrail(Date rptFromDt, Date rptToDt) {
		
		try {
			if (DocumentWorkflowToolUtility.isEmpty(rptFromDt)) {
				rptFromDt = new Date();
			}
			if (DocumentWorkflowToolUtility.isEmpty(rptToDt)) {
				rptToDt = new Date();
			}
			rptFromDt = DocumentWorkflowToolUtility.populateDateTime(rptFromDt, 0, 0, 0);
			String rptFromDate = DocumentWorkflowToolUtility.convertDateToString(rptFromDt);
			rptToDt = DocumentWorkflowToolUtility.populateDateTime(rptToDt, 23, 59, 59);
			String rptToDate = DocumentWorkflowToolUtility.convertDateToString(rptToDt);
			List<DocWkflwProcess> agrmtList = documentAdminDAO.extractAgreementsAuditTrail(rptFromDate, rptToDate);
			
			System.out.println("agrmtList size..."+agrmtList.size());
			
			XSSFWorkbook wb = new XSSFWorkbook();			
			XSSFSheet sheet = wb.createSheet();
			XSSFCellStyle headerStyle = wb.createCellStyle();
			
			Font headerFont = wb.createFont();
			headerFont.setBold(true);
			
			createAuditTrailExcelReportHeader(sheet, headerFont, headerStyle);
			createAuditTrailExcelReportBody(wb, sheet, agrmtList);
			
			File file = new File(this.getAppConfig().getTempFileLocation()+"/report"+System.currentTimeMillis()+".xlsx");
			
			FileOutputStream baos = new FileOutputStream(file);
			wb.write(baos);
			baos.close();
			
			return Response.ok(file).header("Content-Disposition", "attachment; filename=\"Agreements_Audit_Trail_Dump.xlsx\"").build();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}	
	
	
	private void createAuditTrailExcelReportHeader(XSSFSheet sheet, Font headerFont, XSSFCellStyle headerStyle) {

		XSSFRow headerRow = sheet.createRow(0);

		XSSFColor headerColor = new XSSFColor(Color.DARK_GRAY);
		headerStyle.setFillBackgroundColor(headerColor);
		headerStyle.setFont(headerFont);

		for (int i = 0; i < 12; i++) {
			XSSFCell cell = headerRow.createCell(i);
			cell.setCellStyle(headerStyle);

			switch (i) {
			case 0:
				cell.setCellValue("Agreement ID");
				break;
			case 1:
				cell.setCellValue("Vesrion ID");
				break;
			case 2:
				cell.setCellValue("Agreement Type");
				break;
			case 3:
				cell.setCellValue("LOB");
				break;
			case 4:
				cell.setCellValue("Num of Pages");
				break;
			case 5:
				cell.setCellValue("Num of Fields");
				break;				
			case 6:
				cell.setCellValue("Latest Status");
				break;
			case 7:
				cell.setCellValue("Comments");
				break;	
			case 8:
				cell.setCellValue("Agreement Workflow Created By");
				break;	
			case 9:
				cell.setCellValue("Agreement Workflow Created Date");
				break;
			case 10:
				cell.setCellValue("Agreement Workflow Last Updated By");
				break;
			case 11:
				cell.setCellValue("Agreement Workflow Last Updated Date");
				break;	
			}
		}
		return;
	}
	
	private void createAuditTrailExcelReportBody(XSSFWorkbook wb, XSSFSheet sheet, List<DocWkflwProcess> agrmtList) {

		if(agrmtList != null){
			int i = 1;
			XSSFCellStyle cellStyle         = wb.createCellStyle();
			for(DocWkflwProcess r : agrmtList){
				XSSFRow row = sheet.createRow(i);
				
				for(int j=0; j<12; j++){
					XSSFCell cell = row.createCell(j);
					
					switch (j) {
					case 0:
						cell.setCellValue(r.getAgreementId());
						break;
					case 1:
						cell.setCellValue(r.getVersionId());
						break;
					case 2:
						cell.setCellValue(r.getAgreementTypeDesc());
						break;
					case 3:
						cell.setCellValue(r.getLob());
						break;
					case 4:
						cell.setCellValue(r.getNumPages());
						break;
					case 5:
						cell.setCellValue(r.getNumFields());
						break;	
					case 6:
						cell.setCellValue(r.getStatusDescription());
						break;
					case 7:
						cell.setCellValue(r.getComments());
						break;
					case 8:
						cell.setCellValue(r.getCreatedBy());
						break;
					case 9:
						short df = wb.createDataFormat().getFormat("mm/dd/yyyy h:mm");
						cellStyle.setDataFormat(df);
						cell.setCellStyle(cellStyle);						
						cell.setCellValue(r.getCreationDate());
						break;
					case 10:
						cell.setCellValue(r.getLastUpdatedBy());
						break;
					case 11:
						short sdf = wb.createDataFormat().getFormat("mm/dd/yyyy h:mm");
						cellStyle.setDataFormat(sdf);
						cell.setCellStyle(cellStyle);						
						cell.setCellValue(r.getLastUpdationDate());
						break;	
					}
					
				}
				i++;
			}
		}
	
		return;
	}	

	/*public Response updateDocTypes(List<DocumentType> docTypeList){
		return Response.ok().entity(Boolean.TRUE).build();
	}
	public Response updateDocRepos(List<DocumentRepository> docRepoList){
		return Response.ok().entity(Boolean.TRUE).build();
	}
	public Response updateDocTagsMasterList(List<DocumentTag> docTageList){
		return Response.ok().entity(Boolean.TRUE).build();
	}
	public Response updateDocSubTagsMasterList(List<DocumentSubTagValues> docSubTagsList){
		return Response.ok().entity(Boolean.TRUE).build();
	}
	public Response updateDocTypeTagMap(List<DocumentTypeTagMapping> docTypeTagRelList){
		return Response.ok().entity(Boolean.TRUE).build();
	}
	public Response updateDocTagSubTagMap(List<DocumentTagSubTagMapping> docTagSubTagRelList){
		return Response.ok().entity(Boolean.TRUE).build();
	}*/
}
