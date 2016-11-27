package com.jeecms.cms.manager.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.WorkMeal;
import com.jeecms.common.page.Pagination;

public interface WorkMealMng {
	public Pagination getPage(String meetingName, int pageNo, int pageSize);
	
	public List<WorkMeal> getList(String name);

	public WorkMeal findById(Integer id);

	public WorkMeal saveWorkMeal(WorkMeal WorkMeal);

	public void updateWorkMeal(WorkMeal bean);

	public WorkMeal deleteById(Integer id);

	public WorkMeal[] deleteByIds(Integer[] ids);

}