package com.myorg.tools.documentworkflow.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.myorg.tools.documentworkflow.constant.DocumentWorkflowToolConstant;
import com.myorg.tools.documentworkflow.dao.UserAdminDAO;
import com.myorg.tools.documentworkflow.mapper.RoleMapper;
import com.myorg.tools.documentworkflow.mapper.UserMapper;
import com.myorg.tools.documentworkflow.mapper.UserRoleMapper;
import com.myorg.tools.documentworkflow.model.ReverseMappable;
import com.myorg.tools.documentworkflow.model.Role;
import com.myorg.tools.documentworkflow.model.RoleUser;
import com.myorg.tools.documentworkflow.model.RoleUsersMapping;
import com.myorg.tools.documentworkflow.model.User;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class UserAdminDAOImpl extends BaseJDBCTemplate implements UserAdminDAO {

	static Logger log = Logger.getLogger(UserAdminDAOImpl.class.getName());
	
	public List<User> populateUserbase() throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.USER_BASE_POPULATE_SQL;
		List<User> usersList = null;
		try{
			usersList = this.getJdbcTemplateObject().query(SQL, new UserMapper());
		} catch(EmptyResultDataAccessException e) {
			usersList = null;
		}
		return usersList;
	}
	
	public User fetchUserDetail(String userId) throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.FETCH_USER_DETAILS_SQL;
		User userDetailObj = null;
		try{
			userDetailObj = this.getJdbcTemplateObject().queryForObject(SQL, new Object[]{userId}, new UserMapper());
		} catch(EmptyResultDataAccessException e) {
			userDetailObj = null;
		}		
		if (! DocumentWorkflowToolUtility.isEmpty(userDetailObj)) {
			List<Role> roleList = populateRoleForUser(userId);
			if (roleList != null && !roleList.isEmpty()) {
				userDetailObj.setRoleId(roleList.get(0).getRoleId().toString());
				userDetailObj.setUserRoleList(roleList);
			}
		}
		return userDetailObj;
	}
	
	public User authenticateAndFetchDetails(String userId, String password) throws Exception {
		User userDetailObj = fetchUserDetail(userId);
		if (userDetailObj == null)		 {
			throw new Exception("User Id : "+userId +" doesn't exists in the system. Please Contact Admin.");
		} else if (userDetailObj != null && !userDetailObj.getPassword().equals(password)){
			throw new Exception("Login ID / Password entered is not correct.");
		} else {
			return userDetailObj;
		}
	}

	public List<Role> populateMasterRoleList() throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.MASTER_ROLE_POPULATE_SQL;
		List<Role> masterRoleList = null;
		try{
			masterRoleList = this.getJdbcTemplateObject().query(SQL, new RoleMapper());
		} catch(EmptyResultDataAccessException e) {
			masterRoleList = null;
		}
		return masterRoleList;
	}
	
	private List<Role> populateRoleForUser(String userId) throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.POPULATE_ROLE_FOR_USER_SQL;
		List<Role> roleList = null;
		try{
			roleList = this.getJdbcTemplateObject().query(SQL, new Object[]{userId}, new RoleMapper());
		} catch(EmptyResultDataAccessException e) {
			roleList = null;
		}
		return roleList;
	}

	public RoleUsersMapping populateRoleUserbaseMap(Integer roleId) throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.FETCH_ROLE_USER_MAP_SQL;
		List<User> usersList = null;
		try{
			usersList = this.getJdbcTemplateObject().query(SQL, new Object[]{roleId}, new UserMapper());
		} catch(EmptyResultDataAccessException e) {
			usersList = null;
		}
		RoleUsersMapping roleUsersMapping = new RoleUsersMapping();
		roleUsersMapping.setRoleId(roleId);
		roleUsersMapping.setUserList(usersList);
		return roleUsersMapping;
	}

	public List<User> populateUnmappedRoleUserbase(Integer roleId) throws SQLException, Exception {
		List<User> usersList = populateUserbase();
		List<String> userIdsList = new ArrayList<String>();
		for (User user : usersList){
			userIdsList.add(user.getUserId());
		}
		List<User> roleUsersMap = ((RoleUsersMapping)populateRoleUserbaseMap(roleId)).getUserList();
		for (User user : roleUsersMap) {
			if (userIdsList.contains(user.getUserId())) {
				usersList.remove(findUserElementToRemoveFromList(usersList, user.getUserId()));
			}
		}
		return usersList;
	}
	
	private User findUserElementToRemoveFromList(List<User> userList, String userId){
		if (! DocumentWorkflowToolUtility.isEmptyList(userList)) {
			for (User user : userList) {
				if (userId.equals(user.getUserId())){
					return user;
				}
			}
		}
		return null;
	}
	
	public List<RoleUser> populateAllUserRole() throws SQLException, Exception {
		String SQL = DocumentWorkflowToolConstant.FETCH_ALL_ROLE_USER_MAP;
		List<RoleUser> usersList = null;
		try{
			usersList = this.getJdbcTemplateObject().query(SQL, new UserRoleMapper());
		} catch(EmptyResultDataAccessException e) {
			usersList = null;
		}
		return usersList;
	}
	
	 
	
	@Override
	public boolean uploadUserRoleMappings(List<RoleUser> roleUserList, String userId)  {
		
		if(roleUserList != null){

			JdbcTemplate jdbcTemplate = this.getJdbcTemplateObject();
			TransactionDefinition def = new DefaultTransactionDefinition();
			TransactionStatus status = this.getTransactionManager().getTransaction(def);			
			try {
				HashMap<String, Integer> roleMap = DocumentWorkflowToolUtility.mapByValue(new ArrayList<ReverseMappable>(populateMasterRoleList()));	
				log.debug("###### roleMap "+roleMap);
				for(RoleUser doc : roleUserList){
					if(doc != null){
						log.debug("###### roleName "+doc.getRoleName());
						doc.setRoleId(roleMap.get(doc.getRoleName()));
						updateUserRoleInDB(doc, jdbcTemplate, userId);
					}
				}
				this.getTransactionManager().commit(status);
				return true;

			} catch (SQLException e) {
				log.error(e.getMessage(),e);
				this.getTransactionManager().rollback(status);
				return false;
			} catch (Exception e) {
				log.error(e.getMessage(),e);
				this.getTransactionManager().rollback(status);
				return false;				
			}			
		}
		return false;
	}
	
	private void updateUserRoleInDB(RoleUser roleUser, JdbcTemplate jdbcTemplate, String userId){
		
		String INS_SQL = DocumentWorkflowToolConstant.INS_USER;
		String INS_ROLE_MAP = DocumentWorkflowToolConstant.INS_USER_ROLE;
		String SEL_SQL = DocumentWorkflowToolConstant.FETCH_USER;
		String UPD_SQL = DocumentWorkflowToolConstant.UPD_USER;
		String UPD_ROLE_MAP = DocumentWorkflowToolConstant.UPD_USER_ROLE;
		
		@SuppressWarnings("deprecation")
		Integer userCnt = jdbcTemplate.queryForInt(SEL_SQL,roleUser.getUserId());
		
		String tempPassword = "password";
		
		if(userCnt == 0) {
			jdbcTemplate.update(INS_SQL, roleUser.getUserId(), roleUser.getUserName(),tempPassword,"T",roleUser.getUserStatus(),userId,new java.util.Date(),null,userId,new java.util.Date());
			jdbcTemplate.update(INS_ROLE_MAP, roleUser.getUserId(), roleUser.getRoleId(),userId,new java.util.Date(),userId,new java.util.Date());
		} else {
			jdbcTemplate.update(UPD_SQL, roleUser.getUserName(),roleUser.getUserStatus(),userId,new java.util.Date(),roleUser.getUserId());
			jdbcTemplate.update(UPD_ROLE_MAP, roleUser.getRoleId(),userId,new java.util.Date(),roleUser.getUserId());
		}
		
	}
}
