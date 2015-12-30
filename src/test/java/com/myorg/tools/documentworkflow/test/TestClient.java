package com.myorg.tools.documentworkflow.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

import com.myorg.tools.documentworkflow.dto.DocumentDTO;
import com.myorg.tools.documentworkflow.model.User;

public class TestClient {
	private static String url = "http://localhost:8080/documentworkflow/rest/WflService/";

	public static void main(String[] args) throws Exception {
		//getDocumentsForAMaker();
		
/*		HAPPY PATH
		----------------------------------------------------
*/		
		DocumentDTO documentDTO = new DocumentDTO(); 
		
		//Maker in Progress
		documentDTO.setStatusCode(2);
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("ARDHENDU","APM","1"));
		documentDTO.setRoleId(1); 
		startProcess(documentDTO);
		//getDocumentsForAMaker();
		
		//Maker Complete
		documentDTO = new DocumentDTO(); 
		documentDTO.setStatusCode(3);
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("ARDHENDU","APM","1"));
		documentDTO.setRoleId(1); 
		completeProcess(documentDTO);
		//getDocumentsForAMaker();
		
		//Checker Team INbox
		getDocumentsForCheckerTeamInbox(new DocumentDTO());		
		
		//Checker Assign
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("RASHMI","LIGHT","2"));
		documentDTO.setRoleId(2); 	
		assignDocuments(documentDTO,Arrays.asList(new Integer[]{123456}));
		
		//Checker My Inbox
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("RASHMI","LIGHT","2"));
		documentDTO.setRoleId(2); 
		getDocumentsForCheckerMyInbox(documentDTO);

		//Checker In Progress
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("RASHMI","LIGHT","2"));
		documentDTO.setRoleId(2); 
		documentDTO.setStatusCode(17);
		startProcess(documentDTO);

		//Checker Complete (using above user credentials)
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("RASHMI","LIGHT","2"));
		documentDTO.setRoleId(2); 
		documentDTO.setStatusCode(18);
		documentDTO.setComment("Checker Comments...Resolve Hold issue...");
		completeProcess(documentDTO);
		
		//Checker Team INbox
		getDocumentsForCheckerTeamInbox(new DocumentDTO());
		
		
		
/*		MOST UNHAPPY PATH - Cross all hold scenarios
		---------------------------------------------------------------
*/		
		//Maker in Progress
		documentDTO = new DocumentDTO(); 
		documentDTO.setStatusCode(2);
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("ARDHENDU","APM","1"));
		documentDTO.setRoleId(1); 
		startProcess(documentDTO);
		//getDocumentsForAMaker();
		
		//Maker Hold
		documentDTO = new DocumentDTO(); 
		documentDTO.setStatusCode(10);
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("ARDHENDU","APM","1"));
		documentDTO.setRoleId(1);
		documentDTO.setComment("Maker Comments...");
		holdProcess(documentDTO);

		//Checker Team INbox
		getDocumentsForCheckerTeamInbox(new DocumentDTO());		
		
		//Checker Assign
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("RASHMI","LIGHT","2"));
		documentDTO.setRoleId(2); 	
		assignDocuments(documentDTO,Arrays.asList(new Integer[]{123456}));
		
		//Checker My Inbox
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("RASHMI","LIGHT","2"));
		documentDTO.setRoleId(2); 
		getDocumentsForCheckerMyInbox(documentDTO);
		
		//Checker In Progress
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("RASHMI","LIGHT","2"));
		documentDTO.setRoleId(2); 
		documentDTO.setStatusCode(17);
		startProcess(documentDTO);

		//Checker Hold
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("RASHMI","LIGHT","2"));
		documentDTO.setRoleId(2); 
		documentDTO.setStatusCode(15);
		documentDTO.setComment("Checker Comments...Have query...");
		documentDTO.setErrorReasonCode(2);
		holdProcess(documentDTO);
		
		//Onshore SME Team INbox
		getDocumentsForOnshoreSMETeamInbox(new DocumentDTO());
		
		//SME Assign
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("MRINAL","MRINAL","3"));
		documentDTO.setRoleId(3); 	
		assignDocuments(documentDTO,Arrays.asList(new Integer[]{123456}));
		
		//SME My Inbox
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("MRINAL","MRINAL","3"));
		documentDTO.setRoleId(3); 
		getDocumentsForOnshoreSMEMyInbox(documentDTO);
		
		//SME - In Progress
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("MRINAL","MRINAL","3"));
		documentDTO.setRoleId(3); 
		documentDTO.setStatusCode(20);
		startProcess(documentDTO);

		//SME - Hold
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("MRINAL","MRINAL","3"));
		documentDTO.setRoleId(3); 
		documentDTO.setStatusCode(21);
		documentDTO.setComment("SME Comments...Have query to JPMC...");
		holdProcess(documentDTO);
		
		//SME Complete
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("MRINAL","MRINAL","3"));
		documentDTO.setRoleId(3); 
		documentDTO.setStatusCode(22);
		documentDTO.setComment("SME Comments...query resolved...");
		completeProcess(documentDTO);
		
		//Onshore SME Team INbox
		getDocumentsForOnshoreSMETeamInbox(new DocumentDTO());	
		
		

