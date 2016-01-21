import java.util.Date;

import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;


public class Test1 {

	public static void main(String[] args) throws Exception {
		/*XSSFWorkbook wb = new XSSFWorkbook();
		
		XSSFSheet sheet = wb.createSheet();
		
		XSSFRow row = sheet.createRow(0);
		
		//row.createCell(0);
		
		/*String[] allowedValues = new String[]{""};
		
        DataValidationConstraint constraint = null; 
        DataValidation dataValidation = null; 
        DataValidationHelper validationHelper = new XSSFDataValidationHelper(sheet); 
        constraint = validationHelper.createExplicitListConstraint(allowedValues);
        
		
        org.apache.poi.ss.util.CellRangeAddressList addressList = new org.apache.poi.ss.util.CellRangeAddressList(0, 1048575,1, 1);
        dataValidation = validationHelper.createValidation(constraint, addressList); 
        dataValidation.setSuppressDropDownArrow(true);  */      
        //dataValidation.createPromptBox("Valid Values", "The following values are valid for this cell:" +allowedValues); 
        /*dataValidation.setShowPromptBox(true); 
        dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP); 
        dataValidation.createErrorBox("Validation Error", "The following values are valid for this cell:" +allowedValues);
        dataValidation.setShowErrorBox(true);
        sheet.addValidationData(dataValidation); 
        
        FileOutputStream fos = new FileOutputStream("D:/Pratik/Test.xlsx");
        wb.write(fos);
        
        fos.close();
        System.out.println("###### Done");*/
		Date date = DocumentWorkflowToolUtility.populateDateTime(new Date(), 23, 59, 59);
		System.out.println(DocumentWorkflowToolUtility.populateDateTime(new Date(), 23, 59, 59));
		System.out.println(DocumentWorkflowToolUtility.convertDateToString(date));
	}
	
	
	/*{
		 List[] allowedValues = sd.getAllowedValues(); 

         DataValidationConstraint constraint = null; 
         DataValidation dataValidation = null; 
         DataValidationHelper validationHelper = null; 

         if(wbType==WB_TYPE_XSSF) { 
                 validationHelper = new XSSFDataValidationHelper((XSSFSheet)sheet); 
         } else { 
                 validationHelper = new org.apache.poi.hssf.usermodel.HSSFDataValidationHelper((org.apache.poi.hssf.usermodel.HSSFSheet)sheet); 
         } 

         if(allowedValues!=null&&allowedValues.length>0) { 
                 for (int i = 0; i < allowedValues.length; i++) { 
                         if(allowedValues[i]==null)continue; 
org.apache.poi.ss.util.CellRangeAddressList addressList = new org.apache.poi.ss.util.CellRangeAddressList(1, dummyRowMax, i, i); 
                         constraint = validationHelper.createExplicitListConstraint((String[])allowedValues[i].toArray()); 
                 } 

         } 
		
	}*/

}
