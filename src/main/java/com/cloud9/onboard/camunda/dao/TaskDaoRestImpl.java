package com.cloud9.onboard.camunda.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.cloud9.onboard.camunda.constants.RestURLConstants;
import com.cloud9.onboard.camunda.request.TaskFormRequest;
import com.cloud9.onboard.camunda.request.TaskSetAssigneeRequest;
import com.cloud9.onboard.camunda.response.TaskResponse;
import com.cloud9.onboard.pojo.FieldValue;



public class TaskDaoRestImpl implements TaskDao {

	@Autowired
	private RestTemplate restTemplate;
	
	
	@Override
	public List<TaskResponse> getTaskList(String groupId) {
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(RestURLConstants.REST_API_URI_TASK_LIST).queryParam("candidateGroups", groupId).build();
		 TaskResponse[] taskArray = restTemplate.getForObject(uriComponents.toUri(), TaskResponse[].class);
		 return Arrays.asList(taskArray);
	}
	
	@Override
	public void claimtask(TaskSetAssigneeRequest claimRequest, String id) {
		UriComponents uriComponents=UriComponentsBuilder.fromHttpUrl(RestURLConstants.REST_API_COMMON_ENDPOINT).path(RestURLConstants.REST_API_URI_TASK).path("/").path(id).path(RestURLConstants.REST_API_URI_CLAIM).build();
		restTemplate.postForLocation(uriComponents.toUri(), claimRequest);
				
	}

	@Override
	public String submitTaskWithForm(TaskFormRequest request,String taskId) {
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(RestURLConstants.REST_API_URI_TASK_LIST).path("/").path(taskId).path(RestURLConstants.REST_API_URI_TASK_SUBMIT_FORM).build();
		String status = restTemplate.postForObject(uriComponents.toUri(),request,String.class);
		return status;
	}
	
	@Override
	public Map<String,FieldValue> getTaskFormVariables(String taskId) {
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(RestURLConstants.REST_API_URI_TASK).path("/").path(taskId).path(RestURLConstants.REST_API_TASK_FORM_VARIABLES).build();
		ParameterizedTypeReference<HashMap<String, FieldValue>> responseType = 
	               new ParameterizedTypeReference<HashMap<String, FieldValue>>() {};
	               
	               RequestEntity<Void> request = RequestEntity.get(uriComponents.toUri())
	                       .accept(MediaType.APPLICATION_JSON).build();
	               ResponseEntity<HashMap<String,FieldValue>> jsonDictionary = restTemplate.exchange(request, responseType);
	               Map<String,FieldValue> map = jsonDictionary.getBody();
	               return map;
	}
}
