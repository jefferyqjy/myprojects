package com.sys.plugin.jschartrpt;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.sys.common.IBaseDAO;
import com.sys.common.util.CommonUtil;

public class ReportUtil {
	
	public static final String TYPE_BAR = "bar";
	public static final String TYPE_LINE = "line";
	public static final String TYPE_PIE = "pie";
	public static final String NEW_LINE = "\r\n";
	public static String xName = "";
	public static String yName = "";
	public static String title = "";
	
	public static String genReport(String hql, IBaseDAO dao, String type,String divId) {
		List<Object[]> lsobjs = getRecord(hql, dao);
		Map hmPara = initPara(lsobjs);
		if(TYPE_BAR.equalsIgnoreCase(type)) {
			return generateBarLineReport(hmPara,divId,TYPE_BAR);
		}else if(TYPE_LINE.equalsIgnoreCase(type)){
			return generateBarLineReport(hmPara,divId,TYPE_LINE);
		}else if(TYPE_PIE.equalsIgnoreCase(type)) {
			return generateBarLineReport(hmPara,divId,TYPE_PIE);
		}
		return "";
	}

	public static String generateBarLineReport(Map hmPara, String divId, String type) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id=\"").append(divId).append("\">").append(NEW_LINE);
		sb.append(" Wait for the data ...").append(NEW_LINE);
		sb.append("<script type=\"text/javascript\">").append(NEW_LINE);
		sb.append("var myData = new Array(");
		Set set = hmPara.entrySet();
		Iterator it = set.iterator();
		int temp = 0;
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			sb.append("[\"").append(entry.getKey()).append("\",").append(entry.getValue()).append("]").append(",");
			temp++;
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(");").append(NEW_LINE);
		sb.append("var myChart = new JSChart('").append(divId).append("', '").append(type).append("');").append(NEW_LINE);
		sb.append("myChart.setDataArray(myData);").append(NEW_LINE);
		sb.append("myChart.setTitle(\"").append(title).append("\");").append(NEW_LINE);
		sb.append("myChart.setAxisNameX(\"").append(xName).append("\");").append(NEW_LINE);
		sb.append("myChart.setAxisNameY(\"").append(yName).append("\");").append(NEW_LINE);
		sb.append("myChart.draw();").append(NEW_LINE);
		sb.append("</script>").append(NEW_LINE);
		sb.append("</div>").append(NEW_LINE);
		return sb.toString();
	}

	private static Map initPara(List<Object[]> lsobjs) {
		Map hmPara = new TreeMap();
		if(!CommonUtil.isListEmpty(lsobjs)) {
			String x = null;
			String y = null;
			for(Object[] objs : lsobjs) {
				x = String.valueOf(objs[0]);
				y = String.valueOf(objs[1]);
				hmPara.put(x, y);
			}
		}
		return hmPara;
	}
	
	private static List<Object[]> getRecord(String hql,IBaseDAO dao) {
		return dao.getViaHql(hql);
	}
	
	public static void main(String[] args) {
		TreeMap map = new TreeMap();
		map.put("2012", "1000");
		map.put("2013", "1000");
		map.put("2014", "2000");
		map.put("2015", "3000");
		map.put("2016", "5000");
		map.put("2017", "2000");
		map.put("2018", "8000");
		ReportUtil.title = "test";
		System.out.println(generateBarLineReport(map,"test1","pie"));
	}
	

}
