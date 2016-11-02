/**
 *COPYRIGHT NOTICE
 *Copyright (c) 2012～2020, 19e   
 *All rights reserved.
 * @author wjf
 * @file NameOperationImpl.java
 * @brief TODO
 * @version 
 * @date 2016-10-20 下午1:45:22
 * 
 */
package com.e.framework.user.operation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e.framework.user.mapper.NameMapper;
import com.e.framework.user.model.NameModel;
import com.google.gson.JsonObject;

/**
 * @author wjf
 *
 */
@Service("nameOperation")
public class NameOperationImpl implements NameOperation{
	@Autowired
	NameMapper nameMapper;
	@Transactional
	@Override
	public void addName(JsonObject obj){
		NameModel nameModel = new NameModel();
		nameModel.setId("1");
		nameModel.setName(obj.get("name").getAsString());
		nameMapper.saveName(nameModel);
//		System.out.println(result.getName());
	}
}
