package com.cloud9.onboard.camunda.request;

public class TaskSetAssigneeRequest {
	 private String userId;

	    public String getUserId ()
	    {
	        return userId;
	    }

	    public void setUserId (String userId)
	    {
	        this.userId = userId;
	    }

	    @Override
	    public String toString()
	    {
	        return "TaskSetAssigneeRequest [userId = "+userId+"]";
	    }


}