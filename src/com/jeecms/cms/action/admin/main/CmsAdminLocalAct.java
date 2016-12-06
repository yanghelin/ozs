package com.jeecms.cms.action.admin.main;

import static com.jeecms.common.page.SimplePage.cpn;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.entity.assist.CmsWebservice;
import com.jeecms.cms.entity.meeting.MeetingMenu;
import com.jeecms.cms.manager.meeting.MeetingMenuMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.PropertyUtils;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.springmvc.RealPathResolver;
import com.jeecms.core.SimpleMail;
import com.jeecms.core.entity.CmsGroup;
import com.jeecms.core.entity.CmsMemberMeeting;
import com.jeecms.core.entity.CmsRegistMeeting;
import com.jeecms.core.entity.CmsRegistUser;
import com.jeecms.core.entity.CmsRole;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsSubscibeEmail;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.CmsUserExt;
import com.jeecms.core.entity.CmsUserMeeting;
import com.jeecms.core.entity.CmsUserSite;
import com.jeecms.core.entity.MailInfo;
import com.jeecms.core.web.WebErrors;
import com.jeecms.core.web.util.CmsUtils;

/**
 * 本站管理员ACTION
 */
@Controller
public class CmsAdminLocalAct extends CmsAdminAbstract {
	private static final Logger log = LoggerFactory
			.getLogger(CmsAdminLocalAct.class);
	public static String EMAIL_PATH = "/WEB-INF/config/email/email.properties";
	@Autowired
	private RealPathResolver realPathResolver;
	@Autowired
	private MeetingMenuMng meetingMenuMng;
	
	@RequiresPermissions("admin_local:v_list")
	@RequestMapping("/admin_local/v_list.do")
	public String list(String queryUsername, String queryEmail,
			Integer queryGroupId, Boolean queryDisabled, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser currUser = CmsUtils.getUser(request);
		Pagination pagination = manager.getPage(queryUsername, queryEmail, site
				.getId(), queryGroupId, queryDisabled, true,
				currUser.getRank(), cpn(pageNo), CookieUtils
						.getPageSize(request));
		model.addAttribute("pagination", pagination);

		model.addAttribute("queryUsername", queryUsername);
		model.addAttribute("queryEmail", queryEmail);
		model.addAttribute("queryGroupId", queryGroupId);
		model.addAttribute("queryDisabled", queryDisabled);
		model.addAttribute("groupList", cmsGroupMng.getList());
		return "admin/local/list";
	}
	
	@RequiresPermissions("admin_local:v_list_member_regist")
	@RequestMapping("/admin_local/v_list_member_regist.do")
	public String registList(Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		if(site.getId() != null){
			CmsUser currUser = CmsUtils.getUser(request);
			if(currUser != null){
				Pagination pagination = cmsRegistMng.getPage( true,  cpn(pageNo), CookieUtils
								.getPageSize(request));
				model.addAttribute("pagination", pagination);
			}
		}
		
		return "admin/regist/list";
	}
	
	@RequiresPermissions("admin_local:v_list_meeting")
	@RequestMapping("/admin_local/v_list_meeting.do")
	public String meetingList(Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		if(site.getId() != null){
			CmsUser currUser = CmsUtils.getUser(request);
			if(currUser != null){
				Pagination pagination = cmsRegistMng.findAllMeeting( true,  cpn(pageNo), CookieUtils
								.getPageSize(request));
				model.addAttribute("pagination", pagination);
			}
		}
		
		return "admin/meeting/list";
	}
	
