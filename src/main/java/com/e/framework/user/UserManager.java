/**
 *COPYRIGHT NOTICE
 *Copyright (c) 2012～2020, 19e   
 *All rights reserved.
 * @author wjf
 * @file UserManager.java
 * @brief TODO
 * @version 
 * @date 2016-10-21 下午4:21:14
 * 
 */
package com.e.framework.user;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.e.framework.model.ModelParser;

/**
 * @author wjf
 *
 */
public class UserManager {
	private static UserManager instance = null;
	public static UserManager getInstance(){
		synchronized (UserManager.class) {
			if(instance == null)
				instance = new UserManager();
			return instance;
		}
	}
	
	private SqlMapClientTemplate writeTemplate;
	private SqlMapClientTemplate readTemplate;
	private String nameSpace;
	private ModelParser modelParser;
	public SqlMapClientTemplate getWriteTemplate() {
		return writeTemplate;
	}
	public void setWriteTemplate(SqlMapClientTemplate writeTemplate) {
		this.writeTemplate = writeTemplate;
	}
	public SqlMapClientTemplate getReadTemplate() {
		return readTemplate;
	}
	public void setReadTemplate(SqlMapClientTemplate readTemplate) {
		this.readTemplate = readTemplate;
	}
	public String getNameSpace() {
		return nameSpace;
	}
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
	public ModelParser getModelParser() {
		return modelParser;
	}
	public void setModelParser(ModelParser modelParser) {
		this.modelParser = modelParser;
	}
	
	public void step(ModelParser modelParser){
		this.modelParser = modelParser;
//		setReadTemplate(SqlClientManager.getInstance().getReadSqlMap(modelParser.getNamespace()));
//		setWriteTemplate(SqlClientManager.getInstance().getWriteSqlMap(modelParser.getNamespace()));
	}
}
