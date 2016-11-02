/**
 *COPYRIGHT NOTICE
 *Copyright (c) 2012～2020, 19e   
 *All rights reserved.
 * @author wjf
 * @file WriteServiceImpl.java
 * @brief TODO
 * @version 
 * @date 2016-8-29 下午7:40:24
 * 
 */
package com.e.framework.service.impl;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.e.framework.service.WriteService;

/**
 * @author wjf
 *
 */
@Transactional("frameWriteManager")
public class WriteServiceImpl implements WriteService{

	/* (non-Javadoc)
	 * @see com.e.framework.service.WriteService#setDataSource()
	 */
	@Override
	public void setDataSource(SqlMapClientTemplate sqlMapClientTemplate) {
		// TODO Auto-generated method stub
		
	}

}
