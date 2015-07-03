package com.myorg.tools.documentworkflow.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.DocumentTagRelationship;

public class DocumentTagRelationshipMapper implements RowMapper<DocumentTagRelationship> {

	public DocumentTagRelationship mapRow(ResultSet rs, int rowNum) throws SQLException {
      DocumentTagRelationship docTagRelationship = new DocumentTagRelationship();
      docTagRelationship.setDocId(rs.getInt("docId"));
      docTagRelationship.setTagTypeId(rs.getInt("tagTypeId"));
      docTagRelationship.setTagSubTypeId(rs.getInt("tagSubTypeId"));
      docTagRelationship.setCreationDt(rs.getDate("creationDt"));
      docTagRelationship.setCreatedBy(rs.getString("createdBy"));
      docTagRelationship.setLastUpdatedDt(rs.getDate("lastUpdatedDt"));
      docTagRelationship.setLastUpdatedBy(rs.getString("lastUpdatedBy"));
      return docTagRelationship;
    }
}
