package load; 
import java.io.File; 
import java.io.IOException; 
import java.io.PrintWriter; 
import java.util.List; 
import javax.servlet.ServletContext; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import org.apache.commons.fileupload.FileItem; 
import org.apache.commons.fileupload.FileUploadException; 
import org.apache.commons.fileupload.disk.DiskFileItemFactory; 
import org.apache.commons.fileupload.servlet.ServletFileUpload; 
public class UpLoad extends HttpServlet { 
@SuppressWarnings("unchecked") 
@Override 
protected void service(HttpServletRequest req, HttpServletResponse resp) 
throws ServletException, IOException { 
req.setCharacterEncoding("utf-8"); 
resp.setContentType("text/html;charset=utf-8"); 
//Ϊ�������ṩ������Ϣ 
DiskFileItemFactory factory = new DiskFileItemFactory(); 
//�����������ʵ�� 
ServletFileUpload sfu = new ServletFileUpload(factory); 
//��ʼ���� 
sfu.setFileSizeMax(1024*400); 
//ÿ�����������ݻ��װ��һ����Ӧ��FileItem������ 
try { 
List<FileItem> items = sfu.parseRequest(req); 
//���ֱ��� 
for (int i = 0; i < items.size(); i++) { 
FileItem item = items.get(i); 
//isFormFieldΪtrue����ʾ�ⲻ���ļ��ϴ����� 
if(!item.isFormField()){ 
ServletContext sctx = getServletContext(); 
//��ô���ļ�������·�� 
//upload�µ�ĳ���ļ��� �õ���ǰ���ߵ��û� �ҵ���Ӧ���ļ��� 

String path = sctx.getRealPath("/upload"); 
System.out.println(path); 
//����ļ��� 
String fileName = item.getName(); 
System.out.println(fileName); 
//�÷�����ĳЩƽ̨(����ϵͳ),�᷵��·��+�ļ��� 
fileName = fileName.substring(fileName.lastIndexOf("/")+1); 
File file = new File(path+"\\"+fileName); 
if(!file.exists()){ 
item.write(file); 
//���ϴ�ͼƬ�����ּ�¼�����ݿ��� 

resp.sendRedirect("/upload/ok.html"); 
} 
} 
} 
} catch (Exception e) { 
e.printStackTrace(); 
} 

} 
} 