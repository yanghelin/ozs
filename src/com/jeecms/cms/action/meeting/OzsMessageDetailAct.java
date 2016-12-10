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
		model.addAttribute("auth", currUser.getUserMenu("reply"));
		return "meeting/message/detailList";
	}
	
	@RequiresPermissions("ozs_message_detail:reply")
	@RequestMapping("/ozs_message_detail/reply.do")
	public String reply(Integer id, HttpServletRequest request, ModelMap model) {
		OzsMessageDetail detail = ozsMessageDetailMng.findById(id);
		model.addAttribute("detail", detail);
		return "meeting/message/reply";
	}
	
	@RequiresPermissions("ozs_message_detail:save")
	@RequestMapping("/ozs_message_detail/save.do")
	public String save(OzsMessageDetail bean, Integer receiver_id, Integer message_id, HttpServletRequest request,ModelMap model) {
		/*CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}*/
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setSender(currUser);
		bean.setCreateTime(new Date());
		bean.setIsRead((byte)0);
		bean.setIsDelete((byte)0);
		bean.setIsReply((byte)1);
		if(receiver_id != null) {
			CmsUser receiver = new CmsUser();
			receiver.setId(receiver_id);
			bean.setReceiver(receiver);
		}
		if(message_id != null) {
			OzsMessage message = new OzsMessage();
			message.setId(message_id);
			bean.setMessageId(message);
		}
		ozsMessageDetailMng.saveOzsMessageDetail(bean);
		log.info("save OzsMessageDetail id={}", bean.getId());
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
