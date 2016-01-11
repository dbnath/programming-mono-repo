package com.myorg.tools.documentworkflow.util;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

import com.myorg.tools.documentworkflow.model.AgreementErrorType;
import com.myorg.tools.documentworkflow.model.AgreementType;
import com.myorg.tools.documentworkflow.model.AgreementWorkflow;
import com.myorg.tools.documentworkflow.model.Role;
import com.myorg.tools.documentworkflow.model.RoleUser;

public class ExcelUtil {

	public XSSFWorkbook generateDocUploadTemplate(List<AgreementType> agrTypList) throws IOException{
		
		int i=0;
		String[] typeValues = new String[agrTypList.size()];
		for(AgreementType type : agrTypList){
			typeValues[i] = type.getAgrementTypeName();
			i++;
		}
		
		XSSFWorkbook wb = new XSSFWorkbook();			
		XSSFSheet sheet = wb.createSheet();
		XSSFCellStyle headerStyle = wb.createCellStyle();
		
		Font headerFont = wb.createFont();
		headerFont.setBold(true);
		
		createDocUploadTemplateHeader(sheet, headerFont, headerStyle);
		createTemplateBody(sheet, typeValues);
		
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
		XSSFCellStyle headerStyle = wb.createCellStyle();
		
		Font headerFont = wb.createFont();
		headerFont.setBold(true);
		
		createErrTypeTemplateHeader(sheet, headerFont, headerStyle);
		createErrTypeTemplateBody(sheet, agrTypList);
		
		return wb;
	}
	
	public XSSFWorkbook generateRoleUserUploadTemplate(List<RoleUser> roleUserList, List<Role> roleList) throws IOException{
		
		XSSFWorkbook wb = new XSSFWorkbook();			
		XSSFSheet sheet = wb.createSheet();
		XSSFCellStyle headerStyle = wb.createCellStyle();
		
		Font headerFont = wb.createFont();
		headerFont.setBold(true);
		
		createRoleUserTemplateHeader(sheet, headerFont, headerStyle);
		createRoleTemplateBody(sheet, roleUserList, roleList);
		
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
		XSSFCellStyle headerStyle = wb.createCellStyle();
		
		Font headerFont = wb.createFont();
		headerFont.setBold(true);
		
		createAgreementTypeTemplateHeader(sheet, headerFont, headerStyle);
		createAgreementTypeTemplateBody(sheet, agrTypList);
		
		return wb;
	}	
	
