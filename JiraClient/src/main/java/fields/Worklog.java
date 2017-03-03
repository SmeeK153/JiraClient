package fields;

import org.json.JSONObject;

public class Worklog extends Field {
	
	public Worklog(JSONObject worklog){
		super(worklog);
	}
	
	public User getWorklogAuthor(){
		return new User(this.json.getJSONObject("author"));
	}
	
	public User getWorklogPreviousEditor(){
		return new User(this.json.getJSONObject("updateAuthor"));
	}
	
	public boolean writtenBy(User user){
		return this.getWorklogAuthor().equals(user);
	}
	
	public boolean writtenBy(String user){
		return this.getWorklogAuthor().equals(user);
	}
	
	public boolean editedBy(User user){
		return this.getWorklogPreviousEditor().equals(user);
	}
	
	public boolean editedBy(String user){
		return this.getWorklogPreviousEditor().equals(user);
	}
	
	public boolean writtenOrEditedBy(User user){
		return (this.writtenBy(user) || this.editedBy(user));
	}
	
	public boolean writtenOrEditedBy(String user){
		return (this.writtenBy(user) || this.editedBy(user));
	}
}
