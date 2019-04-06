package com.cloud9.onboard.camunda.constants;

public class RestURLConstants {
	
	public final static String REST_API_COMMON_ENDPOINT="http://localhost:8080/engine-rest";
	public final static String REST_API_URI_START_PROCESS_WITH_FORM = "/submit-form";
	public final static String REST_API_URI_START_FORM_VARIABLES = "/form-variables";
	public final static String REST_API_URI_PROCESS_DEFINITIONS = REST_API_COMMON_ENDPOINT+"/process-definition";
	public final static String REST_API_URI_TASK_LIST = REST_API_COMMON_ENDPOINT+"/task";
	public final static String REST_API_URI_TASK = "/task";
	public final static String REST_API_URI_CLAIM = "/claim";
	public final static String REST_API_URI_TASK_SUBMIT_FORM= "/submit-form";
	public final static String REST_API_TASK_FORM_VARIABLES = "/form-variables";

}
