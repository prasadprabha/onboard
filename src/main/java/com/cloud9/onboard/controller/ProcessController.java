package com.cloud9.onboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud9.onboard.camunda.request.StartProcessWithFormRequest;
import com.cloud9.onboard.camunda.response.ProcessDefinitionResponse;
import com.cloud9.onboard.camunda.response.StartProcessWithFormResponse;
import com.cloud9.onboard.camunda.service.ProcessService;
import com.cloud9.onboard.pojo.FieldValue;
import com.cloud9.onboard.pojo.FormField;
import com.cloud9.onboard.pojo.FormValue;

@RestController
public class ProcessController {
	
	@Autowired
	ProcessService processService;
	
	@RequestMapping(value = "/form-fields", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public FormField[] getStartFormVariables(@RequestParam("processDefinitionId") String processDefinitionId) {
		Map<String,FieldValue> fieldMap = processService.getStartFormVariables(processDefinitionId);
		FormField[] formFieldArry = new FormField[fieldMap.size()];
		int index = 0;
		for (Entry<String,FieldValue> fieldValue : fieldMap.entrySet() ) {
			FormField formField = new FormField();
			formField.setControlName(fieldValue.getKey());
			formField.setControlType(fieldValue.getValue().getType());
			formFieldArry[index++] = formField;
		}
		
		return formFieldArry;
	}
	
	@RequestMapping(value = "/get-process-definitions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public FormField[] getProcessDefinitons() {
		int index = 0;
		List<ProcessDefinitionResponse>  processDefintions = processService.getProcessDefinitions();
		FormField[] formFieldArry = new FormField[processDefintions.size()];
		for (ProcessDefinitionResponse response : processDefintions) {
			FormField formField = new FormField();
			formField.setControlName(response.getName());
			formField.setControlType(response.getId());
			formFieldArry[index++] = formField;
			
		}
		
		return formFieldArry;
	}
	
	@RequestMapping(value = "/start-process-form", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE) 
	public StartProcessWithFormResponse startProcess(@RequestBody Map<String,String> variables) {
		StartProcessWithFormRequest startProcessWithFormRequest = new StartProcessWithFormRequest();
		Map<String, HashMap<String, Object>> variableMap  = startProcessWithFormRequest.getVariables();
		String processDefinitionId = "";
		
		
		  for (Map.Entry<String,String> entry : variables.entrySet()) {
			  if(entry.getKey().equals("processDefinitionId")) {
				  processDefinitionId = entry.getValue();
			  } else {
			  HashMap<String,Object> firstName = new HashMap<String,Object>();
			  
			  firstName.put("value", entry.getValue().toString()); 
			  
			  if(entry.getKey().equals("offshore")) {
				  firstName.put("type", "Boolean");
			  } 
			   else {
				  firstName.put("type", "String");
			  }
			 
			  if(entry.getKey().equals("email")) {
				  startProcessWithFormRequest.setBusinessKey(entry.getValue()); 
			  }
			  
			  
			  variableMap.put(entry.getKey().toString(), firstName); 
			  }
		  
		  }
		 
		StartProcessWithFormResponse response = processService.startProcessWithForm(startProcessWithFormRequest, processDefinitionId);
		return response;
	}

}
