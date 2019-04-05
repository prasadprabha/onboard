package com.cloud9.onboard.camunda.service;

import java.util.List;
import java.util.Map;

import com.cloud9.onboard.camunda.request.StartProcessWithFormRequest;
import com.cloud9.onboard.camunda.response.ProcessDefinitionResponse;
import com.cloud9.onboard.camunda.response.StartProcessWithFormResponse;
import com.cloud9.onboard.pojo.FieldValue;

public interface ProcessService {
	
	public StartProcessWithFormResponse startProcessWithForm(StartProcessWithFormRequest request,String processDefinitionId);
	public Map<String,FieldValue> getStartFormVariables(String processDefinitionId);
	List<ProcessDefinitionResponse> getProcessDefinitions();

}
