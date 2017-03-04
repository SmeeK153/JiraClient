package fields;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

public class FieldList<K> {
	
	protected ArrayList<Field> fieldList = new ArrayList<Field>();
	
	public FieldList(Field field){
		this.fieldList.add(field);
	}
	
	public FieldList(Field... fields){
		for(int i = 0; i < fields.length; i++){
			this.fieldList.add(fields[i]);
		}
	}
	
	public FieldList(JSONArray fields){
		for(int i = 0; i < fields.length(); i++){
			this.fieldList.add(new Field(fields.getJSONObject(i)));
		}
	}
	
	public FieldList(JSONObject field){
		this.fieldList.add(new Field(field));
	}
	
	protected ArrayList<K> reduceList(Predicate<K>... predicates){
		Stream stream = Arrays.stream(this.fieldList.toArray());
		
		for(int i = 0; i < predicates.length; i++){
			stream = stream.filter(predicates[i]);
		}

		ArrayList<K> reducedList = new ArrayList(Arrays.asList(stream));
		return reducedList;
	}
	
	public static ArrayList<Field> getCombinedList(FieldList... fieldLists){
		
		ArrayList<Field> globalList = new ArrayList<Field>();
		
		for(int i = 0; i < fieldLists.length; i++){
			FieldList current = fieldLists[i];
			ArrayList<Field> fieldList = current.fieldList;
			fieldList.forEach(e -> {
				globalList.add(e);
			});
		}
		return globalList;
	}
	
	public static ArrayList<Field> getCombinedList(Field... fields){
		
		ArrayList<Field> globalList = new ArrayList<Field>();
		
		for(int i = 0; i < fields.length; i++){
			Field f = fields[i];
			globalList.add(f);
		}
		
		return globalList;
	}

}
