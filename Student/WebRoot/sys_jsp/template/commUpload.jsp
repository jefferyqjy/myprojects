<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:useBean id="fileBean" scope="page" class="net.moq.www.MoqUploadBean" />
<%   
  fileBean.setObjectPath(request.getSession().getServletContext().getRealPath(""));   
  fileBean.setSize(10000*1024);   
  fileBean.setSuffix(".gif.jpg.png.jpge.html.htm");   
  fileBean.setSourceFile(request);   
  String [] saSourceFile = fileBean.getSourceFile();   
  String [] saObjectFile = fileBean.getObjectFileName();   
  String [] saDescription = fileBean.getDescription();   
  int iCount = fileBean.getCount();   
  String sObjectPath = fileBean.getObjectPath();   
  for(int i=0;i<iCount;i++) {   
  out.println("〈br>源始文件:");   
  out.println(saSourceFile[i]);   
  out.println("〈br>目标文件:");   
  out.println(sObjectPath+saObjectFile[i]);   
  out.println("〈br>上传说明:");   
  out.println(saDescription[i]);   
  out.println("〈br>");   
  }   
  out.println("〈br>作者:" + fileBean.getFieldValue("Author"));   
  out.println("〈br>公司:" + fileBean.getFieldValue("Company"));   
  out.println("〈br>说明:" + fileBean.getFieldValue("Comment"));   
  %> 

