package com.myorg.tools.documentworkflow.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RESTClient {

	// http://localhost:8080/RESTfulExample/json/product/post
	public static void main(String[] args) {
 
	  String assignToMeURL = "http://localhost:8080/documentworkflow/rest/WflService/assigndoc";
	  String assingToMeJSON = "[{\"docId\":3,\"wfStatusId\":1,\"isReworked\":\"N\"}]";
	  
	  String submitDocURL = "http://localhost:8080/documentworkflow/rest/WflService/submitwf";
	  String submitDocJSON = "{   \"isFinalSubmit\":true,"+
	"\"docObj\":{\"docId\":3,\"docName\":\"IRA\",\"docTypeId\":1,\"docTypeDesc\":\"Trading Agreements\",\"docRepoId\":3,\"docRepoDesc\":\"Livelink\",\"docHyperlink\":\"https://www.livelink.com\",\"docLocation\":\"/apps/docs/ira\",\"wfStatusId\":2,\"wfStatusDesc\":\"Assigned to Analyst\",\"wfAssignmentGroupId\":1,\"wfAssignmentGroupName\":\"Analyst\",\"wfActivityDesc\":\"Document Assigned to Analyst\",\"isReworked\":\"N\",\"assignedTo\":\"PRATIK\",\"assignedDt\":\"2015-07-05\"},"+
	"\"docDetail\":{\"docId\":3,\"document\":{\"docId\":3,\"docName\":\"IRA\",\"docTypeId\":1,\"docTypeDesc\":\"Trading Agreements\",\"docRepoId\":3,\"docRepoDesc\":\"Livelink\",\"docHyperlink\":\"https://www.livelink.com\",\"docLocation\":\"/apps/docs/ira\",\"isBadLinkReported\":\"Y\",\"isDeleted\":\"N\",\"createdBy\":\"PRATIK\",\"creationDt\":\"2015-07-03\"},\"docTagRelationship\":[{\"docId\":3,\"docTypeId\":3,\"docTagId\":1,\"docSubTagId\":3,\"createdBy\":\"PRATIK\",\"creationDt\":\"2015-07-03\"}]}}";
		
		try {
 
		URL url = new URL("http://localhost:8080/documentworkflow/rest/WflService/submitwf");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
 
		String input = submitDocJSON;
 
		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();
 
		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}
 
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
 
		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}
 
		conn.disconnect();
 
	  } catch (MalformedURLException e) {
 
		e.printStackTrace();
 
	  } catch (IOException e) {
 
		e.printStackTrace();
 
	 }
 
	}
 


}
