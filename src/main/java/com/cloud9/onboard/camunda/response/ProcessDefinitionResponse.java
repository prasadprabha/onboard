package com.cloud9.onboard.camunda.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessDefinitionResponse {
	
	private String id;

    private String category;

    private String description;

    private String name;

    private String resource;

    private String deploymentId;

    private String diagram;

    private String suspended;

    private String key;

    private String version;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getResource ()
    {
        return resource;
    }

    public void setResource (String resource)
    {
        this.resource = resource;
    }

    public String getDeploymentId ()
    {
        return deploymentId;
    }

    public void setDeploymentId (String deploymentId)
    {
        this.deploymentId = deploymentId;
    }

    public String getDiagram ()
    {
        return diagram;
    }

    public void setDiagram (String diagram)
    {
        this.diagram = diagram;
    }

    public String getSuspended ()
    {
        return suspended;
    }

    public void setSuspended (String suspended)
    {
        this.suspended = suspended;
    }

    public String getKey ()
    {
        return key;
    }

    public void setKey (String key)
    {
        this.key = key;
    }

    public String getVersion ()
    {
        return version;
    }

    public void setVersion (String version)
    {
        this.version = version;
    }

    @Override
    public String toString()
    {
        return "ProcessDefinition [id = "+id+", category = "+category+", description = "+description+", name = "+name+", resource = "+resource+", deploymentId = "+deploymentId+", diagram = "+diagram+", suspended = "+suspended+", key = "+key+", version = "+version+"]";
    }


}
