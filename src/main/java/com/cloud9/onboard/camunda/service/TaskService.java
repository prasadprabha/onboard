package com.cloud9.onboard.camunda.service;

import java.util.List;
import java.util.Map;

import com.cloud9.onboard.camunda.request.TaskFormRequest;
import com.cloud9.onboard.camunda.request.TaskSetAssigneeRequest;
import com.cloud9.onboard.camunda.response.TaskResponse;
import com.cloud9.onboard.pojo.FieldValue;

public interface TaskService {
	public List<TaskResponse> getTaskList(String groupId);

	void claimtask(TaskSetAssigneeRequest claimRequest, String id);
	
	public String submitTaskWithForm(TaskFormRequest request,String taskId);

	Map<String, FieldValue> getTaskFormVariables(String taskId);
}
