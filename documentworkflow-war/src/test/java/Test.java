import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public class Test {

	public static void main(String[] args) throws Exception {
		/*XSSFWorkbook wb = new XSSFWorkbook();
		
		XSSFSheet sheet = wb.createSheet();
		
		XSSFRow row = sheet.createRow(0);
		
		//row.createCell(0);
		
		String[] allowedValues = new String[]{"Ardhendu","Pratik","Debasish"};
		
        DataValidationConstraint constraint = null; 
        DataValidation dataValidation = null; 
        DataValidationHelper validationHelper = new XSSFDataValidationHelper(sheet); 
        constraint = validationHelper.createExplicitListConstraint(allowedValues);
		
        org.apache.poi.ss.util.CellRangeAddressList addressList = new org.apache.poi.ss.util.CellRangeAddressList(0, 65536,1, 1);
        dataValidation = validationHelper.createValidation(constraint, addressList); 
        dataValidation.setSuppressDropDownArrow(true); 
        //dataValidation.createPromptBox("Valid Values", "The following values are valid for this cell:" +allowedValues); 
        dataValidation.setShowPromptBox(true); 
        dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP); 
        dataValidation.createErrorBox("Validation Error", "The following values are valid for this cell:" +allowedValues); 
        sheet.addValidationData(dataValidation); 
        
        FileOutputStream fos = new FileOutputStream("D:/Pratik/Test.xlsx");
        wb.write(fos);
        
        fos.close();*/
		
		BigDecimal bd = new BigDecimal(Double.valueOf(13000)/(1000*60));
		bd.setScale(1, RoundingMode.FLOOR);
		
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println(Double.valueOf(df.format(Double.valueOf(13000)/(1000*60))));
		
		
		
		System.out.println(bd.doubleValue()+" ###### "+bd.scale());
        
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
