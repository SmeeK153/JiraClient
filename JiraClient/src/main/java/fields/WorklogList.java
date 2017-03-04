package fields;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

public class WorklogList extends FieldList<Worklog> {
	
	public WorklogList(Worklog worklog){
		super(worklog);
	}
	
	public WorklogList(Worklog... worklogs){
		super(worklogs);
	}
	
	public WorklogList(JSONArray worklogs){
		super(worklogs);
	}
	
	public WorklogList(JSONObject worklog){
		super(worklog);
	}

	public ArrayList<Worklog> getUserWorklogs(User user){
		return this.reduceList(e -> e.writtenBy(user));
	}
	
	public ArrayList<Worklog> getEditedUserWorklogs(User user){
		return this.reduceList(e -> e.editedBy(user));
	}
	
	public ArrayList<Worklog> getEditedUserWorklogs(User user, User editor){
		return this.reduceList(e -> e.writtenBy(user), e -> e.editedBy(user));
	}

}