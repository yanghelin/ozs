package com.jeecms.cms.action.admin;

import static com.jeecms.common.page.SimplePage.cpn;
import static org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.action.admin.main.CmsAdminLocalAct;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.security.encoder.Md5PwdEncoder;
import com.jeecms.common.util.PropertyUtils;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.common.web.freemarker.UUIDDirective;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.CmsMemberMeeting;
import com.jeecms.core.entity.CmsRegistMeeting;
import com.jeecms.core.entity.CmsRegistUser;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsSubscibeEmail;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsRegistMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.manager.ConfigMng;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.core.web.util.CmsUtils;

@Controller
public class CmsLoginAct {
	private static final Logger log = LoggerFactory.getLogger(CmsLoginAct.class);
	/**
	 * 站点id cookie
	 */
	public static final String SITE_COOKIE = "_site_id_cookie";
	@Autowired
	protected CmsRegistMng cmsRegistMng;
	@Autowired
	private SessionProvider session;
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String input(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		//可能进入没有权限子站(cookie记录站点)需要清除cookie
		CookieUtils.cancleCookie(request, response, SITE_COOKIE, null);
		Integer errorTimes=configMng.getConfigLogin().getErrorTimes();
		model.addAttribute("errorTimes", errorTimes);
		return "login";
	}
	
