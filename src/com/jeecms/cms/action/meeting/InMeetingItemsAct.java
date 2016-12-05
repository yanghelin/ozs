package com.jeecms.cms.action.meeting;

import static com.jeecms.common.page.SimplePage.cpn;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.entity.meeting.InMeeting;
import com.jeecms.cms.entity.meeting.InMeetingItems;
import com.jeecms.cms.manager.meeting.InMeetingItemsMng;
import com.jeecms.cms.manager.meeting.InMeetingMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;

/**
 * 所内会议Action
 */
@Controller
public class InMeetingItemsAct {
	private static final Logger log = LoggerFactory.getLogger(InMeetingItemsAct.class);

	@RequiresPermissions("in_meeting_items:list")
	@RequestMapping("/in_meeting_items/list.do")
	public String list(String meetingName,Integer pageNo,HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser currUser = CmsUtils.getUser(request);
		Pagination pagination = manager.getPage(meetingName, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("meetingName", meetingName);
		return "meeting/in/itemsList";
	}
	
	@RequiresPermissions("in_meeting_items:to_add")
	@RequestMapping("/in_meeting_items/to_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		List<InMeeting> meetingList = meetingMng.getList(null);
		model.addAttribute("meetingList", meetingList);
		return "meeting/in/itemsAdd";
	}
	
	@RequiresPermissions("in_meeting_items:save")
	@RequestMapping("/in_meeting_items/save.do")
	public String save(InMeetingItems bean,Integer meetingId, HttpServletRequest request,ModelMap model) {
		/*CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}*/
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setCreateBy(currUser);
		bean.setCreateTime(new Date());
		bean.setIsDelete((byte)0);
		InMeeting meeting = new InMeeting();
		meeting.setId(meetingId);
		bean.setMeetingId(meeting);
		manager.saveInMeetingItems(bean);
		log.info("save InMeetingItems id={}", bean.getId());
		return "redirect:list.do";
	}
	
	@RequiresPermissions("in_meeting_items:delete")
	@RequestMapping("/in_meeting_items/delete.do")
	public String delete(InMeetingItems bean,HttpServletRequest request,ModelMap model) {
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setIsDelete((byte)1);
		bean.setUpdateBy(currUser);
		bean.setUpdateTime(new Date());
		manager.updateInMeetingItems(bean);
		return "redirect:list.do";
	}
	
	@Autowired
	private InMeetingItemsMng manager;
	
	@Autowired
	private InMeetingMng meetingMng;
}
