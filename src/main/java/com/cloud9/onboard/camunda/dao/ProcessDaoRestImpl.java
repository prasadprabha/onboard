package com.cloud9.onboard.camunda.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.cloud9.onboard.camunda.constants.RestURLConstants;
import com.cloud9.onboard.camunda.request.StartProcessWithFormRequest;
import com.cloud9.onboard.camunda.response.ProcessDefinitionResponse;
import com.cloud9.onboard.camunda.response.StartProcessWithFormResponse;
import com.cloud9.onboard.pojo.FieldValue;



public class ProcessDaoRestImpl implements ProcessDao {
	
	Logger logger = LoggerFactory.getLogger(ProcessDaoRestImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	
	@Override
	public List<ProcessDefinitionResponse> getProcessDefinitions() {
		ProcessDefinitionResponse[] definitionResponses = restTemplate
				.getForObject(RestURLConstants.REST_API_URI_PROCESS_DEFINITIONS, ProcessDefinitionResponse[].class);
		for (ProcessDefinitionResponse definitionResponse : definitionResponses) {
			logger.debug("After calling getProcessDefinitions REST:" + definitionResponse.toString());
		}
		return Arrays.asList(definitionResponses);

	}
	  
	/*
	 * @Override public List<ProcessInstanceResponse> getProcessInstances(String
	 * processDefinitionId) { UriComponents uriComponents =
	 * UriComponentsBuilder.fromHttpUrl(RestURLConstants.
	 * REST_API_URI_GET_PROCESS_INSTANCE).path("/").queryParam(
	 * "processDefinitionId", processDefinitionId).build();
	 * ProcessInstanceResponse[]
	 * instanceResponses=restTemplate.getForObject(uriComponents.toUri(),
	 * ProcessInstanceResponse[].class); for(ProcessInstanceResponse
	 * instanceResponse: instanceResponses) {
	 * logger.debug("After calling getProcessDefinitions REST:"+
	 * instanceResponse.toString()); } return Arrays.asList(instanceResponses);
	 * 
	 * }
	 * 
	 * @Override public StartProcessResponse startProcess( StartProcessRequest
	 * startProcessRequest,String id) { UriComponents uriComponents =
	 * UriComponentsBuilder.fromHttpUrl(RestURLConstants.
	 * REST_API_URI_PROCESS_DEFINITIONS).path("/").path(id).path(RestURLConstants.
	 * REST_API_URI_START_PROCESS).build();
	 * logger.debug("Before calling startProcess REST: URI-"+uriComponents.
	 * toUriString()+"Payload-"+startProcessRequest.toString());
	 * StartProcessResponse
	 * startProcessResponse=restTemplate.postForObject(uriComponents.toUri(),
	 * startProcessRequest,StartProcessResponse.class);
	 * logger.debug("After calling startProcess REST:"+startProcessResponse.toString
	 * ()); System.out.println("PROCESS HAS STARTED"); return startProcessResponse;
	 * }
	 */
	 
	
	@Override
	public StartProcessWithFormResponse startProcessWithForm(StartProcessWithFormRequest request,String processDefinitionId) {
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(RestURLConstants.REST_API_URI_PROCESS_DEFINITIONS).path("/").path(processDefinitionId).path(RestURLConstants.REST_API_URI_START_PROCESS_WITH_FORM).build();
		logger.debug("Before calling startProcess with form REST: URI-"+uriComponents.toUriString()+"Payload-"+request.toString());
		StartProcessWithFormResponse startProcessWithResponse = restTemplate.postForObject(uriComponents.toUri(),request,StartProcessWithFormResponse.class);
		logger.debug("After calling startProcess REST:"+startProcessWithResponse.toString());
		return startProcessWithResponse;
	}
	
	
	@Override
	public Map<String,FieldValue> getStartFormVariables(String processDefinitionId) {
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(RestURLConstants.REST_API_URI_PROCESS_DEFINITIONS).path("/").path(processDefinitionId).path(RestURLConstants.REST_API_URI_START_FORM_VARIABLES).build();
		logger.debug("Before calling startProcess with form REST: URI-"+uriComponents.toUriString()+"Payload-");
	//	StartProcessWithFormResponse startProcessWithResponse = restTemplate.getForObject(uriComponents.toUri(),Map.cl);
	//	logger.debug("After calling startProcess REST:"+startProcessWithResponse.toString());
		ParameterizedTypeReference<HashMap<String, FieldValue>> responseType = 
	               new ParameterizedTypeReference<HashMap<String, FieldValue>>() {};
	               
	               RequestEntity<Void> request = RequestEntity.get(uriComponents.toUri())
	                       .accept(MediaType.APPLICATION_JSON).build();
	               ResponseEntity<HashMap<String,FieldValue>> jsonDictionary = restTemplate.exchange(request, responseType);
	               Map<String,FieldValue> map = jsonDictionary.getBody();
	               return map;
	}

}
