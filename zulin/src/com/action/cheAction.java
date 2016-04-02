package com.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.dao.TCheDAO;
import com.dao.TYuyueDAO;
import com.dao.TZulinDAO;
import com.model.TAdmin;
import com.model.TChe;
import com.model.TUser;
import com.model.TYuyue;
import com.opensymphony.xwork2.ActionSupport;

public class cheAction extends ActionSupport
{
	private Integer id;
	private String chexing;
	private String pinpai;
	private String beizhu;

	private String fujian;
	private Integer rizu;
	private String zt;///空闲中----已租出---已预约
	private String del;
	private String quyu;//区域
	private String ttype;//类型
	private String zhxiu;//装修
	private String mianji;//面积
	private String linkman;//联系人
	private String linkphone;//联系电话
	
	
	private String message;
	private String path;
	
	private TCheDAO cheDAO;
	private TZulinDAO zulinDAO;
	private TYuyueDAO yuyueDAO;
	public String cheAdd()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		TAdmin admin=(TAdmin)session.getAttribute("admin");
		
		TChe che=new TChe();
		
		che.setChexing(chexing);
		che.setPinpai(pinpai);
		che.setBeizhu(beizhu);
		che.setFujian(fujian);
		
		che.setRizu(rizu);
		che.setZt("空闲中");
		che.setDel("no");
		che.setUserid(admin.getUserId());
		che.setQuyu(quyu);
		che.setTtype(ttype);
		che.setZhxiu(zhxiu);
		che.setMianji(mianji);
		che.setLinkman(linkman);
		che.setLinkphone(linkphone);
		che.setBookdate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		cheDAO.save(che);
		this.setMessage("操作成功");
		this.setPath("cheMana.action");
		return "succeed";
	}
	
	
	public String cheMana()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		TAdmin admin = (TAdmin) session.getAttribute("admin");
		String sqlString = "";
		if ("1".equals(admin.getUserType())) {
			sqlString = " and userid=" + admin.getUserId();
		}
		String sql="from TChe where del='no' " +sqlString;
		List cheList=cheDAO.getHibernateTemplate().find(sql);
		Map request1=(Map)ServletActionContext.getContext().get("request");
		request1.put("cheList", cheList);
		return ActionSupport.SUCCESS;
	}
	
	public String che_tongji()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		TAdmin admin = (TAdmin) session.getAttribute("admin");
		String sqlString = "";
		if ("1".equals(admin.getUserType())) {
			sqlString = " and userid=" + admin.getUserId();
		}
		if(StringUtils.isBlank(linkman)){
			linkman="";	
		}
		if(StringUtils.isBlank(chexing)){
			chexing="";	
		}
		if(StringUtils.isBlank(quyu)){
			quyu="";	
		}
		if(StringUtils.isBlank(ttype)){
			ttype="";	
		}
		if(StringUtils.isBlank(zhxiu)){
			zhxiu="";	
		}
		if(StringUtils.isBlank(linkman)){
			linkman="";	
		}
		//String sql="from TChe where del='no' " +sqlString+" ";
		String sql="from TChe where del='no' and chexing like '%"+chexing+"%' and quyu like'%"+quyu+"%' and ttype like '%"+ttype+"%' and zhxiu like '%"+zhxiu+"%' and linkman like '%"+linkman+"%' "+sqlString;
		List cheList=cheDAO.getHibernateTemplate().find(sql);
		Map request1=(Map)ServletActionContext.getContext().get("request");
		request1.put("cheList", cheList);
		return ActionSupport.SUCCESS;
	}
	
	public String cheDel()
	{
		TChe che=cheDAO.findById(id);
		che.setDel("yes");
		cheDAO.attachDirty(che);
		
		this.setMessage("操作成功");
		this.setPath("cheMana.action");
		return "succeed";
	}

	
	public String cheAll()
	{
		String sql="from TChe where del='no' and zt='空闲中'";
		List cheList=cheDAO.getHibernateTemplate().find(sql);
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("cheList", cheList);
		return ActionSupport.SUCCESS;
	}
	
	
	public String cheAllQian()
	{
		String sql="from TChe where del='no'";
		List cheList=cheDAO.getHibernateTemplate().find(sql);
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("cheList", cheList);
		return ActionSupport.SUCCESS;
	}
	
	
	public String cheDetailQian()
	{
		TChe che=cheDAO.findById(id);
		String sql="from TYuyue where 1=1 and cheId=" +id;
		List yuyueList=yuyueDAO.getHibernateTemplate().find(sql);
		for(int i=0;i<yuyueList.size();i++)
		{
			TYuyue yuyue=(TYuyue)yuyueList.get(i);
			yuyue.setChe(cheDAO.findById(yuyue.getCheId()));
		}
		
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("che", che);
		request.put("yuyueList", yuyueList);
		return ActionSupport.SUCCESS;
	}
	
	
	public String cheSeaQian()
	{
		String sql="from TChe where del='no' and chexing like '%"+chexing+"%' and quyu like'%"+quyu+"%' and ttype like '%"+ttype+"%' and zhxiu like '%"+zhxiu+"%' ";
		List cheList=cheDAO.getHibernateTemplate().find(sql);
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("cheList", cheList);
		return ActionSupport.SUCCESS;
	}

	

	public TZulinDAO getZulinDAO() {
		return zulinDAO;
	}


	public void setZulinDAO(TZulinDAO zulinDAO) {
		this.zulinDAO = zulinDAO;
	}


	public String getBeizhu()
	{
		return beizhu;
	}



	public void setBeizhu(String beizhu)
	{
		this.beizhu = beizhu;
	}



	public TCheDAO getCheDAO()
	{
		return cheDAO;
	}



	public void setCheDAO(TCheDAO cheDAO)
	{
		this.cheDAO = cheDAO;
	}



	public String getChexing()
	{
		return chexing;
	}



	public void setChexing(String chexing)
	{
		this.chexing = chexing;
	}



	public String getZt()
	{
		return zt;
	}


	public void setZt(String zt)
	{
		this.zt = zt;
	}


	public String getDel()
	{
		return del;
	}



	public void setDel(String del)
	{
		this.del = del;
	}



	public String getFujian()
	{
		return fujian;
	}



	public void setFujian(String fujian)
	{
		this.fujian = fujian;
	}



	public Integer getId()
	{
		return id;
	}



	public void setId(Integer id)
	{
		this.id = id;
	}



	public String getMessage()
	{
		return message;
	}



	public void setMessage(String message)
	{
		this.message = message;
	}



	public String getPath()
	{
		return path;
	}



	public void setPath(String path)
	{
		this.path = path;
	}



	public String getPinpai()
	{
		return pinpai;
	}



	public void setPinpai(String pinpai)
	{
		this.pinpai = pinpai;
	}



	public Integer getRizu()
	{
		return rizu;
	}



	public String getQuyu() {
		return quyu;
	}


	public void setQuyu(String quyu) {
		this.quyu = quyu;
	}


	public String getTtype() {
		return ttype;
	}


	public void setTtype(String ttype) {
		this.ttype = ttype;
	}


	public String getZhxiu() {
		return zhxiu;
	}


	public void setZhxiu(String zhxiu) {
		this.zhxiu = zhxiu;
	}


	public String getMianji() {
		return mianji;
	}


	public void setMianji(String mianji) {
		this.mianji = mianji;
	}


	public String getLinkman() {
		return linkman;
	}


	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}


	public String getLinkphone() {
		return linkphone;
	}


	public void setLinkphone(String linkphone) {
		this.linkphone = linkphone;
	}


	public void setRizu(Integer rizu)
	{
		this.rizu = rizu;
	}


	public TYuyueDAO getYuyueDAO() {
		return yuyueDAO;
	}


	public void setYuyueDAO(TYuyueDAO yuyueDAO) {
		this.yuyueDAO = yuyueDAO;
	}
	
}
