package fields;
//package jira.fields;
//
//import java.util.ArrayList;
//import java.util.stream.Stream;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//public class FieldList<K> {
//	
//	ArrayList<Field> fieldList = new ArrayList<Field>();
//	
//	public FieldList(Field field){
//		this.fieldList.add(field);
//	}
//	
//	public FieldList(JSONArray fields){
//		for(int i = 0; i < fields.length(); i++){
//			this.fieldList.add((Field) new FieldList(fields.getJSONObject(i)));
//		}
//	}
//	
//	public FieldList(JSONObject field){
//		new Field(field);
//		this.fieldList.add(new Field(field));
//	}
//	
//	public static ArrayList<Field> getCombinedList(FieldList... fieldLists){
//		ArrayList<Field> worklogList = new ArrayList<Field>();
//		for(int i = 0; i < worklogs.length; i++){
//			worklogList.add(worklogs[i]);
//		}
//		return worklogList;
//	}
//	
//	public ArrayList<Field> getCombinedList(Field... fields){
//		ArrayList<?> fieldList = new ArrayList();
//		for(int i = 0; i < fields.length; i++){
//			fieldList.add(fields[i]);
//		}
//		return fieldList;
//	}
//	
//	public static ArrayList<Worklog> getCombinedList(WorklogList... worklogLists){
//		ArrayList<Worklog> worklogList = new ArrayList<Worklog>();
//		for(int i = 0; i < worklogLists.length; i++){
//			worklogLists[i].forEach(e -> {
//				worklogList.add(e);
//			});
//		}
//		return worklogList;
//	} 
//	
//	public ArrayList<Worklog> getUserWorklogs(User user){
//		ArrayList<Worklog> worklogs = new ArrayList<Worklog>();
//		this.forEach(e -> {
//			if(e.writtenBy(user)){
//				worklogs.add(e);
//			}
//		});
//		return worklogs;
//	}
//	
//	public ArrayList<Worklog> getEditedUserWorklogs(User user){
//		ArrayList<Worklog> worklogs = new ArrayList<Worklog>();
//		this.getUserWorklogs(user).forEach(e -> {
//			if(e.editedBy(user)){
//				worklogs.add(e);
//			}
//		});
//		return worklogs;
//	}
//	
//	public ArrayList<Worklog> getEditedUserWorklogs(User user, User editor){
//		ArrayList<Worklog> worklogs = new ArrayList<Worklog>();
//		this.getUserWorklogs(user).forEach(e -> {
//			if(e.editedBy(editor)){
//				worklogs.add(e);
//			}
//		});
//		return worklogs;
//	}
//
//}
