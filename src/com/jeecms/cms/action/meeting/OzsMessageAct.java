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

import com.jeecms.cms.entity.meeting.OzsMessage;
import com.jeecms.cms.manager.meeting.OzsMessageDetailMng;
import com.jeecms.cms.manager.meeting.OzsMessageMng;
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
public class OzsMessageAct {
	private static final Logger log = LoggerFactory.getLogger(OzsMessageAct.class);

	@RequiresPermissions("ozs_message:list")
	@RequestMapping("/ozs_message/list.do")
	public String list(String meetingName,Integer pageNo,HttpServletRequest request, ModelMap model) {
		Pagination pagination = ozsMessageMng.getPage(meetingName, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("meetingName", meetingName);
		CmsUser currUser = CmsUtils.getUser(request);
		model.addAttribute("auth", currUser.getUserMenu("message"));
		return "meeting/message/list";
	}
	
	@RequiresPermissions("ozs_message:to_add")
	@RequestMapping("/ozs_message/to_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		List<CmsUser> userList = userMng.getList("", "", site.getId(), null, false, true, null);
		model.addAttribute("userList", userList);
		return "meeting/message/add";
	}
	
	@RequiresPermissions("ozs_message:reply")
	@RequestMapping("/ozs_message/reply.do")
	public String reply(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		List<CmsUser> userList = userMng.getList("", "", site.getId(), null, false, true, null);
		model.addAttribute("userList", userList);
		return "meeting/message/add";
	}
	
	@RequiresPermissions("ozs_message:save")
	@RequestMapping("/ozs_message/save.do")
	public String save(OzsMessage bean,HttpServletRequest request,ModelMap model) {
		/*CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}*/
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setSendBy(currUser);
		bean.setSendTime(new Date());
		bean.setIsDelete((byte)0);
		ozsMessageMng.saveOzsMessage(bean);
		log.info("save OzsMessage id={}", bean.getId());
		//cmsLogMng.operating(request, "cmsUser.log.save", "id=" + bean.getId() + ";username=" + bean.getUsername());
		return "redirect:list.do";
	}
	
	@RequiresPermissions("ozs_message:delete")
	@RequestMapping("/ozs_message/delete.do")
	public String delete(OzsMessage bean,HttpServletRequest request,ModelMap model) {
		bean.setIsDelete((byte)1);
		ozsMessageMng.updateOzsMessage(bean);
		return "redirect:list.do";
	}

	@Autowired
	private OzsMessageMng ozsMessageMng;
	
	@Autowired
	private OzsMessageDetailMng ozsMessageDetailMng;
	
	@Autowired
	protected CmsUserMng userMng;
}
