///**
// *COPYRIGHT NOTICE
// *Copyright (c) 2012～2020, 19e   
// *All rights reserved.
// * @author wjf
// * @file SqlClientManager.java
// * @brief TODO
// * @version 
// * @date 2016-8-29 下午4:52:46
// * 
// */
//package com.e.framework.manager.sqlclient;
//
//import java.io.File;
//import java.io.Reader;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.io.Resources;
//import org.apache.log4j.Logger;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.orm.ibatis.SqlMapClientTemplate;
//
//import com.e.framework.model.ModelParser;
//
///**
// * @author wjf
// * 
// */
//public class SqlClientManager {
//	private static SqlClientManager instance = null;
//	private final Logger logger = Logger.getLogger(SqlClientManager.class);
//	private Map<String, SqlMapClientTemplate> readSqlMapClientTemplate,
//			writeSqlMapClientTemplate;
//	private ApplicationContext applicationContext = null;
//
//	public void setApplicationContext(ApplicationContext applicationContext)
//			throws BeansException {
//		this.applicationContext = applicationContext;
//	}
//
//	public static SqlClientManager getInstance() {
//		synchronized (SqlClientManager.class) {
//			if (instance == null) {
//				instance = new SqlClientManager();
//			}
//		}
//		return instance;
//	}
//
//	private SqlClientManager() {
//		readSqlMapClientTemplate = new HashMap<String, SqlMapClientTemplate>();
//		writeSqlMapClientTemplate = new HashMap<String, SqlMapClientTemplate>();
//	}
//
//	/**
//	 * init 数据源
//	 * 
//	 * @param modules
//	 */
//	public void initSqlMapClientTemplate(Map<String, ModelParser> modules,
//			String rootPath) {
//		try {
//			logger.info("SqlClientManager init datasource");
//			DataSource writeDataSource = (javax.sql.DataSource) applicationContext.getBean("writeDataSource");
//			DataSource readDataSource = (javax.sql.DataSource) applicationContext.getBean("readDataSource");
//			Set<String> keys = modules.keySet();
//			for (String key : keys) {
//				String nameSpace = key.replaceAll("\\.", "\\/");
//				File files = new File(rootPath + nameSpace+"/");
//				String[] files1 = files.list();
//				for (String f : files1) {
//					File xFile = new File(rootPath + nameSpace + f);
//					if (xFile.isDirectory())
//						continue;
//					if (f.endsWith(".xml")) {
//						logger.info("SqlClientManager init datasource :="+nameSpace + " start");
//						Reader reader = Resources.getResourceAsReader(nameSpace + "/" + f);
//						SqlMapClient sqlMapClient = SqlMapClientBuilder
//								.buildSqlMapClient(reader);
//						SqlMapClientTemplate readTemplate = new SqlMapClientTemplate();
//						readTemplate.setSqlMapClient(sqlMapClient);
//						readTemplate.setDataSource(readDataSource);
//						readSqlMapClientTemplate.put(key, readTemplate);
//
//						SqlMapClientTemplate writeTemplate = new SqlMapClientTemplate();
//						writeTemplate.setSqlMapClient(sqlMapClient);
//						writeTemplate.setDataSource(writeDataSource);
//						writeSqlMapClientTemplate.put(key, writeTemplate);
//						logger.info("SqlClientManager init datasource :="+nameSpace + " end");
//					}
//				}
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	/**
//	 * 
//	
//	  * getReadSqlMap(这里用一句话描述这个方法的作用)
//	  *
//	  * @Title: getReadSqlMap
//	  * @Description: TODO
//	  * @param String 各个模块的命名空间
//	  * @return Map<String,SqlMapClientTemplate>    返回类型
//	  * @throws
//	@
//	 */
//	public SqlMapClientTemplate getReadSqlMap(String namespace) {
//		return readSqlMapClientTemplate.get(namespace);
//	}
//	public SqlMapClientTemplate getWriteSqlMap(String namespace) {
//		return writeSqlMapClientTemplate.get(namespace);
//	}
//}
