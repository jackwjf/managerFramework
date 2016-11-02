/**
 *COPYRIGHT NOTICE
 *Copyright (c) 2012～2020, 19e   
 *All rights reserved.
 * @author wjf
 * @file FrameworkInitListener.java
 * @brief TODO
 * @version 
 * @date 2016-8-29 下午5:33:18
 * 
 */
package com.e.framework.manager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author wjf
 *
 */
public class FrameworkInitListener implements ServletContextListener{

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		FrameworkManager frameworkManager = FrameworkManager.getInstance();
		frameworkManager.initalization(arg0);
	}

}
