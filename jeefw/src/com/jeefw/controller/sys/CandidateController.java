package com.jeefw.controller.sys;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.sys.TActivity;
import com.jeefw.model.sys.TCandidate;
import com.jeefw.service.sys.ActivityService;
import com.jeefw.service.sys.CandidateService;

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
@RequestMapping("/sys/candidate")
public class CandidateController extends JavaEEFrameworkBaseController<TCandidate> implements Constant {

	@Resource
	private CandidateService candidateservice;
	@Resource
	private ActivityService activityservice;

	// 查询字典的表格，包括分页、搜索和排序
	@RequestMapping(value = "/getCandidate", method = { RequestMethod.POST, RequestMethod.GET })
		public void getCandidate(HttpServletRequest request, HttpServletResponse response) throws Exception {
			Integer firstResult = Integer.valueOf(request.getParameter("page"));
			Integer maxResults = Integer.valueOf(request.getParameter("rows"));
			String sortedObject = request.getParameter("sidx");
			String sortedValue = request.getParameter("sord");
			String filters = request.getParameter("filters");
			TCandidate tcandidate = new TCandidate();
			if (StringUtils.isNotBlank(filters)) {
				JSONObject jsonObject = JSONObject.fromObject(filters);
				JSONArray jsonArray = (JSONArray) jsonObject.get("rules");
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject result = (JSONObject) jsonArray.get(i);
					String data = URLDecoder.decode(result.getString("data"), "utf-8");
					if (result.getString("field").equals("name") && result.getString("op").equals("eq")) {
						tcandidate.set$eq_name(result.getString("data"));
					}
					if (result.getString("field").equals("age") && result.getString("op").equals("eq")) {
						tcandidate.set$eq_age(result.getInt("data"));
					}
				}
				if (((String) jsonObject.get("groupOp")).equalsIgnoreCase("OR")) {
					tcandidate.setFlag("OR");
				} else {
					tcandidate.setFlag("AND");
				}
			}
			tcandidate.setFirstResult((firstResult - 1) * maxResults);
			tcandidate.setMaxResults(maxResults);
			Map<String, String> sortedCondition = new HashMap<String, String>();
			sortedCondition.put(sortedObject, sortedValue);
			tcandidate.setSortedConditions(sortedCondition);
			QueryResult<TCandidate> queryResult = candidateservice.doPaginationQuery(tcandidate);
			JqGridPageView<TCandidate> tcandidateListView = new JqGridPageView<TCandidate>();
			tcandidateListView.setMaxResults(maxResults);
			List<TCandidate> tcandidateCnList = candidateservice.queryDictWithSubList(queryResult.getResultList());
			if(CollectionUtils.isNotEmpty(tcandidateCnList)) {
				// 排序，获取各个活动下的热门候选人
				for(TCandidate c : tcandidateCnList) {
					c.setHot(false);
					List<TCandidate> list = candidateservice.queryByProerties("aid", c.getAid());
					if(CollectionUtils.isNotEmpty(list)) {
						int max = c.getNumber();
						for(TCandidate tc : list) {
							int tnum = tc.getNumber();
							max = tnum > max ? tnum : max;
						}
						if(max == c.getNumber()) {
							c.setHot(true);
						}
					}
				}
			}
			tcandidateListView.setRows(tcandidateCnList);
			tcandidateListView.setRecords(queryResult.getTotalCount());
			writeJSON(response, tcandidateListView);
		}

	//
	@RequestMapping(value = "/loadCandidate", method = { RequestMethod.POST, RequestMethod.GET })
	public void loadCandidate(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		String aid=request.getParameter("id");
		String k[]={"reviewed","aid"};
		Object v[]={1,Integer.parseInt(aid)};
		List<TCandidate> candidatelist = candidateservice.queryByProerties(k,v);
		 
		writeJSON(response,candidatelist);
	}
			
	
		// 保存部门的实体Bean

		@RequestMapping(value = "/saveTCandidate", method = { RequestMethod.POST, RequestMethod.GET })
		public void doSave(TCandidate entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
			ExtJSBaseParameter parameter = ((ExtJSBaseParameter) entity);
			if (CMD_EDIT.equals(parameter.getCmd())) {
				candidateservice.merge(entity);
			} else if (CMD_NEW.equals(parameter.getCmd())) {
				candidateservice.persist(entity);
			}
			parameter.setSuccess(true);
			writeJSON(response, parameter);
		}

		// 操作部门的删除、导出Excel、字段判断和保存
		@RequestMapping(value = "/operateTCandidate", method = { RequestMethod.POST, RequestMethod.GET })
		public void operateTCandidate(HttpServletRequest request, HttpServletResponse response) throws Exception {
			String oper = request.getParameter("oper");
			String id = request.getParameter("id");
			if (oper.equals("del")) {
				String[] ids = id.split(",");
				deleteTCandidate(request, response, (Long[]) ConvertUtils.convert(ids, Long.class));
			} else if (oper.equals("excel")) {
				response.setContentType("application/msexcel;charset=UTF-8");
				try {
					response.addHeader("Content-Disposition", "attachment;filename=file.xls");
					OutputStream out = response.getOutputStream();
					out.write(URLDecoder.decode(request.getParameter("csvBuffer"), "UTF-8").getBytes());
					out.flush();
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				Map<String, Object> result = new HashMap<String, Object>();
				String aid = request.getParameter("atitle");
				TActivity activity=new TActivity();
				if(aid!=null&&!"".equals(aid)){
				activity=activityservice.get(Long.parseLong(aid));
				}else {
					aid="0";
				}
				String atitle =activity.getTitle();
				String age = request.getParameter("age");
				String name = request.getParameter("name");
				String reviewed=request.getParameter("reviewed");
				TCandidate tcandidate = null;
				if (oper.equals("edit")) {
					tcandidate = candidateservice.get(Long.valueOf(id));
				}
			
				TCandidate entity = new TCandidate();
				entity.setAid(Integer.parseInt(aid));
				entity.setAtitle(atitle);
				entity.setName(name);
				if(age!=null&&!"".equals(age)){
				entity.setAge(Integer.parseInt(age));
				}
				entity.setNumber(0);
				entity.setCandescribe(""); 
				entity.setReviewed(0);
					if (oper.equals("edit")) {
						entity.setId(Long.valueOf(id));
						entity.setCmd("edit");
						doSave(entity, request, response);
					} else if (oper.equals("add")) {
						entity.setCmd("new");
						doSave(entity, request, response);
					} else if (oper.equals("reviewed")) {
						entity=candidateservice.get(Long.valueOf(id));
						entity.setCmd("edit");
						entity.setReviewed(Integer.parseInt(reviewed));
						doSave(entity, request, response);
					}
			}
			} 
			 

		// 删除部门
		@RequestMapping("/deleteTCandidate")
		public void deleteTCandidate(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids) throws IOException {
			boolean flag = candidateservice.deleteByPK(ids);
			if (flag) {
				writeJSON(response, "{success:true}");
			} else {
				writeJSON(response, "{success:false}");
			}
		}

}
