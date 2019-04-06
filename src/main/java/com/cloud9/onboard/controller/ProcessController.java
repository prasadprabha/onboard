package com.cloud9.onboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud9.onboard.camunda.request.StartProcessWithFormRequest;
import com.cloud9.onboard.camunda.request.TaskFormRequest;
import com.cloud9.onboard.camunda.request.TaskSetAssigneeRequest;
import com.cloud9.onboard.camunda.response.ProcessDefinitionResponse;
import com.cloud9.onboard.camunda.response.StartProcessWithFormResponse;
import com.cloud9.onboard.camunda.response.TaskResponse;
import com.cloud9.onboard.camunda.service.ProcessService;
import com.cloud9.onboard.camunda.service.TaskService;
import com.cloud9.onboard.pojo.FieldValue;
import com.cloud9.onboard.pojo.FormField;

@RestController
public class ProcessController {
	
	@Autowired
	ProcessService processService;
	
	@Autowired
	TaskService taskService;
	
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
	
	@RequestMapping(value = "/get-task-for-group", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TaskResponse> getTasksForGroup(@RequestParam("groupId") String groupId) {
		List<TaskResponse>  taskList = taskService.getTaskList(groupId);
		return taskList;
	}
	
	@RequestMapping(value = "/claim-task", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void claimTask(@RequestParam("taskId") String taskId,@RequestParam("userId") String userId) {
		TaskSetAssigneeRequest claimRequest = new TaskSetAssigneeRequest();
		claimRequest.setUserId(userId);
		taskService.claimtask(claimRequest, taskId);
	}
	
	@RequestMapping(value = "/submit-task-form", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE) 
	public String submitTaskWithForm(@RequestBody Map<String,String> variables,@RequestParam("taskId") String taskId) {
		TaskFormRequest taskFormRequest = new TaskFormRequest();
		return taskService.submitTaskWithForm(taskFormRequest, taskId);
	}
	
	@RequestMapping(value = "/get-taskform-variables", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public FormField[] getTaskFormVariables(@RequestParam("taskId") String taskId) {
		Map<String,FieldValue> fieldMap = taskService.getTaskFormVariables(taskId);
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
	

}
