package com.cloud9.onboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud9.onboard.camunda.response.ProcessDefinitionResponse;
import com.cloud9.onboard.camunda.service.ProcessService;
import com.cloud9.onboard.pojo.FieldValue;
import com.cloud9.onboard.pojo.FormField;

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
	public Map<String,String> getProcessDefinitons() {
		Map<String,String> processMap = new HashMap<String,String>();
		List<ProcessDefinitionResponse>  processDefintions = processService.getProcessDefinitions();
		for (ProcessDefinitionResponse response : processDefintions) {
			processMap.put(response.getName(), response.getId());
		}
		
		return processMap;
	}

}
