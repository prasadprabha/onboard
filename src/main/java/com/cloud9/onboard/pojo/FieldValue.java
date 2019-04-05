package com.cloud9.onboard.pojo;

import com.cloud9.onboard.security.providers.ValueInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FieldValue {
	
	public FieldValue() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@JsonProperty("type")
	private String type;
	@JsonProperty("value")
	private Object value;
	@JsonProperty("valueInfo")
	@JsonIgnore
	private ValueInfo valueInfo;
	
	public FieldValue(String type, Object value) {
		super();
		this.type = type;
		this.value = value;
	//	this.valueInfo = valueInfo;
	}
}
