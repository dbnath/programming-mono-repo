package com.myorg.tools.documentworkflow.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.myorg.tools.documentworkflow.dto.BaseDTO;
import com.myorg.tools.documentworkflow.model.Response;
import com.myorg.tools.documentworkflow.model.ReverseMappable;

public class DocumentWorkflowToolUtility {
	
	static Logger log = Logger.getLogger(DocumentWorkflowToolUtility.class.getName());
	
	private static DecimalFormat df = new DecimalFormat("#.##");
	
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		return "".equalsIgnoreCase(obj.toString());
	}
	
	public static boolean isEmptyValue(Object obj){
		if (obj == null) {
			return true;
		}
		return obj.toString().length()==0;
	}
	
	public static boolean isEmptyList(List<?> objList) {
		if (objList == null) {
			return true;
		}
		return objList.size()==0;
	}

	public static boolean isEmptyMap(Map<?, ?> objMap) {
		if (objMap == null) {
			return true;
		}
		return objMap.size()==0;
	}
	
	public static boolean areAllObjectsNull(Object... objects) {
		if (objects == null) {
			return true;
		}
		for (Object obj : objects) {
			if (!isEmpty(obj)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param obj
	 * @param objects
	 * @return
	 */
	public static boolean equalsOneOf(Object obj, Object... objects){
		if (objects == null){
			return false;
		}
		for (Object ob : objects) {
			if (ob.equals(obj)) {
				return true;
			}
		}
		return false;
	}
	
	public static String trimmedString(String s) {
		if (!isEmptyValue(s)) {
			return  s.trim();
		}
		return null;
	}
	
	public static boolean equalStrings(String a, String b, boolean caseInSensitive, boolean trimmed) {
		if (areAllObjectsNull(a,b)) {
			return true;
		}
		if ((isEmptyValue(a) && ! isEmptyValue(b))||(!isEmptyValue(a) && isEmptyValue(b))) {
			return false;
		}
		if (isEmptyValue(a.trim()) && isEmptyValue(b.trim())) {
			return true;
		}
		if (trimmedString(a).equalsIgnoreCase(trimmedString(b))){
			return true;
		}
		return false;
	}
	
	public static boolean equalTrimmedStrings(String a, String b) {
		return equalStrings(a, b, false, true);		
	}
	
	public static HashMap<String, Integer> mapByValue(List<ReverseMappable> t) {

		if (t != null) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for (ReverseMappable t1 : t) {
				if (t1.getMapKey() != null) {
					map.put(t1.getMapKey(), t1.getCode());
				}
			}

			return map;
		}
		return null;
	}
	
	public static String joinAsString(List<Integer> objs, String toAppend) {
		StringBuilder sb = new StringBuilder();
		if (! isEmptyList(objs)) {
			for (@SuppressWarnings("unused") Integer i : objs){
				sb.append(toAppend);
				sb.append(", ");
			}
		}
		return (! isEmptyValue(sb.toString())) ? sb.toString().substring(0, sb.toString().lastIndexOf(", ")) : null;
	}
	
	public static String joinString(List<String> objs, String toAppend) {
		StringBuilder sb = new StringBuilder();
		if (! isEmptyList(objs)) {
			for (@SuppressWarnings("unused") String i : objs){
				sb.append(toAppend);
				sb.append(", ");
			}
		}
		return (! isEmptyValue(sb.toString())) ? sb.toString().substring(0, sb.toString().lastIndexOf(", ")) : null;
	}	
	
	public static <T extends BaseDTO> T setResponse(T dto, String responseCode, String responseMessage){
		Response response = new Response();
		response.setResponseCode(responseCode);
		response.setResponseMessage(responseMessage);
		dto.setResponse(response);
		return dto;
	}
	
	public static Double getTimeDifferenceInMin(Date date1, Date date2){
		
		if (!isEmpty(date1) && !isEmpty(date2)) {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);
			
			Double timeDiff = Math.abs(Double.valueOf(cal2.getTimeInMillis() - cal1.getTimeInMillis())/ (1000 * 60));
			//System.out.println("###### timeDiff "+(cal2.getTimeInMillis() - cal1.getTimeInMillis()));

			return timeDiff;
		}
		//return 0.0;
		return null;
	}
	
	public static Date populateDateTime(Date dt, Integer hour, Integer min, Integer sec) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, sec);
		return cal.getTime();
	}
	
	public static Double getNumforExcelReport(Double d){
		try {
			if(d != null){
				return Double.valueOf(df.format(d));
			}
		} catch (NumberFormatException e) {
			log.error(e.getMessage(),e);
		}
		return d;
	}
	
	public static String convertDateToString(Date dt){
		String strDate = "";
		try{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strDate = dateFormat.format(dt);	
		}catch(Exception e){
			
		}
		return strDate;
    }
}
