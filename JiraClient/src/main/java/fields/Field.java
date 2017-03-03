package fields;

import java.util.HashMap;

import org.json.JSONObject;


//Operations: set (most common), add, remove

public class Field {
	
	protected JSONObject json;
	protected String key;
	protected boolean required;
	protected String name;
	
	protected Field(JSONObject json){
		this.json = json;
	}
}
