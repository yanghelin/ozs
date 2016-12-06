package com.jeecms.cms.action.meeting;

import static com.jeecms.common.page.SimplePage.cpn;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.manager.meeting.InMeetingDetailMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.web.util.CmsUtils;

/**
 * 所内会议Action
 */
@Controller
public class InMeetingDetailAct {

	@RequiresPermissions("in_meeting_detail:list")
	@RequestMapping("/in_meeting_detail/list.do")
	public String list(String meetingName,Integer isAll, Integer pageNo,HttpServletRequest request, ModelMap model) {
		CmsUser currUser = CmsUtils.getUser(request);
		Integer userId = currUser.getId();
		if(isAll != null && isAll == 1) {
			userId = null;
		}
		Pagination pagination = inMeetingDetailMng.getPage(meetingName,userId, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("meetingName", meetingName);
		return "meeting/in/detailList";
	}
	
	@Autowired
	private InMeetingDetailMng inMeetingDetailMng;
	
	@Autowired
	protected CmsUserMng userMng;
}
