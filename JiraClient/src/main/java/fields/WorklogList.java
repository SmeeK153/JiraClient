package fields;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class WorklogList extends ArrayList<Worklog> {
	
	public WorklogList(Worklog worklog){
		this.add(worklog);
	}
	
	public WorklogList(JSONArray worklogs){
		for(int i = 0; i < worklogs.length(); i++){
			this.add(new Worklog(worklogs.getJSONObject(i)));
		}
	}
	
	public WorklogList(JSONObject worklog){
		this.add(new Worklog(worklog));
	}
	
	public static ArrayList<Worklog> getCombinedList(Worklog... worklogs){
		ArrayList<Worklog> worklogList = new ArrayList<Worklog>();
		for(int i = 0; i < worklogs.length; i++){
			worklogList.add(worklogs[i]);
		}
		return worklogList;
	}
	
	public static ArrayList<Worklog> getCombinedList(WorklogList... worklogLists){
		ArrayList<Worklog> worklogList = new ArrayList<Worklog>();
		for(int i = 0; i < worklogLists.length; i++){
			worklogLists[i].forEach(e -> {
				worklogList.add(e);
			});
		}
		return worklogList;
	} 
	
	public ArrayList<Worklog> getUserWorklogs(User user){
		ArrayList<Worklog> worklogs = new ArrayList<Worklog>();
		this.forEach(e -> {
			if(e.writtenBy(user)){
				worklogs.add(e);
			}
		});
		return worklogs;
	}
	
	public ArrayList<Worklog> getEditedUserWorklogs(User user){
		ArrayList<Worklog> worklogs = new ArrayList<Worklog>();
		this.getUserWorklogs(user).forEach(e -> {
			if(e.editedBy(user)){
				worklogs.add(e);
			}
		});
		return worklogs;
	}
	
	public ArrayList<Worklog> getEditedUserWorklogs(User user, User editor){
		ArrayList<Worklog> worklogs = new ArrayList<Worklog>();
		this.getUserWorklogs(user).forEach(e -> {
			if(e.editedBy(editor)){
				worklogs.add(e);
			}
		});
		return worklogs;
	}

}
