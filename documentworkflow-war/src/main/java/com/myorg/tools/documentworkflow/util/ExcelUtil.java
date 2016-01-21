package com.myorg.tools.documentworkflow.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.myorg.tools.documentworkflow.model.AHTBean;
import com.myorg.tools.documentworkflow.model.AgreementErrorType;
import com.myorg.tools.documentworkflow.model.AgreementType;
import com.myorg.tools.documentworkflow.model.AgreementWorkflow;
import com.myorg.tools.documentworkflow.model.DocWkflwProcess;
import com.myorg.tools.documentworkflow.model.Role;
import com.myorg.tools.documentworkflow.model.RoleUser;

public class ExcelUtil {
	
	static Logger log = Logger.getLogger(ExcelUtil.class.getName());

	public XSSFWorkbook generateDocUploadTemplate(List<AgreementType> agrTypList, List<String> makerList) throws IOException{
		
		int i=0;
		String[] typeValues = new String[agrTypList.size()];
		for(AgreementType type : agrTypList){
			typeValues[i] = type.getAgrementTypeName();
			i++;
		}
		
		String[] makerValues = null;
		
		if(DocumentWorkflowToolUtility.isEmptyList(makerList)){
			makerValues = new String[]{""};
		} else {
			makerValues = makerList.toArray(new String[]{});
		}
		log.info("###### makerValues"+makerValues);
		
		XSSFWorkbook wb = new XSSFWorkbook();			
		XSSFSheet sheet = wb.createSheet();
		XSSFCellStyle headerStyle = getHeaderStyle(wb);
		
		createDocUploadTemplateHeader(sheet, headerStyle);
		createTemplateBody(sheet, typeValues, makerValues);
		
		return wb;
	}
	
	public XSSFWorkbook generateErrTypeUploadTemplate(List<AgreementErrorType> agrTypList) throws IOException{
		
		int i=0;
		String[] typeValues = new String[agrTypList.size()];
		for(AgreementErrorType type : agrTypList){
			typeValues[i] = type.getErrorTypeName();
			i++;
		}
		
		XSSFWorkbook wb = new XSSFWorkbook();			
		XSSFSheet sheet = wb.createSheet();
		XSSFCellStyle headerStyle = getHeaderStyle(wb);
		XSSFCellStyle bodyStyle = getBodyStyle(wb);
		
		createErrTypeTemplateHeader(sheet, headerStyle);
		createErrTypeTemplateBody(sheet, agrTypList, bodyStyle);
		
		return wb;
	}
	
	public XSSFWorkbook generateRoleUserUploadTemplate(List<RoleUser> roleUserList, List<Role> roleList) throws IOException{
		
		XSSFWorkbook wb = new XSSFWorkbook();			
		XSSFSheet sheet = wb.createSheet();
		XSSFCellStyle headerStyle = getHeaderStyle(wb);
		XSSFCellStyle bodyStyle = getBodyStyle(wb);
		
		createRoleUserTemplateHeader(sheet, headerStyle);
		createRoleTemplateBody(sheet, roleUserList, roleList, bodyStyle);
		
		return wb;
	}	
	
	public XSSFWorkbook generateAgreementTypeUploadTemplate(List<AgreementType> agrTypList) throws IOException{
		
		int i=0;
		String[] typeValues = new String[agrTypList.size()];
		for(AgreementType type : agrTypList){
			typeValues[i] = type.getAgrementTypeName();
			i++;
		}
		
		XSSFWorkbook wb = new XSSFWorkbook();			
		XSSFSheet sheet = wb.createSheet();
		XSSFCellStyle headerStyle = getHeaderStyle(wb);
		XSSFCellStyle bodyStyle = getBodyStyle(wb);
		
		createAgreementTypeTemplateHeader(sheet, headerStyle);
		createAgreementTypeTemplateBody(sheet, agrTypList, bodyStyle);
		
		return wb;
	}	
	
