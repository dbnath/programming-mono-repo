package com.myorg.tools.documentworkflow.constant;

public class DocumentWorkflowToolConstant {
	
	public static String All_Doc_WKFL_Population_SQL = "select distinct a.ID_DOC ID_DOC, e.NM_DOC NM_DOC, e.ID_DOC_TYPE ID_DOC_TYPE, f.TX_DOC_TYPE TX_DOC_TYPE, e.ID_DOC_REPO ID_DOC_REPO, h.TX_DOC_REPO TX_DOC_REPO, e.DOC_HYPERLINK DOC_HYPERLINK, e.DOC_LOCATION DOC_LOCATION, a.ID_WF_STATUS ID_WF_STATUS, b.TX_WF_STATUS TX_WF_STATUS, c.ID_WF_GRP ID_WF_GRP, d.NM_WF_GRP NM_WF_GRP, c.TX_ACTIVITY_DESC TX_ACTIVITY_DESC, a.IS_REWORKED IS_REWORKED, a.ASSIGNED_TO ASSIGNED_TO, a.ID_ROLE ID_ROLE, a.ASSIGNED_DT ASSIGNED_DT, a.LAST_UPDATED_BY LAST_UPDATED_BY, a.LAST_UPDATE_DT LAST_UPDATE_DT from doc_workflow_process a, doc_wf_status b, wf_grp_activity_map c, doc_wf_grp d, document e, DOC_TYPE f, DOC_REPO h, ROLE g where a.ID_WF_STATUS = b.ID_WF_STATUS and b.ID_WF_STATUS = c.ID_WF_STATUS and c.ID_WF_GRP = d.ID_WF_GRP and a.ID_DOC = e.ID_DOC and e.ID_DOC_TYPE = f.ID_DOC_TYPE and e.ID_DOC_REPO = h.ID_DOC_REPO";
	public static String Doc_WKFL_Population_SQL = "select distinct a.ID_DOC ID_DOC, e.NM_DOC NM_DOC, e.ID_DOC_TYPE ID_DOC_TYPE, f.TX_DOC_TYPE TX_DOC_TYPE, e.ID_DOC_REPO ID_DOC_REPO, h.TX_DOC_REPO TX_DOC_REPO, e.DOC_HYPERLINK DOC_HYPERLINK, e.DOC_LOCATION DOC_LOCATION, a.ID_WF_STATUS ID_WF_STATUS, b.TX_WF_STATUS TX_WF_STATUS, c.ID_WF_GRP ID_WF_GRP, d.NM_WF_GRP NM_WF_GRP, c.TX_ACTIVITY_DESC TX_ACTIVITY_DESC, a.IS_REWORKED IS_REWORKED, a.ASSIGNED_TO ASSIGNED_TO, a.ID_ROLE ID_ROLE, a.ASSIGNED_DT ASSIGNED_DT, a.LAST_UPDATED_BY LAST_UPDATED_BY, a.LAST_UPDATE_DT LAST_UPDATE_DT from doc_workflow_process a, doc_wf_status b, wf_grp_activity_map c, doc_wf_grp d, document e, DOC_TYPE f, DOC_REPO h, ROLE g where a.ID_DOC = ? and a.ID_WF_STATUS = b.ID_WF_STATUS and b.ID_WF_STATUS = c.ID_WF_STATUS and c.ID_WF_GRP = d.ID_WF_GRP and a.ID_DOC = e.ID_DOC and e.ID_DOC_TYPE = f.ID_DOC_TYPE and e.ID_DOC_REPO = h.ID_DOC_REPO";
	public static String WKFL_DTL_DOC_SQL = "select a.ID_DOC ID_DOC, a.NM_DOC NM_DOC, a.ID_DOC_TYPE ID_DOC_TYPE, b.TX_DOC_TYPE, a.ID_DOC_REPO, c.TX_DOC_REPO, a.DOC_HYPERLINK, a.DOC_LOCATION, a.IS_DELETED, a.IS_VALID_LINK, a.CREATED_BY, a.CREATION_DT, a.LAST_UPDATED_BY, a.LAST_UPDATE_DT from Document a, doc_type b, doc_repo c where a.ID_DOC = ? and a.ID_DOC_TYPE=b.ID_DOC_TYPE and a.ID_DOC_REPO=c.ID_DOC_REPO";
	public static String WKFL_DTL_DOC_TAG_REL_SQL = "select a.ID_DOC, a.ID_DOC_TYPE, d.TX_DOC_TYPE, a.ID_DOC_TAG, b.TX_DOC_TAG, a.ID_DOC_SUB_TAG, c.TX_DOC_SUB_TAG, a.CREATED_BY, a.CREATION_DT, a.LAST_UPDATED_BY, a.LAST_UPDATE_DT from doc_tag_rel a, doc_tag_info b, doc_sub_tag_info c, doc_type d where a.ID_DOC = ? and a.ID_DOC_TAG = b.ID_DOC_TAG and a.ID_DOC_SUB_TAG = c.ID_DOC_SUB_TAG and a.ID_DOC_TYPE = d.ID_DOC_TYPE";
	public static String WKFL_DTL_DOC_TAG_OVERRIDE_SQL = "select * from doc_wf_dtl where ID_DOC = ?";
	public static String DOC_TYPE_POPULATE_SQL = "select * from doc_type";
	public static String DOC_REPO_POPULATE_SQL = "select * from doc_repo";
	public static String DOC_TAG_POPULATE_SQL = "select * from doc_tag_info";
	public static String DOC_SUBTAG_POPULATE_SQL = "select * from doc_sub_tag_info";
	public static String DOC_TYPE_TAG_MAP_SQL = "select a.ID_DOC_TAG ID_DOC_TAG, b.TX_DOC_TAG TX_DOC_TAG from doc_type_tag_mapping a, doc_tag_info b where a.ID_DOC_TYPE = ? and a.ID_DOC_TAG = b.ID_DOC_TAG";
	public static String DOC_TAG_SUB_TAG_MAP_SQL = "select a.ID_DOC_SUB_TAG ID_DOC_SUB_TAG, b.TX_DOC_SUB_TAG TX_DOC_SUB_TAG from doc_tag_subtag_map a, doc_sub_tag_info b where a.ID_DOC_TAG = ? and a.ID_DOC_SUB_TAG = b.ID_DOC_SUB_TAG";
	public static String DOC_TYPE_TAG_SUBTAGS_MAP_SQL = "select a.ID_DOC_TAG ID_DOC_TAG, b.TX_DOC_TAG TX_DOC_TAG, c.ID_DOC_SUB_TAG ID_DOC_SUB_TAG, d.TX_DOC_SUB_TAG TX_DOC_SUB_TAG from doc_type_tag_mapping a, doc_tag_info b, doc_tag_subtag_map c, doc_sub_tag_info d where a.ID_DOC_TYPE = ? and a.ID_DOC_TAG = b.ID_DOC_TAG and a.ID_DOC_TAG = c.ID_DOC_TAG and c.ID_DOC_SUB_TAG = d.ID_DOC_SUB_TAG";
	public static String USER_BASE_POPULATE_SQL = "select * from user";
	public static String MASTER_ROLE_POPULATE_SQL = "select * from role";
	public static String FETCH_USER_DETAILS_SQL = "select * from user where ID_USER = ?";
	public static String FETCH_ROLE_USER_MAP_SQL = "select a.ID_USER, b.NM_USER, b.PASSWORD, b.PASSWORD_TYPE, b.USER_STATUS, b.LAST_LOGIN, a.CREATED_BY, a.CREATION_DT, a.UPDATED_BY, a.UPDATE_DT from user_role_map a, user b where a.ID_ROLE = ? and a.ID_USER = b.ID_USER";
	public static String POPULATE_ROLE_FOR_USER_SQL = "select a.ID_ROLE, b.NM_ROLE, b.TX_ROLE from user_role_map a, role b where a.ID_USER = ? and a.ID_ROLE = b.ID_ROLE";
	
