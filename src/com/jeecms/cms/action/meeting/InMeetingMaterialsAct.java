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
import com.jeecms.cms.entity.meeting.InMeetingMaterials;
import com.jeecms.cms.entity.meeting.MeetingAttachment;
import com.jeecms.cms.manager.meeting.InMeetingMaterialsMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;

/**
 * 会议相关材料管理Action
 */
@Controller
public class InMeetingMaterialsAct {
	private static final Logger log = LoggerFactory.getLogger(InMeetingMaterialsAct.class);

	@RequiresPermissions("in_meeting_materials:list")
	@RequestMapping("/in_meeting_materials/list.do")
	public String list(String meetingName,Integer pageNo,HttpServletRequest request, ModelMap model) {
		/*CmsSite site = CmsUtils.getSite(request);
		CmsUser currUser = CmsUtils.getUser(request);*/
		Pagination pagination = inMeetingMaterialsMng.getPage(meetingName, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("meetingName", meetingName);
		return "meeting/in/materialsList";
	}
	
	@RequiresPermissions("in_meeting_materials:to_add")
	@RequestMapping("/in_meeting_materials/to_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		/*CmsSite site = CmsUtils.getSite(request);
		CmsUser currUser = CmsUtils.getUser(request);
		List<CmsGroup> groupList = cmsGroupMng.getList();
		List<CmsRole> roleList = cmsRoleMng.getList();
		model.addAttribute("site", site);
		model.addAttribute("groupList", groupList);
		model.addAttribute("roleList", roleList);
		model.addAttribute("currRank", currUser.getRank());*/
		return "meeting/in/materialsAdd";
	}
	
	@RequiresPermissions("in_meeting_materials:save")
	@RequestMapping("/in_meeting_materials/save.do")
	public String save(InMeetingMaterials bean, Integer meeting_id, Integer attachmentId, HttpServletRequest request,ModelMap model) {
		/*CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}*/
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setCreateBy(currUser);
		bean.setCreateTime(new Date());
		bean.setIsDelete((byte)0);
		
		if(attachmentId != null) {
			MeetingAttachment matt = new MeetingAttachment();
			matt.setId(attachmentId);
			bean.setAttachment(matt);
		}
		InMeeting meeting = new InMeeting();
		meeting.setId(meeting_id);
		bean.setMeetingId(meeting);
		inMeetingMaterialsMng.saveInMeetingMaterials(bean);
		log.info("save InMeetingMaterials id={}", bean.getId());
		//cmsLogMng.operating(request, "cmsUser.log.save", "id=" + bean.getId() + ";username=" + bean.getUsername());
		return "redirect:list.do";
	}
	
	@RequiresPermissions("in_meeting_materials:delete")
	@RequestMapping("/in_meeting_materials/delete.do")
	public String delete(InMeetingMaterials bean,HttpServletRequest request,ModelMap model) {
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setIsDelete((byte)1);
		bean.setUpdateBy(currUser);
		bean.setUpdateTime(new Date());
		inMeetingMaterialsMng.updateInMeetingMaterials(bean);
		return "redirect:list.do";
	}

	@Autowired
	private InMeetingMaterialsMng inMeetingMaterialsMng;
}
