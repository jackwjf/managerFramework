/**
 *COPYRIGHT NOTICE
 *Copyright (c) 2012～2020, 19e   
 *All rights reserved.
 * @author wjf
 * @file IModel.java
 * @brief TODO
 * @version 
 * @date 2016-8-29 下午5:07:19
 * 
 */
package com.e.framework;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import com.google.gson.JsonObject;

/**
 * @author wjf
 *
 */
public interface IModule {
	public void init(String filePathModel);
	
	public void excute(JsonObject context,HttpServletRequest req ,ApplicationContext ctx);
}
