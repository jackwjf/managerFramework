/**
 *COPYRIGHT NOTICE
 *Copyright (c) 2012～2020, 19e   
 *All rights reserved.
 * @author wjf
 * @file BaseTest.java
 * @brief TODO
 * @version 
 * @date 2016-8-27 下午3:04:54
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
public class BaseModule implements IModule{

	/* (non-Javadoc)
	 * @see com.e.framework.IModel#init()
	 */
	@Override
	public void init(String filePathModel) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.e.framework.IModel#start()
	 */
	@Override
	public void excute(JsonObject context,HttpServletRequest req ,ApplicationContext ctx) {
		// TODO Auto-generated method stub
		
	}
}
