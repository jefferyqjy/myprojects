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
  out.println("��br>Դʼ�ļ�:");   
  out.println(saSourceFile[i]);   
  out.println("��br>Ŀ���ļ�:");   
  out.println(sObjectPath+saObjectFile[i]);   
  out.println("��br>�ϴ�˵��:");   
  out.println(saDescription[i]);   
  out.println("��br>");   
  }   
  out.println("��br>����:" + fileBean.getFieldValue("Author"));   
  out.println("��br>��˾:" + fileBean.getFieldValue("Company"));   
  out.println("��br>˵��:" + fileBean.getFieldValue("Comment"));   
  %> 

