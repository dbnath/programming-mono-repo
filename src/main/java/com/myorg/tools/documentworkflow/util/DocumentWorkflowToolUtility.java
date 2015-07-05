package com.myorg.tools.documentworkflow.util;

import java.util.List;
import java.util.Map;

public class DocumentWorkflowToolUtility {
	
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
}
