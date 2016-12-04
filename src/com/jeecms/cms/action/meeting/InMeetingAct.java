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
import com.jeecms.cms.manager.meeting.InMeetingMng;
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
public class InMeetingAct {
	private static final Logger log = LoggerFactory.getLogger(InMeetingAct.class);

	@RequiresPermissions("in_meeting:list")
	@RequestMapping("/in_meeting/list.do")
	public String list(String meetingName,Integer pageNo,HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser currUser = CmsUtils.getUser(request);
		Pagination pagination = inMeetingMng.getPage(meetingName, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("meetingName", meetingName);
		return "meeting/in/list";
	}
	
	@RequiresPermissions("in_meeting:to_add")
	@RequestMapping("/in_meeting/to_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		/*CmsUser currUser = CmsUtils.getUser(request);
		List<CmsGroup> groupList = cmsGroupMng.getList();
		List<CmsRole> roleList = cmsRoleMng.getList();
		model.addAttribute("site", site);
		model.addAttribute("groupList", groupList);
		model.addAttribute("roleList", roleList);
		model.addAttribute("currRank", currUser.getRank());*/
		List<CmsUser> userList = userMng.getList("", "", site.getId(), null, false, true, null);
		model.addAttribute("userList", userList);
		return "meeting/in/add";
	}
	
	@RequiresPermissions("in_meeting:save")
	@RequestMapping("/in_meeting/save.do")
	public String save(InMeeting bean,HttpServletRequest request,ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		/*WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}*/
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setPublisher(currUser);
		bean.setPublishTime(new Date());
		bean.setIsDelete((byte)0);
		bean.setType((byte)1);
		inMeetingMng.saveInMeeting(bean);
		log.info("save InMeeting id={}", bean.getId());
		//cmsLogMng.operating(request, "cmsUser.log.save", "id=" + bean.getId() + ";username=" + bean.getUsername());
		return "redirect:list.do";
	}
	
	@RequiresPermissions("in_meeting:delete")
	@RequestMapping("/in_meeting/delete.do")
	public String delete(InMeeting bean,HttpServletRequest request,ModelMap model) {
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setUpdateBy(currUser);
		bean.setUpdateTime(new Date());
		bean.setIsDelete((byte)1);
		inMeetingMng.updateInMeeting(bean);
		return "redirect:list.do";
	}
	
	@RequiresPermissions("in_meeting:upload")
	@RequestMapping("/in_meeting/upload.do")
	public void upload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile cvsfile) throws Exception {
		log.debug("进入upload方法：上传文件完成，开始执行文件保存。");
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html;chartset=UTF-8");
		String content = null;
		try {
			if(cvsfile.isEmpty()){  
				log.error("上传文件为空，请重新上传。");
            }else if(cvsfile.getSize()>FILE_MAX_SIZE){
            	log.error("文件超过100M大小，请重新上传。");
            }else {
            	/*
        		 * 根据来源类型判断是否包含内容数据。
        		 * 根据发送渠道来判断渠道信息：主要是判断“用户ID”或“用户ID+内容”导入时短信和邮件的区分。
        		 * 根据导入类型判断导入文件是否与之对应：需要根据导入类型来判断导入的头文件是否符合选择的类型
        		 */
        		String itype = request.getParameter("importType");
        		Integer importType = 0;
        		if(StringUtils.isNotBlank(itype)) {
        			importType = Integer.valueOf(itype);
        		}
        		
        		log.debug("文件分析完成！");
            }
		}catch (Exception e) {
			log.error("文件上传失败！", e);
		}finally{
			
			writer.write("");
			if(StringUtils.isNotBlank(content)) {
				writer.write("#-CRM-#"+content);
			}
			writer.flush();
			writer.close();
		}
		log.debug("文件上传成功，返回前台页面！");
	}

	@Autowired
	private InMeetingMng inMeetingMng;
	
	@Autowired
	protected CmsUserMng userMng;
	
	private final Integer FILE_MAX_SIZE = 104857600;//100M大小
}
