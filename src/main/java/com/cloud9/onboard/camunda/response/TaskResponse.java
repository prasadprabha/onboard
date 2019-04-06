package com.cloud9.onboard.camunda.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskResponse {
    private String tenantId;

    private String caseDefinitionId;

    private String formKey;

    private String caseInstanceId;

    private String executionId;

    private String assignee;

    private String id;

    private String caseExecutionId;

    private String processInstanceId;

    private String created;

    private String description;

    private String priority;

    private String taskDefinitionKey;

    private String name;

    private String owner;

    private String due;

    private String parentTaskId;

    private String processDefinitionId;

    private String delegationState;

    public String getTenantId ()
    {
        return tenantId;
    }

    public void setTenantId (String tenantId)
    {
        this.tenantId = tenantId;
    }

    public String getCaseDefinitionId ()
    {
        return caseDefinitionId;
    }

    public void setCaseDefinitionId (String caseDefinitionId)
    {
        this.caseDefinitionId = caseDefinitionId;
    }

    public String getFormKey ()
    {
        return formKey;
    }

    public void setFormKey (String formKey)
    {
        this.formKey = formKey;
    }

    public String getCaseInstanceId ()
    {
        return caseInstanceId;
    }

    public void setCaseInstanceId (String caseInstanceId)
    {
        this.caseInstanceId = caseInstanceId;
    }

    public String getExecutionId ()
    {
        return executionId;
    }

    public void setExecutionId (String executionId)
    {
        this.executionId = executionId;
    }

    public String getAssignee ()
    {
        return assignee;
    }

    public void setAssignee (String assignee)
    {
        this.assignee = assignee;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getCaseExecutionId ()
    {
        return caseExecutionId;
    }

    public void setCaseExecutionId (String caseExecutionId)
    {
        this.caseExecutionId = caseExecutionId;
    }

    public String getProcessInstanceId ()
    {
        return processInstanceId;
    }

    public void setProcessInstanceId (String processInstanceId)
    {
        this.processInstanceId = processInstanceId;
    }

    public String getCreated ()
    {
        return created;
    }

    public void setCreated (String created)
    {
        this.created = created;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getPriority ()
    {
        return priority;
    }

    public void setPriority (String priority)
    {
        this.priority = priority;
    }

    public String getTaskDefinitionKey ()
    {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey (String taskDefinitionKey)
    {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getOwner ()
    {
        return owner;
    }

    public void setOwner (String owner)
    {
        this.owner = owner;
    }

    public String getDue ()
    {
        return due;
    }

    public void setDue (String due)
    {
        this.due = due;
    }

    public String getParentTaskId ()
    {
        return parentTaskId;
    }

    public void setParentTaskId (String parentTaskId)
    {
        this.parentTaskId = parentTaskId;
    }

    public String getProcessDefinitionId ()
    {
        return processDefinitionId;
    }

    public void setProcessDefinitionId (String processDefinitionId)
    {
        this.processDefinitionId = processDefinitionId;
    }

    public String getDelegationState ()
    {
        return delegationState;
    }

    public void setDelegationState (String delegationState)
    {
        this.delegationState = delegationState;
    }

    @Override
    public String toString()
    {
        return "Task Details : [tenantId = "+tenantId+", caseDefinitionId = "+caseDefinitionId+", formKey = "+formKey+", caseInstanceId = "+caseInstanceId+", executionId = "+executionId+", assignee = "+assignee+", id = "+id+", caseExecutionId = "+caseExecutionId+", processInstanceId = "+processInstanceId+", created = "+created+", description = "+description+", priority = "+priority+", taskDefinitionKey = "+taskDefinitionKey+", name = "+name+", owner = "+owner+", due = "+due+", parentTaskId = "+parentTaskId+", processDefinitionId = "+processDefinitionId+", delegationState = "+delegationState+"]";
    }
    }
