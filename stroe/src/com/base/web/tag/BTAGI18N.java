package com.base.web.tag;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.base.common.util.CommonUtil;


public class BTAGI18N extends TagSupport {
	public static String LANGUAGE = "zh_CN";
	
	public static Map<String, String> hmMap = new HashMap<String, String>();
	
	public static String getI18NValue(String key, String module, String defaultValue) {
		String keyName = module + "_" + key;
		String value = hmMap.get(keyName);
		if(CommonUtil.isEmpty(value)) {
			return defaultValue;
		} else {
			return value;
		}
	}
	
	public static String getI18NValue(String key, String module) {
		String keyName = module + "_" + key;
		String value = hmMap.get(keyName);
		try {
			if (CommonUtil.isEmpty(value)) {
				String configPath = BTAGI18N.class.getResource("").getPath();
				int index = configPath.indexOf("com/base/web/tag");
				configPath = configPath.substring(0, index) + module + "/i18n_" + LANGUAGE + ".properties";
				InputStream in = new FileInputStream(configPath);
				Properties props = new Properties();
				props.load(in);
				in.close();
				Set set = props.keySet();
				Iterator it = set.iterator();
				String newKey = null;
				String curKey = null;
				while (it.hasNext()) {
					curKey = (String) it.next();
					newKey = module + "_" + curKey;
					hmMap.put(newKey, props.getProperty(curKey, "[ValueNotFound]::" + newKey));
				}
			} else {
				return value;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hmMap.get(keyName);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String module;
	private String key;

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			String keyName = this.module + "_" + this.key;
			String outValue = this.hmMap.get(keyName);
			if(CommonUtil.isEmpty(outValue)) {
				initConfig();
			}
			outValue = this.hmMap.get(keyName);
			JspWriter out = pageContext.getOut();
			out.print(outValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	private void initConfig() throws FileNotFoundException, IOException {
		String configPath = BTAGI18N.class.getResource("").getPath();
		int index = configPath.indexOf("com/base/web/tag");
		configPath = configPath.substring(0, index) + this.module + "/i18n_" + LANGUAGE + ".properties";
		InputStream in = new FileInputStream(configPath);
		Properties props = new Properties();
		props.load(in);
		in.close();
		Set set = props.keySet();
		Iterator it = set.iterator();
		String newKey = null;
		String curKey = null;
		while(it.hasNext()) {
			curKey = (String)it.next();
			newKey = this.module + "_" + curKey;
			this.hmMap.put(newKey, props.getProperty(curKey, "[ValueNotFound]::" + this.key));
		}
	}

}
