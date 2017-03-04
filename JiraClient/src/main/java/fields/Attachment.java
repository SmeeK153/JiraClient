package fields;

import org.json.JSONObject;

import client.JIRAClient;

public class Attachment extends Field {
	
	private static final String ATTACHMENT_BASE_URL = "https://"+ JIRAClient.JIRA_APP_NAME + "/jira/secure/attachment/";
	private static final String BASE_URL = JIRAClient.JIRA_REST_BASE_URL + "attachment/";
	private int id;
	private String filename;
	private User author;
	
	protected Attachment(JSONObject json) {
		super(json);
	}
	
	public String getFileName(){
		return this.json.getString("filename");
	}
	
	public void getFile(){
		
	}
	
}
