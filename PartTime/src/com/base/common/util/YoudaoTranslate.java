package com.base.common.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.PropertyUtils;

public class YoudaoTranslate {

    public static void main(String[] args) {
        //new ReadByPost().start();
    	testyoudao("我的国家");
    }

    public static String testyoudao( String key){
    	return jsontoobj((tran(key)));
    }
    
    private static String jsontoobj(String json){
    	   // String json = "{name=\"json\",bool:true,int:1,double:2.2,func:function(a){ return a; },array:[1,2]}";  
    	    JSONObject jsonObject = JSONObject.fromObject( json ); 
    	    System.out.println(jsonObject);
    	    Object bean = JSONObject.toBean( jsonObject ); 
    	    /*assertEquals( jsonObject.get( "name" ), PropertyUtils.getProperty( bean, "name" ) );  
    	    assertEquals( jsonObject.get( "bool" ), PropertyUtils.getProperty( bean, "bool" ) );  
    	    assertEquals( jsonObject.get( "int" ), PropertyUtils.getProperty( bean, "int" ) );  
    	    assertEquals( jsonObject.get( "double" ), PropertyUtils.getProperty( bean, "double" ) );  
    	    assertEquals( jsonObject.get( "func" ), PropertyUtils.getProperty( bean, "func" ) );  */
    	    String ss = "";
    	    try {
    	    	 ss = (String) ((List)PropertyUtils.getProperty(bean, "translation")).get(0);
    			System.out.println(ss);
    		} catch (IllegalAccessException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (InvocationTargetException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (NoSuchMethodException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	   
    	    
    	   /* List arrayList = (List)JSONArray.toCollection(jsonObject.getJSONArray("array"));
    	    for(Object object : arrayList){
    	        System.out.println(object);
    	    }*/
    	    
    	    return ss;
    	}
    
    private static  String tran(String key ){
    	
    	  StringBuilder builder = new StringBuilder();
        try {


            URL url = new URL("http://fanyi.youdao.com/openapi.do");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.addRequestProperty("encoding", "UTF-8");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.setRequestMethod("POST");

            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);


            bw.write("keyfrom=fadabvaa&key=522071532&type=data&doctype=json&version=1.1&q="+key);
            bw.flush();

            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String line;
             builder = new StringBuilder();
            while((line = br.readLine()) != null){
                builder.append(line);
            }

            bw.close();
            osw.close();
            os.close();

            br.close();
            isr.close();
            is.close();

            System.out.println(builder.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    	return builder.toString();
    }
    
}

/*class ReadByPost extends Thread{
    @Override
    public void run() {}
}*/