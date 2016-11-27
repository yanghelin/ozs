package com.jeecms.cms.dao.meeting.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.meeting.InMeetingBriefingDao;
import com.jeecms.cms.entity.meeting.InMeetingBriefing;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class InMeetingBriefingDaoImpl extends HibernateBaseDao<InMeetingBriefing, Integer>
		implements InMeetingBriefingDao {
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from InMeetingBriefing bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<InMeetingBriefing> getList(String name) {
		Finder f = Finder.create("select bean from InMeetingBriefing bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	public InMeetingBriefing findById(Integer id) {
		InMeetingBriefing entity = get(id);
		return entity;
	}

	public InMeetingBriefing save(InMeetingBriefing bean) {
		getSession().save(bean);
		return bean;
	}

	public InMeetingBriefing deleteById(Integer id) {
		InMeetingBriefing entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<InMeetingBriefing> getEntityClass() {
		return InMeetingBriefing.class;
	}
}