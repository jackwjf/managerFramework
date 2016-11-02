package com.e.framework.utils;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class Configurer extends PropertyPlaceholderConfigurer{

	private static Properties properties = new Properties();
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		properties.putAll(props);
	}

	public static String getValue(String key){
		return properties.getProperty(key);
	}
	
	
}
