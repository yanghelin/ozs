package com.jeecms.cms.action.front;

import static com.jeecms.cms.Constants.TPLDIR_MEETING;
import static com.jeecms.common.page.SimplePage.cpn;
import static org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;

import java.io.File;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.cms.entity.meeting.OutMeeting;
import com.jeecms.cms.entity.meeting.OutMeetingEroll;
import com.jeecms.cms.manager.meeting.OutMeetingErollMng;
import com.jeecms.cms.manager.meeting.OutMeetingMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.WebErrors;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

/**
 * 所内会议Action
 */
@Controller
public class MeetingAct {
	private static final Logger log = LoggerFactory.getLogger(MeetingAct.class);

	public static final String MEETING_SHOW = "tpl.meeting.show";
	public static final String MEETING_SHOW2 = "tpl.meeting.show2";
	public static final String MEETING_EROLL = "tpl.meeting.eroll";
	public static final String MEETING_SUCCESS = "tpl.meeting.success";
	
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
		if(meeting == null) {
			WebErrors errors=WebErrors.create(request);
			errors.addError("error.meeting.nomeetingfind");
			return FrontUtils.showError(request, response, model, errors);
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
		model.addAttribute("muserType",currUser.getAttr().get("muser_type"));
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEETING, MEETING_EROLL);
	}
	
	@RequestMapping(value = "/meeting/erollSave.jspx", method = RequestMethod.POST)
	public String submit(OutMeetingEroll eroll, HttpServletRequest request,Integer meetingId, String inGoTimePage, String inBackTimePage, String outGoTimePage, String outBackTimePage, ModelMap model) {
		Object error = request.getAttribute(DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if (error != null) {
			model.addAttribute("error",error);
			//model.addAttribute("errorRemaining", unifiedUserMng.errorRemaining(username));
		}
		CmsUser currUser = CmsUtils.getUser(request);
		
		eroll.setUserType(changeUserAttr(currUser.getAttr().get("muser_type")));
		eroll.setLoginId(currUser);
		eroll.setLoginName(currUser.getUsername());
		eroll.setIsDelete((byte)0);
		eroll.setIsDeleteStay((byte)0);
		eroll.setIsDeleteTicket((byte)0);
		eroll.setCreateTime(new Date());
		
		OutMeeting meeting = new OutMeeting();
		meeting.setId(meetingId);
		eroll.setOutMeetingId(meeting);
		
		if(StringUtils.isNotBlank(inGoTimePage)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
			try {
				Date date = format.parse(inGoTimePage);
				eroll.setInGoTime(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(StringUtils.isNotBlank(inBackTimePage)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
			try {
				Date date = format.parse(inBackTimePage);
				eroll.setInBackTime(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(StringUtils.isNotBlank(outGoTimePage)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
			try {
				Date date = format.parse(outGoTimePage);
				eroll.setOutGoTime(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(StringUtils.isNotBlank(outBackTimePage)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
			try {
				Date date = format.parse(outBackTimePage);
				eroll.setOutBackTime(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		erollMng.saveOutMeetingEroll(eroll);
		return "redirect:/meeting/success.jspx";
		//FrontUtils.frontData(request, model, site);
		//return FrontUtils.getTplPath(request, sol, TPLDIR_MEETING, MEETING_SUCCESS);
	}
	
	@RequestMapping("/meeting/success.jspx")
	public String success(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		String sol = site.getSolutionPath();
		CmsUser currUser = CmsUtils.getUser(request);
		if(currUser == null) {
			return "redirect:/zh/login.jspx";
		}
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, sol, TPLDIR_MEETING, MEETING_SUCCESS);
	}
	
	@RequestMapping("/meeting/downloadFile.jspx")
	@ResponseBody
	public void downloadFile(HttpServletRequest request, HttpServletResponse response){
		java.io.FileInputStream fileInputStream = null;
		javax.servlet.ServletOutputStream out = null;
		try {
			response.setContentType("text/html");
			out = response.getOutputStream();
			String filepath = new String(request.getParameter("downloadFilePath").getBytes(Charset.forName("ISO-8859-1")), "GB2312").toString();
			String[] paths = filepath.split("/");
			StringBuffer newFilePath = new StringBuffer("");
			if(paths.length >0) {
				int pathsLength = paths.length;
				for(int i=0; i<pathsLength; i++) {
					newFilePath.append(paths[i]+File.separator);
				}
			}
			//组装下载路径
		    filepath = request.getSession().getServletContext().getRealPath(File.separator)+ newFilePath.toString();
		    String filename = request.getParameter("downloadFileName");
			java.io.File file = new java.io.File(filepath + filename);
			if (!file.exists()) {
				log.info(file.getAbsolutePath() + " 文件不存在!");
				return;
			}
			// 读取文件流
			fileInputStream = new java.io.FileInputStream(file);
			// 下载文件
			// 设置响应头和下载保存的文件名
			if (filename != null && filename.length() > 0) {
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-Disposition", "attachment; filename=" + new String(filename.getBytes("gb2312"),Charset.forName("ISO-8859-1")) + "");
				if (fileInputStream != null) {
					IOUtils.copy(fileInputStream, out);
				}
			}
		} catch (Exception e) {
			log.error("file download exception" , e);
		}finally{
			IOUtils.closeQuietly(fileInputStream);
			IOUtils.closeQuietly(out);
		}

	}
	
	//转换用户类型属性
	public Byte changeUserAttr(String attrValue) {
		Byte value = 0;
		if(StringUtils.isNotEmpty(attrValue)) {
			if(attrValue.equals("参会人员")) {
				value = 1;
			}else if(attrValue.equals("速记")) {
				value = 2;
			}else if(attrValue.equals("媒体")) {
				value = 3;
			}else{
				value = 0;
			}
		}
		return value;
	}
	
	
	@Autowired
	private OutMeetingMng outMeetingMng;
	
	@Autowired
	private OutMeetingErollMng erollMng;
	
}
