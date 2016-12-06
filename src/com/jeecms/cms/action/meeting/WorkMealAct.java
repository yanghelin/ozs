package com.jeecms.cms.action.meeting;

import static com.jeecms.common.page.SimplePage.cpn;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.entity.meeting.WorkMeal;
import com.jeecms.cms.manager.meeting.WorkMealMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;

/**
 * 所内会议Action
 */
@Controller
public class WorkMealAct {
	private static final Logger log = LoggerFactory.getLogger(WorkMealAct.class);

	@RequiresPermissions("work_meal:list")
	@RequestMapping("/work_meal/list.do")
	public String list(String deptName,Integer pageNo,HttpServletRequest request, ModelMap model) {
		Pagination pagination = workMealMng.getPage(deptName, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("deptName", deptName);
		return "meeting/in/workMealList";
	}
	
	@RequiresPermissions("work_meal:to_add")
	@RequestMapping("/work_meal/to_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		return "meeting/in/workMealAdd";
	}
	
	@RequiresPermissions("work_meal:save")
	@RequestMapping("/work_meal/save.do")
	public String save(WorkMeal bean,HttpServletRequest request,ModelMap model) {
		/*CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}*/
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setCreateBy(currUser);
		bean.setCreateTime(new Date());
		bean.setIsDelete((byte)0);
		workMealMng.saveWorkMeal(bean);
		log.info("save InMeeting id={}", bean.getId());
		//cmsLogMng.operating(request, "cmsUser.log.save", "id=" + bean.getId() + ";username=" + bean.getUsername());
		return "redirect:list.do";
	}
	
	@RequiresPermissions("work_meal:delete")
	@RequestMapping("/work_meal/delete.do")
	public String delete(WorkMeal bean,HttpServletRequest request,ModelMap model) {
		CmsUser currUser = CmsUtils.getUser(request);
		bean.setIsDelete((byte)1);
		bean.setUpdateBy(currUser);
		bean.setUpdateTime(new Date());
		workMealMng.updateWorkMeal(bean);
		return "redirect:list.do";
	}

	@Autowired
	private WorkMealMng workMealMng;
}
