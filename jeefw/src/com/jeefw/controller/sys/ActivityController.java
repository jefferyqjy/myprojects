package com.jeefw.controller.sys;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.sys.TActivity;
import com.jeefw.service.sys.ActivityService;

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
@RequestMapping("/sys/activity")
public class ActivityController extends JavaEEFrameworkBaseController<TActivity> implements Constant {

	@Resource
	private ActivityService activityservice;

	// 查询字典的表格，包括分页、搜索和排序
	@RequestMapping(value = "/geActivity", method = { RequestMethod.POST, RequestMethod.GET })
	public void geActivity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer firstResult = Integer.valueOf(request.getParameter("page"));
		Integer maxResults = Integer.valueOf(request.getParameter("rows"));
		String sortedObject = request.getParameter("sidx");
		String sortedValue = request.getParameter("sord");
		String filters = request.getParameter("filters");
		TActivity tactivity = new TActivity();
		if (StringUtils.isNotBlank(filters)) {
			JSONObject jsonObject = JSONObject.fromObject(filters);
			JSONArray jsonArray = (JSONArray) jsonObject.get("rules");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject result = (JSONObject) jsonArray.get(i);
				if (result.getString("field").equals("reviewed") && result.getString("op").equals("eq")) {
					tactivity.setReviewed(result.getInt("data"));
				}
			}
			if (((String) jsonObject.get("groupOp")).equalsIgnoreCase("OR")) {
				tactivity.setFlag("OR");
			} else {
				tactivity.setFlag("AND");
			}
		} 
		tactivity.setFirstResult((firstResult - 1) * maxResults);
		tactivity.setMaxResults(maxResults);
		tactivity.setReviewed(1);
		Map<String, String> sortedCondition = new HashMap<String, String>(); 
		sortedCondition.put(sortedObject, sortedValue);
		tactivity.setSortedConditions(sortedCondition);
		QueryResult<TActivity> queryResult = activityservice.doPaginationQuery(tactivity);
		JqGridPageView<TActivity> dictListView = new JqGridPageView<TActivity>();
		dictListView.setMaxResults(maxResults);
		List<TActivity> dictWithSubList = activityservice.queryDictWithSubList(queryResult.getResultList());
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
			TActivity tactivity = new TActivity();
			if (StringUtils.isNotBlank(filters)) {
				JSONObject jsonObject = JSONObject.fromObject(filters);
				JSONArray jsonArray = (JSONArray) jsonObject.get("rules"); 
			} 
			tactivity.setFirstResult((firstResult - 1) * maxResults);
			tactivity.setMaxResults(maxResults);
			Map<String, String> sortedCondition = new HashMap<String, String>();
			sortedCondition.put(sortedObject, sortedValue);
			tactivity.setSortedConditions(sortedCondition);
			QueryResult<TActivity> queryResult = activityservice.doPaginationQuery(tactivity);
			JqGridPageView<TActivity> dictListView = new JqGridPageView<TActivity>();
			dictListView.setMaxResults(maxResults);
			List<TActivity> dictWithSubList = activityservice.queryDictWithSubList(queryResult.getResultList());
			dictListView.setRows(dictWithSubList);
			dictListView.setRecords(queryResult.getTotalCount());
			writeJSON(response, dictListView);
		}
		
		
		// 加載活动申请  
		@RequestMapping(value = "/loadApplyActivity", method = { RequestMethod.POST, RequestMethod.GET })
		public void loadApplyActivity(HttpServletRequest request, HttpServletResponse response) throws Exception { 
			List<TActivity> tactivitylist = activityservice.queryByProerties("reviewed",1);
			StringBuilder builder = new StringBuilder();
			builder.append("<select>");
			for (int i = 0; i < tactivitylist.size(); i++) {
				builder.append("<option value='" + tactivitylist.get(i).getId() + "'>" + tactivitylist.get(i).getTitle() + "</option>");
			}
			builder.append("</select>");
			writeJSON(response, builder.toString());
		}
				 
				
				
	// 保存字典的实体Bean
	@RequestMapping(value = "/saveDict", method = { RequestMethod.POST, RequestMethod.GET })
	public void doSave(TActivity entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExtJSBaseParameter parameter = ((ExtJSBaseParameter) entity);
		if (CMD_EDIT.equals(parameter.getCmd())) {
			activityservice.merge(entity);
		} else if (CMD_NEW.equals(parameter.getCmd())) {
			activityservice.persist(entity);
		}
		parameter.setSuccess(true);
		writeJSON(response, parameter);
	}

	// 操作字典的删除、导出Excel、字段判断和保存
	@RequestMapping(value = "/operateDict", method = { RequestMethod.POST, RequestMethod.GET })
	public void operateDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oper = request.getParameter("oper");
		String id = request.getParameter("id");
		if (oper.equals("del")) {
			String[] ids = id.split(",");
			deleteDict(request, response, (Long[]) ConvertUtils.convert(ids, Long.class));
		}  else {
			Map<String, Object> result = new HashMap<String, Object>();
			String title = request.getParameter("title"); 
			//SysUser  sessionuser = (SysUser) request.getSession().getAttribute("SESSION_SYS_USER");
			String promoter = request.getParameter("promoter"); 
			
			TActivity tactivity = null;
			if (oper.equals("edit")) {
				tactivity = activityservice.get(Long.valueOf(id));
			}
				TActivity entity = new TActivity();
				entity.setTitle(title);
				entity.setPromoter(promoter);  
//				entity.setReviewed(reviewed); 
				
				if (oper.equals("reviewed")) { 
					entity = activityservice.get(Long.valueOf(id));
					int reviewed = Integer.parseInt(request.getParameter("reviewed")); 
					entity.setReviewed(reviewed); 
				}
				if (StringUtils.isNotBlank(id)) {
					entity.setId(Long.valueOf(id));
					entity.setCmd("edit");
					doSave(entity, request, response);
				} else {
					entity.setCmd("new");
					doSave(entity, request, response);
				}
				
			 
		}
	}

	// 删除字典
	@RequestMapping("/deleteDict")
	public void deleteDict(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids) throws IOException {
		boolean flag = activityservice.deleteByPK(ids);
		if (flag) {
			writeJSON(response, "{success:true}");
		} else {
			writeJSON(response, "{success:false}");
		}
	}

}
