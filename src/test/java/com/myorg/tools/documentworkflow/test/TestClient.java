package com.myorg.tools.documentworkflow.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

import com.myorg.tools.documentworkflow.dto.BaseDTO;
import com.myorg.tools.documentworkflow.dto.DocumentDTO;
import com.myorg.tools.documentworkflow.model.User;

public class TestClient {
	private static String url = "http://localhost:8080/documentworkflow/rest/WflService/";

	public static void main(String[] args) throws Exception {
		getDocumentsForAMaker();
	}

	private static void getDocumentsForAllMakers() throws Exception { 
		DocumentDTO documentDTO = new DocumentDTO(); 
		documentDTO.setDocStatus("New");
		String s = callRESTService(documentDTO, "getDocumentsForAllMakers");
		System.out.println("List size :: " + documentDTO.getDocList() != null ? documentDTO
				.getDocList().size() : null);
	}
	
	private static void getDocumentsForAMaker() throws Exception {
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setUser(new User("ARDHENDU","ARDHENDU","1"));
		String responseString = callRESTService(documentDTO, "getDocumentsForMaker");
		//System.out.println("List size :: " + documentDTO.getDocList() != null ? documentDTO.getDocList().size() : 0);
		System.out.println("###### REsponse "+responseString);
	}	

	private static String callRESTService(DocumentDTO dto, String method)
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String inputJsonString = mapper.writeValueAsString(dto);
		System.out.println("inputJsonString :: " + inputJsonString);
		HttpClient client = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(url + method);
		postRequest.setHeader("Content-Type", "application/json");
		postRequest.setEntity(new StringEntity(inputJsonString));
		HttpResponse response = client.execute(postRequest);

		if (response.getStatusLine().getStatusCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(response.getEntity().getContent())));

		String readLine = br.readLine();
		/*if (readLine != null) {
			dto = (T) new ObjectMapper().readValue(readLine, dto.getClass());
		}*/
		return readLine;
	}

}
