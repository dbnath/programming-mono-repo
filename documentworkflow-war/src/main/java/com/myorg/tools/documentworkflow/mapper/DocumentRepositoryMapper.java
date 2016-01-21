package com.myorg.tools.documentworkflow.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.DocumentRepository;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class DocumentRepositoryMapper implements RowMapper<DocumentRepository> {
	
	public DocumentRepository mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocumentRepository docRepo = new DocumentRepository();
      if (! DocumentWorkflowToolUtility.isEmpty(rs)) {
    	  docRepo.setDocRepoId(rs.getInt("ID_DOC_REPO"));
    	  docRepo.setDocRepoName(rs.getString("TX_DOC_REPO"));
      }
      return docRepo;
    }

}
