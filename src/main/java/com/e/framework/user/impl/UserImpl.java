/**
 *COPYRIGHT NOTICE
 *Copyright (c) 2012～2020, 19e   
 *All rights reserved.
 * @author wjf
 * @file UserImpl.java
 * @brief TODO
 * @version 
 * @date 2016-8-29 下午7:13:18
 * 
 */
package com.e.framework.user.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.e.framework.BaseModule;
import com.e.framework.user.operation.NameOperation;
import com.google.gson.JsonObject;

/**
 * @author wjf
 *
 */
public class UserImpl extends BaseModule{
	private Logger logger = Logger.getLogger(UserImpl.class);
	private int i = 0;
	@Override
	public void excute(JsonObject obj,HttpServletRequest req,ApplicationContext ctx) {
		// TODO Auto-generated method stub
		super.excute(obj,req,ctx);
		i++;
		logger.info("wjf i="+i);
		String method = obj.get("method").getAsString();
		if("addname".equals(method)){
			NameOperation operation = (NameOperation)ctx.getBean("nameOperation");;
			operation.addName(obj);
		}
	}
	
}
