package com.jeecms.cms.action.meeting;

import static com.jeecms.common.page.SimplePage.cpn;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jeecms.cms.entity.meeting.InMeeting;
import com.jeecms.cms.manager.meeting.InMeetingDetailMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.web.util.CmsUtils;

/**
 * 所内会议Action
 */
@Controller
public class InMeetingDetailAct {
	private static final Logger log = LoggerFactory.getLogger(InMeetingDetailAct.class);

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
	
	private final Integer FILE_MAX_SIZE = 104857600;//100M大小
}
