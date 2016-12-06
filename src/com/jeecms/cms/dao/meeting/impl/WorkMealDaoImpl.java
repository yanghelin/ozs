package com.jeecms.cms.dao.meeting.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.meeting.WorkMealDao;
import com.jeecms.cms.entity.meeting.WorkMeal;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class WorkMealDaoImpl extends HibernateBaseDao<WorkMeal, Integer>
		implements WorkMealDao {
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from WorkMeal bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.deptName like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<WorkMeal> getList(String name) {
		Finder f = Finder.create("select bean from WorkMeal bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	public WorkMeal findById(Integer id) {
		WorkMeal entity = get(id);
		return entity;
	}

	public WorkMeal save(WorkMeal bean) {
		getSession().save(bean);
		return bean;
	}

	public WorkMeal deleteById(Integer id) {
		WorkMeal entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<WorkMeal> getEntityClass() {
		return WorkMeal.class;
	}
}