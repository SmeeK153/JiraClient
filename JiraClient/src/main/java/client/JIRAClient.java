package client;

import java.io.File;

import issues.Issue;
import ssl.SSLConnectionManagerFactory;

public class JIRAClient {
	
	private JIRAClient(){}
	private static File JIRA_CERTIFICATE_FILE = new File(System.getProperty("user.home") + "/.testmanager/.credentials/JIRA.crt");
	public static final String JIRA_REST_BASE_URL = "https://jira/jira/api/latest/";
	
	private static String JIRA_REST_ISSUE_BASE_URL = JIRA_REST_BASE_URL + "issue/{issueID}?expand=editmeta";
	private static String JIRA_REST_ATTACHMENT_BASE_URL = JIRA_REST_BASE_URL + "attachment/";
	private static String JIRA_REST_COMMENT_BASE_URL = JIRA_REST_BASE_URL + "comment/";
	private static String JIRA_REST_COMPONENT_BASE_URL = JIRA_REST_BASE_URL + "component/";
	private static String JIRA_REST_FIELD_BASE_URL = JIRA_REST_BASE_URL + "field/";
	private static String JIRA_REST_FILTER_BASE_URL = JIRA_REST_BASE_URL + "filter/";
	
	
	static{
		if(!JIRA_CERTIFICATE_FILE.exists()){
			System.out.println("Certificate for JIRA is missing, request from user.");
			//Put in code for importing the certificate
			
			if(SSLConnectionManagerFactory.getCertificate("JIRA")==null){
				System.out.println("Certificate doesn't exist in keystore, attempting to add from app directory.");
				SSLConnectionManagerFactory.setCertificate(JIRA_CERTIFICATE_FILE, "JIRA");
			}
		}
		
	}
	
	protected static String rootAddress(){
		return "";
	}
	
	public static Issue getIssue(String issueID){
		
		return null;
	}
	
	
}
