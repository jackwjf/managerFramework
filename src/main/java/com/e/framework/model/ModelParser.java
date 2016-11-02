/**
 *COPYRIGHT NOTICE
 *Copyright (c) 2012～2020, 19e   
 *All rights reserved.
 * @author wjf
 * @file ModelBean.java
 * @brief TODO
 * @version 
 * @date 2016-8-29 下午7:05:25
 * 
 */
package com.e.framework.model;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author wjf
 *
 */
public class ModelParser {
	private String ipml;
	private String namespace;
	private String manager;
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getIpml() {
		return ipml;
	}
	public void setIpml(String ipml) {
		this.ipml = ipml;
	}
	/**
	 * 解析xml成modelbean
	 * @param xml
	 */
	public void toXML(File xmlFile){
		SAXReader reader = new SAXReader();  
		try {
			Document doc = reader.read(xmlFile);
			Element root = doc.getRootElement();
			Attribute nameS =  root.attribute("namespace");
			this.namespace = nameS.getText();
			Iterator<Element> iterator = root.elementIterator();  
	        while(iterator.hasNext()){  
	            Element e = iterator.next();  
	            if(e.getName().equals("ipml")){
	            	this.ipml = e.getText();
	            }else if(e.getName().equals("manager")){
	            	this.manager = e.getText();
	            }
	        }  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
