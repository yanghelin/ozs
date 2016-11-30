package com.jeecms.cms.action.meeting;

import static com.jeecms.common.page.SimplePage.cpn;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
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

/**
 * 所内会议Action
 */
@Controller
public class OutMeetingAct {
	private static final Logger log = LoggerFactory.getLogger(OutMeetingAct.class);

	@RequiresPermissions("out_meeting:list")
	@RequestMapping("/out_meeting/list.do")
	public String list(String meetingName,Integer pageNo,HttpServletRequest request, ModelMap model) {
		Pagination pagination = outMeetingMng.getPage(meetingName, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("meetingName", meetingName);
		return "meeting/out/list";
	}
	
	@RequiresPermissions("out_meeting:to_add")
	@RequestMapping("/out_meeting/to_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		/*CmsSite site = CmsUtils.getSite(request);
		CmsUser currUser = CmsUtils.getUser(request);
		List<CmsGroup> groupList = cmsGroupMng.getList();
		List<CmsRole> roleList = cmsRoleMng.getList();
		model.addAttribute("site", site);
		model.addAttribute("groupList", groupList);
		model.addAttribute("roleList", roleList);
		model.addAttribute("currRank", currUser.getRank());*/
		return "meeting/out/add";
	}
	
	@RequiresPermissions("out_meeting:save")
	@RequestMapping("/out_meeting/save.do")
	public String save(OutMeeting bean,Integer contentAttachmentId, Integer agendaAttachmentId, Integer invitationId, Integer relatedDataId, HttpServletRequest request,ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		/*WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}*/
		if(contentAttachmentId != null) {
			MeetingAttachment contentAttach = new MeetingAttachment();
			contentAttach.setId(contentAttachmentId);
			bean.setContentAttachment(contentAttach);
		}
		if(agendaAttachmentId != null) {
			MeetingAttachment agendaAttach = new MeetingAttachment();
			agendaAttach.setId(agendaAttachmentId);
			bean.setAgendaAttachment(agendaAttach);
		}
		if(invitationId != null) {
			MeetingAttachment invitationAttach = new MeetingAttachment();
			invitationAttach.setId(invitationId);
			bean.setInvitation(invitationAttach);
		}
		if(relatedDataId != null) {
			MeetingAttachment relatedDataAttach = new MeetingAttachment();
			relatedDataAttach.setId(relatedDataId);
			bean.setRelatedData(relatedDataAttach);
		}
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setPublisher(currUser);
		bean.setPublishTime(new Date());
		bean.setIsDelete((byte)0);
		bean.setType(0);
		outMeetingMng.saveOutMeeting(bean);
		log.info("save OutMeeting id={}", bean.getId());
		//cmsLogMng.operating(request, "cmsUser.log.save", "id=" + bean.getId() + ";username=" + bean.getUsername());
		return "redirect:list.do";
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

	
	//上传附件
	@RequiresPermissions("out_meeting:content_upload")
	@RequestMapping("/out_meeting/content_upload.do")
	public void contentUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile contentFile) throws Exception {
		log.debug("进入upload方法：上传文件完成，开始执行文件保存。");
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html;chartset=UTF-8");
		CmsSite site = CmsUtils.getSite(request);
		String ctx = request.getContextPath();
		JSONObject json = new JSONObject();
		try {
			if(contentFile.isEmpty()){  
				log.error("上传文件为空，请重新上传。");
            }else if(contentFile.getSize()>FILE_MAX_SIZE){
            	log.error("文件超过100M大小，请重新上传。");
            }else {
        		String origName = contentFile.getOriginalFilename();
    			String ext = FilenameUtils.getExtension(origName).toLowerCase(
    					Locale.ENGLISH);
    			String fileUrl = fileRepository.storeByExt(site.getUploadPath(), ext, contentFile);
				// 加上部署路径
				fileUrl = ctx + fileUrl;
				MeetingAttachment attach = new MeetingAttachment();
				attach.setFilename(origName);
				attach.setIsDelete((byte)0);
				attach.setType(1);//'附件类型：1、国际会议-会议内容；2、国际会议-会议日程；3、国际会议-会议邀请函；4、国际会议-会议相关资料；5、速记/媒体报名-其他资料；6、所内会议-会议资料；
				attach.setPath(fileUrl);
				attach.setCreateTime(new Date());
				MeetingAttachment bean = meetingAttachmentMng.saveMeetingAttachment(attach);
        		
				json.put("code", 200);
				json.put("busId", bean.getId());
				json.put("fileName", java.net.URLEncoder.encode(origName,"UTF-8"));
				writer.write(json.toString());
        		log.debug("文件保存完成！");
            }
		}catch (Exception e) {
			log.error("文件上传失败！", e);
		}finally{
			writer.flush();
			writer.close();
		}
		log.debug("文件上传成功，返回前台页面！");
	}

	@RequiresPermissions("out_meeting:agenda_upload")
	@RequestMapping("/out_meeting/agenda_upload.do")
	public void agendaUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile agendaFile) throws Exception {
		log.debug("进入upload方法：上传文件完成，开始执行文件保存。");
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html;chartset=UTF-8");
		CmsSite site = CmsUtils.getSite(request);
		String ctx = request.getContextPath();
		JSONObject json = new JSONObject();
		try {
			if(agendaFile.isEmpty()){  
				log.error("上传文件为空，请重新上传。");
            }else if(agendaFile.getSize()>FILE_MAX_SIZE){
            	log.error("文件超过100M大小，请重新上传。");
            }else {
        		String origName = agendaFile.getOriginalFilename();
    			String ext = FilenameUtils.getExtension(origName).toLowerCase(
    					Locale.ENGLISH);
    			String fileUrl = fileRepository.storeByExt(site.getUploadPath(), ext, agendaFile);
				// 加上部署路径
				fileUrl = ctx + fileUrl;
				MeetingAttachment attach = new MeetingAttachment();
				attach.setFilename(origName);
				attach.setIsDelete((byte)0);
				attach.setType(2);//'附件类型：1、国际会议-会议内容；2、国际会议-会议日程；3、国际会议-会议邀请函；4、国际会议-会议相关资料；5、速记/媒体报名-其他资料；6、所内会议-会议资料；
				attach.setPath(fileUrl);
				attach.setCreateTime(new Date());
				MeetingAttachment bean = meetingAttachmentMng.saveMeetingAttachment(attach);
        		
				json.put("code", 200);
				json.put("busId", bean.getId());
				json.put("fileName", java.net.URLEncoder.encode(origName,"UTF-8"));
				writer.write(json.toString());
        		log.debug("文件保存完成！");
            }
		}catch (Exception e) {
			log.error("文件上传失败！", e);
		}finally{
			writer.flush();
			writer.close();
		}
		log.debug("文件上传成功，返回前台页面！");
	}
	
