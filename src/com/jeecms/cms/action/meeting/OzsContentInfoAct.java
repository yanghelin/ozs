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
import com.jeecms.cms.entity.meeting.OzsContentInfo;
import com.jeecms.cms.manager.meeting.OzsContentInfoMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;

/**
 * 所内会议Action
 */
@Controller
public class OzsContentInfoAct {
	private static final Logger log = LoggerFactory.getLogger(OzsContentInfoAct.class);

	@RequiresPermissions("ozs_content_info:list")
	@RequestMapping("/ozs_content_info/list.do")
	public String list(String meetingName,Integer pageNo,HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser currUser = CmsUtils.getUser(request);
		Pagination pagination = ozsContentInfoMng.getPage(meetingName, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("meetingName", meetingName);
		model.addAttribute("auth", currUser.getUserMenu("in"));
		return "meeting/in/list";
	}
	
	@RequiresPermissions("ozs_content_info:to_add")
	@RequestMapping("/ozs_content_info/to_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		return "meeting/in/contentInfoAdd";
	}
	
	@RequiresPermissions("ozs_content_info:save")
	@RequestMapping("/ozs_content_info/save.do")
	public String save(OzsContentInfo bean,HttpServletRequest request,ModelMap model) {
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setUpdateBy(currUser);
		bean.setUpdateTime(new Date());
		bean.setIsDelete((byte)0);
		ozsContentInfoMng.saveOzsContentInfo(bean);
		log.info("save OzsContentInfo id={}", bean.getId());
		//cmsLogMng.operating(request, "cmsUser.log.save", "id=" + bean.getId() + ";username=" + bean.getUsername());
		return "redirect:to_edit.do";
	}
	
	@RequiresPermissions("ozs_content_info:to_edit")
	@RequestMapping("/ozs_content_info/to_edit.do")
	public String edit(Integer id, HttpServletRequest request, ModelMap model) {
		OzsContentInfo info = ozsContentInfoMng.findByMaxId();
		if(info == null) {
			info = new OzsContentInfo();
		}
		model.addAttribute("info", info);
		return "meeting/in/contentInfoEdit";
	}
	
	@RequiresPermissions("ozs_content_info:update")
	@RequestMapping("/ozs_content_info/update.do")
	public String update(OzsContentInfo bean,HttpServletRequest request,ModelMap model) {
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setUpdateBy(currUser);
		bean.setUpdateTime(new Date());
		bean.setIsDelete((byte)0);
		if(bean.getId()== null) {
			ozsContentInfoMng.saveOzsContentInfo(bean);
		}else {
			ozsContentInfoMng.updateOzsContentInfo(bean);
		}
		return "redirect:to_edit.do";
	}
	
	@Autowired
	private OzsContentInfoMng ozsContentInfoMng;
}
