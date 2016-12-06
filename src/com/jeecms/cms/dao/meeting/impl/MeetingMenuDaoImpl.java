package com.jeecms.cms.dao.meeting.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.meeting.MeetingMenuDao;
import com.jeecms.cms.entity.meeting.MeetingMenu;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class MeetingMenuDaoImpl extends HibernateBaseDao<MeetingMenu, Integer>
		implements MeetingMenuDao {
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from MeetingMenu bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<MeetingMenu> getList(String name) {
		Finder f = Finder.create("select bean from MeetingMenu bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	public MeetingMenu findById(Integer id) {
		MeetingMenu entity = get(id);
		return entity;
	}

	public MeetingMenu save(MeetingMenu bean) {
		getSession().save(bean);
		return bean;
	}

	public MeetingMenu deleteById(Integer id) {
		MeetingMenu entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<MeetingMenu> getEntityClass() {
		return MeetingMenu.class;
	}
}