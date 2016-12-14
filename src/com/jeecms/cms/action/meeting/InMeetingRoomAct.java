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
import com.jeecms.cms.entity.meeting.InMeetingRoom;
import com.jeecms.cms.manager.meeting.InMeetingItemsMng;
import com.jeecms.cms.manager.meeting.InMeetingMng;
import com.jeecms.cms.manager.meeting.InMeetingRoomMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;

/**
 * 所内会议Action
 */
@Controller
public class InMeetingRoomAct {
	private static final Logger log = LoggerFactory.getLogger(InMeetingRoomAct.class);

	@RequiresPermissions("in_meeting_room:list")
	@RequestMapping("/in_meeting_room/list.do")
	public String list(String meetingName,Integer pageNo,HttpServletRequest request, ModelMap model) {
		/*CmsSite site = CmsUtils.getSite(request);*/
		Pagination pagination = inMeetingRoomMng.getPage(meetingName, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("meetingName", meetingName);
		CmsUser currUser = CmsUtils.getUser(request);
		model.addAttribute("auth", currUser.getUserMenu("room"));
		return "meeting/in/roomList";
	}
	
	@RequiresPermissions("in_meeting_room:to_add")
	@RequestMapping("/in_meeting_room/to_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		List<InMeeting> meetingList = inMeetingMng.getList(null);
		model.addAttribute("meetingList", meetingList);
		return "meeting/in/roomAdd";
	}
	
	@RequiresPermissions("in_meeting_room:save")
	@RequestMapping("/in_meeting_room/save.do")
	public String save(InMeetingRoom bean,Integer meeting_id, HttpServletRequest request,ModelMap model) {
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
		meeting.setId(meeting_id);
		bean.setMeetingId(meeting);
		inMeetingRoomMng.saveInMeetingRoom(bean);
		log.info("save InMeetingRoom id={}", bean.getId());
		//cmsLogMng.operating(request, "cmsUser.log.save", "id=" + bean.getId() + ";username=" + bean.getUsername());
		return "redirect:list.do";
	}
	
	@RequiresPermissions("in_meeting_room:delete")
	@RequestMapping("/in_meeting_room/delete.do")
	public String delete(InMeetingRoom bean,HttpServletRequest request,ModelMap model) {
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setIsDelete((byte)1);
		bean.setUpdateBy(currUser);
		bean.setUpdateTime(new Date());
		inMeetingRoomMng.updateInMeetingRoom(bean);
		return "redirect:list.do";
	}
	
	@RequiresPermissions("in_meeting_room:to_view")
	@RequestMapping("/in_meeting_room/to_view.do")
	public String view(Integer id, HttpServletRequest request, ModelMap model) {
		List<InMeeting> meetingList = inMeetingMng.getList(null);
		InMeetingRoom room = inMeetingRoomMng.findById(id);
		List<InMeetingItems> itemList = null;
		if(room != null && room.getMeetingId() != null && room.getMeetingId().getId() != null) {
			itemList = itemsMng.findByMeetingId(room.getMeetingId().getId());
		}
		model.addAttribute("itemList", itemList);
		model.addAttribute("room", room);
		model.addAttribute("meetingList", meetingList);
		return "meeting/in/roomView";
	}
	
	@Autowired
	private InMeetingRoomMng inMeetingRoomMng;
	
	@Autowired
	private InMeetingMng inMeetingMng;
	
	@Autowired
	private InMeetingItemsMng itemsMng;
}
