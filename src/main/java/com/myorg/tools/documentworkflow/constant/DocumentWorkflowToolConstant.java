package com.myorg.tools.documentworkflow.constant;

public class DocumentWorkflowToolConstant {
	
	public static String All_Doc_WKFL_Population_SQL = "select distinct docId, * from Document_Workflow_Process";
	public static String WKFL_DTL_DOC_SQL = "select * from Document where docId = ";
	public static String WKFL_DTL_DOC_TAG_REL_SQL = "select * from DocumentTagRelationship where docId = ";
	public static String WKFL_DTL_DOC_TAG_OVERRIDE_SQL = "select * from DocumentWorkflowDetail where docId = ";
	public static String DOC_TAG_REL_SQL = "select * from DocumentTagRelationship where tagId = 3 and docId = ";
}
