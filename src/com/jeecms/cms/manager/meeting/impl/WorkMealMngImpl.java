package com.jeecms.cms.manager.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.meeting.WorkMealDao;
import com.jeecms.cms.entity.meeting.WorkMeal;
import com.jeecms.cms.manager.meeting.WorkMealMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class WorkMealMngImpl implements WorkMealMng {
	@Transactional(readOnly = true)
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Pagination page = workMealDao.getPage(name, pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<WorkMeal> getList(String name) {
		List<WorkMeal> list = workMealDao.getList(name);
		return list;
	}

	@Transactional(readOnly = true)
	public WorkMeal findById(Integer id) {
		WorkMeal entity = workMealDao.findById(id);
		return entity;
	}

	public WorkMeal addWorkMeal(WorkMeal bean){
		return bean;
	}

	
	public void updateWorkMeal(WorkMeal bean){
		Updater<WorkMeal> updater = new Updater<WorkMeal>(bean);
		workMealDao.updateByUpdater(updater);
	}

	public WorkMeal saveWorkMeal(WorkMeal bean) {
		return workMealDao.save(bean);
	}

	public WorkMeal deleteById(Integer id) {
		return workMealDao.deleteById(id);
	}

	public WorkMeal[] deleteByIds(Integer[] ids) {
		WorkMeal[] beans = new WorkMeal[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private WorkMealDao workMealDao;

}