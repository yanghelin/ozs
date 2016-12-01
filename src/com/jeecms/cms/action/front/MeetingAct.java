package com.jeecms.cms.action.front;

import static com.jeecms.cms.Constants.TPLDIR_MEETING;
import static com.jeecms.common.page.SimplePage.cpn;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jeecms.cms.entity.assist.CmsAdvertising;
import com.jeecms.cms.entity.meeting.MeetingAttachment;
import com.jeecms.cms.entity.meeting.OutMeeting;
import com.jeecms.cms.manager.meeting.MeetingAttachmentMng;
import com.jeecms.cms.manager.meeting.OutMeetingMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.upload.FileRepository;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
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
		model.addAttribute("meeting",meeting);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEETING, MEETING_SHOW);
	}
	
	@RequestMapping("/meeting/show2.jspx")
	public String show2(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
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
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEETING, MEETING_SHOW2);
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
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEETING, MEETING_EROLL);
	}
	
	@RequestMapping("/meeting/downloadFile.jspx")
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
	
	@RequiresPermissions("out_meeting:delete")
	@RequestMapping("/out_meeting/delete.do")
	public String delete(OutMeeting bean,HttpServletRequest request,ModelMap model) {
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setUpdateBy(currUser);
		bean.setUpdateTime(new Date());
		bean.setIsDelete((byte)1);
		outMeetingMng.updateOutMeeting(bean);
		return "redirect:list.do";
	}
	
	@RequiresPermissions("meeting:detail")
	@RequestMapping("/meeting/detail.do")
	public String meetingDetail(Integer meetingId, HttpServletRequest request, ModelMap model) {
		if(meetingId == null) {
			meetingId = null;
		}
		outMeetingMng.findById(meetingId);
		return "meeting/out/list";
	}
	
	@Autowired
	private OutMeetingMng outMeetingMng;
	
	@Autowired
	private MeetingAttachmentMng meetingAttachmentMng;
	
	@Autowired
	protected FileRepository fileRepository;
	
	private final Integer FILE_MAX_SIZE = 104857600;//100M大小
}
