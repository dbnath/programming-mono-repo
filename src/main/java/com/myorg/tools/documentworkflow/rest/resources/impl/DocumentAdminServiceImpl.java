package com.myorg.tools.documentworkflow.rest.resources.impl;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.myorg.tools.documentworkflow.dao.DocumentAdminDAO;
import com.myorg.tools.documentworkflow.model.Document;
import com.myorg.tools.documentworkflow.model.DocumentRepository;
import com.myorg.tools.documentworkflow.model.DocumentSubTagValues;
import com.myorg.tools.documentworkflow.model.DocumentTag;
import com.myorg.tools.documentworkflow.model.DocumentTagSubTagMapping;
import com.myorg.tools.documentworkflow.model.DocumentType;
import com.myorg.tools.documentworkflow.model.DocumentTypeTagMapping;
import com.myorg.tools.documentworkflow.model.DocumentTypeTagSubTagsMap;
import com.myorg.tools.documentworkflow.rest.resources.BaseResource;
import com.myorg.tools.documentworkflow.rest.resources.DocumentAdminService;
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
	
	public Response uploadDocuments(@FormDataParam("file") InputStream uploadedInputStream,  @FormDataParam("file") FormDataContentDisposition fileDetail, @FormDataParam("path") String path){
		
		try {
			
			String userId = "PRATIK"; //FIXME This is to be replaced with Logged in User Id
			
			List<Document> docList = parseBulkUploadFile(uploadedInputStream);
			documentAdminDAO.uploadDocumentInformation(docList, userId);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private List<Document> parseBulkUploadFile(InputStream stream) throws IOException {

		List<Document> docList = new ArrayList<Document>();
		if (stream != null) {
			XSSFWorkbook wb = new XSSFWorkbook(stream);

			XSSFSheet sheet = wb.getSheetAt(0);
			
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				XSSFRow row = sheet.getRow(i);

				if (row != null) {

					Document doc = new Document();

					for (int j = 0; j < 5; j++) {
						XSSFCell cell = row.getCell(j);

						int colNum = cell.getColumnIndex();

						String s = cell.getRichStringCellValue().getString();

						switch (colNum) {
						case 0:
							doc.setDocName(s);
							break;
						case 1:
							doc.setDocTypeDesc(s);
							break;
						case 2:
							doc.setDocRepoDesc(s);
							break;
						case 3:
							doc.setDocHyperlink(s);
							break;
						case 4:
							doc.setDocRepoDesc(s);
							break;
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
