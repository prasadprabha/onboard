package com.cloud9.onboard.camunda.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud9.onboard.camunda.dao.ProcessDao;
import com.cloud9.onboard.camunda.request.StartProcessWithFormRequest;
import com.cloud9.onboard.camunda.response.ProcessDefinitionResponse;
import com.cloud9.onboard.camunda.response.StartProcessWithFormResponse;
import com.cloud9.onboard.pojo.FieldValue;


@Service
public class ProcessServiceImpl implements ProcessService {
	
	Logger logger = LoggerFactory.getLogger(ProcessServiceImpl.class);

	
	@Autowired
	private ProcessDao processDao;
	

	
	@Override
	public StartProcessWithFormResponse startProcessWithForm(StartProcessWithFormRequest request,String processDefinitionId) {
		return processDao.startProcessWithForm(request, processDefinitionId);
	}
	
	
	@Override
	public Map<String,FieldValue> getStartFormVariables(String processDefinitionId) {
		return processDao.getStartFormVariables(processDefinitionId);
	}
	
	@Override
	public List<ProcessDefinitionResponse> getProcessDefinitions() {
		return processDao.getProcessDefinitions();
	}
}