	@RequestMapping(value = "/memberLoginOut.do", method = RequestMethod.GET)
	public String memberInput(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		//需要清除cookie
		Cookie[]  cookies=(Cookie[]) request.getCookies();
		for(Cookie cookie : cookies){
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		return "memberLogin";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String submit(String username, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Object error = request.getAttribute(DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if (error != null) {
			model.addAttribute("error", error);
			Integer errorRemaining= unifiedUserMng.errorRemaining(username);
			model.addAttribute("errorRemaining", errorRemaining);
		}
		return "login";
	}
	@RequestMapping("/add_member_regist.do")
	public void addRegistList(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		CmsRegistUser cmsRegistUser = new CmsRegistUser();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			Md5PwdEncoder md5 = new Md5PwdEncoder();
			String md5Password = md5.encodePassword(request.getParameter("password"));
			int size = cmsRegistMng.selectByUserName(request.getParameter("userName"));
			if(size == 0){
				cmsRegistUser.setUserName(request.getParameter("userName"));
				cmsRegistUser.setPassword(md5Password);
				cmsRegistUser.setUserMail(request.getParameter("userMail"));
				cmsRegistUser.setUserCompany(request.getParameter("userCompany"));
				cmsRegistUser.setUserPosition(request.getParameter("userPosition"));
				/*cmsRegistUser.setUserEducation(request.getParameter("userEducation"));*/
				cmsRegistUser.setUserTitle(request.getParameter("userTitle"));
				cmsRegistMng.add(cmsRegistUser);
				out.print("success");
				out.flush();
				out.close();
			}else{
				out.print("exist");
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("注册会员插入数据失败");
			try {
				response.getWriter().print("fail");
				out.flush();
				out.close();
			} catch (IOException e1) {
				e.printStackTrace();
				log.error("注册失败返回信息失败");
			}
		} 
	}
	@RequestMapping("/add_email.do")
	public void addEmail(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		CmsSubscibeEmail cmsMail = new CmsSubscibeEmail();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			int size = cmsRegistMng.selectByEmail(request.getParameter("userEmail"));
			if(size == 0){
				cmsMail.setEmail(request.getParameter("userEmail"));
				cmsRegistMng.addEmail(cmsMail);
				out.print("success");
				out.flush();
				out.close();
			}else{
				out.print("exist");
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("订阅邮箱插入数据失败");
			try {
				response.getWriter().print("fail");
				out.flush();
				out.close();
			} catch (IOException e1) {
				e.printStackTrace();
				log.error("订阅失败返回信息失败");
			}
		}
		
	}
	
	@RequestMapping("/rememberLogin.do")
	public String rememberLogin(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		//需要清除cookie
		Cookie[]  cookies=(Cookie[]) request.getCookies();
		for(Cookie cookie : cookies){
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		}
		return "memberLogin";
	}
	
	@RequestMapping("/checkLogin.do")
	public String checkLogin(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		String userName = request.getParameter("username");
//		CmsRegistUser user = cmsRegistMng.findUser(userName);
		Md5PwdEncoder md5 = new Md5PwdEncoder();
		String md5Password = md5.encodePassword(request.getParameter("password"));
		String userPassword = cmsRegistMng.findByUserName(userName);
		if(userPassword != null){
			if(md5Password.equals(userPassword)){
				loginCookie(userName, request, response);
				return "memberIndex";
			}
		}
		return "memberLogin";
	}
	
	@RequestMapping("/meetingList.do")
	public String meetingList(Integer pageNo,
			HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		String username = CookieUtils.getCookie(request, "username").getValue();
		System.out.println(username);
		CmsRegistUser cmsRegistUser = cmsRegistMng.findUser(username);
		List<CmsRegistMeeting> list = cmsRegistMng.finMeetingId(cmsRegistUser.getId());
		
		Pagination pagination = cmsRegistMng.getMeeting( true,  cpn(pageNo), CookieUtils.getPageSize(request), list);
		model.addAttribute("pagination", pagination);
		return "memberRight";
	}
	
	@RequestMapping("/memberRightInfo.do")
	public String meetingInfo(Integer pageNo, HttpServletRequest request, HttpServletResponse reponse, ModelMap model){
		String id = request.getParameter("id");
		String username = CookieUtils.getCookie(request, "username").getValue();
		CmsRegistUser cmsRegistUser = cmsRegistMng.findUser(username);
		if(cmsRegistUser != null){
			CmsMemberMeeting meeting = cmsRegistMng.findMemberMeeting(id);
			CmsRegistMeeting registMeeting = cmsRegistMng.findRegistMeeting(cmsRegistUser.getId(), meeting.getId());
			model.addAttribute("meeting", meeting);
			model.addAttribute("registMeeting", registMeeting);
			return "memberRightInfo";
		}
		return meetingList(pageNo, request, model);
	}
	
	
	
	/*
	@RequestMapping("/uploadFile.do")
	public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
		// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String savePath = this.getServletContext().getRealPath(
				"/WEB-INF/upload");
		String savePath = request.getContextPath() +"/WEB-INFO/upload/"+UUID.randomUUID().toString();
		java.io.File file = new java.io.File(savePath);
		// 判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath + "目录不存在，需要创建");
			// 创建目录
			file.mkdir();
		}
		// 消息提示
		String message = "";
		try {
			// 使用Apache文件上传组件处理文件上传步骤：
			// 1、创建一个DiskFileItemFactory工厂
			org.apache.commons.fileupload.disk.DiskFileItemFactory factory = new org.apache.commons.fileupload.disk.DiskFileItemFactory();
			// 2、创建一个文件上传解析器
			org.apache.commons.fileupload.servlet.ServletFileUpload upload = new org.apache.commons.fileupload.servlet.ServletFileUpload(factory);
			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			// 3、判断提交上来的数据是否是上传表单的数据
			if (!org.apache.commons.fileupload.servlet.ServletFileUpload.isMultipartContent(request)) {
				// 按照传统方式获取数据
				return;
			}
			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<org.apache.commons.fileupload.FileItem> list = upload.parseRequest(request);
			for (org.apache.commons.fileupload.FileItem item : list) {
				// 如果fileitem中封装的是普通输入项的数据
				if (item.isFormField()) {
					String name = item.getFieldName();
					// 解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					// value = new String(value.getBytes("iso8859-1"),"UTF-8");
					System.out.println(name + "=" + value);
				} else {// 如果fileitem中封装的是上传文件
					// 得到上传的文件名称，
					String filename = item.getName();
					System.out.println(filename);
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
					// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = filename
							.substring(filename.lastIndexOf("\\") + 1);
					// 获取item中的上传文件的输入流
					java.io.InputStream in = item.getInputStream();
					// 创建一个文件输出流
					java.io.FileOutputStream out = new java.io.FileOutputStream(savePath + "\\"
							+ filename);
					// 创建一个缓冲区
					byte buffer[] = new byte[1024];
					// 判断输入流中的数据是否已经读完的标识
					int len = 0;
					// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
					while ((len = in.read(buffer)) > 0) {
						// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
						// + filename)当中
						out.write(buffer, 0, len);
					}
					// 关闭输入流
					in.close();
					// 关闭输出流
					out.close();
					// 删除处理文件上传时生成的临时文件
					item.delete();
					message = "文件上传成功！";
				}
			}
		} catch (Exception e) {
			message = "文件上传失败！";
			e.printStackTrace();

		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	*/
	private void loginCookie(String username,HttpServletRequest request,HttpServletResponse response){
		String domain = request.getServerName();
		if (domain.indexOf(".") > -1) {
			domain= domain.substring(domain.indexOf(".") + 1);
		}
		CookieUtils.addCookie(request, response,  "JSESSIONID",  session.getSessionId(request, response), null, domain,"/");
		try {
			//中文乱码
			CookieUtils.addCookie(request, response,   "username",  URLEncoder.encode(username,"utf-8"), null, domain,"/");
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		}
		CookieUtils.addCookie(request, response,  "sso_logout",  null,0,domain,"/");
	}
	
	@Autowired
	private UnifiedUserMng unifiedUserMng;
	@Autowired
	private ConfigMng configMng;
	@Autowired
	protected CmsUserMng manager;
	
	
}
