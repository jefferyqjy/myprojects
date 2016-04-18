package com.base.web.nav;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.manager.xml.util.XMLUtil;

public class NavTree {
	private static Map<String, String> hmFunction = new HashMap<String, String>();

	public static Map<String, String> getHmFunction() {
		return hmFunction;
	}

	public static String genNav(String parentType, String type) {
		hmFunction = new HashMap<String, String>();
		try {
			Document doc = loadConfig();
			Node root = doc.getDocumentElement();
			NodeList mgrList = XMLUtil.getChildListByName(root, "manager");
			return genTree(parentType, mgrList, type);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Document loadConfig() throws Exception {
		String configPath = NavTree.class.getResource("").getPath();
		return XMLUtil.parseXMLFile(configPath + "/tree.xml");
	}

	private static String genTree(String pType, NodeList mgrList, String type) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mgrList.getLength(); i++) {
			Node nd = (Node) mgrList.item(i);
			String parentType = XMLUtil.getNodeAttribute(nd, "type");
			String needShow = XMLUtil.getNodeAttribute(nd, "show");
			if (parentType.equals(pType) && !"false".equals(needShow)) {
				sb.append("<li>");
				String name = XMLUtil.getNodeAttribute(nd, "name");
				sb.append("<a href=\"#\" class=\"nav-top-item\">").append(name).append("</a>");
				genChildTree(nd, sb, type);
				sb.append("</li>");
			}
		}
		return sb.toString();
	}

	private static void genChildTree(Node parent, StringBuffer sb, String type) {
		NodeList list = XMLUtil.getChildListByName(parent, "link");
		if (list != null && list.getLength() > 0) {
			sb.append("<ul>");
			for (int i = 0; i < list.getLength(); i++) {

				Node nd = (Node) list.item(i);
				String href = XMLUtil.getNodeAttribute(nd, "href");
				String name = XMLUtil.getNodeAttribute(nd, "name");
				String tp = XMLUtil.getNodeAttribute(nd, "type");
				if (type.equals(tp)) {
					sb.append("<li><a name=\"subLink\" href='").append(href).append("' target='right'>").append(name)
							.append("</a></li>");
					hmFunction.put(name, href);
				}
			}
			sb.append("</ul>");
		}
	}
}
