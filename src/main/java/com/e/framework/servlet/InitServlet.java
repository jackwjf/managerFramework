/**
 *COPYRIGHT NOTICE
 *Copyright (c) 2012～2020, 19e   
 *All rights reserved.
 * @author wjf
 * @file InitServlet.java
 * @brief TODO
 * @version 
 * @date 2016-8-29 下午7:50:51
 * 
 */
package com.e.framework.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.e.framework.manager.FrameworkManager;
import com.e.framework.model.BodyModel;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author wjf
 * 
 */
public class InitServlet extends HttpServlet {
	private Logger logger =  Logger.getLogger(InitServlet.class);
	private ApplicationContext context;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			/**
			 * 
			 * 
			 * { "namespace":"com.e.framework.user",
			 * 	 "params":"{
			 * 			"method":"addname",
			 * 		    "name":"wjf"
			 * 	 }"
			 * }
			 * 
			 * 
			 * 
			 * 
			 * 
			 */
			InputStream inputStream = req.getInputStream();
			String content = convertStreamToString(inputStream);
			logger.info("+++++++++++++++from custom content="+content);
			JsonParser jsonParser = new JsonParser();
			JsonElement el = jsonParser.parse(content);
			JsonObject obj = el.getAsJsonObject();
			BodyModel body =  new BodyModel();
			body.setNamespace(obj.get("namespace").getAsString());
			body.setParams(obj.get("params").getAsJsonObject());
			FrameworkManager frameworkManager = FrameworkManager.getInstance();
			frameworkManager.startModule(body.getNamespace(), body.getParams(),req,getAppContext(req));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	private ApplicationContext getAppContext(HttpServletRequest request) {
		if (context == null) {
			context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		}
		return context;
	}
}
