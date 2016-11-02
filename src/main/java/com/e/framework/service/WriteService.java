/**
 *COPYRIGHT NOTICE
 *Copyright (c) 2012～2020, 19e   
 *All rights reserved.
 * @author wjf
 * @file WriteService.java
 * @brief TODO
 * @version 
 * @date 2016-8-29 下午7:38:37
 * 
 */
package com.e.framework.service;

import org.springframework.orm.ibatis.SqlMapClientTemplate;


/**
 * @author wjf
 *
 */
public interface WriteService {
	public void setDataSource(SqlMapClientTemplate sqlMapClientTemplate);
}
