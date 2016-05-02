package com.cz.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cz.common.CommonDAO;
import com.cz.common.Info;
import com.cz.entity.Bookhj;
import com.cz.entity.Books;
import com.cz.entity.Bookyy;
import com.cz.entity.Sreader;
import com.cz.entity.Syspros;
import com.cz.entity.Sysuser;
import com.opensymphony.xwork2.ActionSupport;

@WebServlet(name = "Control", description = "Control", urlPatterns = {"/Control"})
public class ControlAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8481431344837294337L;

	public String login() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		String username = request.getParameter("uname");
		String password = request.getParameter("upass");
		String utype = request.getParameter("utype");
		if (utype.equals("管理员")) {
			String sql = " from Sysuser where uname='" + username
					+ "' and upass='" + password + "'";
			List<Sysuser> userlist = dao.findByHql(sql);
			if (userlist.size() != 1) {
				request.setAttribute("error", "");
				return "login";
			} else {
				request.getSession().setAttribute("admin", userlist.get(0));
				return "index";
			}
		} else {
			String sql = " from Sreader where uname='" + username
					+ "' and upass='" + password + "'";
			List<Sreader> userlist = dao.findByHql(sql);
			if (userlist.size() != 1) {
				request.setAttribute("error", "");
				return "login";
			} else {
				request.getSession().setAttribute("reader", userlist.get(0));
				return "index";
			}
		}

	}

	public String addpros() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		Syspros data = new Syspros();
		String type = request.getParameter("type");
		if (type.equals("1"))
			type = "出版社";
		if (type.equals("2"))
			type = "图书类别";
		if (type.equals("3"))
			type = "学历";
		if (type.equals("4"))
			type = "职业";
		data.setProname(request.getParameter("proname"));
		data.setInfoa(type);
		dao.save(data);
		request.setAttribute("suc", "");
		return "addpros";
	}

	public String shbook() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		Bookhj data = (Bookhj) dao.load(request.getParameter("id"), "Bookhj");
		data.setSjtime(request.getParameter("htime"));
		data.setSjstatus("已通过");
		dao.update(data);
		request.setAttribute("suc", "");
		return "addpros";
	}

	public String rshbook() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		Bookhj data = (Bookhj) dao.load(request.getParameter("id"), "Bookhj");
		data.setSjtime(request.getParameter("htime"));
		data.setSjstatus("待审批");
		data.setHtime(null);
		dao.update(data);
		request.setAttribute("suc", "");
		return "allcontrol";
	}

	public String addbookhj() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		String readername = request.getParameter("readername");
		Sreader s = (Sreader) dao.findByHql(
				"from Sreader where uname='" + readername + "'").get(0);
		int kjnum = Integer.parseInt(s.getKjnum());

		if (dao.findByHql(
				"from Bookhj where readername='" + readername
						+ "' and (hbtime is  null or hbtime='')").size() == kjnum) {
			request.setAttribute("kjnum", "");
			return "addbookhj";
		} else if (dao.findByHql(
				"from Bookhj where htime<='"
						+ Info.getDateStr().substring(0, 10)
						+ "' and (hbtime is  null or hbtime='')").size() > 0) {
			request.setAttribute("htime", "");
			return "addbookhj";
		} else {
			Bookhj data = new Bookhj();
			data.setBei(request.getParameter("bei"));
			data.setBookname(request.getParameter("bookname"));
			data.setHtime(request.getParameter("htime"));
			data.setJtime(request.getParameter("jtime"));
			data.setReadername(request.getParameter("readername"));
			data.setYjin(request.getParameter("yjin"));
			dao.save(data);
			request.setAttribute("suc", "");
			return "allcontrol";
		}

	}

	public String updatebookhj() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		Bookhj data = (Bookhj) dao.load(request.getParameter("id"), "Bookhj");
		data.setBei(request.getParameter("bei"));
		data.setBookname(request.getParameter("bookname"));
		data.setHtime(request.getParameter("htime"));
		data.setJtime(request.getParameter("jtime"));
		data.setReadername(request.getParameter("readername"));
		data.setYjin(request.getParameter("yjin"));
		dao.update(data);
		request.setAttribute("suc", "");
		return "addpros";
	}

	public String addbookyy() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		Bookyy data = new Bookyy();
		data.setBei(request.getParameter("bei"));
		data.setBookname(request.getParameter("bookname"));
		data.setHtime(request.getParameter("htime"));
		data.setReadername(request.getParameter("readername"));
		data.setYytime(request.getParameter("yytime"));
		data.setStatus("已通过");
		dao.save(data);
		request.setAttribute("suc", "");
		return "allcontrol";
	}

	public String addrbookyy() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		Bookyy data = new Bookyy();
		data.setBei(request.getParameter("bei"));
		data.setBookname(request.getParameter("bookname"));
		data.setHtime(request.getParameter("htime"));
		data.setReadername(request.getParameter("readername"));
		data.setYytime(request.getParameter("yytime"));
		data.setStatus("待审批");
		dao.save(data);
		request.setAttribute("suc", "");
		return "allcontrol";
	}

	public String updatebookyy() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		Bookyy data = (Bookyy) dao.load(request.getParameter("id"), "Bookyy");
		data.setBei(request.getParameter("bei"));
		data.setBookname(request.getParameter("bookname"));
		data.setHtime(request.getParameter("htime"));
		data.setYytime(request.getParameter("yytime"));
		data.setReadername(request.getParameter("readername"));
		dao.update(data);
		request.setAttribute("suc", "");
		return "allcontrol";
	}

	public String hbook() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		Bookhj data = (Bookhj) dao.load(request.getParameter("id"), "Bookhj");
		data.setHbbei(request.getParameter("hbbei"));
		data.setHbtime(request.getParameter("hbtime"));
		data.setHbkou(request.getParameter("hbkou"));
		dao.update(data);
		request.setAttribute("suc", "");
		return "allcontrol";
	}

	public String addsreader() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		Sreader data = new Sreader();
		data.setEmail(request.getParameter("email"));
		data.setKjnum(request.getParameter("kjnum"));
		data.setTel(request.getParameter("tel"));
		data.setTname(request.getParameter("tname"));
		data.setUname(request.getParameter("uname"));
		data.setUpass(request.getParameter("upass"));
		data.setXueli(request.getParameter("xueli"));
		data.setZiye(request.getParameter("ziye"));
		dao.save(data);
		request.setAttribute("suc", "");
		return "addsreader";
	}

	public String updatesreader() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		Sreader data = (Sreader) dao
				.load(request.getParameter("id"), "Sreader");
		data.setEmail(request.getParameter("email"));
		data.setKjnum(request.getParameter("kjnum"));
		data.setTel(request.getParameter("tel"));
		data.setTname(request.getParameter("tname"));
		data.setUname(request.getParameter("uname"));
		data.setUpass(request.getParameter("upass"));
		data.setXueli(request.getParameter("xueli"));
		data.setZiye(request.getParameter("ziye"));
		dao.update(data);
		request.setAttribute("suc", "");
		return "addsreader";
	}

	public String rupdatesreader() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		Sreader data = (Sreader) dao
				.load(request.getParameter("id"), "Sreader");
		data.setEmail(request.getParameter("email"));
		data.setTel(request.getParameter("tel"));
		data.setTname(request.getParameter("tname"));
		data.setUpass(request.getParameter("upass"));
		data.setXueli(request.getParameter("xueli"));
		data.setZiye(request.getParameter("ziye"));
		dao.update(data);
		request.setAttribute("suc", "");
		return "rupdatesreader";
	}

	public String addbooks() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		Books data = new Books();
		data.setAuthor(request.getParameter("author"));
		data.setBookname(request.getParameter("bookname"));
		data.setCbrq(request.getParameter("cbrq"));
		data.setCbs(request.getParameter("cbs"));
		data.setIsbn(request.getParameter("isbn"));
		data.setJianj(request.getParameter("jianj"));
		data.setKucun(request.getParameter("kucun"));
		data.setPrice(request.getParameter("price"));
		data.setTslb(request.getParameter("tslb"));
		data.setFilename(request.getParameter("filename"));
		dao.save(data);
		request.setAttribute("suc", "");
		return "addbooks";
	}

	public String updatebooks() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		String id = request.getParameter("id");
		Books data = (Books) dao.load(id, "Books");
		data.setAuthor(request.getParameter("author"));
		data.setBookname(request.getParameter("bookname"));
		data.setCbrq(request.getParameter("cbrq"));
		data.setCbs(request.getParameter("cbs"));
		data.setIsbn(request.getParameter("isbn"));
		data.setJianj(request.getParameter("jianj"));
		data.setKucun(request.getParameter("kucun"));
		data.setPrice(request.getParameter("price"));
		data.setTslb(request.getParameter("tslb"));
		data.setFilename(request.getParameter("filename"));
		dao.update(data);
		request.setAttribute("suc", "");
		return "addbooks";
	}

	public String updatepros() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		String id = request.getParameter("id");
		Syspros data = (Syspros) dao.load(id, "Syspros");
		data.setProname(request.getParameter("proname"));
		dao.update(data);
		request.setAttribute("suc", "");
		return "addpros";
	}

	public String deljigou() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		String id = request.getParameter("id");
		dao.delete(id, "Jigou");
		request.setAttribute("suc", "");
		return "addjigou";
	}

	public String uppass() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		Sysuser user = (Sysuser) request.getSession().getAttribute("admin");
		String newpwd = request.getParameter("newpwd");
		int id = user.getId();
		Sysuser u = (Sysuser) dao.findById(id, "Sysuser");
		u.setUpass(newpwd);
		dao.update(u);
		request.setAttribute("suc", "");
		return "uppass";
	}

	public String puppass() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		Sreader reader = (Sreader) request.getSession().getAttribute("reader");
		String newpwd = request.getParameter("newpwd");
		int id = reader.getId();
		Sreader u = (Sreader) dao.findById(id, "Sreader");
		u.setUpass(newpwd);
		dao.update(u);
		request.setAttribute("suc", "");
		return "puppass";
	}

	private static final int BUFFER_SIZE = 16 * 1024;
	private File fujian;
	private String fujianFileName;

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
	}
	
	public String upload() {
		fujianFileName = "a.jpg";
		String newFujianName = Info.generalFileName(fujianFileName);
		String dstPath = ServletActionContext.getServletContext().getRealPath("upfile") + "\\" + newFujianName;
		File dstFile = new File(dstPath);
		copy(this.getFujian(), dstFile);
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("newFujianName", newFujianName);
		request.put("oldFujianName", fujianFileName);
		request.put("fujianPath", "/upload" + "/" + newFujianName);
		fujianFileName = newFujianName;
		return "uploadimg";
	}

	private static void copy(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst),
					BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public File getFujian() {
		return fujian;
	}

	public void setFujian(File fujian) {
		this.fujian = fujian;
	}

	public String getFujianFileName() {
		return fujianFileName;
	}

	public void setFujianFileName(String fujianFileName) {
		this.fujianFileName = fujianFileName;
	}

	public String addsysusers() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		Sysuser u = new Sysuser();
		u.setEmail(request.getParameter("email"));
		u.setTel(request.getParameter("tel"));
		u.setTname(request.getParameter("tname"));
		u.setUname(request.getParameter("uname"));
		u.setUpass(request.getParameter("upass"));
		dao.save(u);
		request.setAttribute("suc", "");
		return "adduser";
	}

	public String updatesysusers() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		id = request.getParameter("id");
		Sysuser u = (Sysuser) dao.load(id, "Sysuser");
		u.setEmail(request.getParameter("email"));
		u.setTel(request.getParameter("tel"));
		u.setTname(request.getParameter("tname"));
		u.setUpass(request.getParameter("upass"));
		dao.update(u);
		request.setAttribute("id", id);
		request.setAttribute("suc", "");
		return "updatesysusers";
	}

	public String pupdatesysusers() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		id = request.getParameter("id");
		Sysuser u = (Sysuser) dao.load(id, "Sysuser");
		u.setEmail(request.getParameter("email"));
		u.setTel(request.getParameter("tel"));
		u.setTname(request.getParameter("tname"));
		u.setUpass(request.getParameter("upass"));
		dao.update(u);
		request.setAttribute("id", id);
		request.setAttribute("suc", "");
		return "pupdatesysusers";
	}

	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
