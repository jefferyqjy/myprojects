package com.jeefw.controller.sys;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.sys.SysUser;
import com.jeefw.model.sys.TCandidate;
import com.jeefw.model.sys.TVoter;
import com.jeefw.service.sys.CandidateService;
import com.jeefw.service.sys.VoterService;

import core.support.ExtJSBaseParameter;
import core.support.JqGridPageView;
import core.support.QueryResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 字典的控制层
 * administrator
 */
@Controller
@RequestMapping("/sys/voter")
public class VoterController extends JavaEEFrameworkBaseController<TVoter> implements Constant {

	@Resource
	private VoterService voterservice;
	@Resource
	private CandidateService candidateservice;
	
	// 查询字典的表格，包括分页、搜索和排序
	@RequestMapping(value = "/geActivity", method = { RequestMethod.POST, RequestMethod.GET })
	public void geActivity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer firstResult = Integer.valueOf(request.getParameter("page"));
		Integer maxResults = Integer.valueOf(request.getParameter("rows"));
		String sortedObject = request.getParameter("sidx");
		String sortedValue = request.getParameter("sord");
		String filters = request.getParameter("filters");
		TVoter tvoter = new TVoter();
		if (StringUtils.isNotBlank(filters)) {
			JSONObject jsonObject = JSONObject.fromObject(filters);
			JSONArray jsonArray = (JSONArray) jsonObject.get("rules");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject result = (JSONObject) jsonArray.get(i);
				if (result.getString("field").equals("dictKey") && result.getString("op").equals("eq")) {
					//tvoter.set$eq_dictKey(result.getString("data"));
				}
				if (result.getString("field").equals("dictValue") && result.getString("op").equals("cn")) {
					//tvoter.set$like_dictValue(result.getString("data"));
				}
			}
			if (((String) jsonObject.get("groupOp")).equalsIgnoreCase("OR")) {
				tvoter.setFlag("OR");
			} else {
				tvoter.setFlag("AND");
			}
		} 
		tvoter.setFirstResult((firstResult - 1) * maxResults);
		tvoter.setMaxResults(maxResults); 
		Map<String, String> sortedCondition = new HashMap<String, String>(); 
		tvoter.setSortedConditions(sortedCondition);
		QueryResult<TVoter> queryResult = voterservice.doPaginationQuery(tvoter);
		JqGridPageView<TVoter> dictListView = new JqGridPageView<TVoter>();
		dictListView.setMaxResults(maxResults);
		List<TVoter> dictWithSubList = voterservice.queryDictWithSubList(queryResult.getResultList());
		dictListView.setRows(dictWithSubList);
		dictListView.setRecords(queryResult.getTotalCount());
		writeJSON(response, dictListView);
	}

	
	// 活动申请
		@RequestMapping(value = "/getApplyActivity", method = { RequestMethod.POST, RequestMethod.GET })
		public void getApplyActivity(HttpServletRequest request, HttpServletResponse response) throws Exception {
			Integer firstResult = Integer.valueOf(request.getParameter("page"));
			Integer maxResults = Integer.valueOf(request.getParameter("rows"));
			String sortedObject = request.getParameter("sidx");
			String sortedValue = request.getParameter("sord");
			String filters = request.getParameter("filters");
			TVoter tvoter = new TVoter();
			if (StringUtils.isNotBlank(filters)) {
				JSONObject jsonObject = JSONObject.fromObject(filters);
				JSONArray jsonArray = (JSONArray) jsonObject.get("rules"); 
			} 
			tvoter.setFirstResult((firstResult - 1) * maxResults);
			tvoter.setMaxResults(maxResults);
			Map<String, String> sortedCondition = new HashMap<String, String>();
			sortedCondition.put(sortedObject, sortedValue);
			tvoter.setSortedConditions(sortedCondition);
			QueryResult<TVoter> queryResult = voterservice.doPaginationQuery(tvoter);
			JqGridPageView<TVoter> dictListView = new JqGridPageView<TVoter>();
			dictListView.setMaxResults(maxResults);
			List<TVoter> dictWithSubList = voterservice.queryDictWithSubList(queryResult.getResultList());
			dictListView.setRows(dictWithSubList);
			dictListView.setRecords(queryResult.getTotalCount());
			writeJSON(response, dictListView);
		}
		
		
	 
				 
				
				
	// 保存字典的实体Bean
	@RequestMapping(value = "/saveDict", method = { RequestMethod.POST, RequestMethod.GET })
	public void doSave(TVoter tvoter, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExtJSBaseParameter parameter = ((ExtJSBaseParameter) tvoter);
		if (CMD_EDIT.equals(parameter.getCmd())) {
			voterservice.merge(tvoter);
		} else if (CMD_NEW.equals(parameter.getCmd())) {
			voterservice.persist(tvoter);
		}
		parameter.setSuccess(true);
		writeJSON(response, parameter);
	}

	// 操作字典的删除、导出Excel、字段判断和保存
	@RequestMapping(value = "/operateDict", method = { RequestMethod.POST, RequestMethod.GET })
	public void operateDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysUser  sessionuser = (SysUser) request.getSession().getAttribute("SESSION_SYS_USER");
		String id = request.getParameter("id");
		String aid = request.getParameter("aid");//活动ID
		String cid = request.getParameter("cid");//候选人ID 
		Map<String, Object> result = new HashMap<String, Object>();
		TCandidate tcandidate = new TCandidate();
		Long ccid=0L;
		if(cid!=null&&!"".equals(cid)){
			ccid=Long.parseLong(cid);
		}
			
		String k[]={"aid","name"};
			
		Object v[]={Integer.parseInt(aid),sessionuser.getUserName()};
		List<TVoter> vlist = voterservice.queryByProerties(k,v);
		if(vlist.size()>0){
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			result.put("message", "您已经投过票了");
			writeJSON(response, result);
			System.out.println("error");
		} else {
			tcandidate =candidateservice.load(ccid);
			tcandidate.setNumber(tcandidate.getNumber()+1);
		
			candidateservice.merge(tcandidate);
		
			InetAddress address = InetAddress.getLocalHost();   
		 
			TVoter tvoter = new TVoter();
			tvoter.setName(sessionuser.getUserName()); 
			tvoter.setVoterip(address.getHostAddress());
			tvoter.setVotertime(new Date());
			if(cid != null && !"".equals(cid)) {
				tvoter.setCid(Integer.parseInt(cid));
			}
			if(aid != null && !"".equals(aid)) {
				tvoter.setAid(Integer.parseInt(aid));
			}
			 
			tvoter.setCmd("new");
			doSave(tvoter, request, response);
			System.out.println("ok");
		}
	}

	// 删除字典
	@RequestMapping("/deleteDict")
	public void deleteDict(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids) throws IOException {
		boolean flag = voterservice.deleteByPK(ids);
		if (flag) {
			writeJSON(response, "{success:true}");
		} else {
			writeJSON(response, "{success:false}");
		}
	}

}
