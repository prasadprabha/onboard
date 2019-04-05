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
import com.cloud9.onboard.camunda.service.ProcessService;
import com.cloud9.onboard.config.Config;
import com.cloud9.onboard.pojo.FieldValue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class, loader = AnnotationConfigContextLoader.class)
public class ProcessServiceTest {
	
	@Autowired
	private ProcessService processService;
	
	@Autowired
	private Environment env;


	@Test
	public void testStartProcessWithForm() {
		StartProcessWithFormRequest startProcessWithFormRequest = new StartProcessWithFormRequest();
		startProcessWithFormRequest.setBusinessKey("prasad.suseela@infosys.com");
		Map<String, HashMap<String, String>> variableMap  = startProcessWithFormRequest.getVariables();
		
		HashMap<String,String> firstName = new HashMap<String,String>();
		firstName.put("value", "Prasad");
		firstName.put("type", "String");
		variableMap.put("firstName", firstName);
		
		HashMap<String,String> lastName = new HashMap<String,String>();
		lastName.put("value", "Prasad");
		lastName.put("type", "String");
		variableMap.put("lastName", lastName);
		
		HashMap<String,String> sowrole = new HashMap<String,String>();
		sowrole.put("value", "ProgramLead");
		sowrole.put("type", "String");
		variableMap.put("amount", sowrole);
		
		HashMap<String,String> middleName = new HashMap<String,String>();
		middleName.put("value", "ProgramLead");
		middleName.put("type", "String");
		variableMap.put("middleName", middleName);
		
		HashMap<String,String> sow = new HashMap<String,String>();
		sow.put("value", "ProgramLead");
		sow.put("type", "String");
		variableMap.put("sow", sow);
		
		HashMap<String,String> doj = new HashMap<String,String>();
		sow.put("value", "1212121");
		sow.put("type", "String");
		variableMap.put("doj", doj);
		
		HashMap<String,String> email = new HashMap<String,String>();
		sow.put("value", "prasad.suseela@infosys.com");
		sow.put("type", "String");
		variableMap.put("email", email);
		
		
		HashMap<String,String> offshore = new HashMap<String,String>();
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

}
