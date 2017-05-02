package com.base.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class PhonePlaceUtil {

	public static void main(String[] args) throws Exception {
		getPhoneLocation("13276616086");
	}

	private static Map<String, String> getPhoneLocation(String phone) throws MalformedURLException, IOException {
		URL url = new URL("http://www.sososjw.com/json?Tel="+phone);
		URLConnection URLconnection = url.openConnection();
		HttpURLConnection httpConnection = (HttpURLConnection) URLconnection;
		int responseCode = httpConnection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			InputStream urlStream = httpConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlStream));
			String sCurrentLine = "";
			String sTotalString = "";
			while ((sCurrentLine = bufferedReader.readLine()) != null) {
				sTotalString += sCurrentLine;
			}
			if(CommonUtil.isNotEmpty(sTotalString)) {
			}
		} else {
			// do nothing
		}
		return new HashMap<String, String>();
	}

}
