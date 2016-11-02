/**
 *COPYRIGHT NOTICE
 *Copyright (c) 2012～2020, 19e   
 *All rights reserved.
 * @author wjf
 * @file BodyModel.java
 * @brief TODO
 * @version 
 * @date 2016-10-20 下午4:05:20
 * 
 */
package com.e.framework.model;

import java.io.Serializable;

import com.google.gson.JsonObject;

/**
 * @author wjf
 *
 */
public class BodyModel implements Serializable{
	public String namespace;
	public JsonObject params;
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public JsonObject getParams() {
		return params;
	}
	public void setParams(JsonObject params) {
		this.params = params;
	}
	
}