	public static String DEL_DOC_TAG_REL_SQL = "delete from doc_tag_rel where ID_DOC = ?";
	public static String INS_DOC_TAG_REL_SQL = "insert into doc_tag_rel values (?, ?, ?, ?, ?, ?, ?, ?)";
	public static String SEL_VER_DOC_TAG_REL_AUDIT_SQL = "select max(ID_VER) from doc_tag_rel_audit where ID_DOC = ?";
	public static String INS_DOC_TAG_REL_AUDIT_SQL = "insert into doc_tag_rel_audit values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static String UPD_DOCUMENT_BAD_LINK_SQL = "update document set IS_VALID_LINK = ? where ID_DOC = ?";
	public static String SEL_VER_DOCUMENT_AUDIT_SQL = "select max(ID_VER) from document_audit where ID_DOC = ?";
	public static String INS_DOCUMENT_AUDIT_SQL = "insert into document_audit values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";
	public static String DEL_DOC_WFL_DTL_SQL = "delete from doc_wf_dtl where ID_DOC = ?";
	public static String INS_DOC_WFL_DTL_SQL = "insert into doc_wf_dtl values (?, ?, ?, ?, ?)";
	public static String SEL_VER_DOC_WFL_DTL_AUDIT_SQL = "select max(ID_VER) from doc_wf_dtl_audit where ID_DOC = ?";
	public static String INS_DOC_WFL_DTL_AUDIT_SQL = "insert into doc_wf_dtl_audit values(?, ?, ?, ?, ?, ?)";
	public static String DEL_DOC_WFL_PROCESS_SQL = "delete from doc_workflow_process where ID_DOC = ?";
	public static String INS_DOC_WFL_PROCESS_SQL = "insert into doc_workflow_process values (?, ?, ?, ?, ?, ?, ?, ?)";
	public static String SEL_VER_DOC_WFL_PROCESS_AUDIT_SQL = "select max(ID_VER) from doc_workflow_process_audit where ID_DOC = ?";
	public static String INS_DOC_WFL_PROCESS_AUDIT_SQL = "insert into doc_workflow_process_audit values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static String INS_DOC_SQL = "insert into document (NM_DOC,ID_DOC_TYPE,ID_DOC_REPO,DOC_HYPERLINK,	DOC_LOCATION,IS_DELETED,IS_VALID_LINK,CREATED_BY,CREATION_DT,LAST_UPDATED_BY,LAST_UPDATE_DT ) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	
	public static String FETCH_DOC_WFL_SQL = "select * from doc_workflow_process where ID_DOC in ";
	
	//public static String UPD_DOC_WFL_ASGN_SQL = "update doc_workflow_process set ASSIGNED_TO= ?, ASSIGNED_DT=?,ID_ROLE=?,LAST_UPDATED_BY=?,LAST_UPDATE_DT=?,IS_REWORKED=? where ";
	
	
}
