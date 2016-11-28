package com.jeecms.cms.action.meeting;

import static com.jeecms.common.page.SimplePage.cpn;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.entity.meeting.InMeeting;
import com.jeecms.cms.manager.meeting.InMeetingMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserExtMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.web.util.CmsUtils;

/**
 * 所内会议Action
 */
@Controller
public class WorkMealAct {
	private static final Logger log = LoggerFactory.getLogger(WorkMealAct.class);

	@RequiresPermissions("work_meal:list")
	@RequestMapping("/work_meal/list.do")
	public String list(String meetingName,Integer pageNo,HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser currUser = CmsUtils.getUser(request);
		Pagination pagination = inMeetingMng.getPage(meetingName, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("meetingName", meetingName);
		return "meeting/in/list";
	}
	
	@RequiresPermissions("work_meal:to_add")
	@RequestMapping("/work_meal/to_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		/*CmsSite site = CmsUtils.getSite(request);
		CmsUser currUser = CmsUtils.getUser(request);
		List<CmsGroup> groupList = cmsGroupMng.getList();
		List<CmsRole> roleList = cmsRoleMng.getList();
		model.addAttribute("site", site);
		model.addAttribute("groupList", groupList);
		model.addAttribute("roleList", roleList);
		model.addAttribute("currRank", currUser.getRank());*/
		return "meeting/in/add";
	}
	
	@RequiresPermissions("work_meal:save")
	@RequestMapping("/work_meal/save.do")
	public String save(InMeeting bean,HttpServletRequest request,ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		/*WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}*/
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setPublisher(currUser);
		bean.setPublishTime(new Date());
		bean.setIsDelete((byte)0);
		bean.setType((byte)1);
		inMeetingMng.saveInMeeting(bean);
		log.info("save InMeeting id={}", bean.getId());
		//cmsLogMng.operating(request, "cmsUser.log.save", "id=" + bean.getId() + ";username=" + bean.getUsername());
		return "redirect:list.do";
	}
	
	@RequiresPermissions("work_meal:delete")
	@RequestMapping("/work_meal/delete.do")
	public String delete(InMeeting bean,HttpServletRequest request,ModelMap model) {
		bean.setIsDelete((byte)1);
		inMeetingMng.updateInMeeting(bean);
		return "redirect:list.do";
	}

	@Autowired
	private InMeetingMng inMeetingMng;
}
