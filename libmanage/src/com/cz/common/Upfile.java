package com.cz.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;


public class Upfile extends HttpServlet {

	public Upfile() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	this.doPost(request, response);
	}

		public void go(String url,HttpServletRequest request, HttpServletResponse response)
		{
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		
		public void gor(String url,HttpServletRequest request, HttpServletResponse response)
		{
			try {
				response.sendRedirect(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String ac = request.getParameter("ac");
		if(ac==null)ac="";
		

		
		if(ac.equals("uploadimg"))
		{
			try {
				String filename="";
			request.setCharacterEncoding("utf-8");
			RequestContext  requestContext = new ServletRequestContext(request);
			if(FileUpload.isMultipartContent(requestContext)){

			   DiskFileItemFactory factory = new DiskFileItemFactory();
			   factory.setRepository(new File(request.getRealPath("/upfile/")+"/"));
			   ServletFileUpload upload = new ServletFileUpload(factory);
			   upload.setSizeMax(100*1024*1024);
			   List items = new ArrayList();
			    
			     items = upload.parseRequest(request);
			     
			    FileItem fileItem = (FileItem) items.get(0);
			   if(fileItem.getName()!=null && fileItem.getSize()!=0)
			    {
			    if(fileItem.getName()!=null && fileItem.getSize()!=0){
			      File fullFile = new File(fileItem.getName());
			      filename = Info.generalFileName(fullFile.getName());
			      File newFile = new File(request.getRealPath("/upfile/")+"/" + filename);
			      try {
			       fileItem.write(newFile);
			      } catch (Exception e) {
			       e.printStackTrace();
			      }
			     }else{
			     }
			    }
			}
			
			go("/js/uploadimg.jsp?filename="+filename, request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			    }
		}
		
		if(ac.equals("uploaddoc"))
		{
			try {
				String filename="";
			request.setCharacterEncoding("utf-8");
			RequestContext  requestContext = new ServletRequestContext(request);
			if(FileUpload.isMultipartContent(requestContext)){

			   DiskFileItemFactory factory = new DiskFileItemFactory();
			   factory.setRepository(new File(request.getRealPath("/upfile/")+"/"));
			   ServletFileUpload upload = new ServletFileUpload(factory);
			   upload.setSizeMax(100*1024*1024);
			   List items = new ArrayList();
			    
			     items = upload.parseRequest(request);
			     
			    FileItem fileItem = (FileItem) items.get(0);
			   if(fileItem.getName()!=null && fileItem.getSize()!=0)
			    {
			    if(fileItem.getName()!=null && fileItem.getSize()!=0){
			      File fullFile = new File(fileItem.getName());
			      filename = Info.generalFileName(fullFile.getName());
			      File newFile = new File(request.getRealPath("/upfile/")+"/" + filename);
			      try {
			       fileItem.write(newFile);
			      } catch (Exception e) {
			       e.printStackTrace();
			      }
			     }else{
			     }
			    }
			}
			
			go("/js/uploaddoc.jsp?docname="+filename, request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			    }
		}
		
		
		out.flush();
		out.close();
	}


	public void init() throws ServletException {
		// Put your code here
	}
	
	

}
