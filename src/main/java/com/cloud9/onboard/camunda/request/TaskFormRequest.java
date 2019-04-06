package com.cloud9.onboard.camunda.request;

import java.util.HashMap;
import java.util.Map;

public class TaskFormRequest {
	Map<String, HashMap<String, String>> variables = new HashMap<String,HashMap<String,String>>();
	
	
	public Map<String, HashMap<String, String>> getVariables() {
		return variables;
	}
	public void setVariables(Map<String, HashMap<String, String>> variables) {
		this.variables = variables;
	}
	
}