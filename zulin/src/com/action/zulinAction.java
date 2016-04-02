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
import com.dao.TZulinDAO;
import com.model.TAdmin;
import com.model.TChe;
import com.model.TUser;
import com.model.TZulin;
import com.opensymphony.xwork2.ActionSupport;

public class zulinAction extends ActionSupport
{
	private Integer id;
	private int cheId;
	private String kehuming;
	private String shenfenzheng;

	private String jiazhaohao;
	private String kaishishijian;
	private Integer yajin;
	
	private String beizhu;
	private String jieshushijian;
	private Integer feiyong;
	
	private TCheDAO cheDAO;
	private TZulinDAO zulinDAO;
	
	public String zulinAdd()
	{
		TZulin zulin=new TZulin();
		
		zulin.setCheId(cheId);
		zulin.setKehuming(kehuming);
		zulin.setShenfenzheng(shenfenzheng);
		zulin.setJiazhaohao(jiazhaohao);
		
		zulin.setKaishishijian(kaishishijian);
		zulin.setYajin(yajin);
		zulin.setBeizhu(beizhu);
		zulin.setJieshushijian("");
		
		zulin.setShifouhuan("未还");
		zulin.setFeiyong(0);
		
		zulinDAO.save(zulin);
		
		update_che_zt("已租出",cheId);
		
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("msg", "租赁信息添加成功");
		return "msg";
	}
	
	
	
	public String zulinMana()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		TAdmin admin = (TAdmin) session.getAttribute("admin");
		String sqlString = "";
		if ("1".equals(admin.getUserType())) {
			sqlString = " and belonguserId=" + admin.getUserId();
		}
		
		String sql="from TZulin where 1=1 "+ sqlString+" order by shifouhuan";
		List zulinList=zulinDAO.getHibernateTemplate().find(sql);
		for(int i=0;i<zulinList.size();i++)
		{
			TZulin zulin=(TZulin)zulinList.get(i);
			zulin.setChe(cheDAO.findById(zulin.getCheId()));
		}
		
