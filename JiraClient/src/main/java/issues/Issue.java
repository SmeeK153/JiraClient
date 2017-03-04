package issues;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import fields.User;
import fields.Worklog;

public abstract class Issue {
	
	JSONObject issue;
	JSONObject fields;
	
	public Issue(JSONObject issue){
		this.issue = issue;
		this.fields = issue.getJSONObject("fields");
	}
	
	private JSONObject getField(String key){
		return this.fields.getJSONObject(key);
	}
	
	private String getFieldString(String key){
		return this.fields.getString(key);
	}
	
	private ArrayList<String> getFieldArray(String key){
		ArrayList<String> values = new ArrayList<String>();
		JSONArray list = this.issue.getJSONArray(key);
		for(int i = 0; i < list.length(); i++){
			values.add(list.getJSONObject(i).getString("name"));
		}
		return values;
	}
	
	private void editField(){
		
	}
	
	private void deleteField(){
		
	}
	
	private void emptyField(){
		
	}
	
	public String getID(){
		return this.issue.getString("key");
	}
	
	public String getProject(){
		return this.getID().split("-")[0];
	}
	
	public String getProjectID(){
		return this.getID().split("-")[1];
	}
	
	public int getSystemID(){
		return this.issue.getInt("id");
	}
	
	public String getStatus(){
		return this.getFieldString("status");
	}
	
	public ArrayList<String> getComponents(){
		return this.getFieldArray("components");
	}
	
	public User getAssignee(){
		return new User(this.fields.getJSONObject("assignee"));
	}
	
	public User getReporter(){
		return new User(this.fields.getJSONObject("reporter"));
	}
	
	public DateTime getLastView(){
		return new DateTime(this.fields.getString("lastViewed"));
	}
	
	public User getCreator(){
		return new User(this.fields.getJSONObject("creator"));
	}
	
	public int getVotes(){
		return this.fields.getJSONObject("votes").getInt("votes");
	}
	
	public boolean currentUserHasVoted(){
		return this.fields.getJSONObject("votes").getBoolean("hasVoted");
	}
	
	public ArrayList<Worklog> getWorklogs(){
		ArrayList<Worklog> list = new ArrayList<Worklog>();
		JSONArray worklogs = this.fields.getJSONObject("worklog").getJSONArray("worklogs");
		for(int i = 0; i < worklogs.length(); i++){
			list.add(new Worklog(worklogs.getJSONObject(i)));
		}
		return list;
	}
}
