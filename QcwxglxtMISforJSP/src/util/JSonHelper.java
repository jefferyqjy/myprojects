package util;

import org.json.JSONObject;

public class JSonHelper {

	public static String toJSonString(Object bean) {
		JSONObject jsonObj = new JSONObject(bean);//静态方法,通过一个bean对象创建一个JSONObject对象
		return jsonObj.toString();//把JSONObject对象转换为json格式的字符串
	}

}
