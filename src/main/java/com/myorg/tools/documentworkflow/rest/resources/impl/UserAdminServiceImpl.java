package com.myorg.tools.documentworkflow.rest.resources.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.myorg.tools.documentworkflow.dao.UserAdminDAO;
import com.myorg.tools.documentworkflow.model.AgreementType;
import com.myorg.tools.documentworkflow.model.Role;
import com.myorg.tools.documentworkflow.model.RoleUser;
import com.myorg.tools.documentworkflow.model.RoleUsersMapping;
import com.myorg.tools.documentworkflow.model.User;
import com.myorg.tools.documentworkflow.rest.resources.BaseResource;
import com.myorg.tools.documentworkflow.rest.resources.UserAdminService;
import com.myorg.tools.documentworkflow.util.ExcelUtil;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

public class UserAdminServiceImpl extends BaseResource implements UserAdminService {
	
	private UserAdminDAO userAdminDAO;

	/**
	 * @return the userAdminDAO
	 */
	public UserAdminDAO getUserAdminDAO() {
		return userAdminDAO;
	}

	/**
	 * @param userAdminDAO the userAdminDAO to set
	 */
	public void setUserAdminDAO(UserAdminDAO userAdminDAO) {
		this.userAdminDAO = userAdminDAO;
	}
	
	public Response populateUsersList() {
		try{
			List<User> usersList = userAdminDAO.populateUserbase();
			return Response.ok().entity(usersList).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	public Response populateMasterRoleList() {
		try{
			List<Role> masterRoleList = userAdminDAO.populateMasterRoleList();
			return Response.ok().entity(masterRoleList).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();
		}
		
	}
	
	public Response populateUserDetail(String userId) {
		try{
			User user = userAdminDAO.fetchUserDetail(userId);
			return Response.ok().entity(user).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	public Response populateRoleUserMapping(Integer roleId) {
		try{
			RoleUsersMapping users = userAdminDAO.populateRoleUserbaseMap(roleId);
			return Response.ok().entity(users).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	public Response populateUnmappedRoleUserbase(Integer roleId) {
		try{
			List<User> usersList = userAdminDAO.populateUnmappedRoleUserbase(roleId);
			return Response.ok().entity(usersList).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	 public Response populateAllRoleUserMapping() {
			try {
				List<RoleUser> roleUserList = userAdminDAO.populateAllUserRole();
				List<Role> roleList = userAdminDAO.populateMasterRoleList();
				ExcelUtil util = new ExcelUtil();
				XSSFWorkbook wb = util.generateRoleUserUploadTemplate(roleUserList,roleList);
				
				File file = new File(this.getAppConfig().getTempFileLocation()+"/RoleUserUploadTemplate"+System.currentTimeMillis()+".xlsx");
				
				FileOutputStream baos = new FileOutputStream(file);
				wb.write(baos);
				baos.close();			
				
				return Response.ok(file).header("Content-Disposition", "attachment; filename=\"RoleUserUploadTemplate.xlsx\"").build();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return Response.status(404).entity("Template Not Available: ").type("text/plain").build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(404).entity("Template Not Available: ").type("text/plain").build();
			}		 
	 }
	 
		public Response uploadUserRoles(@FormDataParam("file") InputStream uploadedInputStream,  @FormDataParam("file") FormDataContentDisposition fileDetail, @FormDataParam("path") String path, @FormDataParam("userId") String userId){
			
			try {
				ExcelUtil util = new ExcelUtil();
				List<RoleUser> roleUserList = util.parseRoleUserUploadFile(uploadedInputStream);
				boolean uploadStatus = userAdminDAO.uploadUserRoleMappings(roleUserList, userId);
				
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

}
