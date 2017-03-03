package issues;

import org.json.JSONObject;

public abstract class SubIssue extends Issue {

	public SubIssue(JSONObject issue) {
		super(issue);
		// TODO Auto-generated constructor stub
	}
	
	public abstract Issue getParentIssue();
}
