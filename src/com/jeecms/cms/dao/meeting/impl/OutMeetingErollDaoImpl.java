package com.jeecms.cms.dao.meeting.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.meeting.OutMeetingErollDao;
import com.jeecms.cms.entity.meeting.OutMeetingEroll;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class OutMeetingErollDaoImpl extends HibernateBaseDao<OutMeetingEroll, Integer>
		implements OutMeetingErollDao {
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from OutMeetingEroll bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<OutMeetingEroll> getList(String name) {
		Finder f = Finder.create("select bean from OutMeetingEroll bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	public OutMeetingEroll findById(Integer id) {
		OutMeetingEroll entity = get(id);
		return entity;
	}

	public OutMeetingEroll save(OutMeetingEroll bean) {
		getSession().save(bean);
		return bean;
	}

	public OutMeetingEroll deleteById(Integer id) {
		OutMeetingEroll entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<OutMeetingEroll> getEntityClass() {
		return OutMeetingEroll.class;
	}
}