package com.cloud9.onboard.camunda.response;

import com.cloud9.onboard.pojo.Link;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties
public class StartProcessWithFormResponse
{
    private String id;

    private String businessKey;

    private Link[] links;

    private String ended;

    private String suspended;

    private String definitionId;
    
    private String caseInstanceId;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getBusinessKey ()
    {
        return businessKey;
    }

    public void setBusinessKey (String businessKey)
    {
        this.businessKey = businessKey;
    }

    public Link[] getLinks ()
    {
        return links;
    }

    public void setLinks (Link[] links)
    {
        this.links = links;
    }

    public String getEnded ()
    {
        return ended;
    }

    public void setEnded (String ended)
    {
        this.ended = ended;
    }

    public String getSuspended ()
    {
        return suspended;
    }

    public void setSuspended (String suspended)
    {
        this.suspended = suspended;
    }

    public String getDefinitionId ()
    {
        return definitionId;
    }

    public void setDefinitionId (String definitionId)
    {
        this.definitionId = definitionId;
    }

    @Override
    public String toString()
    {
        return "StartProcessWithFormResponse [id = "+id+", businessKey = "+businessKey+", links = "+links+", ended = "+ended+", suspended = "+suspended+", definitionId = "+definitionId+"]";
    }

	public String getCaseInstanceId() {
		return caseInstanceId;
	}

	public void setCaseInstanceId(String caseInstanceId) {
		this.caseInstanceId = caseInstanceId;
	}
}