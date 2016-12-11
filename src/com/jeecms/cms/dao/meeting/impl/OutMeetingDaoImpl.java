package com.jeecms.cms.dao.meeting.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.meeting.OutMeetingDao;
import com.jeecms.cms.entity.meeting.OutMeeting;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class OutMeetingDaoImpl extends HibernateBaseDao<OutMeeting, Integer>
		implements OutMeetingDao {
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from OutMeeting bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<OutMeeting> getList(String name) {
		Finder f = Finder.create("select bean from OutMeeting bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	public OutMeeting findById(Integer id) {
		OutMeeting entity = get(id);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public OutMeeting getMaxMeetingId(Integer isCn) {
		Finder f = Finder.create("select o from OutMeeting o where o.id = ");
		if(isCn == null) {
			f.append("(select max(bean.id) from OutMeeting bean where 1=1 and bean.isDelete = 0 and bean.isCn=0)");
		}else {
			f.append("(select max(bean.id) from OutMeeting bean where 1=1 and bean.isDelete = 0 and bean.isCn=1)");
		}
		List<OutMeeting> meetingList = find(f);
		OutMeeting meeting = null;
		if(meetingList != null && meetingList.size()>0) {
			meeting = meetingList.get(0);
		}
		return meeting;
	}

	public OutMeeting save(OutMeeting bean) {
		getSession().save(bean);
		return bean;
	}

	public OutMeeting deleteById(Integer id) {
		OutMeeting entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<OutMeeting> getEntityClass() {
		return OutMeeting.class;
	}
}