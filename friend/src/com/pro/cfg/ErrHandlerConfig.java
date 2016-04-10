package com.pro.cfg;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.pro.utils.CommonUtils;

public class ErrHandlerConfig extends PropertyPlaceholderConfigurer {
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

	public String getErrorMsg(String errCode) {
		return this.props.getProperty(errCode);
	}

	public String getErrorMsg(String errCode, String parameter) {
		return getErrorMsg(errCode, new String[] { parameter });
	}

	public String getErrorMsg(String errCode, String[] parameters) {
		return CommonUtils.getDetailMsg(getErrorMsg(errCode), parameters);
	}

}
