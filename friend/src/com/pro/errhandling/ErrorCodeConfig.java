package com.pro.errhandling;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class ErrorCodeConfig extends PropertyPlaceholderConfigurer {
	private Properties props;

	/***
	 * Loads the Properties Detail
	 * 
	 * @param props
	 */
	protected void loadProperties(Properties props) throws IOException {
		super.loadProperties(props);
		this.props = props;
	}

	/***
	 * 
	 * @return java.util.Properties
	 */
	public Properties getProps() {
		return props;
	}
}
