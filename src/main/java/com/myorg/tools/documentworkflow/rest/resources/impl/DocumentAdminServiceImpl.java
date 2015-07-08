package com.myorg.tools.documentworkflow.rest.resources.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.myorg.tools.documentworkflow.dao.DocumentAdminDAO;
import com.myorg.tools.documentworkflow.model.Document;
import com.myorg.tools.documentworkflow.model.DocumentRepository;
import com.myorg.tools.documentworkflow.model.DocumentSubTagValues;
import com.myorg.tools.documentworkflow.model.DocumentTag;
import com.myorg.tools.documentworkflow.model.DocumentTagSubTagMapping;
import com.myorg.tools.documentworkflow.model.DocumentType;
import com.myorg.tools.documentworkflow.model.DocumentTypeTagMapping;
import com.myorg.tools.documentworkflow.rest.resources.BaseResource;
import com.myorg.tools.documentworkflow.rest.resources.DocumentAdminService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

public class DocumentAdminServiceImpl extends BaseResource implements DocumentAdminService {
	
	private DocumentAdminDAO documentAdminDAO;
	
	/**
	 * @return the documentAdminDAO
	 */
	public DocumentAdminDAO getDocumentAdminDAO() {
		return documentAdminDAO;
	}
	/**
	 * @param documentAdminDAO the documentAdminDAO to set
	 */
	public void setDocumentAdminDAO(DocumentAdminDAO documentAdminDAO) {
		this.documentAdminDAO = documentAdminDAO;
	}
	
	public Response populateDocType() {
		try{
			List<DocumentType> docTypeList = documentAdminDAO.populateDocumentTypes();
			return Response.ok().entity(docTypeList).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();			
		}
	}
	public Response populateDocRepository() {
		try{
			List<DocumentRepository> docRepoList = documentAdminDAO.populateDocumentRepos();
			return Response.ok().entity(docRepoList).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();			
		}
	}
	public Response populateDocTagsMaster() {
		try{
			List<DocumentTag> docTagMasterList = documentAdminDAO.populateDocumentTagsMasterList();
			return Response.ok().entity(docTagMasterList).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();			
		}
	}
	public Response populateDocSubTagsMaster() {
		try{
			List<DocumentSubTagValues> docSubTagMasterList = documentAdminDAO.populateDocumentSubTagsMasterList();
			return Response.ok().entity(docSubTagMasterList).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();			
		}
	}
	public Response populateDocTypeTagsMapping(Integer docTypeId){
		try{
			DocumentTypeTagMapping docTypeTagMap = documentAdminDAO.populateDocumentTypeTagMapping(docTypeId);
			return Response.ok().entity(docTypeTagMap).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();			
		}
	}
	public Response populateUnmappedDocTypeTags(Integer docTypeId){
		try{
			List<DocumentTag> docUnmappedTagMasters = documentAdminDAO.populateUnmappedDocTypeTags(docTypeId);
			return Response.ok().entity(docUnmappedTagMasters).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();			
		}
	}
	public Response populateDocTagSubTagMapping(Integer docTagId){
		try{
			DocumentTagSubTagMapping docTagSubTagMap = documentAdminDAO.populateDocumentTagSubTagMapping(docTagId);
			return Response.ok().entity(docTagSubTagMap).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();			
		}
	}
	public Response populateUnmappedDocTagSubTags(Integer docTagId){
		try{
			List<DocumentSubTagValues> docUnmappedSubTagMasters = documentAdminDAO.populateUnmappedDocTagSubTags(docTagId);
			return Response.ok().entity(docUnmappedSubTagMasters).build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();			
		}
	}
	
	public Response uploadDocuments(@FormDataParam("file") InputStream uploadedInputStream,  @FormDataParam("file") FormDataContentDisposition fileDetail, @FormDataParam("path") String path){
		
		try {
			
			String userId = "PRATIK"; //FIXME This is to be replaced with Logged in User Id
			
			List<Document> docList = parseBulkUploadFile(uploadedInputStream);
			documentAdminDAO.uploadDocumentInformation(docList, userId);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private List<Document> parseBulkUploadFile(InputStream stream) throws IOException {

		List<Document> docList = new ArrayList<Document>();
		if (stream != null) {
			XSSFWorkbook wb = new XSSFWorkbook(stream);

			XSSFSheet sheet = wb.getSheetAt(0);
			
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				XSSFRow row = sheet.getRow(i);

				if (row != null) {

					Document doc = new Document();

					for (int j = 0; j < 5; j++) {
						XSSFCell cell = row.getCell(j);

						int colNum = cell.getColumnIndex();

						String s = cell.getRichStringCellValue().getString();

						switch (colNum) {
						case 0:
							doc.setDocName(s);
							break;
						case 1:
							doc.setDocTypeDesc(s);
							break;
						case 2:
							doc.setDocRepoDesc(s);
							break;
						case 3:
							doc.setDocHyperlink(s);
							break;
						case 4:
							doc.setDocRepoDesc(s);
							break;
						}

					}
					docList.add(doc);
				}

			}
		}
		
		return docList;
	}

	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.rest.resources.DocumentAdminService#getTemplate()
	 */
	@Override
	public Response getTemplate() {
		
		
		try {
			List<DocumentRepository> repoLIst = documentAdminDAO.populateDocumentRepos();
			List<DocumentType> typeList = documentAdminDAO.populateDocumentTypes();
			
			XSSFWorkbook wb = new XSSFWorkbook();			
			XSSFSheet sheet = wb.createSheet();
			
			XSSFRow headerRow = sheet.createRow(0);
			
			XSSFCell cell = headerRow.createCell(0);
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	
	
	
	
	/*public Response updateDocTypes(List<DocumentType> docTypeList){
		return Response.ok().entity(Boolean.TRUE).build();
	}
	public Response updateDocRepos(List<DocumentRepository> docRepoList){
		return Response.ok().entity(Boolean.TRUE).build();
	}
	public Response updateDocTagsMasterList(List<DocumentTag> docTageList){
		return Response.ok().entity(Boolean.TRUE).build();
	}
	public Response updateDocSubTagsMasterList(List<DocumentSubTagValues> docSubTagsList){
		return Response.ok().entity(Boolean.TRUE).build();
	}
	public Response updateDocTypeTagMap(List<DocumentTypeTagMapping> docTypeTagRelList){
		return Response.ok().entity(Boolean.TRUE).build();
	}
	public Response updateDocTagSubTagMap(List<DocumentTagSubTagMapping> docTagSubTagRelList){
		return Response.ok().entity(Boolean.TRUE).build();
	}*/
}
