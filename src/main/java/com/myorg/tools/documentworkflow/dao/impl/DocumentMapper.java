package com.myorg.tools.documentworkflow.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myorg.tools.documentworkflow.model.Document;

public class DocumentMapper implements RowMapper<Document> {

	public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
      Document doc = new Document();
      doc.setDocId(rs.getInt("docId"));
      doc.setDocName(rs.getString("docName"));
      doc.setDocLocation(rs.getString("docLocation"));
      doc.setIsBadLinkReported(rs.getString("isBadLinkReported"));
      doc.setTargetDocLocation(rs.getString("targetDocLocation"));
      doc.setIsDeleted(rs.getString("isDeleted"));
      //doc.setCreationDt(rs.getDate("creationDt"));
      //doc.setCreatedBy(rs.getString("createdBy"));
      return doc;
    }
}