	@RequiresPermissions("admin_local:v_meeting_info")
	@RequestMapping("/admin_local/v_meeting_info.do")
	public String meetingInfo(Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		if(site.getId() != null){
			CmsUser currUser = CmsUtils.getUser(request);
			if(currUser != null){
				String id = request.getParameter("id");
				CmsMemberMeeting meeting = cmsRegistMng.findMemberMeeting(id);
				List<CmsRegistMeeting> registMeetingList = cmsRegistMng.findMeetingUser(id);
				List<CmsUserMeeting> userMeetingList = new ArrayList<CmsUserMeeting>();
				for(CmsRegistMeeting registUser : registMeetingList){
					CmsUserMeeting userMeeting = new CmsUserMeeting();
					CmsRegistUser user = cmsRegistMng.findByUser(String.valueOf(registUser.getRegistId()));
					userMeeting.setUserName(user.getUserName());
					userMeeting.setPath(registUser.getPath());
					userMeeting.setRegistId(String.valueOf(user.getId()));
					userMeeting.setMeetingId(String.valueOf(meeting.getId()));
					userMeetingList.add(userMeeting);
				}
				model.addAttribute("meeting", meeting);
				model.addAttribute("userMeetingList", userMeetingList);
				
			}
		}
		return "admin/meeting/editInfo";
	}
	
	@RequiresPermissions("admin_local:v_save_meeting")
	@RequestMapping("/admin_local/v_save_meeting.do")
	public String saveMeeting(Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		if(site.getId() != null){ 
			String str = request.getParameter("idslist");
			String[] ids = str.split(",");
			String title = request.getParameter("title");
			String createPerson = request.getParameter("sendPerson");
			String container = request.getParameter("container");
			String meetingId = cmsRegistMng.saveMeeting(title, createPerson, container, "Y");
			if(ids.length > 0){
				for(int i=0; i<ids.length; i++){
					cmsRegistMng.saveRegistMeeting(ids[i], meetingId);
				}
			}
		}
		return registList(pageNo, request, model);
	}
	
	@RequiresPermissions("admin_local:v_list_subscibe_email")
	@RequestMapping("/admin_local/v_list_subscibe_email.do")
	public String subscibeEmailList(Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		if(site.getId() != null){
			CmsUser currUser = CmsUtils.getUser(request);
			if(currUser != null){
				Pagination pagination = cmsRegistMng.getPageEmail( true,  cpn(pageNo), CookieUtils
								.getPageSize(request));
				model.addAttribute("pagination", pagination);
			}
		}
		
		return "admin/email/list";
	}
	
	@RequiresPermissions("admin_local:v_editor_email")
	@RequestMapping("/admin_local/v_editor_email.do")
	public String editorHandle(Integer pageNo, HttpServletRequest request, ModelMap model) {
		String emailText = request.getParameter("container");
		List<CmsSubscibeEmail> list = cmsRegistMng.getEmailList();
		for(CmsSubscibeEmail cmsEmail : list){
			sendEmail(emailText, cmsEmail.getEmail());
		}
		return subscibeEmailList(pageNo, request, model);
	}
	
	@RequiresPermissions("admin_local:v_add")
	@RequestMapping("/admin_local/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser currUser = CmsUtils.getUser(request);
		List<CmsGroup> groupList = cmsGroupMng.getList();
		List<CmsRole> roleList = cmsRoleMng.getList();
		List<MeetingMenu> menuList = meetingMenuMng.getList(null);
		model.addAttribute("site", site);
		model.addAttribute("groupList", groupList);
		model.addAttribute("roleList", roleList);
		model.addAttribute("currRank", currUser.getRank());
		model.addAttribute("menuList", menuList);
		return "admin/local/add";
	}

