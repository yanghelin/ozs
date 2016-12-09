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
import com.jeecms.cms.entity.meeting.OzsMessageDetail;
import com.jeecms.cms.manager.meeting.OzsMessageDetailMng;
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
public class OzsMessageDetailAct {
	private static final Logger log = LoggerFactory.getLogger(OzsMessageDetailAct.class);

	@RequiresPermissions("ozs_message_detail:list")
	@RequestMapping("/ozs_message_detail/list.do")
	public String list(String meetingName,Integer pageNo,HttpServletRequest request, ModelMap model) {
		CmsUser currUser = CmsUtils.getUser(request);
		Pagination pagination = ozsMessageDetailMng.getPage(meetingName, currUser.getId(), cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("meetingName", meetingName);
		return "meeting/message/list";
	}
	
	@RequiresPermissions("ozs_message_detail:to_add")
	@RequestMapping("/ozs_message_detail/to_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		List<CmsUser> userList = userMng.getList("", "", site.getId(), null, false, true, null);
		model.addAttribute("userList", userList);
		return "meeting/message/add";
	}
	
	@RequiresPermissions("ozs_message_detail:reply")
	@RequestMapping("/ozs_message_detail/reply.do")
	public String reply(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		List<CmsUser> userList = userMng.getList("", "", site.getId(), null, false, true, null);
		model.addAttribute("userList", userList);
		return "meeting/message/reply";
	}
	
	@RequiresPermissions("ozs_message_detail:save")
	@RequestMapping("/ozs_message_detail/save.do")
	public String save(OzsMessageDetail bean, HttpServletRequest request,ModelMap model) {
		/*CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}*/
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setIsDelete((byte)0);
		ozsMessageDetailMng.saveOzsMessageDetail(bean);
		log.info("save OzsMessageDetail id={}", bean.getId());
		//cmsLogMng.operating(request, "cmsUser.log.save", "id=" + bean.getId() + ";username=" + bean.getUsername());
		return "redirect:list.do";
	}
	
	@RequiresPermissions("ozs_message_detail:delete")
	@RequestMapping("/ozs_message_detail/delete.do")
	public String delete(OzsMessageDetail bean,HttpServletRequest request,ModelMap model) {
		bean.setIsDelete((byte)1);
		ozsMessageDetailMng.updateOzsMessageDetail(bean);
		return "redirect:list.do";
	}

	@Autowired
	private OzsMessageDetailMng ozsMessageDetailMng;
	
	@Autowired
	protected CmsUserMng userMng;
}
