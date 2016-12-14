package com.jeecms.cms.action.meeting;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jeecms.cms.entity.meeting.MeetingAttachment;
import com.jeecms.cms.entity.meeting.OutMeeting;
import com.jeecms.cms.entity.meeting.OutMeetingEroll;
import com.jeecms.cms.manager.meeting.MeetingAttachmentMng;
import com.jeecms.cms.manager.meeting.OutMeetingErollMng;
import com.jeecms.cms.manager.meeting.OutMeetingMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.upload.FileRepository;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;

/**
 * 所内会议Action
 * 0、参会人员	participant
 * 1、发言嘉宾	speaker
 * 2、速记	stenographer
 * 3、媒体	media
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
		CmsUser currUser = CmsUtils.getUser(request);
		model.addAttribute("auth", currUser.getUserMenu("out"));
		return "meeting/out/list";
	}
	
	@RequiresPermissions("out_meeting:to_add")
	@RequestMapping("/out_meeting/to_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		model.addAttribute("isCn", 1);
		return "meeting/out/add";
	}

	@RequiresPermissions("out_meeting:to_add_en")
	@RequestMapping("/out_meeting/to_add_en.do")
	public String enAdd(HttpServletRequest request, ModelMap model) {
		model.addAttribute("isCn", 0);
		return "meeting/out/add";
	}
	
	@RequiresPermissions("out_meeting:to_edit")
	@RequestMapping("/out_meeting/to_edit.do")
	public String edit(Integer id, HttpServletRequest request, ModelMap model) {
		OutMeeting meeting = outMeetingMng.findById(id);
		model.addAttribute("meeting", meeting);
		return "meeting/out/edit";
	}
	
	@RequiresPermissions("out_meeting:save")
	@RequestMapping("/out_meeting/save.do")
	public String save(OutMeeting bean,Integer contentAttachmentId, Integer agendaAttachmentId, Integer invitationId, Integer relatedDataId, HttpServletRequest request,ModelMap model) {
		/*CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateSave(bean, request);
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
	
	@RequiresPermissions("out_meeting:update")
	@RequestMapping("/out_meeting/update.do")
	public String update(OutMeeting bean,Integer contentAttachmentId, Integer agendaAttachmentId, Integer invitationId, Integer relatedDataId, HttpServletRequest request,ModelMap model) {
		/*CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateSave(bean, request);
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
		bean.setUpdateBy(currUser);
		bean.setUpdateTime(new Date());
		outMeetingMng.updateOutMeeting(bean);
		log.info("update OutMeeting id={}", bean.getId());
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
	
	@RequiresPermissions("out_meeting:view")
	@RequestMapping("/out_meeting/view.do")
	public String view(Integer id,HttpServletRequest request,ModelMap model) {
		OutMeeting outMeeting = null;
		if(id != null) {
			outMeeting = outMeetingMng.findById(id);
		}
		model.addAttribute("meeting", outMeeting);
		return "meeting/out/view";
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
	
	
	
	
	//===================================【查看会议报名】和【速记和媒体信息管理】===============================================
	// userType=0 查看会议报名；userType=null 速记和媒体信息管理
	@RequiresPermissions("out_meeting:enroll_list")
	@RequestMapping("/out_meeting/enroll_list.do")
	public String enrollList(String meetingName,String userType,Integer pageNo, HttpServletRequest request,ModelMap model) {
		
		Pagination pagination = enrollMng.getPage(meetingName, userType, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("meetingName", meetingName);
		model.addAttribute("userType", userType);
		if(StringUtils.isNotBlank(userType)) {
			CmsUser currUser = CmsUtils.getUser(request);
			model.addAttribute("auth", currUser.getUserMenu("enroll"));
		}else {
			CmsUser currUser = CmsUtils.getUser(request);
			model.addAttribute("auth", currUser.getUserMenu("media"));
		}
		return "meeting/out/enrollList";
	}
	
	// 会议报名删除
	@RequiresPermissions("out_meeting:enroll_delete")
	@RequestMapping("/out_meeting/enroll_delete.do")
	public String enrollDelete(OutMeetingEroll bean, HttpServletRequest request,ModelMap model) {
		bean.setIsDelete((byte)1);
		enrollMng.updateOutMeetingEroll(bean);
		return "redirect:eroll_list.do?userType=0";
	}
	
	// 媒体报名删除
	@RequiresPermissions("out_meeting:media_delete")
	@RequestMapping("/out_meeting/media_delete.do")
	public String mediaDelete(OutMeetingEroll bean, HttpServletRequest request,ModelMap model) {
		bean.setIsDelete((byte)1);
		enrollMng.updateOutMeetingEroll(bean);
		return "redirect:eroll_list.do";
	}
	
	@RequiresPermissions("out_meeting:to_addMedia")
	@RequestMapping("/out_meeting/to_addMedia.do")
	public String addMedia(HttpServletRequest request, ModelMap model) {
		return "meeting/out/mediaAdd";
	}
	
	@RequiresPermissions("out_meeting:mediaSave")
	@RequestMapping("/out_meeting/mediaSave.do")
	public String mediaSave(OutMeetingEroll bean,Integer otherId, HttpServletRequest request,ModelMap model) {
		if(otherId != null) {
			MeetingAttachment other = new MeetingAttachment();
			other.setId(otherId);
			bean.setOther(other);
		}
		//CmsUser currUser = CmsUtils.getUser(request);
		//bean.setLoginId(currUser);
		//bean.setLoginName(currUser.getUsername());
		bean.setCreateTime(new Date());
		bean.setIsDelete((byte)0);
		//bean.setUserType(changeUserAttr(currUser.getAttr().get("muser_type")));
		enrollMng.saveOutMeetingEroll(bean);
		log.info("save OutMeetingEroll id={}", bean.getId());
		return "redirect:eroll_list.do";
	}
	
	/**
	 * 
	 * @param id
	 * @param showType	显示类型:不传参数是【查看报名信息】和【速记媒体信息管理】；1、机票管理
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("out_meeting:enroll_view")
	@RequestMapping("/out_meeting/enroll_view.do")
	public String enrollView(Integer id, String showType, HttpServletRequest request,ModelMap model) {
		OutMeetingEroll enroll = null;
		if(id != null) {
			enroll = enrollMng.findById(id);
		}
		model.addAttribute("out", enroll);
		model.addAttribute("showType", showType);
		return "meeting/out/enrollView";
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
	
	@RequiresPermissions("out_meeting:other_upload")
	@RequestMapping("/out_meeting/other_upload.do")
	public void otherUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile otherFile) throws Exception {
		log.debug("进入upload方法：上传文件完成，开始执行文件保存。");
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html;chartset=UTF-8");
		CmsSite site = CmsUtils.getSite(request);
		String ctx = request.getContextPath();
		JSONObject json = new JSONObject();
		try {
			if(otherFile.isEmpty()){  
				log.error("上传文件为空，请重新上传。");
            }else if(otherFile.getSize()>FILE_MAX_SIZE){
            	log.error("文件超过100M大小，请重新上传。");
            }else {
        		String origName = otherFile.getOriginalFilename();
    			String ext = FilenameUtils.getExtension(origName).toLowerCase(
    					Locale.ENGLISH);
    			String fileUrl = fileRepository.storeByExt(site.getUploadPath(), ext, otherFile);
				// 加上部署路径
				fileUrl = ctx + fileUrl;
				MeetingAttachment attach = new MeetingAttachment();
				attach.setFilename(origName);
				attach.setIsDelete((byte)0);
				attach.setType(5);//'附件类型：1、国际会议-会议内容；2、国际会议-会议日程；3、国际会议-会议邀请函；4、国际会议-会议相关资料；5、速记/媒体报名-其他资料；6、所内会议-会议资料；
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
	
	@RequiresPermissions("out_meeting:download")
	@RequestMapping("/out_meeting/download.do")
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
	
	
	//=======================住宿、机票、车辆管理================================
	//type：1、住宿管理；2、机票管理；3、车辆管理
	@RequiresPermissions("out_meeting:stay_list")
	@RequestMapping("/out_meeting/stay_list.do")
	public String stayList(String meetingName, Integer pageNo, HttpServletRequest request,ModelMap model) {
		
		Pagination pagination = enrollMng.getPage(meetingName, 1, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("meetingName", meetingName);
		CmsUser currUser = CmsUtils.getUser(request);
		model.addAttribute("auth", currUser.getUserMenu("stay"));
		return "meeting/out/stayList";
	}
	
	@RequiresPermissions("out_meeting:ticket_list")
	@RequestMapping("/out_meeting/ticket_list.do")
	public String ticketList(String meetingName, Integer pageNo, HttpServletRequest request,ModelMap model) {
		
		Pagination pagination = enrollMng.getPage(meetingName, 2, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("meetingName", meetingName);
		CmsUser currUser = CmsUtils.getUser(request);
		model.addAttribute("auth", currUser.getUserMenu("ticket"));
		return "meeting/out/ticketList";
	}
	
	@RequiresPermissions("out_meeting:car_list")
	@RequestMapping("/out_meeting/car_list.do")
	public String carList(String meetingName, Integer pageNo, HttpServletRequest request,ModelMap model) {
		
		Pagination pagination = enrollMng.getPage(meetingName, 3, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("meetingName", meetingName);
		return "meeting/out/carList";
	}
	@RequiresPermissions("out_meeting:stay_delete")
	@RequestMapping("/out_meeting/stay_delete.do")
	public String stayDelete(OutMeetingEroll bean,HttpServletRequest request,ModelMap model) {
		bean.setIsDeleteStay((byte)1);
		enrollMng.updateOutMeetingEroll(bean);
		return "redirect:stayList.do";
	}
	@RequiresPermissions("out_meeting:ticket_delete")
	@RequestMapping("/out_meeting/ticket_delete.do")
	public String ticketDelete(OutMeetingEroll bean,HttpServletRequest request,ModelMap model) {
		bean.setIsDeleteTicket((byte)1);
		enrollMng.updateOutMeetingEroll(bean);
		return "redirect:ticketList.do";
	}
	@Autowired
	private OutMeetingMng outMeetingMng;
	
	@Autowired
	private OutMeetingErollMng enrollMng;
	
	@Autowired
	private MeetingAttachmentMng meetingAttachmentMng;
	
	@Autowired
	protected FileRepository fileRepository;
	
	private final Integer FILE_MAX_SIZE = 104857600;//100M大小
}
