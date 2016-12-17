package com.jeecms.cms.dao.meeting.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.meeting.InMeetingDao;
import com.jeecms.cms.entity.meeting.InMeeting;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class InMeetingDaoImpl extends HibernateBaseDao<InMeeting, Integer>
		implements InMeetingDao {
	@Autowired
	private JdbcTemplate jdbc;
	public void execSql(String sql) {
		jdbc.execute(sql);
	}
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from InMeeting bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<InMeeting> getList(String name) {
		Finder f = Finder.create("select bean from InMeeting bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	public InMeeting findById(Integer id) {
		InMeeting entity = get(id);
		return entity;
	}

	public InMeeting save(InMeeting bean) {
		getSession().save(bean);
		return bean;
	}

	public InMeeting deleteById(Integer id) {
		InMeeting entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<InMeeting> getEntityClass() {
		return InMeeting.class;
	}
}