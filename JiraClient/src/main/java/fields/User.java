package fields;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import client.JIRAClient;
import ssl.SSLConnectionManagerFactory;

public class User {

	private static final String JIRA_USERNAME_BASE_URL = JIRAClient.JIRA_REST_BASE_URL + "/user";
	private static JSONArray users = SSLConnectionManagerFactory.getSSLConnectionManager()
			.getDefaultSSLConnection(
					User.JIRA_USERNAME_BASE_URL + "/assignable/search?startAt=0&project=TGT&maxResults=100000")
			.getJSONArrayResponse();
	private String systemName;
	private String username;
	
	public User(JSONObject json){
		this.systemName = json.getString("name");
		this.username = json.getString("displayName");
	}
	
	public User(String username){
		//Check if it has a space: for system name, or friendly name
		if(username.contains(" ")){
			this.username = username;
			//Lookup the system name that doesn't have 'x_' before it
		} else {
			//Lookup the friendly username
			this.systemName = username;
		}
		
	}
	
	public static ArrayList<User> getCurrentUsers(){
		ArrayList<User> users = new ArrayList<User>();
		JSONArray userDefinitions = User.users;
		for(int i = 0; i < userDefinitions.length(); i++){
			users.add(new User(userDefinitions.getJSONObject(i)));
		}
		return users;
	}
	
	public boolean equals(String username){
		String first = this.username.split(" ")[0];
		String last = this.username.split(" ")[1];
		return (username.equals(first) || username.equals(last) || username.equals(this.systemName) || username.equals(this.username));
	}
	
	public String getJIRAUserSystemName(){
		return this.systemName;
	}
	
	public String getJIRAUsername(){
		return this.username;
	}
}
