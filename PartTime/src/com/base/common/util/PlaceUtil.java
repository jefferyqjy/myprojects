package com.base.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.manager.xml.util.XMLUtil;

public class PlaceUtil {
	private static boolean isInit = false;
	
	private static List<String> lsProvince = new ArrayList<String>();
	private static Map<String, List<String>> hmCity = new HashMap<String, List<String>>();
	
	
	public static List<String> getProvinces() {
		if(!isInit) {
			try {
				Document doc = loadProvincesConfig();
				Element eRoot = doc.getDocumentElement();
				NodeList nl = XMLUtil.getChildListByName(eRoot, "Province");
				if(nl != null) {
					int len = nl.getLength();
					Node node = null;
					for(int i=0;i<len;i++) {
						node = nl.item(i);
						if(node.getNodeType() != Element.ELEMENT_NODE) {
							continue;
						}
						lsProvince.add(XMLUtil.getNodeValue(node, true));
					}
				}
				initCities();
				isInit = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lsProvince;
	}
	
	private static void initCities() {
		try {
			Document doc = loadCitiesConfig();
			Element eRoot = doc.getDocumentElement();
			NodeList nl = XMLUtil.getChildListByName(eRoot, "City");
			if(nl != null) {
				int len = nl.getLength();
				Node node = null;
				String pid = null;
				for(int i=0;i<len;i++) {
					node = nl.item(i);
					if(node.getNodeType() != Element.ELEMENT_NODE) {
						continue;
					}
					pid = XMLUtil.getNodeAttribute(node, "PID");
					List<String> lsCity = hmCity.get(pid);
					if(lsCity == null) {
						lsCity = new ArrayList();
						lsCity.add(XMLUtil.getNodeValue(node, true));
						hmCity.put(pid, lsCity);
					} else {
						lsCity.add(XMLUtil.getNodeValue(node, true));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String genProvincesSelectString(boolean needEmpty, String selectName, String event) {
		StringBuilder sb = new StringBuilder(1024);
		sb.append("<select name=\"").append(selectName).append("\" ");
		sb.append("id=\"").append(selectName).append("\"");
		if(event != null && !"".equals(event)) {
			sb.append(" ").append(event);
		}
		sb.append(">").append("\r\n");
		if(needEmpty) {
			sb.append("<option value=\"\"").append("></option>").append("\r\n");
		}
		List<String> list = PlaceUtil.getProvinces();
		for(String option : list) {
			sb.append("<option value=\"").append(option).append("\">").append(option).append("</option>").append("\r\n");
		}
		sb.append("</select>");
		return sb.toString();
	}
	
	public static List<String> getCities(String province) {
		getProvinces();
		return hmCity.get(province);
	}
	
	public static String genCitiesSelectString(String province,boolean needEmpty, String selectName, String event) {
		List<String> list = PlaceUtil.getCities(province);
		StringBuilder sb = new StringBuilder(1024);
		sb.append("<select name=\"").append(selectName).append("\" ");
		sb.append("id=\"").append(selectName).append("\"");
		if(event != null && !"".equals(event)) {
			sb.append(" ").append(event);
		}
		sb.append(">").append("\r\n");
		if(needEmpty) {
			sb.append("<option value=\"\"").append("></option>").append("\r\n");
		}
		for(String option : list) {
			sb.append("<option value=\"").append(option).append("\">").append(option).append("</option>").append("\r\n");
		}
		sb.append("</select>");
		return sb.toString();
	}
	
	public static String genCitiesSelectString(String province) {
		List<String> list = PlaceUtil.getCities(province);
		StringBuilder sb = new StringBuilder(1024);
		for(String option : list) {
			sb.append("<option value=\"").append(option).append("\">").append(option).append("</option>").append("\r\n");
		}
		return sb.toString();
	}
	
	private static Document loadProvincesConfig() throws Exception {
		String configPath = PlaceUtil.class.getResource("").getPath();
		return XMLUtil.parseXMLFile(configPath + "/Provinces.xml");
	}
	
	private static Document loadCitiesConfig() throws Exception {
		String configPath = PlaceUtil.class.getResource("").getPath();
		return XMLUtil.parseXMLFile(configPath + "/Cities.xml");
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(genCitiesSelectString("江苏省",true,"city",null));
	}

}
