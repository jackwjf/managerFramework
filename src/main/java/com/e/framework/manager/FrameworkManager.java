/**
 *COPYRIGHT NOTICE
 *Copyright (c) 2012～2020, 19e   
 *All rights reserved.
 * @author wjf
 * @file FrameworkManager.java
 * @brief TODO
 * @version 
 * @date 2016-8-29 下午5:21:35
 * 
 */
package com.e.framework.manager;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.e.framework.BaseModule;
import com.e.framework.model.ModelParser;
import com.google.gson.JsonObject;

/**
 * @author wjf
 * 
 */
public class FrameworkManager {
	private final Logger logger = Logger.getLogger(FrameworkManager.class);
	private static FrameworkManager instance = null;
	private ClassLoader loader;
	private Map<String, ModelParser> modules;

	public static FrameworkManager getInstance() {
		synchronized (FrameworkManager.class) {
			if (instance == null) {
				instance = new FrameworkManager();
			}
			return instance;
		}
	}

	private FrameworkManager() {
		loader = Thread.currentThread().getContextClassLoader();
		modules = new LinkedHashMap<String, ModelParser>();
	}

	public void initalization(ServletContextEvent servletContextEvent) {
		logger.info("FrameworkManager initalization models start");
		String filePathModel = servletContextEvent.getServletContext().getRealPath(
				"/")
				+ "WEB-INF/model/";
		String classPath = servletContextEvent.getServletContext().getRealPath("/") + "WEB-INF/classes/";
		File modelFiles = new File(filePathModel);
		//初始化sqlclient
		for (String fileString : modelFiles.list()) {
			logger.info("loading model-xml = " + fileString);
			ModelParser modelParser = new ModelParser();
			modelParser.toXML(new File(filePathModel+fileString));
			loadModule(modelParser.getNamespace(), modelParser);
			logger.info("loaded model-xml = " + fileString + " to model parser");
		}
//		SqlClientManager sqlClientManager = SqlClientManager.getInstance();
//      ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()); 
//		sqlClientManager.setApplicationContext(context);
//		sqlClientManager.initSqlMapClientTemplate(modules,classPath);
		
		initModule(filePathModel);
	}

	private void loadModule(String nameSpace, ModelParser modelParser) {
		try {
//			Class modClass = loader.loadClass(module);
//			BaseModule mod = (BaseModule) modClass.newInstance();
			this.modules.put(nameSpace, modelParser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initModule(String filePathModel) {
		for (ModelParser modelParser : modules.values()) {
			try {
				Class modClass = loader.loadClass(modelParser.getManager());
				BaseModule mod = (BaseModule) modClass.newInstance();
				mod.init(filePathModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void startModule(String namespace,JsonObject content,HttpServletRequest req,ApplicationContext contxt ){
		try{
			Class modClass = loader.loadClass(modules.get(namespace).getIpml());
			BaseModule mod = (BaseModule) modClass.newInstance();
			mod.excute(content,req,contxt);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