/*		SEMI UNHAPPY SCENARIO - MAKER HOLD CHECKER COMPLETE
		-----------------------------------------------------------
*/
		//Maker in Progress
		documentDTO = new DocumentDTO(); 
		documentDTO.setStatusCode(2);
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("ARDHENDU","APM","1"));
		documentDTO.setRoleId(1); 
		startProcess(documentDTO);
		//getDocumentsForAMaker();
		
		//Maker Hold
		documentDTO = new DocumentDTO(); 
		documentDTO.setStatusCode(10);
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("ARDHENDU","APM","1"));
		documentDTO.setRoleId(1);
		documentDTO.setComment("Maker Comments...");
		holdProcess(documentDTO);

		//Checker Team INbox
		getDocumentsForCheckerTeamInbox(new DocumentDTO());		
		
		//Checker Assign
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("RASHMI","LIGHT","2"));
		documentDTO.setRoleId(2); 	
		assignDocuments(documentDTO,Arrays.asList(new Integer[]{123456}));
		
		//Checker My Inbox
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("RASHMI","LIGHT","2"));
		documentDTO.setRoleId(2); 
		getDocumentsForCheckerMyInbox(documentDTO);
		
		//Checker In Progress
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("RASHMI","LIGHT","2"));
		documentDTO.setRoleId(2); 
		documentDTO.setStatusCode(17);
		startProcess(documentDTO);
		
		//Checker Complete (using above user credentials)
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("RASHMI","LIGHT","2"));
		documentDTO.setRoleId(2); 
		documentDTO.setStatusCode(18);
		completeProcess(documentDTO);
		
		//Checker Team INbox
		getDocumentsForCheckerTeamInbox(new DocumentDTO());	