		Map request1=(Map)ServletActionContext.getContext().get("request");
		request1.put("zulinList", zulinList);
		return ActionSupport.SUCCESS;
	}
	
	public String cheZhuChe(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		TUser user=(TUser)session.getAttribute("user");
		
		String sql="from TZulin where userid="+user.getUserId();
		List zucheList=zulinDAO.getHibernateTemplate().find(sql);
		for(int i=0;i<zucheList.size();i++)
		{
			TZulin zulin=(TZulin)zucheList.get(i);
			zulin.setChe(cheDAO.findById(zulin.getCheId()));
		}
		Map request1=(Map)ServletActionContext.getContext().get("request");
		request1.put("zucheList", zucheList);
		return ActionSupport.SUCCESS;		
	}
	
	public String qiuZhu(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		TUser user=(TUser)session.getAttribute("user");
		
		TChe tChe=cheDAO.findById(cheId);
		
		TZulin zulin=new TZulin();
		zulin.setCheId(cheId);
		zulin.setUserId(user.getUserId());
		zulin.setKehuming(user.getUserRealname());
		zulin.setShenfenzheng(user.getUserTel());
		zulin.setFeiyong(tChe.getRizu());
		zulin.setUserId(user.getUserId());
		zulin.setBelonguserId(tChe.getUserid());
		zulin.setKaishishijian(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		zulin.setShifouhuan("未还");
		
		zulinDAO.save(zulin);
		
		update_che_zt("已租出",cheId);
		
		
		return cheZhuChe();		
	}
	
	public String zulinDel()
	{
		TZulin zulin=zulinDAO.findById(id);
		zulinDAO.delete(zulin);
		
		update_che_zt("空闲中",zulin.getCheId());
		
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("msg", "租赁信息删除成功");
		return "msg";
	}

	
		
	
	public String huanche()
	{
		TZulin zulin=zulinDAO.findById(id);
		zulin.setShifouhuan("已搬走");
		zulin.setJieshushijian(jieshushijian);
		//zulin.setFeiyong(feiyong);
		zulin.setYajin(yajin);
		zulin.setBeizhu(beizhu);
		update_che_zt("空闲中",zulin.getCheId());
		zulinDAO.attachDirty(zulin);
		
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("msg", "终止出租完毕");
		return "msg";
	}

	
	public String zulin_tongji()
	{
		String sql="from TZulin where kehuming like '%"+kehuming+"%'";
		List zulinList=zulinDAO.getHibernateTemplate().find(sql);
		for(int i=0;i<zulinList.size();i++)
		{
			TZulin zulin=(TZulin)zulinList.get(i);
			zulin.setChe(cheDAO.findById(zulin.getCheId()));
		}
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("zulinList", zulinList);
		return ActionSupport.SUCCESS;
	}
	public String zulin_tongji1()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		TAdmin admin = (TAdmin) session.getAttribute("admin");
		String sqlString = "";
		if ("1".equals(admin.getUserType())) {
			sqlString = " and belonguserId=" + admin.getUserId();
		}
		if(StringUtils.isBlank(kehuming)){
			kehuming="";
		}
		if(StringUtils.isBlank(shenfenzheng)){
			shenfenzheng="";
		}
	String sql="from TZulin where 1=1 and kehuming like '%"+kehuming+"%' and shenfenzheng like '%"+shenfenzheng+"%' "+sqlString+" order by shifouhuan";
	List zulinList=zulinDAO.getHibernateTemplate().find(sql);
	for(int i=0;i<zulinList.size();i++)
	{
		TZulin zulin=(TZulin)zulinList.get(i);
		zulin.setChe(cheDAO.findById(zulin.getCheId()));
	}
	
	Map request1=(Map)ServletActionContext.getContext().get("request");
	request1.put("zulinList", zulinList);
	return ActionSupport.SUCCESS;
}
	public void update_che_zt(String zt,Integer cheId)
	{
		String sql="update TChe set zt=? where id=?";
		Object[] c={zt,cheId};
		
		cheDAO.getHibernateTemplate().bulkUpdate(sql,c);
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



	public int getCheId()
	{
		return cheId;
	}



	public void setCheId(int cheId)
	{
		this.cheId = cheId;
	}


	public Integer getFeiyong()
	{
		return feiyong;
	}


	public void setFeiyong(Integer feiyong)
	{
		this.feiyong = feiyong;
	}


	public Integer getId()
	{
		return id;
	}


	public void setId(Integer id)
	{
		this.id = id;
	}


	public String getJiazhaohao()
	{
		return jiazhaohao;
	}


	public void setJiazhaohao(String jiazhaohao)
	{
		this.jiazhaohao = jiazhaohao;
	}


	public String getJieshushijian()
	{
		return jieshushijian;
	}


	public void setJieshushijian(String jieshushijian)
	{
		this.jieshushijian = jieshushijian;
	}


	public String getKaishishijian()
	{
		return kaishishijian;
	}


	public void setKaishishijian(String kaishishijian)
	{
		this.kaishishijian = kaishishijian;
	}


	public String getKehuming()
	{
		return kehuming;
	}


	public void setKehuming(String kehuming)
	{
		this.kehuming = kehuming;
	}



	public String getShenfenzheng()
	{
		return shenfenzheng;
	}


	public void setShenfenzheng(String shenfenzheng)
	{
		this.shenfenzheng = shenfenzheng;
	}


	public Integer getYajin()
	{
		return yajin;
	}


	public void setYajin(Integer yajin)
	{
		this.yajin = yajin;
	}


	public TZulinDAO getZulinDAO()
	{
		return zulinDAO;
	}


	public void setZulinDAO(TZulinDAO zulinDAO)
	{
		this.zulinDAO = zulinDAO;
	}
	
}
