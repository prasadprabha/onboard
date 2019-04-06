package com.cloud9.onboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.cloud9.onboard.camunda.request.StartProcessWithFormRequest;
import com.cloud9.onboard.camunda.response.ProcessDefinitionResponse;
import com.cloud9.onboard.camunda.response.StartProcessWithFormResponse;
import com.cloud9.onboard.camunda.response.TaskResponse;
import com.cloud9.onboard.camunda.service.ProcessService;
import com.cloud9.onboard.camunda.service.TaskService;
import com.cloud9.onboard.config.Config;
import com.cloud9.onboard.pojo.FieldValue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class, loader = AnnotationConfigContextLoader.class)
public class ProcessServiceTest {
	
	@Autowired
	private ProcessService processService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private Environment env;


	@Test
	public void testStartProcessWithForm() {
		StartProcessWithFormRequest startProcessWithFormRequest = new StartProcessWithFormRequest();
		startProcessWithFormRequest.setBusinessKey("prasad.suseela@infosys.com");
		Map<String, HashMap<String, Object>> variableMap  = startProcessWithFormRequest.getVariables();
		
		HashMap<String,Object> firstName = new HashMap<String,Object>();
		firstName.put("value", "Prasad");
		firstName.put("type", "String");
		variableMap.put("firstName", firstName);
		
		HashMap<String,Object> lastName = new HashMap<String,Object>();
		lastName.put("value", "Prasad");
		lastName.put("type", "String");
		variableMap.put("lastName", lastName);
		
		HashMap<String,Object> sowrole = new HashMap<String,Object>();
		sowrole.put("value", "ProgramLead");
		sowrole.put("type", "String");
		variableMap.put("amount", sowrole);
		
		HashMap<String,Object> middleName = new HashMap<String,Object>();
		middleName.put("value", "ProgramLead");
		middleName.put("type", "String");
		variableMap.put("middleName", middleName);
		
		HashMap<String,Object> sow = new HashMap<String,Object>();
		sow.put("value", "ProgramLead");
		sow.put("type", "String");
		variableMap.put("sow", sow);
		
		HashMap<String,Object> doj = new HashMap<String,Object>();
		sow.put("value", "1212121");
		sow.put("type", "String");
		variableMap.put("doj", doj);
		
		HashMap<String,Object> email = new HashMap<String,Object>();
		sow.put("value", "prasad.suseela@infosys.com");
		sow.put("type", "String");
		variableMap.put("email", email);
		
		
		HashMap<String,Object> offshore = new HashMap<String,Object>();
		offshore.put("value", "true");
		offshore.put("type", "Boolean");
		variableMap.put("offshore", offshore);
		
		String processDefinitionId = "Onboardprocess:1:df039aae-57a0-11e9-9373-7a0cb89bf9ec";
		StartProcessWithFormResponse response = processService.startProcessWithForm(startProcessWithFormRequest, processDefinitionId);
		System.out.println(response);
	}
	

	@Test
	public void testStartFormProcessVariables () {
		Map<String,FieldValue> variablesMap = processService.getStartFormVariables("Onboardprocess:1:df039aae-57a0-11e9-9373-7a0cb89bf9ec");
		System.out.println(variablesMap);
	}
	
	@Test
	public void testStartGetProcessDefintions () {
		List<ProcessDefinitionResponse>  processDefintions = processService.getProcessDefinitions();
		System.out.println(processDefintions);
	}
	
	
	@Test
	public void testGetTasksForGroup() {
		List<TaskResponse> taskList = taskService.getTaskList("inductionteam");
		System.out.println(taskList);
	}

}
