package com.jeecms.cms.dao.meeting.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.meeting.InMeetingItemsDao;
import com.jeecms.cms.entity.meeting.InMeetingItems;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class InMeetingItemsDaoImpl extends HibernateBaseDao<InMeetingItems, Integer>
		implements InMeetingItemsDao {
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from InMeetingItems bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.meetingId.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<InMeetingItems> getList(String name) {
		Finder f = Finder.create("select bean from InMeetingItems bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	public InMeetingItems findById(Integer id) {
		InMeetingItems entity = get(id);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<InMeetingItems> findByMeetingId(Integer meetingId) {
		Finder f = Finder.create("select bean from InMeetingItems bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (meetingId != null) {
			f.append(" and bean.meetingId.id =:mid");
			f.setParam("mid", meetingId);
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	public InMeetingItems save(InMeetingItems bean) {
		getSession().save(bean);
		return bean;
	}

	public InMeetingItems deleteById(Integer id) {
		InMeetingItems entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<InMeetingItems> getEntityClass() {
		return InMeetingItems.class;
	}
}