	private void createDocUploadTemplateHeader(XSSFSheet sheet, XSSFCellStyle headerStyle) {

		XSSFRow headerRow = sheet.createRow(0);
		
		for (int i = 0; i < 4; i++) {
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
			/*case 3:
				cell.setCellValue("Status");
				break;*/
			case 3:
				cell.setCellValue("Assigned To");
				break;
			}
		}
		for(int j=0; j<4; j++){
			sheet.setColumnWidth(j, 4000);
		}
		sheet.createFreezePane(0, 1);
		return;
	}
	
	private void createTemplateBody(XSSFSheet sheet, String[] typeValues,String[] makerValues) {

		
		DataValidationHelper validationHelper = new XSSFDataValidationHelper(sheet);
		String[] allowedValues = new String[]{""};
		
        DataValidationConstraint typeConstraint = validationHelper.createExplicitListConstraint(typeValues);
        DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(allowedValues);
        DataValidationConstraint makerConstraint = validationHelper.createExplicitListConstraint(makerValues);
        
        org.apache.poi.ss.util.CellRangeAddressList typeAddressList = new org.apache.poi.ss.util.CellRangeAddressList(1, 1001,1, 1);
        DataValidation typeValidation = validationHelper.createValidation(typeConstraint, typeAddressList);
        DataValidation dataValidation = null;
        
        typeValidation.setSuppressDropDownArrow(true);
        //dataValidation.createPromptBox("Valid Values", "The following values are valid for this cell:" +allowedValues); 
        
        typeValidation.setShowPromptBox(true); 
        typeValidation.setErrorStyle(DataValidation.ErrorStyle.STOP); 
        typeValidation.createErrorBox("Validation Error", "You can only select values from dropdown");
        typeValidation.setShowErrorBox(true);
        sheet.addValidationData(typeValidation); 
        
        org.apache.poi.ss.util.CellRangeAddressList addressList = new org.apache.poi.ss.util.CellRangeAddressList(1002, 1048575,0,3);
        dataValidation = validationHelper.createValidation(constraint, addressList); 
        dataValidation.setSuppressDropDownArrow(false);        
        dataValidation.setShowPromptBox(true); 
        dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP); 
        dataValidation.createErrorBox("Validation Error", "You can enter maximum 1000 rows at a time");
        dataValidation.setShowErrorBox(true);
        sheet.addValidationData(dataValidation); 	
        
        org.apache.poi.ss.util.CellRangeAddressList makerAddressList = new org.apache.poi.ss.util.CellRangeAddressList(1, 1001,3, 3);
        DataValidation makerValidation = validationHelper.createValidation(makerConstraint, makerAddressList);
        makerValidation.setSuppressDropDownArrow(true);
        makerValidation.setShowPromptBox(true); 
        makerValidation.setErrorStyle(DataValidation.ErrorStyle.STOP); 
        makerValidation.createErrorBox("Validation Error", "You can only select values from dropdown");
        makerValidation.setShowErrorBox(true);
        sheet.addValidationData(makerValidation);         
		return;
	}
	
	private void createErrTypeTemplateHeader(XSSFSheet sheet,  XSSFCellStyle headerStyle) {

		XSSFRow headerRow = sheet.createRow(0);
		
		for (int i = 0; i < 2; i++) {
			XSSFCell cell = headerRow.createCell(i);
			cell.setCellStyle(headerStyle);
			
			switch (i) {
			case 0:
				cell.setCellValue("Error Reason ID");
				break;
			case 1:
				cell.setCellValue("Error Reason");
				break;
			}
		}
		return;
	}	
	
	private void createErrTypeTemplateBody(XSSFSheet sheet, List<AgreementErrorType> agrTypList, XSSFCellStyle bodyStyle) {

		for(int j=0; j<agrTypList.size(); j++ ){
			XSSFRow row = sheet.createRow(j+1);
			AgreementErrorType type = agrTypList.get(j);
			for (int i = 0; i < 2; i++) {
				XSSFCell cell = row.createCell(i);
				cell.setCellStyle(bodyStyle);

				switch (i) {
				case 0:
					cell.setCellValue(type.getErrorTypeId());
					break;
				case 1:
					cell.setCellValue(type.getErrorTypeName());
					break;
				}
			}			
		}
		
		for (int i = 0; i < 2; i++) {
			sheet.setColumnWidth(i, 3500);
		}
		
		DataValidationHelper validationHelper = new XSSFDataValidationHelper(sheet);
		String[] allowedValues = new String[]{""};
		
        DataValidationConstraint editConstraint = validationHelper.createExplicitListConstraint(allowedValues);
        DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(allowedValues);
        
        org.apache.poi.ss.util.CellRangeAddressList editAddressList = new org.apache.poi.ss.util.CellRangeAddressList(1, 1001,0, 0);
        DataValidation editValidation = validationHelper.createValidation(editConstraint, editAddressList);
        editValidation.setSuppressDropDownArrow(false);
        editValidation.setShowPromptBox(true); 
        editValidation.setErrorStyle(DataValidation.ErrorStyle.STOP); 
        editValidation.createErrorBox("Validation Error", "You cannot add / update Error Reason ID");
        editValidation.setShowErrorBox(true);
        sheet.addValidationData(editValidation); 
        
        org.apache.poi.ss.util.CellRangeAddressList addressList = new org.apache.poi.ss.util.CellRangeAddressList(1002, 1048575,0,1);
        DataValidation dataValidation = validationHelper.createValidation(constraint, addressList); 
        dataValidation.setSuppressDropDownArrow(false);        
        dataValidation.setShowPromptBox(true); 
        dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP); 
        dataValidation.createErrorBox("Validation Error", "You can enter maximum 1000 rows at a time");
        dataValidation.setShowErrorBox(true);
        sheet.addValidationData(dataValidation); 		
		return;
	}	
	
	private void createRoleUserTemplateHeader(XSSFSheet sheet, XSSFCellStyle headerStyle) {

		XSSFRow headerRow = sheet.createRow(0);

		for (int i = 0; i < 4; i++) {
			XSSFCell cell = headerRow.createCell(i);
			cell.setCellStyle(headerStyle);
			
			switch (i) {
			case 0:
				cell.setCellValue("User ID");
				break;
			case 1:
				cell.setCellValue("User Name");
				break;
			case 2:
				cell.setCellValue("User Status");
				break;
			case 3:
				cell.setCellValue("Role");
				break;
			}
		}
		return;
	}	
	
	private void createRoleTemplateBody(XSSFSheet sheet, List<RoleUser> roleUserList, List<Role> roleList, XSSFCellStyle bodyStyle) {

		for(int j=0; j<roleUserList.size(); j++ ){
			XSSFRow row = sheet.createRow(j+1);
			RoleUser roleUser = roleUserList.get(j);
			
			for (int i = 0; i < 4; i++) {
				XSSFCell cell = row.createCell(i);
				cell.setCellStyle(bodyStyle);
				
				switch (i) {
				case 0:
					cell.setCellValue(roleUser.getUserId());
					break;
				case 1:
					cell.setCellValue(roleUser.getUserName());
					break;
				case 2:
					cell.setCellValue(roleUser.getUserStatus());
					break;
				case 3:
					cell.setCellValue(roleUser.getRoleName());
					break;					
				}
			}			
		}
		
		DataValidationHelper validationHelper = new XSSFDataValidationHelper(sheet);
		String[] allowedValues = new String[]{""};
		String[] allowedStatus = new String[]{"Active", "Inactive"};
		String[] allowedRoles = new String[roleList.size()];
		
		for(int i =0; i<roleList.size(); i++){
			allowedRoles[i] = roleList.get(i).getRoleDesc();
		}
		
        DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(allowedValues);
        DataValidationConstraint statusConstraint = validationHelper.createExplicitListConstraint(allowedStatus);
        DataValidationConstraint roleConstraint = validationHelper.createExplicitListConstraint(allowedRoles);
        
        org.apache.poi.ss.util.CellRangeAddressList roleAddressList = new org.apache.poi.ss.util.CellRangeAddressList(1, 1048575,3, 3);
        DataValidation roleValidation = validationHelper.createValidation(roleConstraint, roleAddressList);
        roleValidation.setSuppressDropDownArrow(true);
        roleValidation.setShowPromptBox(true); 
        roleValidation.setErrorStyle(DataValidation.ErrorStyle.STOP); 
        roleValidation.createErrorBox("Validation Error", "Please select from Dropdown");
        roleValidation.setShowErrorBox(true);
        sheet.addValidationData(roleValidation);         
        
        
        org.apache.poi.ss.util.CellRangeAddressList userStatusList = new org.apache.poi.ss.util.CellRangeAddressList(1, 1048575,2, 2);
        DataValidation statusValidation = validationHelper.createValidation(statusConstraint, userStatusList);
        statusValidation.setSuppressDropDownArrow(true);
        statusValidation.setShowPromptBox(true); 
        statusValidation.setErrorStyle(DataValidation.ErrorStyle.STOP); 
        statusValidation.createErrorBox("Validation Error", "Please select from Dropdown");
        statusValidation.setShowErrorBox(true);
        sheet.addValidationData(statusValidation);         
               
        org.apache.poi.ss.util.CellRangeAddressList addressList = new org.apache.poi.ss.util.CellRangeAddressList(1001, 1048575,0,3);
        DataValidation dataValidation = validationHelper.createValidation(constraint, addressList); 
        dataValidation.setSuppressDropDownArrow(false);        
        dataValidation.setShowPromptBox(true); 
        dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP); 
        dataValidation.createErrorBox("Validation Error", "You can enter maximum 1000 rows at a time");
        dataValidation.setShowErrorBox(true);
        sheet.addValidationData(dataValidation); 		
		return;
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
								log.info("###### agreemen id "+s);
								break;
							case 1:
								doc.setAgreementTypeDesc(s);
								log.info("###### agreemen type "+s);
								break;
							case 2:
								doc.setLob(s);
								log.info("###### lob "+s);
								break;
							case 3:
								doc.setWfStatusDesc(DocumentWorkflowToolUtility.isEmpty(s) ? "New" : s);
								log.info("###### status id "+s);
								break;
							case 4:
								doc.setAssignedTo(s);
								log.info("###### assigned to "+s);
								break;
							}

						}
						docList.add(doc);
					}
				}

			}
		}

		return docList;
	}	
	
	public List<AgreementErrorType> parseErrReasonUploadFile(InputStream stream) throws IOException {

		List<AgreementErrorType> docList = new ArrayList<AgreementErrorType>();
		if (stream != null) {
			XSSFWorkbook wb = new XSSFWorkbook(stream);

			XSSFSheet sheet = wb.getSheetAt(0);

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				XSSFRow row = sheet.getRow(i);

				if (row != null) {

					AgreementErrorType doc = new AgreementErrorType();

					for (int j = 0; j < 2; j++) {
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
								if(s != null){
									doc.setErrorTypeId(Double.valueOf(s).intValue());;
									log.info("###### Error Type id "+doc.getErrorTypeId());
								}
								break;
							case 1:
								doc.setErrorTypeName(s);
								log.info("###### Error Type Name "+s);
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
	
	public List<AgreementType> parseAgreementTypeUploadFile(InputStream stream) throws IOException {

		List<AgreementType> docList = new ArrayList<AgreementType>();
		if (stream != null) {
			XSSFWorkbook wb = new XSSFWorkbook(stream);

			XSSFSheet sheet = wb.getSheetAt(0);

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				XSSFRow row = sheet.getRow(i);

				if (row != null) {

					AgreementType doc = new AgreementType();

					for (int j = 0; j < 2; j++) {
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

							switch (colNum) {
							case 0:
								if(s != null){
									doc.setAgreementTypeId(Double.valueOf(s).intValue());;
									log.info("###### Error Type id "+doc.getAgreementTypeId());
								}
								break;
							case 1:
								if(s != null){
									doc.setAgrementTypeName(s);
									log.info("###### Error Type Code "+s);
								}
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
	
	private void createAgreementTypeTemplateHeader(XSSFSheet sheet, XSSFCellStyle headerStyle) {

		XSSFRow headerRow = sheet.createRow(0);

		for (int i = 0; i < 2; i++) {
			XSSFCell cell = headerRow.createCell(i);
			cell.setCellStyle(headerStyle);
			
			switch (i) {
			case 0:
				cell.setCellValue("Agreement Type ID");
				break;
			case 1:
				cell.setCellValue("Agreement Type");
				break;
			}
		}
		return;
	}	
	
	private void createAgreementTypeTemplateBody(XSSFSheet sheet, List<AgreementType> agrTypList, XSSFCellStyle bodyStyle) {

		for(int j=0; j<agrTypList.size(); j++ ) {
			XSSFRow row = sheet.createRow(j+1);
			AgreementType type = agrTypList.get(j);
			for (int i = 0; i < 2; i++) {
				XSSFCell cell = row.createCell(i);
				cell.setCellStyle(bodyStyle);
				
				switch (i) {
				case 0:
					cell.setCellValue(type.getAgreementTypeId());
					break;
				case 1:
					cell.setCellValue(type.getAgrementTypeName());
					break;
				}
			}			
		}
		
		for (int i = 0; i < 2; i++) {
			sheet.setColumnWidth(i, 3500);
		}
		
		DataValidationHelper validationHelper = new XSSFDataValidationHelper(sheet);
		String[] allowedValues = new String[]{""};
		
        DataValidationConstraint editConstraint = validationHelper.createExplicitListConstraint(allowedValues);
        DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(allowedValues);
        
        org.apache.poi.ss.util.CellRangeAddressList editAddressList = new org.apache.poi.ss.util.CellRangeAddressList(1, 1001,0, 0);
        DataValidation editValidation = validationHelper.createValidation(editConstraint, editAddressList);
        editValidation.setSuppressDropDownArrow(false);
        editValidation.setShowPromptBox(true); 
        editValidation.setErrorStyle(DataValidation.ErrorStyle.STOP); 
        editValidation.createErrorBox("Validation Error", "You cannot add / update Error Reason ID");
        editValidation.setShowErrorBox(true);
        sheet.addValidationData(editValidation); 
        
        org.apache.poi.ss.util.CellRangeAddressList addressList = new org.apache.poi.ss.util.CellRangeAddressList(1002, 1048575,0,1);
        DataValidation dataValidation = validationHelper.createValidation(constraint, addressList); 
        dataValidation.setSuppressDropDownArrow(false);        
        dataValidation.setShowPromptBox(true); 
        dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP); 
        dataValidation.createErrorBox("Validation Error", "You can enter maximum 1000 rows at a time");
        dataValidation.setShowErrorBox(true);
        sheet.addValidationData(dataValidation); 		
		return;
	}		
	
	public List<RoleUser> parseRoleUserUploadFile(InputStream stream) throws IOException {

		List<RoleUser> roleUserList = new ArrayList<RoleUser>();
		if (stream != null) {
			XSSFWorkbook wb = new XSSFWorkbook(stream);

			XSSFSheet sheet = wb.getSheetAt(0);

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				XSSFRow row = sheet.getRow(i);

				if (row != null) {

					RoleUser roleUser = new RoleUser();

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

							switch (colNum) {
							case 0:
								if(s != null){
									roleUser.setUserId(s);
								}
								break;
							case 1:
								if(s != null){
									roleUser.setUserName(s);
								}
								break;
							case 2:
								if(s != null){
									roleUser.setUserStatus(s);
								}
								break;
							case 3:
								if(s != null){
									roleUser.setRoleName(s);
								}
								break;								
							}
						}
						
					}
					roleUserList.add(roleUser);
				}
			}
		}

		return roleUserList;
	}
	
	public XSSFCellStyle getHeaderStyle(XSSFWorkbook wb){
		XSSFCellStyle headerStyle = wb.createCellStyle();
		
		XSSFColor headerColor = new XSSFColor();
		headerColor.setIndexed(57);
		headerStyle.setWrapText(true);
		
		headerStyle.setVerticalAlignment(VerticalAlignment.TOP);
		headerStyle.setFillForegroundColor(headerColor);
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setBorderLeft(BorderStyle.THIN);
		headerStyle.setBorderRight(BorderStyle.THIN);
		headerStyle.setBorderTop(BorderStyle.THIN);
		headerStyle.setBorderBottom(BorderStyle.THICK);		
		
		Font headerFont = wb.createFont();
		//headerFont.setColor(IndexedColors.RED.getIndex());
		headerFont.setBold(true);
		headerStyle.setFont(headerFont);
		return headerStyle;
	}
	
	public XSSFCellStyle getBodyStyle(XSSFWorkbook wb){
		XSSFCellStyle headerStyle = wb.createCellStyle();
		headerStyle.setWrapText(true);
		headerStyle.setVerticalAlignment(VerticalAlignment.TOP);
		headerStyle.setBorderLeft(BorderStyle.THIN);
		headerStyle.setBorderRight(BorderStyle.THIN);
		headerStyle.setBorderTop(BorderStyle.THIN);
		headerStyle.setBorderBottom(BorderStyle.THIN);
		return headerStyle;
	}	
	
	public void createExcelReportHeader(XSSFSheet sheet, XSSFCellStyle headerStyle) {

		XSSFRow headerRow = sheet.createRow(0);

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
		sheet.createFreezePane(0, 1);
		return;
	}
	
	public void createExcelReportBody(XSSFSheet sheet, XSSFCellStyle bodyStyle, List<DocWkflwProcess> agrmtList, Map<String, AHTBean> ahtMap) {

		if(agrmtList != null){
			int i = 1;
			
			double makerTime = 0.0;
			double checkerTime = 0.0;
			double smeTime = 0.0;
			double totalTime = 0.0;
			double age = 0.0;
			int makerCnt = 0;
			int checkerCnt = 0;
			int smeCnt = 0;
			int totalCnt = 0;
			int ageCnt = 0;
			
			DecimalFormat df = new DecimalFormat("#.##");
			
			for(DocWkflwProcess r : agrmtList){
				XSSFRow row = sheet.createRow(i);
				
				for(int j=0; j<16; j++){
					XSSFCell cell = row.createCell(j);
					cell.setCellStyle(bodyStyle);
					
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
						Double thisMakerTime = ahtMap.get(r.getAgreementId()).getMakerHeldTime();
						if(thisMakerTime != null && thisMakerTime > 0.0){
							makerCnt++;
						}
						cell.setCellValue(DocumentWorkflowToolUtility.getNumforExcelReport(thisMakerTime));
						makerTime += thisMakerTime;
						break;
					case 12:
						Double thisCheckerTime = ahtMap.get(r.getAgreementId()).getCheckerHeldTime();
						if(thisCheckerTime != null && thisCheckerTime > 0.0){
							checkerCnt++;
						}						
						cell.setCellValue(DocumentWorkflowToolUtility.getNumforExcelReport(thisCheckerTime));
						checkerTime += thisCheckerTime;
						break;
					case 13:
						Double thisSMETime = ahtMap.get(r.getAgreementId()).getSmeHeldTime();
						if(thisSMETime != null && thisSMETime > 0.0){
							smeCnt++;
						}						
						cell.setCellValue(DocumentWorkflowToolUtility.getNumforExcelReport(thisSMETime));
						log.info("###### thisSMETime "+thisSMETime);
						smeTime += thisSMETime;
						break;
					case 14:
						Double thisTotalTime = ahtMap.get(r.getAgreementId()).getTotalHeldTime();
						if(thisTotalTime != null && thisTotalTime > 0.0){
							totalCnt++;
						}						
						cell.setCellValue(DocumentWorkflowToolUtility.getNumforExcelReport(thisTotalTime));
						totalTime += thisTotalTime;
						break;	
					case 15:
						Double thisAge = ahtMap.get(r.getAgreementId()).getAge();
						if(thisAge != null && thisAge > 0.0){
							ageCnt++;
						}						
						cell.setCellValue(DocumentWorkflowToolUtility.getNumforExcelReport(thisAge));
						age += thisAge;
						break;						
					}
					
				}
				i++;
			}
			createAverageRow(sheet, makerTime, checkerTime, smeTime, totalTime, age, agrmtList.size(), bodyStyle, df, makerCnt, checkerCnt, smeCnt, totalCnt, ageCnt);
			for(int j=0; j<16; j++){
				sheet.setColumnWidth(j, 3500);
			}
		}
	
		return;
	}
	
	private void createAverageRow(XSSFSheet sheet,	double makerTime, double checkerTime, double smeTime,double totalTime,double age, int numOfRecords,XSSFCellStyle bodyStyle, DecimalFormat df
			,int makerCnt,
			int checkerCnt,
			int smeCnt,
			int totalCnt,
			int ageCnt){
		XSSFRow row = sheet.createRow(numOfRecords +1);
		
		for (int j = 10; j < 16; j++) {
			XSSFCell cell = row.createCell(j);
			cell.setCellStyle(bodyStyle);

			switch (j) {
			case 10:
				cell.setCellValue("Average");
				break;
			case 11:
				if (makerCnt != 0) {
					cell.setCellValue(DocumentWorkflowToolUtility.getNumforExcelReport(makerTime / makerCnt));
				} else {
					cell.setCellValue(0);
				}
				break;
			case 12:
				if (checkerCnt != 0) {
					cell.setCellValue(DocumentWorkflowToolUtility.getNumforExcelReport(checkerTime / checkerCnt));
				} else {
					cell.setCellValue(0);
				}
				break;
			case 13:
				if (smeCnt != 0) {
					cell.setCellValue(DocumentWorkflowToolUtility.getNumforExcelReport(smeTime / smeCnt));
				} else {
					cell.setCellValue(0);
				}
				break;
			case 14:
				if (totalCnt != 0) {
					cell.setCellValue(DocumentWorkflowToolUtility.getNumforExcelReport(totalTime / totalCnt));
				} else {
					cell.setCellValue(0);
				}
				break;
			case 15:
				if (ageCnt != 0) {
					cell.setCellValue(DocumentWorkflowToolUtility.getNumforExcelReport(age / ageCnt));
				} else {
					cell.setCellValue(0);
				}
				break;
			}
		}
		
		
	}
	
	public void createAuditTrailExcelReportHeader(XSSFSheet sheet, XSSFCellStyle headerStyle) {

		XSSFRow headerRow = sheet.createRow(0);

		for (int i = 0; i < 12; i++) {
			XSSFCell cell = headerRow.createCell(i);
			cell.setCellStyle(headerStyle);

			switch (i) {
			case 0:
				cell.setCellValue("Agreement ID");
				break;
			case 1:
				cell.setCellValue("Version");
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
				cell.setCellValue("Created By");
				break;	
			case 9:
				cell.setCellValue("Created Date");
				break;
			case 10:
				cell.setCellValue("Last Updated By");
				break;
			case 11:
				cell.setCellValue("Last Updated Date");
				break;	
			}
		}
		sheet.createFreezePane(0, 1);
		return;
	}
	
	public void createAuditTrailExcelReportBody(XSSFWorkbook wb, XSSFSheet sheet, List<DocWkflwProcess> agrmtList, XSSFCellStyle bodyStyle) {

		if(agrmtList != null){
			int i = 1;
			XSSFCellStyle dateCellStyle = wb.createCellStyle();
			short df = wb.createDataFormat().getFormat("mm/dd/yyyy h:mm");
			dateCellStyle.setDataFormat(df);
			
			for(DocWkflwProcess r : agrmtList){
				XSSFRow row = sheet.createRow(i);
				
				for(int j=0; j<12; j++){
					XSSFCell cell = row.createCell(j);
					cell.setCellStyle(bodyStyle);
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
						cell.setCellStyle(dateCellStyle);						
						cell.setCellValue(r.getCreationDate());
						break;
					case 10:
						cell.setCellValue(r.getLastUpdatedBy());
						break;
					case 11:
						cell.setCellStyle(dateCellStyle);						
						cell.setCellValue(r.getLastUpdationDate());
						break;	
					}
					
				}
				i++;
			}
		}
		
		for(int j=0; j<12; j++){
			sheet.setColumnWidth(j, 3500);
		}
	
		return;
	}	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<AgreementType> agrTypList = new ArrayList<AgreementType>();
		
		agrTypList.add(new AgreementType(1, "Trade Agreement"));
		agrTypList.add(new AgreementType(2, "ISDA Master"));
		agrTypList.add(new AgreementType(3, "IRS Document"));
		
		ExcelUtil util = new ExcelUtil();
		try {
			XSSFWorkbook wb = new XSSFWorkbook();			
			XSSFSheet sheet = wb.createSheet();
			XSSFCellStyle headerStyle = wb.createCellStyle();
			XSSFColor headerColor = new XSSFColor();
			headerColor.setIndexed(57);
			headerStyle.setWrapText(true);
			
			headerStyle.setVerticalAlignment(VerticalAlignment.TOP);
			headerStyle.setFillForegroundColor(headerColor);
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			Font headerFont = wb.createFont();
			//headerFont.setColor(IndexedColors.RED.getIndex());
			headerFont.setBold(true);
			headerStyle.setFont(headerFont);
			
			
			
			util.createExcelReportHeader(sheet, headerStyle);
			
			sheet.setColumnWidth(0, 3500);
			
			File file = new File("D:/temp/report"+System.currentTimeMillis()+".xlsx");
			
			FileOutputStream baos = new FileOutputStream(file);			
			wb.write(baos);
			wb.close();
			baos.close();
			
			/*XSSFWorkbook wb = new XSSFWorkbook(new File("D:/Temp/report1452850238427.xlsx"));	
			
			System.out.println(wb.getSheetAt(0).getRow(0).getCell(0).getCellStyle().getFillBackgroundColorColor().getIndexed());
			System.out.println(wb.getSheetAt(0).getRow(0).getCell(1).getCellStyle().getFillBackgroundColor());
			System.out.println(IndexedColors.GREEN.getIndex());
			XSSFColor headerColor = new XSSFColor();
			headerColor.setIndexed(64);
			
			wb.close();*/
			
			log.info("###### DONE");
		} catch (IOException e) {
			log.error(e);
		} 
	}

}
