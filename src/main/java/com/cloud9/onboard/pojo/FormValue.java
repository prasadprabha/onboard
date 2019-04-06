package com.cloud9.onboard.pojo;

import java.io.Serializable;

public class FormValue implements Serializable{
    private static final long serialVersionUID = 1L;
	
	
	public FormValue() {
		super();
	}
	String fieldName;
	String fieldValue;
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	
}
