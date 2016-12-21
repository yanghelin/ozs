package com.jeecms.cms.action.meeting;

import static com.jeecms.common.page.SimplePage.cpn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
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

import com.jeecms.cms.entity.meeting.InMeeting;
import com.jeecms.cms.entity.meeting.InMeetingBriefing;
import com.jeecms.cms.entity.meeting.MeetingAttachment;
import com.jeecms.cms.manager.meeting.InMeetingBriefingMng;
import com.jeecms.cms.manager.meeting.InMeetingMng;
import com.jeecms.cms.manager.meeting.MeetingAttachmentMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.upload.FileRepository;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;

/**
 * 工作简报Action
 */
@Controller
public class InMeetingBriefingAct {
	private static final Logger log = LoggerFactory.getLogger(InMeetingBriefingAct.class);

	@RequiresPermissions("in_meeting_briefing:list")
	@RequestMapping("/in_meeting_briefing/list.do")
	public String list(String meetingName,Integer pageNo,HttpServletRequest request, ModelMap model) {
		/*CmsSite site = CmsUtils.getSite(request);
		*/
		Pagination pagination = inMeetingBriefingMng.getPage(meetingName, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("meetingName", meetingName);
		CmsUser currUser = CmsUtils.getUser(request);
		model.addAttribute("auth", currUser.getUserMenu("briefing"));
		return "meeting/in/briefingList";
	}
	
	@RequiresPermissions("in_meeting_briefing:to_add")
	@RequestMapping("/in_meeting_briefing/to_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		List<InMeeting> meetingList = meetingMng.getList(null);
		model.addAttribute("meetingList", meetingList);
		return "meeting/in/briefingAdd";
	}
	
	@RequiresPermissions("in_meeting_briefing:save")
	@RequestMapping("/in_meeting_briefing/save.do")
	public String save(InMeetingBriefing bean, Integer meeting_id, HttpServletRequest request,ModelMap model) {
		/*CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}*/
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setCreateBy(currUser);
		bean.setCreateTime(new Date());
		bean.setIsDelete((byte)0);
		
		InMeeting meeting = new InMeeting();
		meeting.setId(meeting_id);
		bean.setMeetingId(meeting);
		inMeetingBriefingMng.saveInMeetingBriefing(bean);
		log.info("save InMeetingBriefing id={}", bean.getId());
		return "redirect:list.do";
	}
	
	@RequiresPermissions("in_meeting_briefing:delete")
	@RequestMapping("/in_meeting_briefing/delete.do")
	public String delete(InMeetingBriefing bean,HttpServletRequest request,ModelMap model) {
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setIsDelete((byte)1);
		bean.setUpdateBy(currUser);
		bean.setUpdateTime(new Date());
		inMeetingBriefingMng.updateInMeetingBriefing(bean);
		return "redirect:list.do";
	}

	//上传附件
	@RequiresPermissions("in_meeting_briefing:upload")
	@RequestMapping("/in_meeting_briefing/upload.do")
	public void contentUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile attachmentFile) throws Exception {
		log.debug("进入upload方法：上传文件完成，开始执行文件保存。");
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html;chartset=UTF-8");
		CmsSite site = CmsUtils.getSite(request);
		String ctx = request.getContextPath();
		JSONObject json = new JSONObject();
		try {
			if(attachmentFile.isEmpty()){  
				log.error("上传文件为空，请重新上传。");
            }else if(attachmentFile.getSize()>FILE_MAX_SIZE){
            	log.error("文件超过100M大小，请重新上传。");
            }else {
        		String origName = attachmentFile.getOriginalFilename();
    			String ext = FilenameUtils.getExtension(origName).toLowerCase(
    					Locale.ENGLISH);
    			String fileUrl = fileRepository.storeByExt(site.getUploadPath(), ext, attachmentFile);
				// 加上部署路径
				fileUrl = ctx + fileUrl;
				MeetingAttachment attach = new MeetingAttachment();
				attach.setFilename(origName);
				attach.setIsDelete((byte)0);
				attach.setType(7);//'附件类型：1、国际会议-会议内容；2、国际会议-会议日程；3、国际会议-会议邀请函；4、国际会议-会议相关资料；5、速记/媒体报名-其他资料；6、所内会议-会议资料；7、所内会议-简报资料';
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
	
	@RequiresPermissions("in_meeting_briefing:download")
	@RequestMapping("/in_meeting_briefing/download.do")
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
	
	@RequiresPermissions("in_meeting_briefing:zip")
	@RequestMapping("/in_meeting_briefing/zip.do")
	@ResponseBody
	public void downloadZipFile(HttpServletRequest request, HttpServletResponse response){
		java.io.FileInputStream fileInputStream = null;
		javax.servlet.ServletOutputStream out = null;
		List<MeetingAttachment> attachList = null;
		List<File> fileList = null;
		String tempFilePath = "";
		try {
			String id = request.getParameter("id");
			if(StringUtils.isNotBlank(id)) {
				Integer bid = Integer.valueOf(id);
				InMeetingBriefing briefing = inMeetingBriefingMng.findById(bid);
				if(briefing != null && StringUtils.isNotBlank(briefing.getAttachment())) {
					attachList = meetingAttachmentMng.findByIds(briefing.getAttachment());
				}
			}
			if(attachList != null) {
				fileList = new ArrayList<File>();
				for(int i=0; i<attachList.size(); i++) {
					MeetingAttachment attach = attachList.get(i);
					String allPath = transformPath(request, attach.getPath());
					File file = new File(allPath);
					if (!file.exists()) {
						log.info(file.getAbsolutePath() + " 文件不存在!");
						return;
					}
					fileList.add(file);
					if(i==0) {
						tempFilePath = allPath;
					}
				}
			}
			
			
			if(StringUtils.isNotBlank(tempFilePath)) {
				tempFilePath = tempFilePath.substring(0, tempFilePath.lastIndexOf(".")+1)+"zip";
				ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(tempFilePath));
				byte[] buffer = new byte[1024];
				for(int i=0; i<fileList.size(); i++) {
					File file = fileList.get(i);
					FileInputStream fis = new FileInputStream(fileList.get(i));
					zipOut.putNextEntry(new ZipEntry(file.getName()));
					//设置压缩文件内的字符编码，不然会变成乱码 
					zipOut.setEncoding("GBK");  
					int len;
					// 读入需要下载的文件的内容，打包到zip文件 
					while ((len = fis.read(buffer)) > 0) {
						zipOut.write(buffer, 0, len);
					}
					zipOut.closeEntry();  
					fis.close();
				}
			}
			
			response.setContentType("text/html");
			out = response.getOutputStream();
			
			
			// 读取文件流
			fileInputStream = new java.io.FileInputStream(tempFilePath);
			// 下载文件
			// 设置响应头和下载保存的文件名
			String tempFileName = "简报.zip";
			if (tempFileName != null && tempFileName.length() > 0) {
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-Disposition", "attachment; filename=" + new String(tempFileName.getBytes("gb2312"),Charset.forName("ISO-8859-1")) + "");
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
	
	//转换路径并生成全路径
	private String transformPath(HttpServletRequest request, String filePath) {
		String[] paths = filePath.split("/");
		StringBuffer newFilePath = new StringBuffer("");
		if(paths.length >0) {
			int pathsLength = paths.length;
			for(int i=0; i<pathsLength; i++) {
				newFilePath.append(paths[i]+File.separator);
			}
		}
		//组装下载路径
		filePath = request.getSession().getServletContext().getRealPath(File.separator)+ newFilePath.toString();
		return filePath;
	}
	
	@Autowired
	private InMeetingBriefingMng inMeetingBriefingMng;
	
	@Autowired
	private InMeetingMng meetingMng;
	
	@Autowired
	private MeetingAttachmentMng meetingAttachmentMng;
	
	@Autowired
	protected FileRepository fileRepository;
	
	private final Integer FILE_MAX_SIZE = 104857600;//100M大小
}