	@RequiresPermissions("admin_local:v_edit")
	@RequestMapping("/admin_local/v_edit.do")
	public String edit(Integer id, Integer queryGroupId, Boolean queryDisabled,
			HttpServletRequest request,HttpServletResponse  response,ModelMap model) throws IOException {
		CmsSite site = CmsUtils.getSite(request);
		String queryUsername = RequestUtils.getQueryParam(request,
				"queryUsername");
		String queryEmail = RequestUtils.getQueryParam(request, "queryEmail");
		CmsUser currUser = CmsUtils.getUser(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsUser admin = manager.findById(id);
		CmsUserSite userSite = admin.getUserSite(site.getId());

		List<CmsGroup> groupList = cmsGroupMng.getList();
		List<CmsRole> roleList = cmsRoleMng.getList();

		model.addAttribute("cmsAdmin", admin);
		model.addAttribute("site", site);
		model.addAttribute("userSite", userSite);
		model.addAttribute("roleIds", admin.getRoleIds());
		model.addAttribute("groupList", groupList);
		model.addAttribute("roleList", roleList);
		model.addAttribute("currRank", currUser.getRank());

		model.addAttribute("queryUsername", queryUsername);
		model.addAttribute("queryEmail", queryEmail);
		model.addAttribute("queryGroupId", queryGroupId);
		model.addAttribute("queryDisabled", queryDisabled);
		return "admin/local/edit";
	}

	@RequiresPermissions("admin_local:o_save")
	@RequestMapping("/admin_local/o_save.do")
	public String save(CmsUser bean, CmsUserExt ext, String username,
			String email, String password, Boolean selfAdmin, Integer rank, Integer groupId,Integer departmentId,
			Integer[] roleIds, Integer[] channelIds,
			Byte[] steps, Boolean[] allChannels,Boolean[] allControlChannels,
			HttpServletRequest request,ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Integer[] siteIds = new Integer[] { site.getId() };
		String ip = RequestUtils.getIpAddr(request);
		bean = manager.saveAdmin(username, email, password, ip, false,
				selfAdmin, rank, groupId, departmentId,roleIds, channelIds,
				siteIds, steps, allChannels,allControlChannels, ext);
		cmsWebserviceMng.callWebService("true",username, password, email, ext,CmsWebservice.SERVICE_TYPE_ADD_USER);
		log.info("save CmsAdmin id={}", bean.getId());
		cmsLogMng.operating(request, "cmsUser.log.save", "id=" + bean.getId()
				+ ";username=" + bean.getUsername());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("admin_local:o_update")
	@RequestMapping("/admin_local/o_update.do")
	public String update(CmsUser bean, CmsUserExt ext, String password,
			Integer groupId,Integer departmentId, Integer[] roleIds,Integer[] channelIds, Byte step, Boolean allChannel,Boolean allControlChannel,
			String queryUsername, String queryEmail, Integer queryGroupId,
			Boolean queryDisabled, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateUpdate(bean.getId(),bean.getRank(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = manager.updateAdmin(bean, ext, password, groupId,departmentId, roleIds,channelIds, site.getId(), step, allChannel,allControlChannel);
		cmsWebserviceMng.callWebService("true",bean.getUsername(), password, null, ext,CmsWebservice.SERVICE_TYPE_UPDATE_USER);
		log.info("update CmsAdmin id={}.", bean.getId());
		cmsLogMng.operating(request, "cmsUser.log.update", "id=" + bean.getId()
				+ ";username=" + bean.getUsername());
		return list(queryUsername, queryEmail, queryGroupId, queryDisabled,
				pageNo, request, model);
	}

	@RequiresPermissions("admin_local:o_delete")
	@RequestMapping("/admin_local/o_delete.do")
	public String delete(Integer[] ids, Integer queryGroupId,
			Boolean queryDisabled, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		String queryUsername = RequestUtils.getQueryParam(request,
				"queryUsername");
		String queryEmail = RequestUtils.getQueryParam(request, "queryEmail");
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsUser[] beans = manager.deleteByIds(ids);
		CmsUser user =CmsUtils.getUser(request);
		boolean deleteCurrentUser=false;
		for (CmsUser bean : beans) {
			Map<String,String>paramsValues=new HashMap<String, String>();
			paramsValues.put("username", bean.getUsername());
			paramsValues.put("admin", "true");
			cmsWebserviceMng.callWebService(CmsWebservice.SERVICE_TYPE_DELETE_USER, paramsValues);
			log.info("delete CmsAdmin id={}", bean.getId());
			if(user.getUsername().equals(bean.getUsername())){
				deleteCurrentUser=true;
			}else{
				cmsLogMng.operating(request, "cmsUser.log.delete", "id="
						+ bean.getId() + ";username=" + bean.getUsername());
			}
		}
		if(deleteCurrentUser){
			 Subject subject = SecurityUtils.getSubject();
			 subject.logout();
			 return "login";
		}
		return list(queryUsername, queryEmail, queryGroupId, queryDisabled,
				pageNo, request, model);
	}

	@RequiresPermissions("admin_local:v_channels_add")
	@RequestMapping(value = "/admin_local/v_channels_add.do")
	public String channelsAdd(Integer siteId, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return channelsAddJson(siteId, request, response, model);
	}

	@RequiresPermissions("admin_local:v_channels_edit")
	@RequestMapping(value = "/admin_local/v_channels_edit.do")
	public String channelsEdit(Integer userId, Integer siteId,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		return channelsEditJson(userId, siteId, request, response, model);
	}

	@RequiresPermissions("admin_local:v_check_username")
	@RequestMapping(value = "/admin_local/v_check_username.do")
	public void checkUsername(HttpServletRequest request, HttpServletResponse response) {
		checkUserJson(request, response);
	}

	@RequiresPermissions("admin_local:v_check_email")
	@RequestMapping(value = "/admin_local/v_check_email.do")
	public void checkEmail(String email, HttpServletResponse response) {
		checkEmailJson(email, response);
	}
	
	@RequiresPermissions("admin_local:v_depart_add")
	@RequestMapping(value = "/admin_local/v_depart_add.do")
	public String departTree(String root, HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		return departAdd(root, request, response, model);
	}

	private WebErrors validateSave(CmsUser bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		return errors;
	}

	private WebErrors validateEdit(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldExist(id, errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateUpdate(Integer id,Integer rank, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldExist(id, errors)) {
			return errors;
		}
		if (vldParams(id,rank, request, errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifEmpty(ids, "ids");
		for (Integer id : ids) {
			vldExist(id, errors);
		}
		return errors;
	}

	private boolean vldExist(Integer id, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		CmsUser entity = manager.findById(id);
		if (errors.ifNotExist(entity, CmsUser.class, id)) {
			return true;
		}
		return false;
	}

	private boolean vldParams(Integer id,Integer rank, HttpServletRequest request,
			WebErrors errors) {
		CmsUser user = CmsUtils.getUser(request);
		CmsUser entity = manager.findById(id);
		//提升等级大于当前登录用户
		if (rank > user.getRank()) {
			errors.addErrorCode("error.noPermissionToRaiseRank", id);
			return true;
		}
		//修改的用户等级大于当前登录用户 无权限
		if (entity.getRank() > user.getRank()) {
			errors.addErrorCode("error.noPermission", CmsUser.class, id);
			return true;
		}
		return false;
	}

	public void sendEmail(String text, String email){
		MailInfo mailInfo = new MailInfo();
        mailInfo.setMailServerHost("smtp.163.com");  
        mailInfo.setMailServerPort("25");  
        mailInfo.setValidate(true);  
        mailInfo.setUsername(PropertyUtils.getPropertyValue(new File(realPathResolver.get(EMAIL_PATH)), "username").trim());  
        mailInfo.setPassword(PropertyUtils.getPropertyValue(new File(realPathResolver.get(EMAIL_PATH)), "password").trim());// 您的邮箱密码  
        mailInfo.setFromAddress(PropertyUtils.getPropertyValue(new File(realPathResolver.get(EMAIL_PATH)), "fromAddress").trim());  
        mailInfo.setToAddress(email);  
        mailInfo.setSubject("中国中东欧国家智库交流与合作网络订阅推送");  
                  
        //附件  
//        String[] attachFileNames={"d:/Sunset.jpg"};  
//        mailInfo.setAttachFileNames(attachFileNames);  
          
        // 这个类主要来发送邮件  
        //mailInfo.setContent("设置邮箱内容");  
        //SimpleMail.sendTextMail(mailInfo);// 发送文体格式  
        StringBuffer demo = new StringBuffer();  
        demo.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">")  
        .append("<html>")  
        .append("<head>")  
        .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">")  
        .append("<title>中国中东欧国家智库交流与合作网络</title>")  
        .append("<style type=\"text/css\">")  
        .append(".test{font-family:\"Microsoft Yahei\";font-size: 13px;color: red;}")  
        .append("</style>")  
        .append("</head>")  
        .append("<body>")  
        .append("<span class=\"test\">")
        .append(text)
        .append("</span>")  
        .append("</body>")  
        .append("</html>");  
        mailInfo.setContent(demo.toString());  
        SimpleMail.sendHtmlMail(mailInfo);// 发送html格式  
	}
}