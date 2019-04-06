package com.cloud9.onboard.camunda.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cloud9.onboard.camunda.dao.TaskDao;
import com.cloud9.onboard.camunda.request.TaskFormRequest;
import com.cloud9.onboard.camunda.request.TaskSetAssigneeRequest;
import com.cloud9.onboard.camunda.response.TaskResponse;
import com.cloud9.onboard.pojo.FieldValue;

public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskDao taskDao;

	@Override
	public List<TaskResponse> getTaskList(String groupId) {
		return taskDao.getTaskList(groupId);
	}
	

	@Override
	public void claimtask(TaskSetAssigneeRequest claimRequest, String id) {
		taskDao.claimtask(claimRequest, id);
		
	}
	
	@Override
	public String submitTaskWithForm(TaskFormRequest request,String taskId) {
		return taskDao.submitTaskWithForm(request, taskId);
		
	}
	
	@Override
	public Map<String,FieldValue> getTaskFormVariables(String taskId) {
		return taskDao.getTaskFormVariables(taskId);
	}
}
