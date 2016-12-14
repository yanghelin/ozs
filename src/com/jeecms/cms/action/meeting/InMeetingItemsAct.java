package com.jeecms.cms.action.meeting;

import static com.jeecms.common.page.SimplePage.cpn;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONException;
import org.json.JSONObject;
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
import com.jeecms.common.web.ResponseUtils;
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
		model.addAttribute("auth", currUser.getUserMenu("item"));
		return "meeting/in/itemsList";
	}
	
	@RequiresPermissions("in_meeting_items:to_add")
	@RequestMapping("/in_meeting_items/to_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		List<InMeeting> meetingList = meetingMng.getList(null);
		model.addAttribute("meetingList", meetingList);
		return "meeting/in/itemsAdd";
	}

	@RequiresPermissions("in_meeting_items:to_edit")
	@RequestMapping("/in_meeting_items/to_edit.do")
	public String edit(Integer id, HttpServletRequest request, ModelMap model) {
		InMeetingItems item = manager.findById(id);
		List<InMeeting> meetingList = meetingMng.getList(null);
		model.addAttribute("meetingList", meetingList);
		model.addAttribute("item", item);
		return "meeting/in/itemsEdit";
	}
	
	@RequiresPermissions("in_meeting_items:save")
	@RequestMapping("/in_meeting_items/save.do")
	public String save(InMeetingItems bean,Integer meeting_id, HttpServletRequest request,ModelMap model) {
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
		manager.saveInMeetingItems(bean);
		log.info("save InMeetingItems id={}", bean.getId());
		return "redirect:list.do";
	}
	
	@RequiresPermissions("in_meeting_items:update")
	@RequestMapping("/in_meeting_items/update.do")
	public String update(InMeetingItems bean,Integer meeting_id, HttpServletRequest request,ModelMap model) {
		/*CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}*/
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setUpdateBy(currUser);
		bean.setUpdateTime(new Date());
		InMeeting meeting = new InMeeting();
		meeting.setId(meeting_id);
		bean.setMeetingId(meeting);
		manager.updateInMeetingItems(bean);
		log.info("update InMeetingItems id={}", bean.getId());
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
	
	@RequiresPermissions("in_meeting_items:to_view")
	@RequestMapping("/in_meeting_items/to_view.do")
	public String view(Integer id, HttpServletRequest request, ModelMap model) {
		InMeetingItems item = manager.findById(id);
		List<InMeeting> meetingList = meetingMng.getList(null);
		model.addAttribute("meetingList", meetingList);
		model.addAttribute("item", item);
		return "meeting/in/itemsView";
	}
	

	@RequiresPermissions("in_meeting_items:findByMeetingId")
	@RequestMapping("/in_meeting_items/findByMeetingId.do")
	public void findByMeetingId(Integer meetingId,HttpServletResponse response) throws JSONException {
		JSONObject json = new JSONObject();
		List<InMeetingItems> itemList = null;
		if(meetingId != null) {
			itemList = manager.findByMeetingId(meetingId);
		}
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		if(itemList != null && itemList.size()>0) {
			for(InMeetingItems item:itemList) {
				Map<String,String> itemMap = new HashMap<String, String>();
				itemMap.put("name", item.getItemName());
				itemMap.put("value", item.getItemNum().toString());
				mapList.add(itemMap);
			}
		}
		json.put("mapList", mapList);
		ResponseUtils.renderJson(response, json.getString("mapList").toString());
	}

	
	@Autowired
	private InMeetingItemsMng manager;
	
	@Autowired
	private InMeetingMng meetingMng;
}
