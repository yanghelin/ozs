package com.jeecms.cms.action.front;

import static com.jeecms.cms.Constants.TPLDIR_MEETING;
import static com.jeecms.common.page.SimplePage.cpn;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jeecms.cms.entity.assist.CmsAdvertising;
import com.jeecms.cms.entity.meeting.MeetingAttachment;
import com.jeecms.cms.entity.meeting.OutMeeting;
import com.jeecms.cms.manager.meeting.MeetingAttachmentMng;
import com.jeecms.cms.manager.meeting.OutMeetingMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.upload.FileRepository;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

/**
 * 所内会议Action
 */
@Controller
public class MeetingAct {
	private static final Logger log = LoggerFactory.getLogger(MeetingAct.class);

	public static final String MEETING_SHOW = "tpl.meeting.show";
	public static final String MEETING_EROLL = "tpl.meeting.eroll";
	
	@RequiresPermissions("out_meeting:list")
	@RequestMapping("/out_meeting/list.do")
	public String list(String meetingName,Integer pageNo,HttpServletRequest request, ModelMap model) {
		Pagination pagination = outMeetingMng.getPage(meetingName, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("meetingName", meetingName);
		return "meeting/out/list";
	}
	
	@RequestMapping("/meeting/show.jspx")
	public String show(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser currUser = CmsUtils.getUser(request);
		if(currUser == null) {
			return "redirect:/zh/login.jspx";
		}
		OutMeeting meeting = null;
		if(id == null) {
			meeting = outMeetingMng.getMaxMeetingId();
		}else {
			meeting = outMeetingMng.findById(id);
		}
		model.addAttribute("meeting",meeting);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEETING, MEETING_SHOW);
	}
	
	
	@RequestMapping("/meeting/eroll.jspx")
	public String toEroll(Integer id, HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser currUser = CmsUtils.getUser(request);
		if(currUser == null) {
			return "redirect:/zh/login.jspx";
		}
		OutMeeting meeting = null;
		if(id == null) {
			meeting = outMeetingMng.getMaxMeetingId();
		}else {
			meeting = outMeetingMng.findById(id);
		}
		model.addAttribute("meeting",meeting);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEETING, MEETING_EROLL);
	}
	
	
	
	@RequiresPermissions("out_meeting:delete")
	@RequestMapping("/out_meeting/delete.do")
	public String delete(OutMeeting bean,HttpServletRequest request,ModelMap model) {
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setUpdateBy(currUser);
		bean.setUpdateTime(new Date());
		bean.setIsDelete((byte)1);
		outMeetingMng.updateOutMeeting(bean);
		return "redirect:list.do";
	}
	
	@RequiresPermissions("meeting:detail")
	@RequestMapping("/meeting/detail.do")
	public String meetingDetail(Integer meetingId, HttpServletRequest request, ModelMap model) {
		if(meetingId == null) {
			meetingId = null;
		}
		outMeetingMng.findById(meetingId);
		return "meeting/out/list";
	}
	
	@Autowired
	private OutMeetingMng outMeetingMng;
	
	@Autowired
	private MeetingAttachmentMng meetingAttachmentMng;
	
	@Autowired
	protected FileRepository fileRepository;
	
	private final Integer FILE_MAX_SIZE = 104857600;//100M大小
}
