/**
 *COPYRIGHT NOTICE
 *Copyright (c) 2012～2020, 19e   
 *All rights reserved.
 * @author wjf
 * @file UserManager.java
 * @brief TODO
 * @version 
 * @date 2016-10-21 下午3:58:01
 * 
 */
package com.e.framework.user.impl;

import java.io.File;

import com.e.framework.BaseModule;
import com.e.framework.model.ModelParser;
import com.e.framework.user.UserManager;

/**
 * @author wjf
 *
 */
public class UserStepSource extends BaseModule{
	@Override
	public void init(String filePathModel) {
		// TODO Auto-generated method stub
		super.init(filePathModel);
		ModelParser modelParser = new ModelParser();
		modelParser.toXML(new File(filePathModel+"user-config.xml"));
		UserManager userManager = UserManager.getInstance();
		userManager.step(modelParser);
		
	}
	
	
}
