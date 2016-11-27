package com.jeecms.cms.action.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jeecms.core.entity.CmsRegistMeeting;
import com.jeecms.core.manager.CmsRegistMng;
import com.jeecms.core.manager.impl.CmsRegistMngImpl;

public class ServletDownload extends HttpServlet {

	 private static final long serialVersionUID = 1L;  
     
	 
	 @Autowired
		protected CmsRegistMng cmsRegistMng;
		
		public void init(ServletConfig config) throws ServletException {
			   SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
			         config.getServletContext());
			   super.init(config);
			}
		
	    /** 
	     * @see HttpServlet#HttpServlet() 
	     */  
	    public ServletDownload() {  
	        super();  
	        // TODO Auto-generated constructor stub  
	    }  
	  
	    /** 
	     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
	     */  
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	        // TODO Auto-generated method stub  
	          
	        //获得请求文件名  
	        String registId = request.getParameter("registId");  
	        String meetingId = request.getParameter("meetingId");  
	        
	        CmsRegistMeeting registMeeting = cmsRegistMng.findRegistMeeting(Integer.valueOf(registId), Integer.valueOf(meetingId));
	        String path = registMeeting.getPath();
	        File file = new File(path);
//          // 取得文件名。
          String fileName = file.getName();  // 文件的默认保存名
          fileName = new String(fileName.getBytes(),"iso-8859-1");
	        // 读到流中
	        InputStream inStream = new FileInputStream(path);// 文件的存放路径
	        // 设置输出的格式
	        response.reset();
	        response.setContentType("bin");
	        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
	        // 循环取出流中的数据
	        byte[] b = new byte[100];
	        int len;
	        try {
	            while ((len = inStream.read(b)) > 0)
	                response.getOutputStream().write(b, 0, len);
	            inStream.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
//	        // path是指欲下载的文件的路径。
//            File file = new File(path);
//            // 取得文件名。
//            String filename = file.getName();
//            // 取得文件的后缀名。
//            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
//
//            // 以流的形式下载文件。
//            InputStream fis = new BufferedInputStream(new FileInputStream(path));
//            byte[] buffer = new byte[fis.available()];
//            fis.read(buffer);
//            fis.close();
//            // 清空response
//            response.reset();
//            // 设置response的Header
//            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
//            response.addHeader("Content-Length", "" + file.length());
//            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//            response.setContentType("application/octet-stream");
//            toClient.write(buffer);
//            toClient.flush();
//            toClient.close();
	        
	        
	        
//	        int start = path.lastIndexOf("\\");
//	        String filename = path.substring(start+1);
	        
	        
	        
//	        //设置文件MIME类型  
//	        response.setContentType(getServletContext().getMimeType(filename));  
//	        //设置Content-Disposition  
//	        response.setHeader("Content-Disposition", "attachment:filename="+filename);  
//	        //读取目标文件，通过response将目标文件写到客户端  
//	        //获取目标文件的绝对路径  
////	        String fullFileName = getServletContext().getRealPath("/download/" + filename);  
//	        //System.out.println(fullFileName);  
//	        //读取文件  
////	        InputStream in = new FileInputStream(fullFileName);  
//	        InputStream in = new FileInputStream(path); 
//	        OutputStream out = response.getOutputStream();  
//	          
//	        //写文件  
//	        int b;  
//	        while((b=in.read())!= -1)  
//	        {  
//	            out.write(b);  
//	        }  
//	          
//	        in.close();  
//	        out.close();  
	    }  

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
