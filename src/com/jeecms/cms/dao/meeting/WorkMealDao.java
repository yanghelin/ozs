package com.jeecms.cms.dao.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.WorkMeal;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

/**
 * 订餐管理DAO接口
 */
public interface WorkMealDao{
	public Pagination getPage(String name, int pageNo, int pageSize);
	
	public List<WorkMeal> getList(String name);

	public WorkMeal findById(Integer id);

	public WorkMeal save(WorkMeal bean);

	public WorkMeal updateByUpdater(Updater<WorkMeal> updater);

	public WorkMeal deleteById(Integer id);
}