/*		SEMI UNHAPPY PATH - MAKER HOLD CHECKER HOLD SME COMPLETE
		---------------------------------------------------------------
*/		
		//Maker in Progress
		documentDTO = new DocumentDTO(); 
		documentDTO.setStatusCode(2);
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("ARDHENDU","APM","1"));
		documentDTO.setRoleId(1); 
		startProcess(documentDTO);
		//getDocumentsForAMaker();
		
		//Maker Hold
		documentDTO = new DocumentDTO(); 
		documentDTO.setStatusCode(10);
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("ARDHENDU","APM","1"));
		documentDTO.setRoleId(1);
		documentDTO.setComment("Maker Comments...");
		holdProcess(documentDTO);

		//Checker Team INbox
		getDocumentsForCheckerTeamInbox(new DocumentDTO());		
		
		//Checker Assign
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("RASHMI","LIGHT","2"));
		documentDTO.setRoleId(2); 	
		assignDocuments(documentDTO,Arrays.asList(new Integer[]{123456}));
		
		//Checker My Inbox
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("RASHMI","LIGHT","2"));
		documentDTO.setRoleId(2); 
		getDocumentsForCheckerMyInbox(documentDTO);
		
		//Checker In Progress
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("RASHMI","LIGHT","2"));
		documentDTO.setRoleId(2); 
		documentDTO.setStatusCode(17);
		startProcess(documentDTO);

		//Checker Hold
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("RASHMI","LIGHT","2"));
		documentDTO.setRoleId(2); 
		documentDTO.setStatusCode(15);
		documentDTO.setComment("Checker Comments...Have query...");
		documentDTO.setErrorReasonCode(2);
		holdProcess(documentDTO);
		
		
		//Onshore SME Team INbox
		getDocumentsForOnshoreSMETeamInbox(new DocumentDTO());
		
		//SME Assign
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("MRINAL","MRINAL","3"));
		documentDTO.setRoleId(3); 	
		assignDocuments(documentDTO,Arrays.asList(new Integer[]{123456}));
		
		//SME My Inbox
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("MRINAL","MRINAL","3"));
		documentDTO.setRoleId(3); 
		getDocumentsForOnshoreSMEMyInbox(documentDTO);
		
		//SME - In Progress
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("MRINAL","MRINAL","3"));
		documentDTO.setRoleId(3); 
		documentDTO.setStatusCode(20);
		startProcess(documentDTO);

		//SME Complete
		documentDTO = new DocumentDTO(); 
		documentDTO.setAgreementId(123456);
		documentDTO.setUser(new User("MRINAL","MRINAL","3"));
		documentDTO.setRoleId(3); 
		documentDTO.setStatusCode(22);
		documentDTO.setComment("SME Comments...query resolved...");
		completeProcess(documentDTO);
		
		//Onshore SME Team INbox
		getDocumentsForOnshoreSMETeamInbox(new DocumentDTO());	
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
	
	private static void getDocumentsForCheckerTeamInbox(DocumentDTO documentDTO) throws Exception { 
		String s = callRESTService(documentDTO, "getDocumentsForAllCheckers");
		System.out.println("###### Checker Team Inbox Response "+s);
	}
	
	private static void getDocumentsForCheckerMyInbox(DocumentDTO documentDTO) throws Exception { 
		String s = callRESTService(documentDTO, "getDocumentsForChecker");
		System.out.println("###### Checker My Inbox Response "+s);
	}	
	
	private static void getDocumentsForOnshoreSMETeamInbox(DocumentDTO documentDTO) throws Exception { 
		String s = callRESTService(documentDTO, "getDocumentsForAllSMEs");
		System.out.println("###### Onshore SME Team Inbox Response "+s);
	}
	
	private static void getDocumentsForOnshoreSMEMyInbox(DocumentDTO documentDTO) throws Exception { 
		String s = callRESTService(documentDTO, "getDocumentsForSME");
		System.out.println("###### Onshore SME My Inbox Response "+s);
	}	

	private static void assignDocuments(DocumentDTO dto,List<Integer> docIds) throws Exception { 
		ObjectMapper mapper = new ObjectMapper();
		String inputJsonString = mapper.writeValueAsString(docIds);
		System.out.println("inputJsonString :: " + inputJsonString);
		HttpClient client = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(url + "assignWorkflows");
		postRequest.setHeader("Content-Type", "application/json");
		postRequest.setHeader("x-docwrkflow-auth", dto.getUser().getUserId()+"|"+dto.getRoleId());
		postRequest.setEntity(new StringEntity(inputJsonString));
		HttpResponse response = client.execute(postRequest);

		if (response.getStatusLine().getStatusCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(response.getEntity().getContent())));

		String readLine = br.readLine();

		System.out.println("###### REsponse "+readLine);
	}	
	 	
	
	private static void getDocumentsForAChecker() throws Exception {
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setUser(new User("ARDHENDU","ARDHENDU","1"));
		String responseString = callRESTService(documentDTO, "getDocumentsForChecker");
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
		//postRequest.setHeader("x-docwrkflow-auth", dto.getUser().getUserId()+"|"+dto.getRoleId());
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
	
	private static void startProcess(DocumentDTO documentDTO) throws Exception { 
		String s = callRESTService(documentDTO, "startProcess");
		System.out.println("Output Json :: " + s);
	}
	
	private static void completeProcess(DocumentDTO documentDTO) throws Exception { 
		String s = callRESTService(documentDTO, "completeProcess");
		System.out.println("Output Json :: " + s);
	}
	
	private static void holdProcess(DocumentDTO documentDTO) throws Exception { 
		String s = callRESTService(documentDTO, "holdProcess");
		System.out.println("Output Json :: " + s);
	}

}
