package com.pro.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pro.pojo.TwitterBean;
import com.pro.pojo.TwitterCommentBean;
import com.pro.service.InterestService;
import com.pro.service.MemberService;
import com.pro.service.ReportService;
import com.pro.service.SubjectService;
import com.pro.service.TwitterCommentService;
import com.pro.service.TwitterService;
import com.pro.service.UniversityService;

@Controller
@RequestMapping(value = "/twitter")
public class TwitterCtl {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	UniversityService universityService;

	@Autowired
	InterestService interestService;
	
	@Autowired
	ReportService reportService; 
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	TwitterService twitterService;
	
	@Autowired
	TwitterCommentService twitterCommentService;
	
	@RequestMapping(value = "/preList.spring", method = RequestMethod.GET)
	public ModelAndView preUpdate(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "twitter/list";
		Integer id = (Integer)session.getAttribute("ONLINE_MEMBER_ID");
		if (id == null || id < 0) {
			pageUrl = "error";
			mav.addObject("ERROR_MESSAGE", "ÇëÏÈµÇÂ½");
		} else {
			List<TwitterBean> twitterList = twitterService.findByUserId(id);
			if(CollectionUtils.isNotEmpty(twitterList)) {
				for(TwitterBean twitter : twitterList) {
					Integer twitterId = twitter.getId();
					List<TwitterCommentBean> list = twitterCommentService.findByTwitterId(twitterId);
					twitter.setCommentList(list);
				}
			}
			mav.addObject("TwitterList", twitterList);
		}
		
		mav.setViewName(pageUrl);
		
		return mav;
	} 
	
	@RequestMapping(value = "/addTwitter.spring", method = RequestMethod.POST)
	@ResponseBody
	public String addTwitter(HttpSession session, @RequestParam(value = "content", required = false) String content) throws Exception {
		String flag = "1";
		Integer id = (Integer)session.getAttribute("ONLINE_MEMBER_ID");
		try {
			TwitterBean twitter = new TwitterBean();
			twitter.setContent(content);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			twitter.setCreateTime(df.format(new Date()));
			twitter.setUserId(id);
			twitterService.add(twitter);
		} catch (Exception e) {
			e.printStackTrace();
			flag = "0";
		}
		return flag;
	}

	@RequestMapping(value = "/deleteComment.spring", method = RequestMethod.POST)
	@ResponseBody
	public String deleteComment(@RequestParam(value = "commentId", required = false) Integer id, HttpSession session) throws Exception {
		String flag = "1";
		try {
			jdbcTemplate.update("DELETE FROM COM_PRO_TWITTER_COMMENT WHERE ID= ?", id);
		} catch (Exception e) {
			e.printStackTrace();
			flag = "0";
		}
		return flag;
	}
	
}