	@RequiresPermissions("out_meeting:invitation_upload")
	@RequestMapping("/out_meeting/invitation_upload.do")
	public void invitationUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile invitationFile) throws Exception {
		log.debug("进入upload方法：上传文件完成，开始执行文件保存。");
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html;chartset=UTF-8");
		CmsSite site = CmsUtils.getSite(request);
		String ctx = request.getContextPath();
		JSONObject json = new JSONObject();
		try {
			if(invitationFile.isEmpty()){  
				log.error("上传文件为空，请重新上传。");
            }else if(invitationFile.getSize()>FILE_MAX_SIZE){
            	log.error("文件超过100M大小，请重新上传。");
            }else {
        		String origName = invitationFile.getOriginalFilename();
    			String ext = FilenameUtils.getExtension(origName).toLowerCase(
    					Locale.ENGLISH);
    			String fileUrl = fileRepository.storeByExt(site.getUploadPath(), ext, invitationFile);
				// 加上部署路径
				fileUrl = ctx + fileUrl;
				MeetingAttachment attach = new MeetingAttachment();
				attach.setFilename(origName);
				attach.setIsDelete((byte)0);
				attach.setType(3);//'附件类型：1、国际会议-会议内容；2、国际会议-会议日程；3、国际会议-会议邀请函；4、国际会议-会议相关资料；5、速记/媒体报名-其他资料；6、所内会议-会议资料；
				attach.setPath(fileUrl);
				attach.setCreateTime(new Date());
				MeetingAttachment bean = meetingAttachmentMng.saveMeetingAttachment(attach);
        		
				json.put("code", 200);
				json.put("busId", bean.getId());
				json.put("fileName", java.net.URLEncoder.encode(origName,"UTF-8"));
				writer.write(json.toString());
        		log.debug("文件保存完成！");
            }
		}catch (Exception e) {
			log.error("文件上传失败！", e);
		}finally{
			writer.flush();
			writer.close();
		}
		log.debug("文件上传成功，返回前台页面！");
	}
	
	@RequiresPermissions("out_meeting:related_data_upload")
	@RequestMapping("/out_meeting/related_data_upload.do")
	public void relatedDataUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile relatedDataFile) throws Exception {
		log.debug("进入upload方法：上传文件完成，开始执行文件保存。");
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html;chartset=UTF-8");
		CmsSite site = CmsUtils.getSite(request);
		String ctx = request.getContextPath();
		JSONObject json = new JSONObject();
		try {
			if(relatedDataFile.isEmpty()){  
				log.error("上传文件为空，请重新上传。");
            }else if(relatedDataFile.getSize()>FILE_MAX_SIZE){
            	log.error("文件超过100M大小，请重新上传。");
            }else {
        		String origName = relatedDataFile.getOriginalFilename();
    			String ext = FilenameUtils.getExtension(origName).toLowerCase(
    					Locale.ENGLISH);
    			String fileUrl = fileRepository.storeByExt(site.getUploadPath(), ext, relatedDataFile);
				// 加上部署路径
				fileUrl = ctx + fileUrl;
				MeetingAttachment attach = new MeetingAttachment();
				attach.setFilename(origName);
				attach.setIsDelete((byte)0);
				attach.setType(4);//'附件类型：1、国际会议-会议内容；2、国际会议-会议日程；3、国际会议-会议邀请函；4、国际会议-会议相关资料；5、速记/媒体报名-其他资料；6、所内会议-会议资料；
				attach.setPath(fileUrl);
				attach.setCreateTime(new Date());
				MeetingAttachment bean = meetingAttachmentMng.saveMeetingAttachment(attach);
        		
				json.put("code", 200);
				json.put("busId", bean.getId());
				json.put("fileName", java.net.URLEncoder.encode(origName,"UTF-8"));
				writer.write(json.toString());
        		log.debug("文件保存完成！");
            }
		}catch (Exception e) {
			log.error("文件上传失败！", e);
		}finally{
			writer.flush();
			writer.close();
		}
		log.debug("文件上传成功，返回前台页面！");
	}
	
	@Autowired
	private OutMeetingMng outMeetingMng;
	
	@Autowired
	private MeetingAttachmentMng meetingAttachmentMng;
	
	@Autowired
	protected FileRepository fileRepository;
	
	private final Integer FILE_MAX_SIZE = 104857600;//100M大小
}