	private void createDocUploadTemplateHeader(XSSFSheet sheet, Font headerFont, XSSFCellStyle headerStyle) {

		XSSFRow headerRow = sheet.createRow(0);

		XSSFColor headerColor = new XSSFColor(Color.DARK_GRAY);
		headerStyle.setFillBackgroundColor(headerColor);
		headerStyle.setFont(headerFont);
		
		
		for (int i = 0; i < 5; i++) {
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
				cell.setCellValue("Status");
				break;
			case 4:
				cell.setCellValue("Assigned To");
				break;
			}
		}
		return;
	}
	
	private void createTemplateBody(XSSFSheet sheet, String[] typeValues) {

		
		DataValidationHelper validationHelper = new XSSFDataValidationHelper(sheet);
		String[] allowedValues = new String[]{""};
		
        DataValidationConstraint typeConstraint = validationHelper.createExplicitListConstraint(typeValues);
        DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(allowedValues);
        
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
	
	private void createErrTypeTemplateHeader(XSSFSheet sheet, Font headerFont, XSSFCellStyle headerStyle) {

		XSSFRow headerRow = sheet.createRow(0);

		XSSFColor headerColor = new XSSFColor(Color.DARK_GRAY);
		headerStyle.setFillBackgroundColor(headerColor);
		headerStyle.setFont(headerFont);
		
		
		for (int i = 0; i < 3; i++) {
			XSSFCell cell = headerRow.createCell(i);
			cell.setCellStyle(headerStyle);
			
			switch (i) {
			case 0:
				cell.setCellValue("Error Reason ID");
				break;
			case 1:
				cell.setCellValue("Error Reason Code");
				break;
			case 2:
				cell.setCellValue("Error Reason");
				break;
			}
		}
		return;
	}	
	
	private void createErrTypeTemplateBody(XSSFSheet sheet, List<AgreementErrorType> agrTypList) {

		for(int j=0; j<agrTypList.size(); j++ ){
			XSSFRow row = sheet.createRow(j+1);
			AgreementErrorType type = agrTypList.get(j);
			for (int i = 0; i < 3; i++) {
				XSSFCell cell = row.createCell(i);

				switch (i) {
				case 0:
					cell.setCellValue(type.getErrorTypeId());
					break;
				case 1:
					cell.setCellValue(type.getErrorTypeCode());
					break;
				case 2:
					cell.setCellValue(type.getErrorTypeName());
					break;
				}
			}			
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
        
        org.apache.poi.ss.util.CellRangeAddressList addressList = new org.apache.poi.ss.util.CellRangeAddressList(1002, 1048575,0,2);
        DataValidation dataValidation = validationHelper.createValidation(constraint, addressList); 
        dataValidation.setSuppressDropDownArrow(false);        
        dataValidation.setShowPromptBox(true); 
        dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP); 
        dataValidation.createErrorBox("Validation Error", "You can enter maximum 1000 rows at a time");
        dataValidation.setShowErrorBox(true);
        sheet.addValidationData(dataValidation); 		
		return;
	}	
	
	private void createRoleUserTemplateHeader(XSSFSheet sheet, Font headerFont, XSSFCellStyle headerStyle) {

		XSSFRow headerRow = sheet.createRow(0);

		XSSFColor headerColor = new XSSFColor(Color.DARK_GRAY);
		headerStyle.setFillBackgroundColor(headerColor);
		headerStyle.setFont(headerFont);
		
		
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
	
	private void createRoleTemplateBody(XSSFSheet sheet, List<RoleUser> roleUserList, List<Role> roleList) {

		for(int j=0; j<roleUserList.size(); j++ ){
			XSSFRow row = sheet.createRow(j+1);
			RoleUser roleUser = roleUserList.get(j);
			for (int i = 0; i < 4; i++) {
				XSSFCell cell = row.createCell(i);

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

					for (int j = 0; j < 3; j++) {
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
									System.out.println("###### Error Type id "+doc.getErrorTypeId());
								}
								break;
							case 1:
								if(s != null){
									doc.setErrorTypeCode(s);
									System.out.println("###### Error Type Code "+s);
								}
								break;
							case 2:
								doc.setErrorTypeName(s);
								System.out.println("###### Error Type Name "+s);
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
									System.out.println("###### Error Type id "+doc.getAgreementTypeId());
								}
								break;
							case 1:
								if(s != null){
									doc.setAgrementTypeName(s);
									System.out.println("###### Error Type Code "+s);
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
	
	private void createAgreementTypeTemplateHeader(XSSFSheet sheet, Font headerFont, XSSFCellStyle headerStyle) {

		XSSFRow headerRow = sheet.createRow(0);

		XSSFColor headerColor = new XSSFColor(Color.green);
		headerStyle.setFillBackgroundColor(headerColor);
		headerStyle.setFont(headerFont);
		
		
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
	
	private void createAgreementTypeTemplateBody(XSSFSheet sheet, List<AgreementType> agrTypList) {

		for(int j=0; j<agrTypList.size(); j++ ){
			XSSFRow row = sheet.createRow(j+1);
			AgreementType type = agrTypList.get(j);
			for (int i = 0; i < 2; i++) {
				XSSFCell cell = row.createCell(i);

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
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<AgreementType> agrTypList = new ArrayList<AgreementType>();
		
		agrTypList.add(new AgreementType(1, "Trade Agreement"));
		agrTypList.add(new AgreementType(2, "ISDA Master"));
		agrTypList.add(new AgreementType(3, "IRS Document"));
		
		ExcelUtil util = new ExcelUtil();
		try {
			//XSSFWorkbook wb = util.generateDocUploadTemplate(agrTypList);
			//File file = new File(this.getAppConfig().getTempFileLocation()+"/template"+System.currentTimeMillis()+".xlsx");
			File file = new File("C:/Users/106753/Downloads/ErrorTypeUploadTemplate (1).xlsx");//new File("D:/temp/template"+System.currentTimeMillis()+".xlsx");
			FileInputStream baos = new FileInputStream(file);
			//util.parseBulkUploadFile(baos);
			util.parseErrReasonUploadFile(baos);
			//wb.write(baos);
			baos.close();			
			System.out.println("###### DONE");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
