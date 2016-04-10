package com.pro.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pro.utils.DateUtils;

@Controller
@RequestMapping("/photo")
public class PhotoCtl {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final int UPLOAD_BUF_SIZE = 4096;
	private static final String UPLOAD_ROOT = "resource";

	 @RequestMapping(value = "download", method = RequestMethod.GET)  
	    public void downloadFile(@RequestParam String fileName,HttpServletResponse response,HttpServletRequest req){    
	        response.setCharacterEncoding("utf-8");    
	        response.setContentType("multipart/form-data");    
	        String fName = fileName.substring(fileName.lastIndexOf(File.separator)+1);
	        response.setHeader("Content-Disposition", "attachment;fileName="+fName);    

	        try {    
	            File file=new File(fileName);    
	            InputStream inputStream=new FileInputStream(file);    
	            OutputStream os=response.getOutputStream();    
	            byte[] b=new byte[1024];    
	            int length;    
	            while((length=inputStream.read(b))>0){    
	                os.write(b,0,length);    
	            }    
	            inputStream.close();    
	        } catch (FileNotFoundException e) {    
	            e.printStackTrace();    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }    
	    }  
	 
	 @RequestMapping(value = "upload", method = RequestMethod.POST)  
		@ResponseBody
		public String uploadSubjectFiles(HttpServletRequest request, HttpServletResponse response){
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			
			//get request parameters
			String[] photoBox = multipartRequest.getParameterValues("photoBoxId");		
			int photoBoxId = Integer.parseInt(photoBox[0]);
			//handle files
			try {
				List<String[]> files = storeFileToWorkSpace(multipartRequest);
				List<Object[]> para = new ArrayList<Object[]>();
				for (String[] file:files) {
					Object[] p = new Object[3];
					p[0] = photoBoxId;
					p[1] = file[0].substring(file[0].indexOf("\\friend"));
					p[2] = file[1];
					para.add(p);
				}
			 this.jdbcTemplate.batchUpdate("INSERT INTO COM_PRO_PHOTO(PHOTOBOX_ID, PATH, NAME, CRE_DATE) VALUES (?,?,?,NOW())",  para);
			 
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	 
	 /**
		 * Upload files to workspace
		 * @param request
		 * @return
		 * @throws IOException 
		 */
		private List<String[]> storeFileToWorkSpace(MultipartHttpServletRequest request) throws IOException{
			List<String[]> subjectFiles = new ArrayList<String[]>();
			String workspace = createWorkSpace(request);
			Map<String, MultipartFile> fileMap = request.getFileMap(); 
			
			for(Map.Entry<String, MultipartFile> entry : fileMap.entrySet()){
				String[] para = new String[2];
				MultipartFile mf = entry.getValue();
				String fileName = mf.getOriginalFilename();
				String filePath = workspace + File.separator + UUID.randomUUID().toString()+ fileName.substring(fileName.lastIndexOf("."));
				byte[] buff = new byte[UPLOAD_BUF_SIZE]; 
	            FileOutputStream output = new FileOutputStream(filePath); 
	            InputStream inputStream = mf.getInputStream();
	            int bytecount = 1;  
	            while ((bytecount = inputStream.read(buff, 0, UPLOAD_BUF_SIZE)) != -1) { 
	                output.write(buff, 0, bytecount);  
	            }  
	            output.flush();  
	            output.close();  
	            para[0] = filePath;
	            para[1] = fileName;
	            subjectFiles.add(para);
			}
			return subjectFiles;
		}
		
		/**
		 * CreateWorkSpace for new job
		 * @param request
		 * @return
		 */
		private String createWorkSpace(HttpServletRequest request){
			StringBuilder buf = new StringBuilder();
			buf.append(request.getSession().getServletContext().getRealPath("/"));
			buf.append(File.separator);
			buf.append(UPLOAD_ROOT);
			buf.append(File.separator);
			buf.append(DateUtils.getCurrentDate());
			String workspace = buf.toString();
			buf = null;
			File file = new File(workspace);
			if(!file.exists()){
				file.mkdirs();
			}
			return workspace;
		}
}
